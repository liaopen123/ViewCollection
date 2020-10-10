package almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test

import almostlover.com.viewcollection.R
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.alibaba.android.vlayout.LayoutHelper
import com.alibaba.android.vlayout.layout.SingleLayoutHelper

class HomeTopBannerAdpter(context: Context?): VBaseAdapter<HomeTopBannerAdpter.TopBannerHolder>(context) {

    private  var bannerList: List<String>? =null

    private lateinit var onItemClickListener: OnItemClickListener

    fun  notifyHasData(bannerList:List<String>){
        this.bannerList = bannerList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TopBannerHolder {

    return TopBannerHolder(inflater.inflate(R.layout.item_home_banner,p0,false))
    }




    override fun getItemCount(): Int {
        return 1
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
        return SingleLayoutHelper()
    }

    private  var mTopBannerHolder: TopBannerHolder? =null

    override fun onBindViewHolder(topBannerHolder: TopBannerHolder, p1: Int) {
        mTopBannerHolder = topBannerHolder
        bannerList?.let {
            topBannerHolder.bnr_home_banner.text = "hello"
        }

    }


    inner class TopBannerHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {


         var lly_par: LinearLayout
         var bnr_home_banner: TextView

        init {
             lly_par = itemView.findViewById<LinearLayout>(R.id.lly_par)
            bnr_home_banner = itemView.findViewById<TextView>(R.id.bnr_home_banner)
        }

    }

    interface  OnItemClickListener{
          fun onItemClick(position:Int)
    }



    fun setItemClickListener(onItemClickListener:OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }


    override fun onViewAttachedToWindow(holder: TopBannerHolder) {
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: TopBannerHolder) {
        super.onViewDetachedFromWindow(holder)
    }



}