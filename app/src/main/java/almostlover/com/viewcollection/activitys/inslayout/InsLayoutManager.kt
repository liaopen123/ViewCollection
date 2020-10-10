package almostlover.com.viewcollection.activitys.inslayout

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class InsLayoutManager : RecyclerView.LayoutManager(),AnkoLogger{
    private var mVerticalOffset=0 //竖直偏移量 每次换行都要更具这个offset判断
    private var mFirstVisiblePosition=0  //屏幕可见的第一个view的position
    private var mLastVisiblePosition=0  //屏幕可见的最后的一个view的position


    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return  RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }


    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        //没有数据就不进行处理
        if (itemCount==0){
            detachAndScrapAttachedViews(recycler)
            return
        }
        //state.isPreLayout()是支持动画的
        if (childCount==0&&state.isPreLayout) {
            return
        }
        //onLayoutChildren方法在RecyclerView 初始化时 会执行两遍
        detachAndScrapAttachedViews(recycler);

        mLastVisiblePosition = itemCount;
        //进行布局
        fill(recycler,state)
    }

    /**
     * 1.回收所有不可见的子view
     * 2.layout所有可见的子view
     */
    private fun fill(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        var topOffset =  paddingTop  //布局时候的上偏移
        var leftOffset =  paddingLeft  //布局时候的左偏移
        var lineMaxHeight = 0  //每一行最大的高度
        var minPos = mFirstVisiblePosition  //初始化的时候，不知道要layout多少个view  假设从0~itemcount-1
        mLastVisiblePosition = itemCount-1

        for ( i in minPos until mLastVisiblePosition) {
            var child = recycler.getViewForPosition(i)
            addView(child)
            measureChildWithMargins(child,0,0)
            //计算宽度  包括margin  如果左偏移量+child的宽度< 总宽度的话  就可以layout了
            if (leftOffset + getDecoratedMeasurementHorizontal(child)<=getHorizontalSpace()){
                layoutDecoratedWithMargins(child, leftOffset, topOffset, leftOffset + getDecoratedMeasurementHorizontal(child), topOffset + getDecoratedMeasurementVertical(child))
                //layout完成以后 修改leftOffset和lineHeight
                var viewWidth = getDecoratedMeasurementHorizontal(child)
                leftOffset += viewWidth
                info { "viewWidth::$viewWidth,,,leftOffset:::$leftOffset" }
                //每一行最大高度
                lineMaxHeight = Math.max(lineMaxHeight, getDecoratedMeasurementVertical(child));

            }else{
                //如果放不下的话
                //改变top  left  lineHeight
                leftOffset = paddingLeft
                topOffset += lineMaxHeight
                lineMaxHeight = 0

                //另起一行的时候 得判断一下边界
//                if (topOffset-dy>height-paddingBottom) {
                if (topOffset>height-paddingBottom) {
                    //越界了 就回收
                    removeAndRecycleView(child,recycler)
                    mLastVisiblePosition = i-1  //得到了最后item的position
                }else{
                    //没有越界
                    layoutDecoratedWithMargins(child, leftOffset, topOffset, leftOffset + getDecoratedMeasurementHorizontal(child), topOffset + getDecoratedMeasurementVertical(child));
                    //改变 left  lineHeight
                    leftOffset += getDecoratedMeasurementHorizontal(child);
                    lineMaxHeight = Math.max(lineMaxHeight, getDecoratedMeasurementVertical(child));

                }
            }
        }

    }


    /**
     * 获取某个childView在水平方向所占的空间
     *
     * @param view
     * @return
     */
    fun getDecoratedMeasurementHorizontal(view: View): Int {
        val params = view.getLayoutParams() as RecyclerView.LayoutParams
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
        val params = view.getLayoutParams() as RecyclerView.LayoutParams
        return (getDecoratedMeasuredHeight(view) + params.topMargin
                + params.bottomMargin)
    }

    fun getVerticalSpace(): Int {
        return height - paddingTop - paddingBottom
    }

    fun getHorizontalSpace(): Int {
        return width - paddingLeft - paddingRight
    }

}