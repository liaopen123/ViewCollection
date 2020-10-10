package almostlover.com.viewcollection.views.layoutmanager

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import android.util.SparseArray
import android.util.SparseBooleanArray
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoLogger
import kotlin.math.roundToInt


class LPHLayoutManager1 : androidx.recyclerview.widget.RecyclerView.LayoutManager(),AnkoLogger{
    private var mStartX: Long =0L
    private var mStartY: Long =0L
    private var mDecoratedChildWidth: Int = 0
    private var mDecoratedChildHeight: Int = 0
    var mOffsetAll = 0

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
        if(itemCount<0 || state.isPreLayout){
            mOffsetAll = 0
            return
        }
        mAllItemFrames.clear(); //mAllItemFrame存储了所有Item的位置信息
        mHasAttachedItems.clear(); //mHasAttachedItems存储了Item是否已经被添加到控件中

      var scrap =   recycler.getViewForPosition(0)
        addView(scrap)
         measureChildWithMargins(scrap,0,0)

        mDecoratedChildWidth = getDecoratedMeasuredWidth(scrap) //计算测量布局的宽高
        mDecoratedChildHeight = getDecoratedMeasuredHeight(scrap) //计算测量布局的宽高

        //计算第一个item X轴值 和Y轴值
        mStartX = Math.round(0.5*(getHorizontalWith()-mDecoratedChildWidth))
        mStartY = Math.round(0.5*(getVerticalHeight()-mDecoratedChildHeight))

        var offset:Float =  mStartX.toFloat()

        //储存所有的item具体位置
        for(index in 0 until itemCount){
            var  frame = mAllItemFrames[index]
            if (frame==null){
                frame = Rect()
            }
             frame.set(
                 offset.toDouble().roundToInt(),
                 mStartY.toInt(),
                 (offset.toDouble() + mDecoratedChildWidth).roundToInt(),
                 mStartY.toInt()+mDecoratedChildHeight)
            mAllItemFrames.put(index,frame)
            mHasAttachedItems.put(index,false)
            offset += getIntervalDistance()

        }
        detachAndScrapAttachedViews(recycler)

        layoutItems(recycler,state,SCROLL_TO_RIGHT)
        mRecycle = recycler; //保存回收器
        mState = state; //保存状态
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
        state: androidx.recyclerview.widget.RecyclerView.State, scrollDirection: Int
    ) {
        if (state.isPreLayout) return
        val displayFrame = Rect(
            mOffsetAll,
            0,
            mOffsetAll + getHorizontalWith(),
            getVerticalHeight()
        ) //获取当前显示的区域

        //回收或者更新已经显示的Item
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val position = getPosition(child!!)
            if (!Rect.intersects(displayFrame, mAllItemFrames[position])) {
                //Item没有在显示区域，就说明需要回收
                removeAndRecycleView(child, recycler) //回收滑出屏幕的View
                mHasAttachedItems.put(position, false)
            } else { //Item还在显示区域内，更新滑动后Item的位置
                layoutItem(child, mAllItemFrames[position]) //更新Item位置
                mHasAttachedItems.put(position, true)
            }
        }
        for (i in 0 until itemCount) {
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
    }

}


