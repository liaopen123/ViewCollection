package almostlover.com.viewcollection.activitys.jetpack_component.lifecycle

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyLocationListener(context: Context,lifecycle:Lifecycle) :LifecycleObserver {
  var context=context
  var lifecycle=lifecycle
    var enable = false
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start(){
        if (enable){
            //connect
        }
    }

    fun  enable(){
        enable=true
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)){

        }
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        // disconnect if connected
    }

}