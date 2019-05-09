package almostlover.com.viewcollection.views.sixway2dragview.drag6scroller

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Scroller

class Drag6ScrollerView(context: Context, attrs: AttributeSet) : ImageView(context, attrs) {


    private var mScroller: Scroller = Scroller(context)
    private val TAG: String? = "Drag1OnLayoutView"
    private var lastX: Int = 0
    private var lastY: Int = 0


    override fun onTouchEvent(event: MotionEvent): Boolean {

        //获取到手指处的横坐标和纵坐标
        val x = event.x.toInt()
        val y = event.y.toInt()


        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

                lastX = x
                lastY = y
            }

            MotionEvent.ACTION_MOVE -> {

                //计算移动的距离
                val offX = x - lastX
                val offY = y - lastY

                (parent as View).scrollBy(-offX, -offY)
            }

            MotionEvent.ACTION_UP -> {

                val viewGroup = parent as View

                //开启滑动,让其回到原点
                mScroller.startScroll(
                    viewGroup.scrollX,
                    viewGroup.scrollY,
                    -viewGroup.scrollX, -viewGroup.scrollY
                )
            }
        }
        return true
    }


    override fun computeScroll() {
        super.computeScroll()

        if (mScroller.computeScrollOffset()) {
            (parent as View).scrollTo(
                mScroller.currX,
                mScroller.currY
            )
        }

        invalidate()//必须要调用
    }

}