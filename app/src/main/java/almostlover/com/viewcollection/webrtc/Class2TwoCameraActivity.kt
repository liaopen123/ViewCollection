package almostlover.com.viewcollection.webrtc

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.webrtc.adapter.PeerConnectionAdapter
import almostlover.com.viewcollection.webrtc.adapter.SdpAdapter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_class2_two_camera.*
import org.webrtc.*
import org.webrtc.PeerConnection.IceServer
import org.webrtc.PeerConnectionFactory
import org.webrtc.SurfaceTextureHelper


class Class2TwoCameraActivity : AppCompatActivity() {
   lateinit var peerConnectionFactory: PeerConnectionFactory
    lateinit var peerConnectionLocal: PeerConnection
    lateinit var peerConnectionRemote: PeerConnection
    lateinit var mediaStreamLocal: MediaStream
    lateinit var mediaStreamRemote: MediaStream
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class2_two_camera)
        val eglBaseContext = EglBase.create().eglBaseContext
        // create PeerConnectionFactory
        PeerConnectionFactory.initialize(
            PeerConnectionFactory.InitializationOptions
                .builder(this)
                .createInitializationOptions()
        )
        val options = PeerConnectionFactory.Options()
        val defaultVideoEncoderFactory =
            DefaultVideoEncoderFactory(eglBaseContext, true, true)
        val defaultVideoDecoderFactory =
            DefaultVideoDecoderFactory(eglBaseContext)
        peerConnectionFactory = PeerConnectionFactory.builder()
            .setOptions(options)
            .setVideoEncoderFactory(defaultVideoEncoderFactory)
            .setVideoDecoderFactory(defaultVideoDecoderFactory)
            .createPeerConnectionFactory()
        val surfaceTextureHelper =
            SurfaceTextureHelper.create("CaptureThread", eglBaseContext)
        // create VideoCapturer
        val videoCapturer = createCameraCapturer(true)
        val videoSource =
            peerConnectionFactory.createVideoSource(videoCapturer!!.isScreencast)
        videoCapturer.initialize(
            surfaceTextureHelper,
            applicationContext,
            videoSource.capturerObserver
        )
        videoCapturer.startCapture(480, 640, 30)
        localView!!.setMirror(true)
        localView.init(eglBaseContext, null)
        // create VideoTrack
        val videoTrack = peerConnectionFactory.createVideoTrack("100", videoSource)
        //        // display in localView
//        videoTrack.addSink(localView);
        val remoteSurfaceTextureHelper =
            SurfaceTextureHelper.create("RemoteCaptureThread", eglBaseContext)
        // create VideoCapturer
        val remoteVideoCapturer = createCameraCapturer(false)
        val remoteVideoSource =
            peerConnectionFactory.createVideoSource(remoteVideoCapturer!!.isScreencast)
        remoteVideoCapturer.initialize(
            remoteSurfaceTextureHelper,
            applicationContext,
            remoteVideoSource.capturerObserver
        )
        remoteVideoCapturer.startCapture(480, 640, 30)
        remoteView.setMirror(false)
        remoteView.init(eglBaseContext, null)
        // create VideoTrack
        val remoteVideoTrack =
            peerConnectionFactory.createVideoTrack("102", remoteVideoSource)
        //        // display in remoteView
//        remoteVideoTrack.addSink(remoteView);
        mediaStreamLocal = peerConnectionFactory.createLocalMediaStream("mediaStreamLocal")
        mediaStreamLocal.addTrack(videoTrack)
        mediaStreamRemote = peerConnectionFactory.createLocalMediaStream("mediaStreamRemote")
        mediaStreamRemote.addTrack(remoteVideoTrack)
        call(mediaStreamLocal, mediaStreamRemote)
    }

    private fun call(localMediaStream: MediaStream?, remoteMediaStream: MediaStream?) {
        val iceServers: List<IceServer> = ArrayList()
        peerConnectionLocal = peerConnectionFactory!!.createPeerConnection(
            iceServers,
            object : PeerConnectionAdapter("localconnection") {
                override fun onIceCandidate(iceCandidate: IceCandidate) {
                    super.onIceCandidate(iceCandidate)
                    peerConnectionRemote!!.addIceCandidate(iceCandidate)
                }

                override fun onAddStream(mediaStream: MediaStream) {
                    super.onAddStream(mediaStream)
                    val remoteVideoTrack = mediaStream.videoTracks[0]
                    runOnUiThread { remoteVideoTrack.addSink(localView) }
                }
            })!!
        peerConnectionRemote = peerConnectionFactory!!.createPeerConnection(
            iceServers,
            object : PeerConnectionAdapter("remoteconnection") {
                override fun onIceCandidate(iceCandidate: IceCandidate) {
                    super.onIceCandidate(iceCandidate)
                    peerConnectionLocal!!.addIceCandidate(iceCandidate)
                }

                override fun onAddStream(mediaStream: MediaStream) {
                    super.onAddStream(mediaStream)
                    val localVideoTrack = mediaStream.videoTracks[0]
                    runOnUiThread { localVideoTrack.addSink(remoteView) }
                }
            })!!
        peerConnectionLocal!!.addStream(localMediaStream)
        peerConnectionLocal!!.createOffer(object : SdpAdapter("local offer sdp") {
            override fun onCreateSuccess(sessionDescription: SessionDescription) {
                super.onCreateSuccess(sessionDescription)
                // todo crashed here
                peerConnectionLocal!!.setLocalDescription(
                    SdpAdapter("local set local"),
                    sessionDescription
                )
                peerConnectionRemote!!.addStream(remoteMediaStream)
                peerConnectionRemote!!.setRemoteDescription(
                    SdpAdapter("remote set remote"),
                    sessionDescription
                )
                peerConnectionRemote!!.createAnswer(object : SdpAdapter("remote answer sdp") {
                    override fun onCreateSuccess(sdp: SessionDescription) {
                        super.onCreateSuccess(sdp)
                        peerConnectionRemote!!.setLocalDescription(
                            SdpAdapter("remote set local"),
                            sdp
                        )
                        peerConnectionLocal!!.setRemoteDescription(
                            SdpAdapter("local set remote"),
                            sdp
                        )
                    }
                }, MediaConstraints())
            }
        }, MediaConstraints())
    }

    private fun createCameraCapturer(isFront: Boolean): VideoCapturer? {
        val enumerator = Camera1Enumerator(false)
        val deviceNames = enumerator.deviceNames
        // First, try to find front facing camera
        for (deviceName in deviceNames) {
            if (if (isFront) enumerator.isFrontFacing(deviceName) else enumerator.isBackFacing(
                    deviceName
                )
            ) {
                val videoCapturer: VideoCapturer? = enumerator.createCapturer(deviceName, null)
                if (videoCapturer != null) {
                    return videoCapturer
                }
            }
        }
        return null
    }
}