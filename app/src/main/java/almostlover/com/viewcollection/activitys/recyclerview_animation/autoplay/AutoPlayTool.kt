package almostlover.com.viewcollection.activitys.recyclerview_animation.autoplay

import almostlover.com.viewcollection.utils.DensityUtil
import android.graphics.Rect
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View


/**
 * 自动播放的工具
 */
class AutoPlayTool {
    private var mHolder: AutoPlayItem? = null
    private var visiblePercent = 60
    private var mode = MODE_PLAY_FIRST

    constructor() {}
    constructor(visiblePercent: Int) {
        this.visiblePercent = visiblePercent
    }

    constructor(visiblePercent: Int, mode: Int) {
        this.visiblePercent = visiblePercent
        this.mode = mode
    }

    fun setMode(mode: Int) {
        this.mode = mode
    }

    /**
     * 当滑动停止的时候，开始视频播放
     * @param recyclerView
     * @return
     */
    fun onActiveWhenNoScrolling(recyclerView: androidx.recyclerview.widget.RecyclerView): Int {
        var layoutManager: androidx.recyclerview.widget.LinearLayoutManager? = null
        if (recyclerView.layoutManager is androidx.recyclerview.widget.LinearLayoutManager) {
            layoutManager = recyclerView.layoutManager as androidx.recyclerview.widget.LinearLayoutManager?
        }
        if (layoutManager != null) {
            var firstItemPosition = layoutManager.findFirstVisibleItemPosition()
            val lastItemPosition = layoutManager.findLastVisibleItemPosition()
            val items: LinkedHashMap<Int, AutoPlayItem> = LinkedHashMap()
            while (firstItemPosition <= lastItemPosition) {
                val holder =
                    recyclerView.findViewHolderForLayoutPosition(firstItemPosition)
//                if (holder is AutoPlayItem) {
                    val view: View? = (holder as AutoPlayItem).autoplayView
                    if (view != null && getVisible(view, visiblePercent)) {
                        if (mode == MODE_PLAY_FIRST) { //优先播放第一个的情况
                            (holder as AutoPlayItem).setActive()
                            mHolder = holder
                            return firstItemPosition
                        }
                        items[firstItemPosition] = holder as AutoPlayItem
                    }
//                }
                firstItemPosition++
            }
            //下面的逻辑是播放靠中间的视频
            var d = Int.MAX_VALUE
            var findHolder: AutoPlayItem? = null
            var position = -1
            //找出距离中间最近的一个
            for ((key, value) in items) {
                val d2 = getDistanceFromCenter(value.autoplayView)
                if (d2 < d) {
                    findHolder = value
                    d = d2
                    position = key
                }
            }
            if (mHolder !== findHolder) {
                if (mHolder != null) {
                    mHolder!!.deactivate()
                }
                mHolder = findHolder
            }
            if (mHolder != null) {
                mHolder!!.setActive()
                return position
            }
        }
        return -1
    }

    //当视频画出屏幕时停止播放
    fun onScrolledAndDeactivate(recyclerView: androidx.recyclerview.widget.RecyclerView?) {
        if (mHolder != null && mHolder!!.autoplayView != null && !getVisible(
                mHolder!!.autoplayView,
                visiblePercent
            )
        ) {
            mHolder!!.deactivate()
        }
    }

    /**
     * 用于停止滑出去的视频
     */
    fun onScrolledAndDeactivate() {
        if (mHolder != null && mHolder!!.autoplayView != null && !getVisible(
                mHolder!!.autoplayView,
                visiblePercent
            )
        ) {
            mHolder!!.deactivate()
        }
    }

    fun setVisiblePercent(visiblePercent: Int) {
        this.visiblePercent = visiblePercent
    }

    private fun getVisiblePercent(v: View): Int {
        val r = Rect()
        val visible: Boolean = v.getLocalVisibleRect(r)
        return if (visible && v.getMeasuredHeight() > 0) {
            100 * r.height() / v.getMeasuredHeight()
        } else -1
    }

    private fun getVisible(v: View, value: Int): Boolean {
        val r = Rect()
        val visible: Boolean = v.getLocalVisibleRect(r)
        return if (visible && v.getVisibility() === View.VISIBLE) {
            if (getVisiblePercent(v) >= value) {
                true
            } else {
                false
            }
        } else false
    }

    private fun getDistanceFromCenter(view: View): Int {
        val centerHeight = (DensityUtil().screenHeight / 2.3) as Int //中间线靠上一点，
        //项目代码原因，可以写getResources().getDisplayMetrics().heightPixels;
        val viewLocation = IntArray(2)
        view?.getLocationOnScreen(viewLocation)
        return Math.abs(viewLocation[1] + view!!.height / 2 - centerHeight)
    }

    companion object {
        var MODE_PLAY_FIRST = 0
        var MODE_PLAY_CENTER = 1
    }
}