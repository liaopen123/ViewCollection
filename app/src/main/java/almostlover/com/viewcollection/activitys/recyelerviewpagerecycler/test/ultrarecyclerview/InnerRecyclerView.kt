package almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test.ultrarecyclerview

import almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test.ultrarecyclerview.OutRecyclerView.Companion.isIntercept2Inner
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager



class InnerRecyclerView(context: Context, attr:AttributeSet) :
    androidx.recyclerview.widget.RecyclerView(context,attr){
    val TAG = "InnerRecyclerView"
   fun setOnHeadLister(){

       addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {

           override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
               super.onScrolled(recyclerView, dx, dy)
               Log.i( TAG, "--------------------------------------")
               val layoutManager = recyclerView.layoutManager as androidx.recyclerview.widget.LinearLayoutManager?
               val firstCompletelyVisibleItemPosition = layoutManager!!.findFirstCompletelyVisibleItemPosition()
               Log.i( TAG, "firstCompletelyVisibleItemPosition: $firstCompletelyVisibleItemPosition")
               if (firstCompletelyVisibleItemPosition == 0) {
                   Log.i(TAG, "滑动到顶部")
                   isIntercept2Inner = false
               }
               val lastCompletelyVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()
               Log.i( TAG, "lastCompletelyVisibleItemPosition: $lastCompletelyVisibleItemPosition")
               if (lastCompletelyVisibleItemPosition == layoutManager.itemCount - 1)
                   Log.i( TAG, "滑动到底部")
           }
       })
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.e(TAG,"dispatchTouchEvent"+super.dispatchTouchEvent(ev))

        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        Log.e(TAG,"onTouchEvent"+super.onTouchEvent(e))
        return super.onTouchEvent(e)
    }

}