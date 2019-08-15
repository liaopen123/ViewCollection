package almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test.ultrarecyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent

class OutRecyclerView(context: Context, attr:AttributeSet) :RecyclerView(context,attr){
        val TAG = "OutRecyclerView"
    companion object{
        var  isIntercept2Inner = false
    }


    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        if(!isIntercept2Inner){

            //如果不拦截的话 就走原来的路
            Log.e("onInterceptTouchEvent","onInterceptTouchEvent:"+"不拦截");
            return super.onInterceptTouchEvent(e)
        }else{
            //如果拦截
            Log.e("onInterceptTouchEvent","onInterceptTouchEvent:"+"拦截");
            return false
        }

    }



    /**
     * 是否到头 了
     */
    fun isHeadSteak(){
        isIntercept2Inner = true

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