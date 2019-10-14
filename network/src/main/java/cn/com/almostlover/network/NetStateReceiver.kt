package cn.com.almostlover.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import cn.com.almostlover.network.listener.NetworkObserver

class NetStateReceiver : BroadcastReceiver(){


    var listener:NetworkObserver? = null



    override fun onReceive(context: Context?, intent: Intent?) {

    }

}