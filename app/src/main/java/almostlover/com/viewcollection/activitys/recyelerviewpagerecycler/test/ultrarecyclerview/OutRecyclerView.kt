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
        return e?.action != MotionEvent.ACTION_DOWN
    }

//    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
//
//        Log.e(TAG,"super.onInterceptTouchEvent(e)"+super.onInterceptTouchEvent(e))
//        when(e?.action){
//            MotionEvent.ACTION_DOWN ->{
//                if(isIntercept2Inner){
//                    return false
//                }
//            }
//            MotionEvent.ACTION_MOVE ->{
//                if(isIntercept2Inner){
//                    return false
//                }
//            }
//            MotionEvent.ACTION_UP,MotionEvent.ACTION_CANCEL ->{}
//
//
//        }
//        return super.onInterceptTouchEvent(e)
//    }
////
////    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
////        if(!isIntercept2Inner){
////
////            //如果不拦截的话 就走原来的路
////            Log.e("onInterceptTouchEvent","onInterceptTouchEvent:"+"不拦截");
////            return super.onInterceptTouchEvent(e)
////        }else{
////            //如果拦截
////            Log.e("onInterceptTouchEvent","onInterceptTouchEvent:"+"拦截");
////            return false
////        }
////
////    }
//
//
//
//    /**
//     * 是否到头 了
//     */
//    fun isHeadSteak(){
//        isIntercept2Inner = true
//
//    }
//
//
//    override fun onTouchEvent(e: MotionEvent?): Boolean {
//        Log.e(TAG,"onTouchEvent"+super.onTouchEvent(e))
//        return super.onTouchEvent(e)
//    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when(ev?.action){

            MotionEvent.ACTION_DOWN ->{
                Log.e(TAG,"父类 dispatchTouchEvent---ACTION_DOWN")
            }
            MotionEvent.ACTION_MOVE ->{
                Log.e(TAG,"父类 dispatchTouchEvent---ACTION_MOVE")
            }
            MotionEvent.ACTION_UP ->{
                Log.e(TAG,"父类 dispatchTouchEvent---ACTION_UP")
            }
        }
        return super.dispatchTouchEvent(ev)
    }


    override fun onTouchEvent(ev: MotionEvent): Boolean {

        when(ev?.action){

            MotionEvent.ACTION_DOWN ->{
                Log.e(TAG,"父类 自己消费了onTouchEvent---ACTION_DOWN")
            }
            MotionEvent.ACTION_MOVE ->{
                Log.e(TAG,"父类 自己消费了onTouchEvent---ACTION_MOVE")
            }
            MotionEvent.ACTION_UP ->{
                Log.e(TAG,"父类 自己消费了onTouchEvent---ACTION_UP")
            }
        }


        return super.onTouchEvent(ev)
    }

}