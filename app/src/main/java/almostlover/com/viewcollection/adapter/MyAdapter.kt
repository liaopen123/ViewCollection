package almostlover.com.viewcollection.adapter

import almostlover.com.viewcollection.R
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

 class MyAdapter(context1: Context) : BaseAdapter() {
    private var context11: Context

    init {
        context11 = context1
    }

    override fun getCount(): Int {
        return 100
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        return View.inflate(context11, R.layout.item_lv, null)
    }
}