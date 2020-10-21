package almostlover.com.viewcollection.activitys.layoutmanager

import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class LPHLinearLayout @JvmOverloads constructor() :RecyclerView.LayoutManager(),AnkoLogger{
    var mLastVisiblePosition = 0
    var mVerticalOffset = 0

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return  RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }
    //这个方法初始时候 只会调用2次
    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        info { "onLayoutChildren start" }
        var leftOffset = paddingLeft
        var topOffset = paddingTop
        if (itemCount==0) {
            detachAndScrapAttachedViews(recycler)
            return
        }

        if (childCount==0&&state.isPreLayout) {
            return
        }

        detachAndScrapAttachedViews(recycler)

        mLastVisiblePosition  = itemCount
        for (i in 0 until mLastVisiblePosition) {
            val child = recycler.getViewForPosition(i)
            addView(child)
            measureChildWithMargins(child,0,0)
            //
            if (topOffset+getDecoratedMeasurementVertical(child)>=height) {
                info { "可见的条目为:${i-1},一个屏幕不能再装了。。$height" }
                mLastVisiblePosition = i-1
                removeAndRecycleView(child,recycler)
            }else{
                layoutDecoratedViewWithMargins(child,leftOffset,topOffset)
                topOffset+=getDecoratedMeasurementVertical(child)
            }
        }
    }

    private fun layoutDecoratedViewWithMargins(child: View, leftOffset: Int, topOffset: Int) {
        layoutDecoratedWithMargins(child,leftOffset,topOffset,leftOffset+getDecoratedMeasurementHorizontal(child),topOffset+getDecoratedMeasurementVertical(child))
    }


    //由于上述方法没有考虑margin的存在，所以我参考LinearLayoutManager的源码：
    /**
     * 获取某个childView在水平方向所占的空间
     *
     * @param view
     * @return
     */
    fun getDecoratedMeasurementHorizontal(view: View): Int {

        val params = view.layoutParams as RecyclerView.LayoutParams
       var viewWidth =  (getDecoratedMeasuredWidth(view) + params.leftMargin
                + params.rightMargin)
        info { "View的宽度:$viewWidth" }
        return viewWidth
    }

    /**
     * 获取某个childView在竖直方向所占的空间
     *
     * @param view
     * @return
     */
    fun getDecoratedMeasurementVertical(view: View): Int {
        val params = view.layoutParams as RecyclerView.LayoutParams
     val viewHeight =    (getDecoratedMeasuredHeight(view) + params.topMargin
                + params.bottomMargin)
        info { "View的高度:$viewHeight" }
        return viewHeight
    }

    //模仿LLM Horizontal 源码

    fun getVerticalSpace(): Int {
        return height - paddingTop - paddingBottom
    }

    fun getHorizontalSpace(): Int {
        return width - paddingLeft - paddingRight
    }

    override fun canScrollVertically(): Boolean {
        return true
    }

    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        var leftOffset = paddingLeft
        var topOffset = paddingTop
        for (i in childCount-1 ..0 ) {
            var child = getChildAt(i)
            layoutDecoratedViewWithMargins(child!!,mVerticalOffset,topOffset)
            mVerticalOffset+=getDecoratedMeasurementVertical(child!!)
        }




        offsetChildrenVertical(-dy)
        return dy
    }

}