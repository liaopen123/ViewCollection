package almostlover.com.viewcollection.activitys.persisitantrv.adapter

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.activitys.viewpager2.VPChildFragment
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class PersisitantAdapter(val context:AppCompatActivity) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private val inflater = LayoutInflater.from(context)

    override fun getItemViewType(position: Int): Int {
        return position

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType==0) {
        return    PViewHolder(inflater.inflate(R.layout.item_persistant_one,parent,false))
        }else{
        return  VPViewHolder(inflater.inflate(R.layout.item_persistant_two,parent,false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position==0) {
            val pViewHolder = holder as PViewHolder
        }else{
            val vpViewHolder = holder as VPViewHolder
            vpViewHolder.vp2.adapter = VPAdapter(context.supportFragmentManager,context.lifecycle)
        }
    }

    override fun getItemCount()=2






    inner class PViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }
    inner class VPViewHolder(var itemView1: View):RecyclerView.ViewHolder(itemView1){
        var vp2:ViewPager2 = itemView1.findViewById(R.id.vp2)

    }
    private inner class VPAdapter(fm: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(fm,lifecycle){
        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
            return VPChildFragment.newInstance("$position","")
        }

    }


}