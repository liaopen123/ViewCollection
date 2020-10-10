package almostlover.com.viewcollection.activitys.jetpack_component.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MyObserver:LifecycleObserver,AnkoLogger{
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connectListener(){
        info { "注册生命周期" }
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun disconnectListener(){
        info { "取消生命周期" }
    }
}