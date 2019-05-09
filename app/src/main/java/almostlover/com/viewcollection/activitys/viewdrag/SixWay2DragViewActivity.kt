package almostlover.com.viewcollection.activitys.viewdrag

import almostlover.com.viewcollection.R
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_onelayout.*

class SixWay2DragViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onelayout)
        btn.setOnClickListener {
            parent_container.requestLayout()
        }

        btn_next.setOnClickListener {
            startActivity(Intent(this, ViewDragHelperActivity::class.java))
        }
    }
}
