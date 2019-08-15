package almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test.ultrarecyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.support.v7.widget.LinearLayoutManager



class InnerRecyclerView(context: Context, attr:AttributeSet) :RecyclerView(context,attr){
    val TAG = "InnerRecyclerView"
   fun setOnHeadLister(){
       if (OutRecyclerView.isIntercept2Inner) {
//           parent.requestDisallowInterceptTouchEvent(true)
       }
//       addOnScrollListener(object : RecyclerView.OnScrollListener() {
//
//           override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//               super.onScrolled(recyclerView, dx, dy)
//               Log.i( TAG, "--------------------------------------")
//               val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
//               val firstCompletelyVisibleItemPosition = layoutManager!!.findFirstCompletelyVisibleItemPosition()
//               Log.i( TAG, "firstCompletelyVisibleItemPosition: $firstCompletelyVisibleItemPosition")
//               if (firstCompletelyVisibleItemPosition == 0)
//                   Log.i( TAG, "滑动到顶部")
//               val lastCompletelyVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()
//               Log.i( TAG, "lastCompletelyVisibleItemPosition: $lastCompletelyVisibleItemPosition")
//               if (lastCompletelyVisibleItemPosition == layoutManager.itemCount - 1)
//                   Log.i( TAG, "滑动到底部")
//           }
//       })
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