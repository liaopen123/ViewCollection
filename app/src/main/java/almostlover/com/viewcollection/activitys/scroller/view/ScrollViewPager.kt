package almostlover.com.viewcollection.activitys.scroller.view

import almostlover.com.viewcollection.utils.lphLog
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.Scroller


class ScrollViewPager @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    style: Int = 0
) : ViewGroup(context, attributeSet) {


    private var mMaxVelocity: Int
    private var mTouchSlop: Int
    private var mScroller: Scroller
    private var mVelocityTracker: VelocityTracker? = null
    /**
     * 当前显示的是第几个屏幕
     */
    private var mCurrentPage = 0

    init {
        mScroller = Scroller(context)
        val viewConfiguration = ViewConfiguration.get(context)
        mTouchSlop = viewConfiguration.scaledPagingTouchSlop
        mMaxVelocity = viewConfiguration.scaledMinimumFlingVelocity
    }

    private var mLastX: Float = 0.0f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        for (i in 0 until childCount) {
            val child = getChildAt(i)

            child.measure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var startLeft = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)

            child.layout(startLeft, 0, startLeft + child.measuredWidth, child.measuredHeight)
            startLeft += child.measuredWidth
            lphLog("child.measuredWidth${child.measuredWidth},child.measuredHeight${child.measuredHeight},startLeft:$startLeft")
        }

    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        initVelocityTrackerIfNotExists()
        mVelocityTracker?.addMovement(event)
        val event = event!!
        val x = event.x
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                //刚开始放下的位置点
                mLastX = event.x
                mScroller.abortAnimation()
            }

            MotionEvent.ACTION_MOVE -> {
                val dx = (mLastX - x).toInt()

                scrollBy(dx, 0)
                mLastX = x
            }

            MotionEvent.ACTION_UP ->{
                val velocityTracker = mVelocityTracker
                velocityTracker?.computeCurrentVelocity(1000)
                val xVelocity = velocityTracker?.xVelocity
                if (xVelocity!! >mMaxVelocity && mCurrentPage>0) {
                    scrollToPage(mCurrentPage - 1)

                }else if (xVelocity!!<-mMaxVelocity &&mCurrentPage<(childCount-1)) {
                    scrollToPage(mCurrentPage +1)
                }else{
                    slowScrollToPage()
                }
                recycleVelocityTracker()
            }
        }


        return true
    }

    override fun computeScroll() {
        super.computeScroll()
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.currX, mScroller.currY)
            invalidate()
        }
    }
    /**
     * 缓慢滑动抬起手指的情形，需要判断是停留在本Page还是往前、往后滑动
     */
    private fun slowScrollToPage() {
        //当前的偏移位置
        val scrollX = scrollX
        val scrollY = scrollY
        //判断是停留在本Page还是往前一个page滑动或者是往后一个page滑动
        val whichPage = (getScrollX() + width / 2) / width
        scrollToPage(whichPage)
    }

    /**
     * 滑动到指定屏幕
     * @param indexPage
     */
    private fun scrollToPage(indexPage: Int) {
        mCurrentPage = indexPage
        if (mCurrentPage > childCount - 1) {
            mCurrentPage = childCount - 1
        }
        //计算滑动到指定Page还需要滑动的距离
        val dx = mCurrentPage * width - scrollX
        mScroller.startScroll(scrollX, 0, dx, 0, Math.abs(dx) * 2)//动画时间设置为Math.abs(dx) * 2 ms
        //记住，使用Scroller类需要手动invalidate
        invalidate()
    }

    private fun recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker!!.recycle()
            mVelocityTracker = null
        }
    }

    private fun initVelocityTrackerIfNotExists() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain()
        }
    }
}