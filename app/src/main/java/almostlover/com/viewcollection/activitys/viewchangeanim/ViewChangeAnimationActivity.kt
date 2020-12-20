package almostlover.com.viewcollection.activitys.viewchangeanim

import almostlover.com.viewcollection.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_view_change_animation.*

class ViewChangeAnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_change_animation)
        tv_1.setOnClickListener {
            container.removeView(it)
        }
        tv_2.setOnClickListener {
            container.removeView(it)
        }
        tv_3.setOnClickListener {
            container.removeView(it)
        }
        tv_4.setOnClickListener {
            container.removeView(it)
        }
    }
}