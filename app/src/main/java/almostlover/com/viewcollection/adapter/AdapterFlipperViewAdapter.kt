package almostlover.com.viewcollection.adapter

import almostlover.com.viewcollection.views.flipperview.AnnouncementItemView
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class AdapterFlipperViewAdapter(strList:MutableList<String>,context: Context):BaseAdapter(){

    var mStrList = strList
    var mContext = context


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemHolder:ItemHolder
        var itemView = convertView
        if(null==itemView){
             itemView = AnnouncementItemView(mContext)
            itemHolder = ItemHolder()
            itemHolder.itemView = itemView
        }else{
            itemHolder = itemView.tag as ItemHolder
        }

        itemHolder.itemView.setText(mStrList.get(position))

        return itemView!!
    }

    override fun getItem(position: Int): Any {
        return mStrList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mStrList.size
    }
    class ItemHolder {
        lateinit var itemView: AnnouncementItemView
    }
}