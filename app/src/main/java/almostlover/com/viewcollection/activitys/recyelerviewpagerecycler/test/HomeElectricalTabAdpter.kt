package almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test

import almostlover.com.viewcollection.R
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.layout.StickyLayoutHelper

class HomeElectricalTabAdpter(context: Context?) : VBaseAdapter<HomeElectricalTabAdpter.TopBannerHolder>(context) {


    private lateinit var stickyLayoutHelper: StickyLayoutHelper

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TopBannerHolder {

        return TopBannerHolder(inflater.inflate(R.layout.item_home_electrical_product_tab, p0, false))
    }


    override fun getItemCount(): Int {
        return 1
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
         stickyLayoutHelper = StickyLayoutHelper()
        return stickyLayoutHelper
    }


    override fun onBindViewHolder(topBannerHolder: TopBannerHolder, p1: Int) {


    }


    inner class TopBannerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {




    }


    fun isSticky(): Boolean {
        return stickyLayoutHelper.isStickyNow
    }


}











