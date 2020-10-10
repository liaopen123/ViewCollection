package almostlover.com.viewcollection.activitys.nestedscrolling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.utils.RvUtils
import kotlinx.android.synthetic.main.activity_nested_scrolling01.*

class NestedScrolling01Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested_scrolling01)
        RvUtils.loadData(rv,this)
    }
}
