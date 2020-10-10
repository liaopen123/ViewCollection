package almostlover.com.viewcollection.activitys.recyclerviewviewpagertest2

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.MainAdapter
import almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.PagerFragment
import almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test.HomeElectricalTabAdpter
import almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test.HomeTopBannerAdpter
import almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test.HomeViewPagerAdapter
import almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test.SimpleCardFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import kotlinx.android.synthetic.main.activity_recyecler_view_pager_test.*

/**
 * recyclerview  嵌套 ViewPager  再在里面嵌套 recyclerview
 */
class RecyeclerViewPagerRecyclerViewActivity : AppCompatActivity(), MainAdapter.PagerChangeListener {
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
    var fragments = mutableListOf<PagerFragment>()
    private val mFragments: ArrayList<androidx.fragment.app.Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyecler_view_pager_test)
        data.add("推荐1")
        data.add("男装1")
        data.add("女装1")
        data.add("童装1")
        data.add("鞋子1")

        val layoutParams = viewpager.layoutParams
        layoutParams.height = resources!!.displayMetrics!!.heightPixels
        mFragments.add(SimpleCardFragment.getInstance("gaga"))
        mFragments.add(SimpleCardFragment.getInstance("gaga"))
        mFragments.add(SimpleCardFragment.getInstance("gaga"))
        mFragments.add(SimpleCardFragment.getInstance("gaga"))
        var mAdapter = MyPagerAdapter(supportFragmentManager)
        viewpager.adapter = (mAdapter)
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
//        delegateAdapter.addAdapter(homeViewPagerAdapter)
        homeViewPagerAdapter.notifyHasData()
//        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                Log.e(TAG,"isSticky:"+homeTabAdapter.isSticky())
//                if(homeTabAdapter.isSticky()){
//                }
//                super.onScrolled(recyclerView, dx, dy)
//            }
//        })
    }


    private inner class MyPagerAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fm) {

        private val mTitles = arrayOf("11", "22", "33","44")

        override fun getCount(): Int {
            return mFragments.size
        }


        override fun getPageTitle(position: Int): CharSequence {
            return mTitles[position]
        }

        override fun getItem(position: Int): androidx.fragment.app.Fragment {
            return mFragments.get(position)
        }
    }
}
