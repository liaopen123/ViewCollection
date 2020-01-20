package almostlover.com.viewcollection.activitys.screenrecorder.mediarecorder

import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.MediaRecorder
import android.media.projection.MediaProjection
import java.lang.Exception

class MediaRecordThread : Thread {

    private var createVirtualDisplay: VirtualDisplay? = null
    private var mDpi: Int
    private var mBitrate: Int
    private var mHeight: Int
    private var mWidth: Int
    private lateinit var mMediaProjection: MediaProjection
    private lateinit var mDstPath: String
    private  var mMediaRecorder: MediaRecorder? = null

    private val FRAME_RATE = 60 // 60 fps

    constructor(
        width: Int,
        height: Int,
        bitrate: Int,
        dpi: Int,
        mediaProjection: MediaProjection,
        dstPath: String
    ) {
        mWidth = width
        mHeight = height
        mBitrate = bitrate
        mDpi = dpi
        mMediaProjection = mediaProjection
        mDstPath = dstPath
    }


    override fun run() {
        super.run()
        try {
            initMediaRecorder()
            createVirtualDisplay = mMediaProjection.createVirtualDisplay(
                "LPHDisplay",
                mWidth,
                mHeight,
                mDpi,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_PUBLIC,
                mMediaRecorder?.surface,
                null,
                null
            )
        } catch (e: Exception) {

        } finally {

        }
    }


    private fun initMediaRecorder() {
        mMediaRecorder = MediaRecorder()
        mMediaRecorder?.apply {
            setVideoSource(MediaRecorder.VideoSource.SURFACE)
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setOutputFile(mDstPath)
            setVideoSize(mWidth, mHeight)
            setVideoFrameRate(FRAME_RATE)
            setVideoEncodingBitRate(mBitrate)
            setVideoEncoder(MediaRecorder.VideoEncoder.H264)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        }


        try {
            mMediaRecorder?.prepare()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun release() {
        createVirtualDisplay?.apply {
            release()
        }

        mMediaRecorder?.apply {
            stop()
            reset()
            release()
        }

        mMediaProjection.apply {
            stop()
        }

    }


}