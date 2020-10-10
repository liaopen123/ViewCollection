package almostlover.com.viewcollection.activitys.jetpack_component.lifecycle

import almostlover.com.viewcollection.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner

class LifecycleActivity : AppCompatActivity(),LifecycleOwner {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)

        lifecycle.addObserver(MyObserver())
    }
}