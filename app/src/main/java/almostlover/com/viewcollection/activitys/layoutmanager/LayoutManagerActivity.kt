package almostlover.com.viewcollection.activitys.layoutmanager

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.adapter.CommonAdapter
import almostlover.com.viewcollection.views.layoutmanager.douyinLayoutManager.TanTanLayoutManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_layout_manager.*

class LayoutManagerActivity : AppCompatActivity() {
    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_manager)
        rv.apply {
            layoutManager = CardLayoutManager()
            val arrayList = ArrayList<String>()
            for (i in 1..100) {
                arrayList.add("" + i)
            }
            rv.adapter = CommonAdapter(arrayList)

        }
    }
}