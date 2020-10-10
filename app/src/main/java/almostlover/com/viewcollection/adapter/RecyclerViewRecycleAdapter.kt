package almostlover.com.viewcollection.adapter

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.utils.UIUtils
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class RecyclerViewRecycleAdapter(context: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<BaseViewHolder>() {
    val TAG:String = "recycleAdapter"
    var context:Context = context
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BaseViewHolder {
        Log.e(TAG,"onCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.item_rv_recycler, p0, false)
        return BaseViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.e(TAG,"getItemCount:40")
        return 40
    }

    override fun onBindViewHolder(p0: BaseViewHolder, p1: Int) {
        Log.e(TAG,"onBindViewHolder")
    }

}



class BaseViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view){

    init {
        val container = view.findViewById<LinearLayout>(R.id.ll_container)
        val layoutParams = container.layoutParams
        layoutParams.height = UIUtils.getScreenHegith()/2
        layoutParams.width = UIUtils.getScreenHegith()/5
        container.layoutParams = layoutParams
    }




}