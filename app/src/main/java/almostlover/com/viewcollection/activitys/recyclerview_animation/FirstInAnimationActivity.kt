package almostlover.com.viewcollection.activitys.recyclerview_animation

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.activitys.recyclerview_animation.autoplay.AutoPlayTool
import almostlover.com.viewcollection.adapter.InsRVAdapter
import almostlover.com.viewcollection.utils.DemoUtils
import almostlover.com.viewcollection.views.SpacesItemDecoration
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.felipecsl.asymmetricgridview.AsymmetricRecyclerViewAdapter
import com.felipecsl.asymmetricgridview.Utils
import kotlinx.android.synthetic.main.activity_first_in_animation.*


class FirstInAnimationActivity : AppCompatActivity() {
    private val demoUtils: DemoUtils = DemoUtils()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_in_animation)

        val resId: Int = R.anim.layout_animation_fall_down
        val animation: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(this, resId)
        rv.layoutAnimation = animation
//        RvUtils.loadData(rv,this)


        val adapter =
            InsRVAdapter(
                demoUtils.moarItems(50)
            )
        rv.setRequestedColumnCount(3)
        rv.isDebugging = true
        rv.requestedHorizontalSpacing = Utils.dpToPx(
            this,
            3f
        )
        rv.addItemDecoration(
            SpacesItemDecoration(20)
        )
        rv.adapter = AsymmetricRecyclerViewAdapter<InsRVAdapter.ViewHolder1>(
            this,
            rv,
            adapter
        )

        rv.addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
            var autoPlayTool = AutoPlayTool(60, 1)
            override fun onScrollStateChanged(
                @NonNull recyclerView: androidx.recyclerview.widget.RecyclerView,
                newState: Int
            ) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = rv.layoutManager as androidx.recyclerview.widget.LinearLayoutManager
                    val firstItemPosition = layoutManager.findFirstVisibleItemPosition()
                    val lastItemPostion = layoutManager.findLastVisibleItemPosition()
                    Log.e("FirstInAnimation","firstItemPosition:$firstItemPosition,,lastItemPostion:$lastItemPostion")
                }
            }

            override fun onScrolled(
                @NonNull recyclerView: androidx.recyclerview.widget.RecyclerView,
                dx: Int,
                dy: Int
            ) {
                super.onScrolled(recyclerView, dx, dy)
                autoPlayTool.onScrolledAndDeactivate()
            }
        })
    }
}