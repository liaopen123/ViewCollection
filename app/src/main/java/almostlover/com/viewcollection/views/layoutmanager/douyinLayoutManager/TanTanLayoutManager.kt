package almostlover.com.viewcollection.views.layoutmanager.douyinLayoutManager

import android.content.Context
import android.graphics.Rect
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.DisplayMetrics
import android.util.Log
import android.util.SparseArray
import android.util.SparseBooleanArray
import android.view.View

class TanTanLayoutManager(context: Context) :
    androidx.recyclerview.widget.RecyclerView.LayoutManager() {
    var TAG ="DouYinLayoutManager"
    var totalOffset = 0
    var mDecoratedChildWidth = 0
    var mDecoratedChildHeight = 0
    var mStartX = 0
    var mStartY = 0
    var mEndX = 0
    /**是否为平面滚动，Item之间没有叠加，也没有缩放 */
    private val mIsFlatFlow = false

    /**item 向右移动 */
    private val SCROLL_TO_RIGHT = 1

    /**item 向左移动 */
    private val SCROLL_TO_LEFT = 2
    /**保存所有的Item的上下左右的偏移量信息 */
    private val mAllItemFrames: SparseArray<Rect> = SparseArray<Rect>()
    /**记录Item是否出现过屏幕且还没有回收。true表示出现过屏幕上，并且还没被回收 */
    private val mHasAttachedItems = SparseBooleanArray()
    override fun generateDefaultLayoutParams(): androidx.recyclerview.widget.RecyclerView.LayoutParams {
       return  androidx.recyclerview.widget.RecyclerView.LayoutParams(
           androidx.recyclerview.widget.RecyclerView.LayoutParams.WRAP_CONTENT,
           androidx.recyclerview.widget.RecyclerView.LayoutParams.WRAP_CONTENT)
    }

    override fun onLayoutChildren(recycler: androidx.recyclerview.widget.RecyclerView.Recycler, state: androidx.recyclerview.widget.RecyclerView.State) {
        if (itemCount<=0||state.isPreLayout){
            return
        }
        var scrap  = recycler.getViewForPosition(0)
        addView(scrap)
        measureChildWithMargins(scrap,0,0)

        mDecoratedChildWidth  = getDecoratedMeasuredWidth(scrap)
        mDecoratedChildHeight  = getDecoratedMeasuredHeight(scrap)

        mStartX = width/10
        mEndX = width/10*9
        mStartY = height/10

        for (i in 0 until itemCount) { //存储所有item具体位置
            var frame = mAllItemFrames[i]
            if (frame == null) {
                frame = Rect()
            }
            frame.set(mStartX, height/4-3*i, mEndX,   height/3)
            //保存位置信息
            mAllItemFrames.put(i, frame) //保存位置信息
            mHasAttachedItems.put(i, false)
        }

        detachAndScrapAttachedViews(recycler)
        layoutItems(recycler, state, SCROLL_TO_RIGHT) //布局Item
    }


    private fun layoutItems(
        recycler: androidx.recyclerview.widget.RecyclerView.Recycler,
        state: androidx.recyclerview.widget.RecyclerView.State,
        scrollDirection: Int
    ) {

        if(state.isPreLayout){
            return
        }
        var displayFrame = Rect(mStartX,0,mEndX,getVerticalHeight())//获取当前显示区域
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
        layoutDecorated(child,frame.left,frame.top,frame.right,frame.bottom)

    }
    fun getHorizontalWith(): Int {
        return width-paddingRight-paddingLeft
    }
    fun getVerticalHeight(): Int {
        return height-paddingTop-paddingBottom
    }


}