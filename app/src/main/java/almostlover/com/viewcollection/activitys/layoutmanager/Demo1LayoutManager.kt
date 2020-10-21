package almostlover.com.viewcollection.activitys.layoutmanager

import android.graphics.Rect
import android.util.Log
import android.util.SparseArray
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class Demo1LayoutManager : RecyclerView.LayoutManager(),AnkoLogger{

    private var mVerticalOffset = 0//竖直偏移量 每次换行时，要根据这个offset判断
    private var mFirstVisiPos = 0 //屏幕可见的第一个View的Position
    private var mLastVisiPos = 0//屏幕可见的最后一个View的Position


        override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
            return  RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        info { "onLayoutChildren start" }
        if (itemCount==0) {
            detachAndScrapAttachedViews(recycler)
            return
        }

        if (childCount==0&&state.isPreLayout) {
            return
        }

        detachAndScrapAttachedViews(recycler)    //onLayoutChildren方法在RecyclerView 初始化时 会执行两遍

        mLastVisiPos = itemCount

        fill(recycler, state)


    }

    private fun fill(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        var topOffset = paddingTop
        var leftOffset  =paddingLeft
        var lineMaxHeight = 0
        val minPos = mFirstVisiPos
        mLastVisiPos = itemCount-1

        for (i in 0 until mLastVisiPos) {
            var view = recycler.getViewForPosition(i)
            addView(view)
            measureChildWithMargins(view, 0, 0)

            //计算宽高 是否合适
            if (leftOffset + getDecoratedMeasurementHorizontal(view) < getHorizontalSpace()) {
                //说明水平方向还能装
                layoutDecoratedWithMargins(
                    view,
                    leftOffset,
                    topOffset,
                    leftOffset + getDecoratedMeasurementHorizontal(view),
                    topOffset + getDecoratedMeasurementVertical(view)
                )
                leftOffset += getDecoratedMeasurementHorizontal(view)
                lineMaxHeight = Math.max(lineMaxHeight, getDecoratedMeasurementVertical(view))

            } else {
                //说明装不下了
                leftOffset = paddingLeft  //重新另起一行
                topOffset +=lineMaxHeight
                lineMaxHeight = 0

                //另起一行 判断是否超过了边界
                if (topOffset>height-paddingBottom) {
                    removeAndRecycleView(view, recycler)
                    mLastVisiPos=i-1
                }else{
                    //没有超过边界 则继续
                    layoutDecoratedWithMargins(
                        view,
                        leftOffset,
                        topOffset,
                        leftOffset + getDecoratedMeasurementHorizontal(view),
                        topOffset + getDecoratedMeasurementVertical(view)
                    )
                    leftOffset += getDecoratedMeasurementHorizontal(view)
                    lineMaxHeight = Math.max(lineMaxHeight, getDecoratedMeasurementVertical(view))
                }
            }
        }
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
        return (getDecoratedMeasuredWidth(view) + params.leftMargin
                + params.rightMargin)
    }

    /**
     * 获取某个childView在竖直方向所占的空间
     *
     * @param view
     * @return
     */
    fun getDecoratedMeasurementVertical(view: View): Int {
        val params = view.layoutParams as RecyclerView.LayoutParams
        return (getDecoratedMeasuredHeight(view) + params.topMargin
                + params.bottomMargin)
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

    /**
     * dy:滑动的偏移量
     *
     */
    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State
    ): Int {


        info { "Demo1LayoutManager dy:$dy,,childCount:${childCount},,mVerticalOffset:$mVerticalOffset" }
        if (dy==0||childCount==0) {
            return 0
        }
        var realOffset = dy

        if (mVerticalOffset+realOffset<0) {
            //说明到了上边界
            realOffset = -mVerticalOffset
        }else if (realOffset>0) {
            //说明下拉
            var lastChild = getChildAt(childCount - 1)
            if (getPosition(lastChild!!)==itemCount-1) {
              var gap =   height-paddingBottom-getDecoratedBottom(lastChild)
                if (gap>0) {
                    realOffset=-gap
                }else if (gap==0){
                    realOffset=0
                }else{
                    realOffset= Math.min(realOffset, -gap)
                }
            }
        }
        realOffset = fill(recycler, state, realOffset)
        mVerticalOffset += realOffset//累加实际滑动距离
        offsetChildrenVertical(-realOffset)  //这句话移动所有的childView.
        return realOffset
    }


    private val mItemRects //key 是View的position，保存View的bounds ，
            : SparseArray<Rect> = SparseArray<Rect>()

    /**
     * 填充childView的核心方法,应该先填充，再移动。
     * 在填充时，预先计算dy的在内，如果View越界，回收掉。
     * 一般情况是返回dy，如果出现View数量不足，则返回修正后的dy.
     *
     * @param recycler
     * @param state
     * @param dy       RecyclerView给我们的位移量,+,显示底端， -，显示头部
     * @return 修正以后真正的dy（可能剩余空间不够移动那么多了 所以return <|dy|）
     */
    private fun fill(recycler: Recycler, state: RecyclerView.State, dy: Int): Int {
        var dy = dy
        var topOffset = paddingTop
        //回收越界子View
        if (childCount > 0) { //滑动时进来的
            for (i in childCount - 1 downTo 0) {
                val child = getChildAt(i)
                if (dy > 0) { //需要回收当前屏幕，上越界的View
                    if (getDecoratedBottom(child!!) - dy < topOffset) {
                        removeAndRecycleView(child, recycler)
                        mFirstVisiPos++
                        continue
                    }
                } else if (dy < 0) { //回收当前屏幕，下越界的View
                    if (getDecoratedTop(child!!) - dy > height - paddingBottom) {
                        removeAndRecycleView(child, recycler)
                        mLastVisiPos--
                        continue
                    }
                }
            }
            //detachAndScrapAttachedViews(recycler);
        }
        var leftOffset = paddingLeft
        var lineMaxHeight = 0
        //布局子View阶段
        if (dy >= 0) {
            var minPos = mFirstVisiPos
            mLastVisiPos = itemCount - 1
            if (childCount > 0) {
                val lastView = getChildAt(childCount - 1)
                minPos = getPosition(lastView!!) + 1 //从最后一个View+1开始吧
                topOffset = getDecoratedTop(lastView)
                leftOffset = getDecoratedRight(lastView)
                lineMaxHeight = Math.max(
                    lineMaxHeight, getDecoratedMeasurementVertical(
                        lastView
                    )
                )
            }
            //顺序addChildView
            for (i in minPos..mLastVisiPos) {
                //找recycler要一个childItemView,我们不管它是从scrap里取，还是从RecyclerViewPool里取，亦或是onCreateViewHolder里拿。
                val child = recycler.getViewForPosition(i)
                addView(child)
                measureChildWithMargins(child, 0, 0)
                //计算宽度 包括margin
                if (leftOffset + getDecoratedMeasurementHorizontal(child) <= getHorizontalSpace()) { //当前行还排列的下
                    layoutDecoratedWithMargins(
                        child,
                        leftOffset,
                        topOffset,
                        leftOffset + getDecoratedMeasurementHorizontal(child),
                        topOffset + getDecoratedMeasurementVertical(child)
                    )

                    //保存Rect供逆序layout用
                    val rect = Rect(
                        leftOffset,
                        topOffset + mVerticalOffset,
                        leftOffset + getDecoratedMeasurementHorizontal(child),
                        topOffset + getDecoratedMeasurementVertical(child) + mVerticalOffset
                    )
                    mItemRects!!.put(i, rect)

                    //改变 left  lineHeight
                    leftOffset += getDecoratedMeasurementHorizontal(child)
                    lineMaxHeight = Math.max(lineMaxHeight, getDecoratedMeasurementVertical(child))
                } else { //当前行排列不下
                    //改变top  left  lineHeight
                    leftOffset = paddingLeft
                    topOffset += lineMaxHeight
                    lineMaxHeight = 0

                    //新起一行的时候要判断一下边界
                    if (topOffset - dy > height - paddingBottom) {
                        //越界了 就回收
                        removeAndRecycleView(child, recycler)
                        mLastVisiPos = i - 1
                    } else {
                        layoutDecoratedWithMargins(
                            child,
                            leftOffset,
                            topOffset,
                            leftOffset + getDecoratedMeasurementHorizontal(child),
                            topOffset + getDecoratedMeasurementVertical(child)
                        )

                        //保存Rect供逆序layout用
                        val rect = Rect(
                            leftOffset,
                            topOffset + mVerticalOffset,
                            leftOffset + getDecoratedMeasurementHorizontal(child),
                            topOffset + getDecoratedMeasurementVertical(child) + mVerticalOffset
                        )
                        mItemRects!!.put(i, rect)

                        //改变 left  lineHeight
                        leftOffset += getDecoratedMeasurementHorizontal(child)
                        lineMaxHeight =
                            Math.max(lineMaxHeight, getDecoratedMeasurementVertical(child))
                    }
                }
            }
            //添加完后，判断是否已经没有更多的ItemView，并且此时屏幕仍有空白，则需要修正dy
            val lastChild = getChildAt(childCount - 1)
            if (getPosition(lastChild!!) == itemCount - 1) {
                val gap = height - paddingBottom - getDecoratedBottom(lastChild)
                if (gap > 0) {
                    dy -= gap
                }
            }
        } else {
            /**
             * ##  利用Rect保存子View边界
             * 正序排列时，保存每个子View的Rect，逆序时，直接拿出来layout。
             */
            var maxPos = itemCount - 1
            mFirstVisiPos = 0
            if (childCount > 0) {
                val firstView = getChildAt(0)
                maxPos = getPosition(firstView!!) - 1
            }
            for (i in maxPos downTo mFirstVisiPos) {
                val rect: Rect = mItemRects!![i]
                if (rect.bottom - mVerticalOffset - dy < paddingTop) {
                    mFirstVisiPos = i + 1
                    break
                } else {
                    val child = recycler.getViewForPosition(i)
                    addView(child, 0) //将View添加至RecyclerView中，childIndex为1，但是View的位置还是由layout的位置决定
                    measureChildWithMargins(child, 0, 0)
                    layoutDecoratedWithMargins(
                        child,
                        rect.left,
                        rect.top - mVerticalOffset,
                        rect.right,
                        rect.bottom - mVerticalOffset
                    )
                }
            }
        }
        Log.d(
            "TAG",
            "count= [" + childCount + "]" + ",[recycler.getScrapList().size():" + recycler.scrapList.size + ", dy:" + dy + ",  mVerticalOffset" + mVerticalOffset + ", "
        )
        return dy
    }
}