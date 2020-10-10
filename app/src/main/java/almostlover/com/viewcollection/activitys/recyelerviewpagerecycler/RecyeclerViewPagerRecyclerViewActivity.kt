package almostlover.com.viewcollection.activitys.recyelerviewpagerecycler

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test.HomeElectricalTabAdpter
import almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test.HomeTopBannerAdpter
import almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test.HomeViewPagerAdapter
import almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test.SimpleCardFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import kotlinx.android.synthetic.main.activity_recyecler_view_pager_recycler_view.*
import kotlinx.android.synthetic.main.activity_recyecler_view_pager_recycler_view.rv
import kotlinx.android.synthetic.main.activity_recyecler_view_pager_test.*

/**
 * recyclerview  嵌套 ViewPager  再在里面嵌套 recyclerview
 */
class RecyeclerViewPagerRecyclerViewActivity : AppCompatActivity(),MainAdapter.PagerChangeListener {
    companion object{
         var isHeadSteak: Boolean = false
    }
    var TAG = "RecyeclerViewPagerRecyclerViewActivity"
    private val mFragments: ArrayList<androidx.fragment.app.Fragment> = ArrayList()
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



        mainAdapter = HomeTopBannerAdpter(this)
        homeTabAdapter = HomeElectricalTabAdpter(this)
        val homeViewPagerAdapter = HomeViewPagerAdapter(this)
        delegateAdapter.addAdapter(mainAdapter)
        delegateAdapter.addAdapter(homeTabAdapter)
        delegateAdapter.addAdapter(homeViewPagerAdapter)
        homeViewPagerAdapter.notifyHasData()
        rv.addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
                Log.e(TAG,"isSticky:"+homeTabAdapter.isSticky())
                if(homeTabAdapter.isSticky()){
//                    rv.isHeadSteak()
                    isHeadSteak = true
                    Log.e(TAG,"吸顶了")
                }else{
                    Log.e(TAG,"没有吸顶了")
                    isHeadSteak = false
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }



    private inner class MyPagerAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fm) {

        private val mTitles = arrayOf("11", "22", "33")

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
