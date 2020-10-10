package almostlover.com.viewcollection.utils

import almostlover.com.viewcollection.activitys.inslayout.InsLayoutManager
import almostlover.com.viewcollection.adapter.BigImageAdapter
import almostlover.com.viewcollection.adapter.CommonAdapter
import almostlover.com.viewcollection.adapter.InsAdapter
import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RvUtils {

    companion object {

        fun loadData(rv: androidx.recyclerview.widget.RecyclerView, context: Context) {

            rv.layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(context)
            val arrayList = ArrayList<String>()
            for (i in 1..100) {
                arrayList.add("" + i)
            }
            rv.adapter = CommonAdapter(arrayList)
        }
        fun loadGVData(rv: androidx.recyclerview.widget.RecyclerView, context: Context) {

            rv.layoutManager =
                androidx.recyclerview.widget.GridLayoutManager(context, 3)
            val arrayList = ArrayList<String>()
            for (i in 1..100) {
                arrayList.add("" + i)
            }
            rv.adapter = CommonAdapter(arrayList)
        }

        fun loadImageData(rv: androidx.recyclerview.widget.RecyclerView, context: Context) {

            rv.layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(context)
            val arrayList = ArrayList<String>()
            for (i in 1..100) {
                arrayList.add("" + i)
            }
            rv.adapter = BigImageAdapter(arrayList)
        }

        fun loadInsData(rv: RecyclerView, context: Context) {

            rv.layoutManager =InsLayoutManager()
            val arrayList = ArrayList<String>()
            for (i in 1..100) {
                arrayList.add("" + i)
            }
            rv.adapter = InsAdapter(arrayList)
        }

    }


    class CanNotScrollLinearLayoutManager(context: Context) : androidx.recyclerview.widget.LinearLayoutManager(context) {


        private var isScrollEnabled = true


        fun setScrollEnabled(flag: Boolean) {
            this.isScrollEnabled = flag
        }

        override fun canScrollVertically(): Boolean {
            return isScrollEnabled && super.canScrollVertically()
        }


    }




}