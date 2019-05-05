package almostlover.com.viewcollection.views.verticalslidelayout

import android.content.Context
import android.support.v4.view.GestureDetectorCompat
import android.support.v4.view.ViewCompat
import android.support.v4.widget.ViewDragHelper
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

class DragLayout(context: Context, attr: AttributeSet) : ViewGroup(context, attr) {


    private var gestureDetector: GestureDetectorCompat
    private var mDragHelper: ViewDragHelper
    private var mViewHeight: Int? = null
    private var view1: View? = null
    public var view2: View? = null


    init {
        mDragHelper = ViewDragHelper.create(this, 10f, DragHelperCallback())
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_BOTTOM)
        gestureDetector = GestureDetectorCompat(context, YScrollDetector())
    }


    override fun computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        view1 = getChildAt(0)
        view2 = getChildAt(1)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)

        val maxWidth = View.MeasureSpec.getSize(widthMeasureSpec)
        val maxHeight = View.MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(
            resolveSizeAndState(maxWidth, widthMeasureSpec, 0),
            resolveSizeAndState(maxHeight, heightMeasureSpec, 0)
        )
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        //只在初始化的时候调用 一些参数只做全局变量保存
        if (view1?.top == 0) {
            view1?.layout(l, 0, r, b - t)
            view2?.layout(l, 0, r, b - t)

            mViewHeight = view1?.measuredHeight
            mViewHeight?.let { view2?.offsetTopAndBottom(it) }
        } else {
            view1?.layout(l, view1?.top!!, r, view1!!.bottom)
            view2?.layout(l, view2?.top!!, r, view2!!.bottom)
        }
    }


    inner class DragHelperCallback : ViewDragHelper.Callback() {
        //用来指定拖动的对象，如果返回值为true，则表示ViewGroup中的所有View都可拖动
        override fun tryCaptureView(p0: View, p1: Int): Boolean {
            return true
        }


        override fun onViewPositionChanged(changedView: View, left: Int, top: Int, dx: Int, dy: Int) {
            var childIndex = 1
            if (changedView == view2) {
                childIndex = 2
            }

            onViewPosChanged(childIndex, top)
        }

        override fun getViewVerticalDragRange(child: View): Int {
            // 这个用来控制拖拽过程中松手后，自动滑行的速度，暂时给一个随意的数值
            return 1
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            super.onViewReleased(releasedChild, xvel, yvel)
            //// 滑动松开后，需要向上或者乡下粘到特定的位置
        }

        fun onViewPosChanged(viewIndex: Int, posTop: Int) {
            if (viewIndex == 1) {
                val offsetTopBottom = mViewHeight!! + view1!!.top - view2!!.top
                view2!!.offsetTopAndBottom(offsetTopBottom)
            } else if (viewIndex == 2) {
                val offsetTopBottom = view2!!.top - mViewHeight!! - view1!!.top
                view1!!.offsetTopAndBottom(offsetTopBottom)

            }

            invalidate()
        }
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        // 统一交给mDragHelper处理，由DragHelperCallback实现拖动效果
        mDragHelper.processTouchEvent(e) // 该行代码可能会抛异常，正式发布时请将这行代码加上try catch
        return true
    }
    /* touch事件的拦截与处理都交给mDraghelper来处理 */
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {

        if (view1!!.getBottom() > 0 && view1!!.getTop() < 0) {
            // view粘到顶部或底部，正在动画中的时候，不处理touch事件
            return false
        }

        val yScroll = gestureDetector.onTouchEvent(ev)
        var shouldIntercept = false
        try {
            shouldIntercept = mDragHelper.shouldInterceptTouchEvent(ev)
        } catch (e: Exception) {
        }

        val action = ev.actionMasked

        if (action == MotionEvent.ACTION_DOWN) {
            // action_down时就让mDragHelper开始工作，否则有时候导致异常 他大爷的
            mDragHelper.processTouchEvent(ev)
            var downTop1 = view1!!.getTop()
        }

        return shouldIntercept && yScroll
    }
    inner class YScrollDetector : GestureDetector.SimpleOnGestureListener() {

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            return Math.abs(distanceY) > Math.abs(distanceX)
        }
    }
}