package almostlover.com.viewcollection.webrtc

import almostlover.com.viewcollection.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_class1_camera.*
import org.webrtc.*

class Class1CameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class1_camera)

        /**
         * 主要步骤如下
        创建PeerConnectionFactory
        创建并启动VideoCapturer
        用PeerConnectionFactory创建VideoSource
        用PeerConnectionFactory和VideoSource创建VideoTrack
        初始化视频控件SurfaceViewRenderer
        将VideoTrack展示到SurfaceViewRenderer中
         */


        val createInitializationOptions =
            PeerConnectionFactory.InitializationOptions.builder(this).createInitializationOptions()
        PeerConnectionFactory.initialize(createInitializationOptions)
        //创建 peerConnectionFactory 需要配置
        val peerConnectionFactory =
            PeerConnectionFactory.builder().createPeerConnectionFactory()

        val audioSource = peerConnectionFactory.createAudioSource(MediaConstraints())
        //创建audio source
        peerConnectionFactory.createAudioTrack("101",audioSource)

        var eglBaseContext: EglBase.Context = EglBase.create().eglBaseContext


        val surfaceTextureHelper:SurfaceTextureHelper = SurfaceTextureHelper.create("CaptureThread", eglBaseContext)


        val cameraCapturer = createCameraCapturer()
        val videoSource =
            peerConnectionFactory.createVideoSource(cameraCapturer!!.isScreencast())

        cameraCapturer.initialize(surfaceTextureHelper,application,videoSource.capturerObserver)
        cameraCapturer.startCapture(1080,1920,30)

        surface_view_render.setMirror(true)
        surface_view_render.init(eglBaseContext,null)

        var videoTrack :VideoTrack = peerConnectionFactory.createVideoTrack("101",videoSource)
        videoTrack.addSink(surface_view_render)

    }



    fun createCameraCapturer(): VideoCapturer? {
        var cameraEnumerator = Camera1Enumerator(false)

        val deviceNames = cameraEnumerator.deviceNames

        for (deviceName in deviceNames) {
            val videoCapturer = cameraEnumerator.createCapturer(deviceName, null)

            if (videoCapturer!=null) {
                return  videoCapturer
            }
        }

        for (device in deviceNames) {
            if (!cameraEnumerator.isFrontFacing(device)) {
                val createCapturer = cameraEnumerator.createCapturer(device, null)
                if (createCapturer!=null){
                    return  createCapturer
                }
            }
        }

        return null
    }
}
