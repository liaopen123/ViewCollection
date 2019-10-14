package almostlover.com.viewcollection.activitys.nestedscrolling.common

import android.content.Context
import android.support.v4.view.NestedScrollingChild
import android.support.v4.view.NestedScrollingChildHelper
import android.util.AttributeSet
import android.widget.LinearLayout

class CommonNestedScrollingChild @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? =null,typeStyle:Int = 0 ):LinearLayout(context,attributeSet),NestedScrollingChild{

    private var mNestedScrollingChildHelper: NestedScrollingChildHelper

    init {
        mNestedScrollingChildHelper = NestedScrollingChildHelper(this)
        mNestedScrollingChildHelper.isNestedScrollingEnabled = true
    }

    override fun setNestedScrollingEnabled(enabled: Boolean) {
        mNestedScrollingChildHelper.isNestedScrollingEnabled = enabled
    }

    override fun isNestedScrollingEnabled(): Boolean {
        return mNestedScrollingChildHelper.isNestedScrollingEnabled
    }

    override fun startNestedScroll(axes: Int): Boolean {
        return mNestedScrollingChildHelper.startNestedScroll(axes)
    }

    override fun stopNestedScroll() {
        mNestedScrollingChildHelper.stopNestedScroll()
    }

    override fun hasNestedScrollingParent(): Boolean {
        return mNestedScrollingChildHelper.hasNestedScrollingParent()
    }

    override fun dispatchNestedFling(
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        return mNestedScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed)
    }

    override fun dispatchNestedPreFling(velocityX: Float, velocityY: Float): Boolean {
        return mNestedScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY)
    }

    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?
    ): Boolean {
        return mNestedScrollingChildHelper.dispatchNestedScroll(
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            offsetInWindow
        )
    }


    override fun dispatchNestedPreScroll(
        dx: Int,
        dy: Int,
        consumed: IntArray?,
        offsetInWindow: IntArray?
    ): Boolean {
        return mNestedScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
    }


}