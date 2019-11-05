package almostlover.com.viewcollection.activitys.screenrecorder

import android.Manifest
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.IBinder
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Button

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.utils.lphLog
import android.content.Context
import android.util.Log
import android.widget.Toast
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.support.v4.app.SupportActivity
import android.support.v4.app.SupportActivity.ExtraData
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.media.ImageReader
import android.media.MediaCodec
import android.media.MediaCodecInfo
import android.media.MediaFormat
import android.view.Surface
import kotlinx.android.synthetic.main.activity_screen_recorder.*
import org.jetbrains.anko.displayMetrics


class ScreenRecorderActivity : AppCompatActivity() {

    companion object {
        private val RECORD_REQUEST_CODE = 101
        private val STORAGE_REQUEST_CODE = 102
        private val AUDIO_REQUEST_CODE = 103
    }

    private var surface1: Surface? = null
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
        surface1 = surface.holder.surface
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
        createVirtualDisplay()
//        simulateHome()
        lphLog("已经开始屏幕录制")


    }

    private fun createVirtualDisplay() {
        mediaProjection?.createVirtualDisplay(
            "ScreenSharingDemo",
            mScreenWidth,
            mScreenHeight,
            mScreenDensity,
            DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
            surface1,
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


    private fun VideoHardCode() {
        var mBitRate :Int = 2000000
        val videoFormat =
            MediaFormat.createVideoFormat("video/avc", mScreenWidth, mScreenHeight)
        videoFormat.apply {
            setInteger(
                MediaFormat.KEY_COLOR_FORMAT,
                MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface
            )
            setInteger(MediaFormat.KEY_BIT_RATE, mBitRate)
            setInteger(MediaFormat.KEY_FRAME_RATE,30)
            setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 1)//关键帧率
        }

        val mEncoder = MediaCodec.createEncoderByType("video/avc")
        mEncoder.configure(videoFormat,null,null,MediaCodec.CONFIGURE_FLAG_ENCODE)
        val mSurface = mEncoder.createInputSurface()
        mEncoder.start()

    }
}