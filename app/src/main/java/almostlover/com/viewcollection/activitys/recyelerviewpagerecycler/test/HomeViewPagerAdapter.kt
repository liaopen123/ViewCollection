package almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test.ultrarecyclerview.TouchViewPager
import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.layout.SingleLayoutHelper

class HomeViewPagerAdapter(context: Context?) : VBaseAdapter<HomeViewPagerAdapter.TopBannerHolder>(context) {
    var context = context
    private val mFragments: ArrayList<androidx.fragment.app.Fragment> = ArrayList()
    private var bannerList: List<String>? = null

    private lateinit var onItemClickListener: OnItemClickListener

    fun notifyHasData() {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TopBannerHolder {

        return TopBannerHolder(inflater.inflate(R.layout.item_home_viewpager, p0, false))
    }


    override fun getItemCount(): Int {
        return 1
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
        return SingleLayoutHelper()
    }

    private var mTopBannerHolder: TopBannerHolder? = null

    override fun onBindViewHolder(topBannerHolder: TopBannerHolder, p1: Int) {
        mTopBannerHolder = topBannerHolder
        val layoutParams = mTopBannerHolder!!.vp1.layoutParams
        layoutParams.height = context!!.getResources()!!.getDisplayMetrics()!!.heightPixels
        mFragments.add(SimpleCardFragment.getInstance("gaga"))
        mFragments.add(SimpleCardFragment.getInstance("gaga"))
        mFragments.add(SimpleCardFragment.getInstance("gaga"))
        val activity = context as androidx.fragment.app.FragmentActivity
        var mAdapter = MyPagerAdapter(activity.supportFragmentManager)
        mTopBannerHolder!!.vp1.adapter = (mAdapter)
        mTopBannerHolder!!.vp1.parent.requestDisallowInterceptTouchEvent(false)
    }


    inner class TopBannerHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {


        var lly_par: LinearLayout
        var vp1: TouchViewPager

        init {
            lly_par = itemView.findViewById<LinearLayout>(R.id.lly_par)
            vp1 = itemView.findViewById<TouchViewPager>(R.id.vp1)
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }


    override fun onViewAttachedToWindow(holder: TopBannerHolder) {
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: TopBannerHolder) {
        super.onViewDetachedFromWindow(holder)
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