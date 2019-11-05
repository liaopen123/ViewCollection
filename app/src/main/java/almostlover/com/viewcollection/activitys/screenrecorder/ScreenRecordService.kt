package almostlover.com.viewcollection.activitys.screenrecorder

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.MediaRecorder
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.Environment
import android.os.IBinder
import android.util.Log
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class ScreenRecordService : Service() {

    private val TAG = "ScreenRecordingService"

    private var mScreenWidth: Int = 0
    private var mScreenHeight: Int = 0
    private var mScreenDensity: Int = 0
    private var mResultCode: Int = 0
    private var mResultData: Intent? = null
    /** 是否为标清视频  */
    private var isVideoSd: Boolean = false
    /** 是否开启音频录制  */
    private var isAudio: Boolean = false

    private var mMediaProjection: MediaProjection? = null
    private var mMediaRecorder: MediaRecorder? = null
    private var mVirtualDisplay: VirtualDisplay? = null


   override fun onCreate() {
        Log.i(TAG, "Service onCreate() is called")
    }



    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.i(TAG, "Service onStartCommand() is called")

        mResultCode = intent.getIntExtra("code", -1)
        mResultData = intent.getParcelableExtra("data")
        mScreenWidth = intent.getIntExtra("width", 720)
        mScreenHeight = intent.getIntExtra("height", 1280)
        mScreenDensity = intent.getIntExtra("density", 1)
        isVideoSd = intent.getBooleanExtra("quality", true)
        isAudio = intent.getBooleanExtra("audio", true)

        mMediaProjection = createMediaProjection()
        mMediaRecorder = createMediaRecorder()
        mVirtualDisplay =
            createVirtualDisplay() // 必须在mediaRecorder.prepare() 之后调用，否则报错"fail to get surface"
        mMediaRecorder!!.start()

        return START_NOT_STICKY
    }

    private fun createMediaProjection(): MediaProjection {
        Log.i(TAG, "Create MediaProjection")
         return   (getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager).getMediaProjection(
            mResultCode,
            mResultData!!
        )
    }

    private fun createMediaRecorder(): MediaRecorder {
        val formatter = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
        val curDate = Date(System.currentTimeMillis())
        val curTime = formatter.format(curDate).replace(" ", "")
        var videoQuality = "HD"
        if (isVideoSd) videoQuality = "SD"

        Log.i(TAG, "Create MediaRecorder")
        val mediaRecorder = MediaRecorder()
        if (isAudio) mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE)
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mediaRecorder.setOutputFile("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)}  \\/ $videoQuality$curTime.mp4")
        mediaRecorder.setVideoSize(
            mScreenWidth,
            mScreenHeight
        )  //after setVideoSource(), setOutFormat()
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264)  //after setOutputFormat()
        if (isAudio) mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)  //after setOutputFormat()
        val bitRate: Int
        if (isVideoSd) {
            mediaRecorder.setVideoEncodingBitRate(mScreenWidth * mScreenHeight)
            mediaRecorder.setVideoFrameRate(30)
            bitRate = mScreenWidth * mScreenHeight / 1000
        } else {
            mediaRecorder.setVideoEncodingBitRate(5 * mScreenWidth * mScreenHeight)
            mediaRecorder.setVideoFrameRate(60) //after setVideoSource(), setOutFormat()
            bitRate = 5 * mScreenWidth * mScreenHeight / 1000
        }
        try {
            mediaRecorder.prepare()
        } catch (e: IllegalStateException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        Log.i(
            TAG,
            "Audio: " + isAudio + ", SD video: " + isVideoSd + ", BitRate: " + bitRate + "kbps"
        )

        return mediaRecorder
    }

    private fun createVirtualDisplay(): VirtualDisplay {
        Log.i(TAG, "Create VirtualDisplay")
        return mMediaProjection!!.createVirtualDisplay(
            TAG, mScreenWidth, mScreenHeight, mScreenDensity,
            DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR, mMediaRecorder?.surface, null, null
        )
    }

  override  fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "Service onDestroy")
        if (mVirtualDisplay != null) {
            mVirtualDisplay!!.release()
            mVirtualDisplay = null
        }
        if (mMediaRecorder != null) {
            mMediaRecorder!!.setOnErrorListener(null)
            mMediaProjection!!.stop()
            mMediaRecorder!!.reset()
        }
        if (mMediaProjection != null) {
            mMediaProjection!!.stop()
            mMediaProjection = null
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

}