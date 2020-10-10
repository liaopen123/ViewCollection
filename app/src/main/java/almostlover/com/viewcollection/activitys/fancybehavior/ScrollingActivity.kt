package almostlover.com.viewcollection.activitys.fancybehavior

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.utils.RvUtils
import almostlover.com.viewcollection.utils.lphLog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        lphLog("ScrollingActivityScrollingActivityScrollingActivityScrollingActivityScrollingActivity")
         RvUtils.loadData(rv,this)

    }
}
