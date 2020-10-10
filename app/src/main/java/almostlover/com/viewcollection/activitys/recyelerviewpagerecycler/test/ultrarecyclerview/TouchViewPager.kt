package almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.test.ultrarecyclerview

import almostlover.com.viewcollection.activitys.recyelerviewpagerecycler.RecyeclerViewPagerRecyclerViewActivity
import android.content.Context
import androidx.viewpager.widget.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent

class TouchViewPager(context: Context,attr:AttributeSet):
    androidx.viewpager.widget.ViewPager(context,attr){

    val TAG = "TouchViewPager"

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        when(ev?.action){
            MotionEvent.ACTION_DOWN ->{
                Log.e(TAG,"子类 自己消费了dispatchTouchEvent---ACTION_DOWN")
                getParent().requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE ->{
                if(RecyeclerViewPagerRecyclerViewActivity.isHeadSteak){
                    parent.requestDisallowInterceptTouchEvent(true)
                }else{
                    parent.requestDisallowInterceptTouchEvent(false)
                }

                Log.e(TAG,"子类 自己消费了dispatchTouchEvent---ACTION_MOVE")
            }
            MotionEvent.ACTION_UP ->{
                Log.e(TAG,"子类 自己消费了dispatchTouchEvent---ACTION_UP")
            }

        }


        return super.dispatchTouchEvent(ev)
    }


    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        when(ev?.action){

            MotionEvent.ACTION_DOWN ->{
                Log.e(TAG,"子类 onTouchEvent---ACTION_DOWN")
            }
            MotionEvent.ACTION_MOVE ->{
                Log.e(TAG,"子类 onTouchEvent---ACTION_MOVE")
            }
            MotionEvent.ACTION_UP ->{
                Log.e(TAG,"子类 onTouchEvent---ACTION_UP")
            }
        }
        return super.onTouchEvent(ev)
    }


}