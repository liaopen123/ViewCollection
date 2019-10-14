package cn.com.almostlover.network.listener

import cn.com.almostlover.network.type.Type

interface NetworkObserver {

    //
    fun onDisConnect()

    fun onConnect(type:Type )
}

