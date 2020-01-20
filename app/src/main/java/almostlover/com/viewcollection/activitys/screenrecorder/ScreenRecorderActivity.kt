package almostlover.com.viewcollection.activitys.screenrecorder

import android.content.Intent
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Button

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.activitys.screenrecorder.mediacodec.RecordService
import almostlover.com.viewcollection.utils.lphLog
import android.widget.Toast
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.*
import android.os.Environment
import kotlinx.android.synthetic.main.activity_screen_recorder.*
import org.jetbrains.anko.displayMetrics
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


class ScreenRecorderActivity : AppCompatActivity() {

    companion object {
        private val RECORD_REQUEST_CODE = 101
        private val STORAGE_REQUEST_CODE = 102
        private val AUDIO_REQUEST_CODE = 103
    }

    private var mVirtualDisplay: VirtualDisplay? = null
    private var mMediaRecorder: MediaRecorder? = null
    private var service: Intent? = null
    private val isAudio: Boolean = true //是否开启音频录制
    private val isVideoSd: Boolean = true //是否为标清视频
    private var isStarted: Boolean = false //是否开启视频录制
    private var mScreenDensity: Int = 0
    private var mScreenHeight: Int = 0
    private var mScreenWidth: Int = 0
    private var mediaProjection: MediaProjection? = null
    private lateinit var mediaProjectionManager: MediaProjectionManager
    private val projectionManager: MediaProjectionManager? = null
    private val recordService: RecordService? = null

    private val startBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_screen_recorder)
        getScreenBaseInfo()
        mediaProjectionManager =
            getSystemService(MEDIA_PROJECTION_SERVICE) as MediaProjectionManager


        start_record.setOnClickListener {
            if (isStarted) {
                stopScreenRecording()
            } else {
                val createScreenCaptureIntent = mediaProjectionManager.createScreenCaptureIntent()
                startActivityForResult(createScreenCaptureIntent, RECORD_REQUEST_CODE)
                start_record.text = "停止"
            }
        }

        stop_record.setOnClickListener {

            mMediaRecorder?.apply {
                stop()
                reset()
            }
            mVirtualDisplay?.release()
            mediaProjection?.stop()


        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode != RECORD_REQUEST_CODE) {
            lphLog("Unknown request code: $requestCode")
            return
        }
        if (resultCode != RESULT_OK) {
            Toast.makeText(
                this,
                "User denied screen sharing permission", Toast.LENGTH_SHORT
            ).show()
            return
        }

        mediaProjection = projectionManager?.getMediaProjection(resultCode, data)
        mediaProjection?.registerCallback(object : MediaProjection.Callback() {
            override fun onStop() {
                super.onStop()
                lphLog("停止了")
            }
        }, null)
        setMediaRecorder()
        createVirtualDisplay()
        mMediaRecorder?.start()
//        simulateHome()
        lphLog("已经开始屏幕录制")


    }

    private fun createVirtualDisplay() {
         mVirtualDisplay = mediaProjection?.createVirtualDisplay(
            "ScreenSharingDemo",
            mScreenWidth,
            mScreenHeight,
            mScreenDensity,
            DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
            mMediaRecorder?.surface,
            null,
            null
        )
    }

    private fun statusIsStarted() {

    }

    private fun simulateHome() {

        var intent = Intent(Intent.ACTION_MAIN).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            addCategory(Intent.CATEGORY_HOME)
        }
        startActivity(intent)
    }


    private fun stopScreenRecording() {
        if (service != null) {
            stopService(service)
            isStarted = false
        }
    }


    private fun getScreenBaseInfo() {
        var metrics: DisplayMetrics = this.displayMetrics
        windowManager.defaultDisplay.getMetrics(metrics)
        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;
        mScreenDensity = metrics.densityDpi;
    }

//
//    private fun VideoHardCode() {
//        var mBitRate :Int = 2000000
//        val videoFormat =
//            MediaFormat.createVideoFormat("video/avc", mScreenWidth, mScreenHeight)
//        videoFormat.apply {
//            setInteger(
//                MediaFormat.KEY_COLOR_FORMAT,
//                MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface
//            )
//            setInteger(MediaFormat.KEY_BIT_RATE, mBitRate)
//            setInteger(MediaFormat.KEY_FRAME_RATE,30)
//            setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 1)//关键帧率
//        }
//
//        val mEncoder = MediaCodec.createEncoderByType("video/avc")
//        mEncoder.configure(videoFormat,null,null,MediaCodec.CONFIGURE_FLAG_ENCODE)
//        val mSurface = mEncoder.createInputSurface()
//        mEncoder.start()
//
//    }



    private  fun  setMediaRecorder(){
        val dir = getSavingDir()
        if (!dir.exists() && !dir.mkdirs()) {
            lphLog("Permission Denied ")
            return
        }
        val format = SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US)
        val file = File(
            dir, "Screenshots-" + format.format(Date())
                    + "-lphTest.mp4"
        )

        if (mMediaRecorder==null) {

            mMediaRecorder =  MediaRecorder()
        }
        mMediaRecorder?.apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setVideoSource(MediaRecorder.VideoSource.SURFACE)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setOutputFile( file.absolutePath )
            setVideoSize(mScreenWidth, mScreenHeight)
            setVideoEncoder(MediaRecorder.VideoEncoder.H264)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setVideoEncodingBitRate(((mScreenWidth * mScreenHeight * 3.6).toInt()))
            setVideoFrameRate(20)
            try {
               prepare()
            }catch (e:Exception){

            }

        }


    }


    private fun getSavingDir(): File {
        return File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES),
            "Screenshots"
        )
    }
}