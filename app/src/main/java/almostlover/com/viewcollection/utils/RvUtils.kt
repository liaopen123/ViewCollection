package almostlover.com.viewcollection.utils

import almostlover.com.viewcollection.adapter.BigImageAdapter
import almostlover.com.viewcollection.adapter.CommonAdapter
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


class RvUtils {

    companion object {

        fun loadData(rv: RecyclerView, context: Context) {

            rv.layoutManager = CanNotScrollLinearLayoutManager(context)
            val arrayList = ArrayList<String>()
            for (i in 1..100) {
                arrayList.add("" + i)
            }
            rv.adapter = CommonAdapter(arrayList)
        }

        fun loadImageData(rv: RecyclerView, context: Context) {

            rv.layoutManager = LinearLayoutManager(context)
            val arrayList = ArrayList<String>()
            for (i in 1..100) {
                arrayList.add("" + i)
            }
            rv.adapter = BigImageAdapter(arrayList)
        }


    }


    class CanNotScrollLinearLayoutManager(context: Context) : LinearLayoutManager(context) {


        private var isScrollEnabled = true


        fun setScrollEnabled(flag: Boolean) {
            this.isScrollEnabled = flag
        }

        override fun canScrollVertically(): Boolean {
            return isScrollEnabled && super.canScrollVertically()
        }
    }


}