package almostlover.com.viewcollection.activitys.scroller.view

import almostlover.com.viewcollection.utils.lphLog
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView

class OutterScrollView(context: Context, attrs: AttributeSet) : ScrollView(context, attrs) {
    private var mLastY: Float = 0.0f
    private var isTop = false
    private var isBottom = false
//    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
//        val y = ev.y
//        when(ev.action) {
//            MotionEvent.ACTION_DOWN ->{
//                mLastY = y
//
//
//            }
//
//            MotionEvent.ACTION_MOVE->{
//                lphLog("isTop：$isTop ,,isBottom$isBottom  ,往上滑${(y-mLastY)<0} ,往下滑动${(y-mLastY)>0}")
//                if(isTop&&(y-mLastY)<0){
//                    //到达顶部往上滑动
//                    return false
//                }
//                if(isBottom&&(y-mLastY)>0){
//                    //到达底部往下滑动
//                    return false
//                }
//            }
//        }
//
//
//        return isBottom||isTop
//    }


  fun setIsBottom(){
      isBottom = true
  }
    fun setIsTop(){
      isTop = true
  }

   fun setIsMiddle(){
      isTop = false
       isBottom = false
    }
}
