package almostlover.com.viewcollection.activitys.recyelerviewpagerecycler

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test.HomeElectricalTabAdpter
import almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test.HomeTopBannerAdpter
import almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test.HomeViewPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import kotlinx.android.synthetic.main.activity_recyecler_view_pager_recycler_view.*

/**
 * recyclerview  嵌套 ViewPager  再在里面嵌套 recyclerview
 */
class RecyeclerViewPagerRecyclerViewActivity : AppCompatActivity(),MainAdapter.PagerChangeListener {
    var TAG = "RecyeclerViewPagerRecyclerViewActivity"
    override fun pagerChange(position: Int) {
        currentFragment = fragments[position] as PagerFragment
    }

    private lateinit var homeTabAdapter: HomeElectricalTabAdpter
    private lateinit var delegateAdapter: DelegateAdapter
    private lateinit var virtualLayoutManager: VirtualLayoutManager
    private lateinit var currentFragment: PagerFragment
    private lateinit var mainAdapter: HomeTopBannerAdpter
    var data = ArrayList<String>()
    var  fragments = mutableListOf<PagerFragment>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyecler_view_pager_recycler_view)
        data.add("推荐")
        data.add("男装")
        data.add("女装")
        data.add("童装")
        data.add("鞋子")


         virtualLayoutManager = VirtualLayoutManager(this)
         delegateAdapter = DelegateAdapter(virtualLayoutManager)
        rv.layoutManager = virtualLayoutManager
        rv.adapter = delegateAdapter
        rv.isNestedScrollingEnabled = true

        for (i in data.indices) {
            fragments.add(PagerFragment.newInstance(data[i]))
        }

        //屏幕高度
        val dm = applicationContext.resources.displayMetrics
        for (i in data.indices) {
            fragments.add(PagerFragment.newInstance(data[i]))
        }
        mainAdapter = HomeTopBannerAdpter(this)
        homeTabAdapter = HomeElectricalTabAdpter(this)
        val homeViewPagerAdapter = HomeViewPagerAdapter(this)
        delegateAdapter.addAdapter(mainAdapter)
        delegateAdapter.addAdapter(homeTabAdapter)
        delegateAdapter.addAdapter(homeViewPagerAdapter)
        homeViewPagerAdapter.notifyHasData()
        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                Log.e(TAG,"isSticky:"+homeTabAdapter.isSticky())
                if(homeTabAdapter.isSticky()){
                    rv.isHeadSteak()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }
}
