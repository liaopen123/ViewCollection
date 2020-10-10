package almostlover.com.viewcollection.views.layoutmanager

import android.animation.ValueAnimator
import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import android.util.SparseArray
import android.util.SparseBooleanArray
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import org.jetbrains.anko.AnkoLogger


class LPHLayoutManager : androidx.recyclerview.widget.RecyclerView.LayoutManager(),AnkoLogger{
    private var mStartX: Long =0L
    private var mStartY: Long =0L
    private var mDecoratedChildWidth: Int = 0
    private var mDecoratedChildHeight: Int = 0
    private var mSelectPosition: Int = 0
    private var mLastSelectPosition: Int = 0
    var mOffsetAll = 0

    /**滚动动画 */
    private var mAnimation: ValueAnimator? = null

    /**Item间隔与item宽的比例 */
    private val mIntervalRatio = 0.5f

    /**保存所有的Item的上下左右的偏移量信息 */
    private val mAllItemFrames: SparseArray<Rect> = SparseArray<Rect>()

    /**item 向右移动 */
    private val SCROLL_TO_RIGHT = 1

    /**item 向左移动 */
    private val SCROLL_TO_LEFT = 2

    /**RecyclerView的Item回收器 */
    private var mRecycle: Recycler? = null

    /**RecyclerView的状态器 */
    private var mState: androidx.recyclerview.widget.RecyclerView.State? = null
    /**记录Item是否出现过屏幕且还没有回收。true表示出现过屏幕上，并且还没被回收 */
    private val mHasAttachedItems = SparseBooleanArray()

    /**是否为平面滚动，Item之间没有叠加，也没有缩放 */
    private val mIsFlatFlow = false

    override fun generateDefaultLayoutParams(): androidx.recyclerview.widget.RecyclerView.LayoutParams {
        return androidx.recyclerview.widget.RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onLayoutChildren(recycler: Recycler, state: androidx.recyclerview.widget.RecyclerView.State) {
        //如果没有item，直接返回
        //跳过preLayout，preLayout主要用于支持动画
        if (itemCount <= 0 || state.isPreLayout) {
            mOffsetAll = 0
            return
        }
        mAllItemFrames.clear() //mAllItemFrame存储了所有Item的位置信息
        mHasAttachedItems.clear() //mHasAttachedItems存储了Item是否已经被添加到控件中

        //得到子view的宽和高，这里的item的宽高都是一样的，所以只需要进行一次测量
        val scrap = recycler.getViewForPosition(0)
        addView(scrap)
        measureChildWithMargins(scrap, 0, 0)
        //计算测量布局的宽高
        mDecoratedChildWidth = getDecoratedMeasuredWidth(scrap)
        mDecoratedChildHeight = getDecoratedMeasuredHeight(scrap)
        //计算第一个Item X轴的起始位置坐标,这里第一个Item居中显示
        mStartX = Math.round((getHorizontalWith() - mDecoratedChildWidth) * 1.0f / 2).toLong()
        //计算第一个Item Y轴的启始位置坐标，这里为控件竖直方向居中
        mStartY = Math.round((getVerticalHeight() - mDecoratedChildHeight) * 1.0f / 2).toLong()
        var offset = mStartX.toFloat() //item X轴方向的位置坐标
        for (i in 0 until itemCount) { //存储所有item具体位置
            var frame = mAllItemFrames[i]
            if (frame == null) {
                frame = Rect()
            }
            frame[Math.round(offset), mStartY.toInt(), Math.round(offset + mDecoratedChildWidth)] =
                (mStartY + mDecoratedChildHeight).toInt()
            mAllItemFrames.put(i, frame) //保存位置信息
            mHasAttachedItems.put(i, false)
            //计算Item X方向的位置，即上一个Item的X位置+Item的间距
            offset = offset + getIntervalDistance()
        }
        detachAndScrapAttachedViews(recycler)
        layoutItems(recycler, state, SCROLL_TO_RIGHT) //布局Item
        mRecycle = recycler //保存回收器
        mState = state //保存状态
    }



    fun getHorizontalWith(): Int {
        return width-paddingRight-paddingLeft
    }
    fun getVerticalHeight(): Int {
        return height-paddingTop-paddingBottom
    }

    /**
     * 获取Item间隔
     */
    private fun getIntervalDistance(): Float {
        return mDecoratedChildWidth * mIntervalRatio
    }


    private fun layoutItems(
        recycler: Recycler,
        state: androidx.recyclerview.widget.RecyclerView.State,
        scrollDirection: Int
    ) {

        if(state.isPreLayout){
            return
        }
        var displayFrame = Rect(mOffsetAll,0,mOffsetAll+getHorizontalWith(),getVerticalHeight())//获取当前显示区域
        //回收或者更新已经显示的item
        for (index in 0 until childCount){
            var child = getChildAt(index)
            val position = getPosition(child!!)

            if (!Rect.intersects(displayFrame,mAllItemFrames.get(position))) {
                //item 没有在显示区域 就说明要回收
                removeAndRecycleView(child,recycler)
                mHasAttachedItems.put(position,false)
            }else{
                //如果是在布局内的
                layoutItem(child,mAllItemFrames.get(index))
                mHasAttachedItems.put(position, true)
            }
        }
        for (i in 0 until itemCount){
            //在方框内  并且没有被回收
            if (Rect.intersects(displayFrame, mAllItemFrames[i]) &&
                !mHasAttachedItems[i]
            ) { //加载可见范围内，并且还没有显示的Item
                val scrap = recycler.getViewForPosition(i)
                measureChildWithMargins(scrap, 0, 0)
                if (scrollDirection == SCROLL_TO_LEFT || mIsFlatFlow) {
                    //向左滚动，新增的Item需要添加在最前面
                    addView(scrap, 0)
                } else { //向右滚动，新增的item要添加在最后面
                    addView(scrap)
                }
                layoutItem(scrap, mAllItemFrames[i]) //将这个Item布局出来
                mHasAttachedItems.put(i, true)
            }
        }


    }

    private fun layoutItem(child: View, frame: Rect) {
        layoutDecorated(child,frame.left-mOffsetAll,frame.top,frame.right-mOffsetAll,frame.bottom)
        child.setScaleX(computeScale(frame.left - mOffsetAll)); //缩放
        child.setScaleY(computeScale(frame.left - mOffsetAll)); //缩放
    }

    /**
     * 计算Item缩放系数
     * @param x Item的偏移量
     * @return 缩放系数
     */
    private fun computeScale(x: Int): Float {
        var scale =
            1 - Math.abs(x - mStartX) * 1.0f / Math.abs(mStartX + mDecoratedChildWidth / mIntervalRatio)
        if (scale < 0) scale = 0f
        if (scale > 1) scale = 1f
        return scale
    }


    override fun canScrollHorizontally(): Boolean {
        return true
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: Recycler?,
        state: androidx.recyclerview.widget.RecyclerView.State?
    ): Int {
        if (mAnimation!=null&&mAnimation?.isRunning!!){
            mAnimation!!.cancel()
        }
        if (mAnimation != null && mAnimation!!.isRunning) mAnimation!!.cancel()
        var travel = dx
        if (dx + mOffsetAll < 0) {
            travel = -mOffsetAll
        } else if (dx + mOffsetAll > getMaxOffset()) {
            travel = ((getMaxOffset() - mOffsetAll).toInt())
        }
        mOffsetAll += travel //累计偏移量

        layoutItems(recycler!!, state!!, if (dx > 0) SCROLL_TO_RIGHT else SCROLL_TO_LEFT)
        return travel


    }


    private fun startScroll(from: Int, to: Int) {
        if (mAnimation != null && mAnimation!!.isRunning()) {
            mAnimation!!.cancel()
        }
        val direction: Int = if (from < to) SCROLL_TO_RIGHT else SCROLL_TO_LEFT
        mAnimation = ValueAnimator.ofFloat(from.toFloat(), to.toFloat())
        mAnimation!!.setDuration(500)
        mAnimation!!.setInterpolator(DecelerateInterpolator())
        mAnimation!!.addUpdateListener(ValueAnimator.AnimatorUpdateListener { animation ->
            mOffsetAll = Math.round(animation.animatedValue as Float)
            layoutItems(mRecycle!!, mState!!, direction)
        })
    }

    /**
     * 获取最大偏移量
     */
    private fun getMaxOffset(): Float {
        return (itemCount - 1) * getIntervalDistance()
    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        when (state) {
            androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE ->             //滚动停止时
                fixOffsetWhenFinishScroll()
            androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING -> {
            }
            androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_SETTLING -> {
            }
        }
    }

    private fun fixOffsetWhenFinishScroll() {
        //计算滚动了多少个Item
        var scrollN = (mOffsetAll * 1.0f / getIntervalDistance()).toInt()
        //计算scrollN位置的Item超出控件中间位置的距离
        val moreDx = mOffsetAll % getIntervalDistance()
        if (moreDx > getIntervalDistance() * 0.5) { //如果大于半个Item间距，则下一个Item居中
            scrollN++
        }
        //计算最终的滚动距离
        val finalOffset = (scrollN * getIntervalDistance()).toInt()
        //启动居中显示动画
        startScroll(mOffsetAll, finalOffset)
        //计算当前居中的Item的位置
        mSelectPosition = Math.round(finalOffset * 1.0f / getIntervalDistance())
    }

    override fun scrollToPosition(position: Int) {
        if (position < 0 || position > itemCount - 1) return
        mOffsetAll = calculateOffsetForPosition(position)
        if (mRecycle == null || mState == null) {
            //如果RecyclerView还没初始化完，先记录下要滚动的位置
            mSelectPosition = position
        } else {
            layoutItems(
                mRecycle!!, mState!!,
                if (position > mSelectPosition) SCROLL_TO_RIGHT else SCROLL_TO_LEFT
            )
        }
    }

    override fun smoothScrollToPosition(
        recyclerView: androidx.recyclerview.widget.RecyclerView?,
        state: androidx.recyclerview.widget.RecyclerView.State?,
        position: Int
    ) {
        if (position < 0 || position > itemCount - 1) return
        val finalOffset: Int = calculateOffsetForPosition(position)
        if (mRecycle == null || mState == null) {
            //如果RecyclerView还没初始化完，先记录下要滚动的位置
            mSelectPosition = position
        } else {
            startScroll(mOffsetAll, finalOffset)
        }
    }


    override fun onAdapterChanged(
        oldAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>?,
        newAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>?
    ) {
        removeAllViews()
        mRecycle = null
        mState = null
        mOffsetAll = 0
        mSelectPosition = 0
        mLastSelectPosition = 0
        mHasAttachedItems.clear()
        mAllItemFrames.clear()
    }

    /**
     * 计算Item所在的位置偏移
     * @param position 要计算Item位置
     */
    private fun calculateOffsetForPosition(position: Int): Int {
        return Math.round(getIntervalDistance() * position)
    }

    /**
     * 获取中间位置
     *
     * Note:该方法主要用于[RecyclerCoverFlow.getChildDrawingOrder]判断中间位置
     *
     * 如果需要获取被选中的Item位置，调用[.getSelectedPos]
     */
    fun getCenterPosition(): Int {
        var pos = (mOffsetAll / getIntervalDistance()).toInt()
        val more = (mOffsetAll % getIntervalDistance()).toInt()
        if (Math.abs(more) >= getIntervalDistance() * 0.5f) {
            if (more >= 0) pos++ else pos--
        }
        return pos
    }
}


