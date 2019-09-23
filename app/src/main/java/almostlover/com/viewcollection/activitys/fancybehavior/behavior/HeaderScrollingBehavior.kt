package almostlover.com.viewcollection.activitys.fancybehavior.behavior

import android.content.Context
import android.os.Handler
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Scroller
import java.lang.ref.WeakReference
import kotlin.math.abs




class HeaderScrollingBehavior(context: Context, attr: AttributeSet) :
    CoordinatorLayout.Behavior<RecyclerView>(context, attr) {

    private val TAG: String = "HeaderScrollingBehavior"
    var isExpanded = false
    var isScrolling = false


    lateinit var dependentView: WeakReference<View>
    lateinit var scroller: Scroller   //用来实现用户释放手指后的滑动动画
    lateinit var handler: Handler //驱动scroller的运行


    init {
        scroller = Scroller(context)
        handler = Handler()
    }

    private val flingRunnable = object : Runnable {
        override fun run() {
            if (scroller.computeScrollOffset()) {
                getDependentView()!!.translationY = scroller.currY.toFloat()
                handler.post(this)
            } else {
                isExpanded = getDependentView()!!.translationY != 0f
                isScrolling = false
            }
        }
    }

    /**
     * 设置依赖
     */
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: RecyclerView,
        dependency: View
    ): Boolean {

        if (dependency?.id == almostlover.com.viewcollection.R.id.scrolling_header) {
            dependentView = WeakReference(dependency)
            return true
        }
        return false
    }


    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: RecyclerView,
        layoutDirection: Int
    ): Boolean {

        val layoutParams = child.layoutParams as CoordinatorLayout.LayoutParams
        if (layoutParams.height == CoordinatorLayout.LayoutParams.MATCH_PARENT) {
            child.layout(
                0, 0, parent.width,
                (parent.height - getDependentViewCollaspedHeight()!!).toInt()
            )
            return true
        }

        return super.onLayoutChild(parent, child, layoutDirection)
    }


    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: RecyclerView,
        dependency: View
    ): Boolean {
        Log.e(TAG, "都一次初始化加载进来的时候就会调用一次:onDependentViewChanged")
        val resources = getDependentView()!!.resources
        val progress =
            1f - Math.abs(
                dependency.translationY / (dependency.height - resources.getDimension(
                    almostlover.com.viewcollection.R.dimen.collapsed_header_height
                ))
            )

        child.translationY = dependency.height.toFloat()


        val scale = 1 + 0.4f * (1f - progress)
        dependency.scaleX = scale
        dependency.scaleY = scale

        dependency.alpha = progress

        return true
    }

    /**
     * 表示用户按下，是否处理此次滑动，return true 表示处理
     * false：表示不管.
     */
    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: RecyclerView,
        directTargetChild: View,
        target: View,
        axes: Int,//滑动方向，是水平，还是垂直
        type: Int
    ): Boolean {
        //只拦截垂直方向的滚动
        return (axes and ViewCompat.SCROLL_AXIS_VERTICAL)!=0
    }

    override fun onNestedScrollAccepted(
        coordinatorLayout: CoordinatorLayout,
        child: RecyclerView,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ) {

        scroller.abortAnimation()  //停止上一次的动画
        isScrolling = false

        super.onNestedScrollAccepted(
            coordinatorLayout,
            child,
            directTargetChild,
            target,
            axes,
            type
        )
    }


    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: RecyclerView,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        Log.e("onNestedPreScroll", "dy:$dy")
        if (dy<0) {
            return
        }

        val dependentView1 = getDependentView()

        val newTranslateY = dependentView1?.translationY!! - dy
        val minHeaderTranslate = dependentView1.height - getDependentViewCollaspedHeight()

        if (newTranslateY > minHeaderTranslate) {
            dependentView1.translationY = newTranslateY
            consumed[1] = dy
        }
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }


    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: RecyclerView,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        Log.e("onNestedScroll", "dyUnconsumed:$dyUnconsumed")

        if (dyUnconsumed>0) {
            return
        }

        val dependentView1 = getDependentView()
        val newTranslateY  = dependentView1?.translationY!! - dyUnconsumed
        var maxHeaderTranslate = 0
        if (newTranslateY < maxHeaderTranslate) {
            dependentView1.translationY = newTranslateY
        }
//        super.onNestedScroll(
//            coordinatorLayout,
//            child,
//            target,
//            dxConsumed,
//            dyConsumed,
//            dxUnconsumed,
//            dyUnconsumed,
//            type
//        )
    }

    override fun onNestedPreFling(
        coordinatorLayout: CoordinatorLayout,
        child: RecyclerView,
        target: View,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return onUserStopDragging(velocityY)
    }

    override fun onStopNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: RecyclerView,
        target: View,
        type: Int
    ) {
        if (!isScrolling) {
            onUserStopDragging(800f)
        }
    }

    private fun onUserStopDragging(velocityX: Float):Boolean {
        var velocityX = velocityX
        val dependentView1 = getDependentView()
        var translationY = dependentView1!!.translationY
        val minheaderTranslate = -(dependentView1.height - getDependentViewCollaspedHeight())

        if (translationY==0f ||translationY==minheaderTranslate) {
            return false
        }

        var targetState:Boolean

        if (abs(velocityX) <=800) {
            targetState = abs(translationY) >= abs(translationY - minheaderTranslate)

            velocityX=800f

        }else{
            targetState = velocityX > 0
        }
        var targetTranslateY = 0f
        if (targetState) {
             targetTranslateY = minheaderTranslate

        } else {
            targetTranslateY = 0f
        }

        scroller.startScroll(0, translationY.toInt(),0, (targetTranslateY-translationY).toInt())
        handler.post(flingRunnable)
        isScrolling = true

        return true


    }


    fun getDependentViewCollaspedHeight(): Float {
        return getDependentView()?.resources?.getDimension(almostlover.com.viewcollection.R.dimen.collapsed_header_height)!!
    }


    fun getDependentView(): View? {
        return dependentView.get()
    }
}