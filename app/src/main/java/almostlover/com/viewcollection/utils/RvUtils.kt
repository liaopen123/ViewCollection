package almostlover.com.viewcollection.utils

import almostlover.com.viewcollection.adapter.CommonAdapter
import android.support.v7.widget.RecyclerView

class RvUtils{

    companion object{

        fun loadData(rv:RecyclerView){
            val arrayList = ArrayList<String>()
            for(i in 1..100){
                arrayList.add(""+i)
            }
            rv.adapter = CommonAdapter(arrayList)
        }


    }


}