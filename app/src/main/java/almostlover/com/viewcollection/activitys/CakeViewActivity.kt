package almostlover.com.viewcollection.activitys

import almostlover.com.viewcollection.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cake_view.*

class CakeViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cake_view)
        btn_start.setOnClickListener { cakeView.start() }
        btn_stop.setOnClickListener { cakeView.stop() }

    }
}
