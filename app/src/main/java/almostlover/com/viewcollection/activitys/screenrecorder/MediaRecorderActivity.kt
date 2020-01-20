package almostlover.com.viewcollection.activitys.screenrecorder

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.activitys.screenrecorder.mediarecorder.MediaRecordThread
import android.content.Context
import android.content.Intent
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.DisplayMetrics
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_media_recorder.*
import org.jetbrains.anko.displayMetrics
import java.io.File
import java.util.*

class MediaRecorderActivity : AppCompatActivity() {

    private lateinit var mediaRecordThread: MediaRecordThread
    private lateinit var mediaProjectionManager: MediaProjectionManager
    private lateinit var mediaProjection: MediaProjection
    private val SCREEN_RECORDER_RESULT_CODE = 1

    private var mScreenDensity: Int = 0
    private var mScreenHeight: Int = 0
    private var mScreenWidth: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_recorder)
        getScreenBaseInfo()


        tv_start.setOnClickListener {
            initProjectManager()
        }

        tv_stop.setOnClickListener {
            mediaRecordThread?.apply {
                release()
            }
        }
    }

    private fun initProjectManager() {
         mediaProjectionManager =
            getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager


        val intent = mediaProjectionManager.createScreenCaptureIntent()
        startActivityForResult(intent,SCREEN_RECORDER_RESULT_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode!=SCREEN_RECORDER_RESULT_CODE){
            Toast.makeText(this,"unknown result intent",Toast.LENGTH_LONG).show()
            return
        }
        if (resultCode==RESULT_OK) {
             mediaProjection = mediaProjectionManager.getMediaProjection(resultCode, data)
        }

        var file =   File(getSavingDir(),"111Screenshots-lphTest.mp4")
        mScreenWidth =720
        mScreenHeight = 1280
        mScreenDensity = 1
         mediaRecordThread = MediaRecordThread(mScreenWidth,mScreenHeight,6000000,mScreenDensity,mediaProjection,file.absolutePath)
        mediaRecordThread.start()



    }
    private fun getScreenBaseInfo() {
        var metrics: DisplayMetrics = this.displayMetrics
        windowManager.defaultDisplay.getMetrics(metrics)
        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;
        mScreenDensity = metrics.densityDpi;
    }

    private fun getSavingDir(): File {
        return File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES),
            "Screenshots"
        )
    }
}
