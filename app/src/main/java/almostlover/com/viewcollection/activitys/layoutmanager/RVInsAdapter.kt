package almostlover.com.viewcollection.activitys.layoutmanager;

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.utils.UIUtils
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


public class RVInsAdapter(var mContext: Context, var datas: ArrayList<String>): RecyclerView.Adapter<RVInsAdapter.RVHolder>(),AnkoLogger{
    val TYPE_SMALL =0
    val TYPE_BIG =1
    var onCreateViewHolderCount = 0
    var onBindViewHolderCount = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVHolder {
        info { "RVAdapter onCreateViewHolder${onCreateViewHolderCount++}" }
//        val view = View.inflate(mContext, R.layout.item_lph_linearlayoutmanager, null)

        if (viewType==TYPE_SMALL) {
            val view: View = LayoutInflater.from(mContext).inflate(R.layout.item_ins_small_img, parent, false)
            return RVHolder(view)
        }else{
            val view: View = LayoutInflater.from(mContext).inflate(R.layout.item_ins_big_img, parent, false)
            return RVHolder(view)
        }


    }

    override fun onBindViewHolder(holder: RVHolder, position: Int) {
        info { "RVAdapter onBindViewHolder${onBindViewHolderCount++}" }

        if (position % 18 === 1||position%18===9){
            val layoutParams = holder.tv.layoutParams as LinearLayout.LayoutParams
            layoutParams.width= UIUtils.getScreenWidth()/3
            layoutParams.height= UIUtils.getScreenWidth()/3
            holder.itemView.layoutParams = layoutParams
            holder.tv.text= datas[position]
        }else{
            val layoutParams = holder.itemView.layoutParams as RecyclerView.LayoutParams
            layoutParams.width= UIUtils.getScreenWidth()/3
            layoutParams.height= UIUtils.getScreenWidth()/3
            holder.itemView.layoutParams = layoutParams
            holder.tv.text= datas[position]
        }


    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun getItemViewType(position: Int): Int {

        if (position % 18 === 1||position%18===9){
            return TYPE_BIG
        }else{
            return TYPE_SMALL
        }
    }


    inner class RVHolder(var vh: View):RecyclerView.ViewHolder(vh){
        var tv:TextView = vh.findViewById(R.id.tv_content)
    }
}