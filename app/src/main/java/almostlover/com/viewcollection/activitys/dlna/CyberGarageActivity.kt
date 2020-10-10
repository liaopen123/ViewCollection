package almostlover.com.viewcollection.activitys.dlna

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.utils.cybergarage.upnp.ControlPoint
import almostlover.com.viewcollection.utils.cybergarage.upnp.Device
import almostlover.com.viewcollection.utils.cybergarage.upnp.device.DeviceChangeListener
import almostlover.com.viewcollection.utils.lphLog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cyber_garage.*
import java.net.URL


class CyberGarageActivity : AppCompatActivity() {

    private lateinit var mControlPoint: ControlPoint
    var devicesList: ArrayList<Device> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cyber_garage)

        //初始化
        initControlPoint()
        addDeviceListener()
        addSearchResponseListener()
        addDevicesChangeListener()


        //发送动作
        /**
         * 发送动作之前 先要确认设备的实例 是否支持投屏播放
         * 其中 支持投屏播放的设备的devicesType的值为:urn:schemas-upnp-org:device:MediaRenderer:x
         */


        initClick()

    }


    private fun addSearchResponseListener() {
        mControlPoint.addSearchResponseListener {
            lphLog("发现一个新的设备 A new device was searched, remoteAddress is" + it.remoteAddress)
        }
    }


    private fun addDevicesChangeListener() {
        mControlPoint.addDeviceChangeListener(object : DeviceChangeListener {
            override fun deviceAdded(dev: Device?) {
                lphLog("一个设备新增了：Device was removed, device name: " + dev?.friendlyName)

                if ("urn:schemas-upnp-org:device:MediaRenderer:1".equals(dev?.deviceType)) {
                    lphLog("新增一个 支持投屏的设备")
                    dev?.let {

                        val locationUrl = it.getLocation()
// 获取服务
                        val service =
                            it.getService("urn:schemas-upnp-org:service:AVTransport:1")
                        val url = URL(locationUrl)
// SDD
                        val sddUrl =
                            lphLog("locationUrl的ip地址和端口号 + ${service.scpdurl}")
                        lphLog("locationUrl + $locationUrl")

                        devicesList.add(it)
                    }
                }
            }

            override fun deviceRemoved(dev: Device?) {
                lphLog("一个设备断开连接了：Device was added, device name:" + dev?.friendlyName)
                if ("urn:schemas-upnp-org:device:MediaRenderer:1".equals(dev?.deviceType)) {
                    lphLog("删除一个 支持投屏的设备")
                    dev?.let { devicesList.remove(it) }
                }
            }

        })
    }

    private fun playVideo() {
        //实例ID
        val instanceID = "0"
        //视频播放地址：
        val currentUrl =
            "http://220.112.193.197/mp4files/A18400000009E79A/vjs.zencdn.net/v/oceans.mp4"
        val device = devicesList[0]
        //获取服务: DLNA投屏播放的服务的serviceType值为：urn:schemas-upnp-org:service:AVTransport:x;
        val service = device.getService("urn:schemas-upnp-org:service:AVTransport:1")
        //获取动作:
        val transportAction = service.getAction("SetAVTransportURI")
        //设置参数
        transportAction.setArgumentValue("InstanceID", instanceID)
        transportAction.setArgumentValue("CurrentURI", currentUrl)

        //SetAVTransportURI
        if (transportAction.postControlAction()) {
            //发送成功
            val playAction = service.getAction("Play")
            playAction.setArgumentValue("InstanceID", instanceID)
            //play失败
            if (!playAction.postControlAction()) {
                lphLog("播放失败了:" + playAction.status.description)
            }

        } else {
            lphLog("投屏失败:" + transportAction.status.description)
        }
    }


    private fun initClick() {
        tv_search.setOnClickListener {
            searchDevice()
        }

        tv_device_des.setOnClickListener {
            getDeviceDescription()
        }
        tv_play.setOnClickListener {
            playVideo()
        }


    }

    private fun getDeviceDescription() {
        val device = devicesList[0]
        //设备描述文档
        val location = device.location
        val service = device.getService("urn:schemas-upnp-org:service:AVTransport:1")
        val url = URL(location)
        lphLog("locationUrl的ip地址和端口号 + ${service.scpdurl}")


    }

    private fun addDeviceListener() {
        mControlPoint.addNotifyListener {
            it.isRootDevice
            lphLog("得到设备了：Got Notification from device, remoteAddress is" + it.remoteAddress)
        }
    }

    private fun initControlPoint() {

        mControlPoint = ControlPoint()
        Thread(Runnable { mControlPoint.start() }).start()
    }

    private fun searchDevice() {
        Thread(object : Runnable {
            override fun run() {
                mControlPoint.start()
                mControlPoint.search()
            }

        }).start()
    }


}
