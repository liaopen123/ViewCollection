package almostlover.com.viewcollection.views.dragviewhelper

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import androidx.customview.widget.ViewDragHelper

class DragLin @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    style: Int = 0
) : RelativeLayout(context, attributeSet, style){
    lateinit var itemView: View
    lateinit var delView:View
    private var viewDragHelper: ViewDragHelper
    val mPoint = Point()

init {
    viewDragHelper =   ViewDragHelper.create(this, 1f,CallB())

}

    override fun onInterceptHoverEvent(event: MotionEvent): Boolean {
        return viewDragHelper.shouldInterceptTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        viewDragHelper.processTouchEvent(event)
        return true
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        mPoint.x = itemView.left
        mPoint.y = itemView.right
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        itemView = getChildAt(0)
        delView = getChildAt(1)
    }

    override fun computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            invalidate()
        }
    }


    inner  class CallB : ViewDragHelper.Callback() {
        //确定那个
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return child == itemView
        }

        //
        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            var leftBound = width-delView.width
            var rightBound = width
            var newLeft = Math.min(left.coerceAtLeast(-delView.width), paddingLeft);
            return newLeft
        }

        override fun onViewPositionChanged(changedView: View, left: Int, top: Int, dx: Int, dy: Int) {
            super.onViewPositionChanged(changedView!!, left, top, dx, dy)
            mPoint.x = left
        }

        //当手离开屏幕不在拖拽
        override  fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            super.onViewReleased(releasedChild!!, xvel, yvel)
            if (-itemView.x > delView.width / 2) {
                //当滑动的位置超过了delview的一半的时候那么自动滑动到完全覆盖状态,高度不动
                viewDragHelper.settleCapturedViewAt(-delView.width, mPoint.y)
                invalidate()
            } else {
                //当滑动的位置不超过了delview的一半的时候那么还原到delview完全显示状态,高度不动
                viewDragHelper.settleCapturedViewAt(paddingLeft, mPoint.y)
                invalidate()
            }
        }
    }
}