package almostlover.com.viewcollection.activitys.scroller.activity

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.activitys.scroller.view.CustomScrollView
import almostlover.com.viewcollection.utils.lphLog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_scroll_history.*

class ScrollHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_history)
        innersv.setOnScrollChangeListener(object : CustomScrollView.OnScrollChangeListener{
            override fun middlePositon() {
                lphLog("在中间")
                outtersv.setIsMiddle()
            }

            override fun onScrollToEnd() {
                lphLog("在END")
                outtersv.setIsBottom()
            }

            override fun onScrollToStart() {
                lphLog("在顶部")
                outtersv.setIsTop()
            }

        })
    }
}
