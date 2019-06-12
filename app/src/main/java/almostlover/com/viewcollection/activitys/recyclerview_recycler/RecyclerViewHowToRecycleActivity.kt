package almostlover.com.viewcollection.activitys.recyclerview_recycler

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.adapter.RecyclerViewRecycleAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_recycler_view_how_to_recycle.*

class RecyclerViewHowToRecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_how_to_recycle)
        rv.layoutManager = GridLayoutManager(this, 5)
        rv.adapter = RecyclerViewRecycleAdapter(this)


    }
}
