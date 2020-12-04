package almostlover.com.viewcollection.activitys.persisitantrv

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.activitys.persisitantrv.adapter.PersisitantAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_persisitant_r_v.*

class PersistantRVActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_persisitant_r_v)
        rv.apply {
            layoutManager =LinearLayoutManager(this@PersistantRVActivity)
            adapter = PersisitantAdapter(this@PersistantRVActivity)

        }

    }
}