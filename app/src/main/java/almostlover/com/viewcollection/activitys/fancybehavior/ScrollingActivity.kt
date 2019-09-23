package almostlover.com.viewcollection.activitys.fancybehavior

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.adapter.MyAdapter
import almostlover.com.viewcollection.utils.RvUtils
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

         RvUtils.loadData(rv)

    }
}
