package almostlover.com.viewcollection.activitys.nestedscrolling.common

import android.content.Context
import androidx.annotation.Nullable
import androidx.core.view.NestedScrollingParent
import androidx.core.view.NestedScrollingParentHelper
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import java.util.jar.Attributes

class CommonNestedScrollingParent @JvmOverloads constructor(
    context:Context,
    attributeSet: AttributeSet?  =null,
    typeStyle:Int = 0):LinearLayout(context,attributeSet),NestedScrollingParent{

    private var nestedScrollingParentHelper: NestedScrollingParentHelper

    init {
         nestedScrollingParentHelper = NestedScrollingParentHelper(this)
    }


    override fun onStartNestedScroll(@Nullable child: View,@Nullable target: View, nestedScrollAxes: Int): Boolean {
        return super.onStartNestedScroll(child, target, nestedScrollAxes)
    }

    override fun onNestedScrollAccepted(@Nullable child: View, @Nullable target: View, axes: Int) {
        super.onNestedScrollAccepted(child, target, axes)
    }

    override fun onNestedScroll(
        @Nullable target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int
    ) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
    }

    override fun onNestedPreScroll(@Nullable target: View, dx: Int, dy: Int, @Nullable consumed: IntArray) {
        super.onNestedPreScroll(target, dx, dy, consumed)
    }


    override fun onNestedFling(
        @Nullable target: View,
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        return super.onNestedFling(target, velocityX, velocityY, consumed)
    }

    override fun onNestedPreFling(@Nullable target: View, velocityX: Float, velocityY: Float): Boolean {
        return super.onNestedPreFling(target, velocityX, velocityY)
    }

    override fun getNestedScrollAxes(): Int {
        return super.getNestedScrollAxes()
    }


    override fun onStopNestedScroll(@Nullable child: View) {
        super.onStopNestedScroll(child)
    }


}