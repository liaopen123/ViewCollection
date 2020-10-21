package almostlover.com.viewcollection.activitys.layoutmanager;

import almostlover.com.viewcollection.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


public class RVAdapter(var mContext: Context, var datas: ArrayList<String>): RecyclerView.Adapter<RVAdapter.RVHolder>(),AnkoLogger{

    var onCreateViewHolderCount = 0
    var onBindViewHolderCount = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVHolder {
        info { "RVAdapter onCreateViewHolder${onCreateViewHolderCount++}" }
//        val view = View.inflate(mContext, R.layout.item_lph_linearlayoutmanager, null)
        val view: View = LayoutInflater.from(mContext).inflate(R.layout.item_lph_linearlayoutmanager, parent, false)
        return RVHolder(view)
    }

    override fun onBindViewHolder(holder: RVHolder, position: Int) {
        info { "RVAdapter onBindViewHolder${onBindViewHolderCount++}" }
        holder.tv.text= datas[position]
    }

    override fun getItemCount(): Int {
        return datas.size
    }


    inner class RVHolder(var vh: View):RecyclerView.ViewHolder(vh){
        var tv:TextView = vh.findViewById(R.id.tv_content)
    }
}