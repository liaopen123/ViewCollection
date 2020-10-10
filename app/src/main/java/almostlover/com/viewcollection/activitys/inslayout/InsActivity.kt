package almostlover.com.viewcollection.activitys.inslayout

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.utils.RvUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ins.*

class InsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ins)
        RvUtils.loadInsData(rv,this)
    }
}