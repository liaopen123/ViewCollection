package almostlover.com.viewcollection.activitys.layoutmanager

import androidx.recyclerview.widget.RecyclerView
import android.view.View

class CardLayoutManager : androidx.recyclerview.widget.RecyclerView.LayoutManager(){
    override fun generateDefaultLayoutParams(): androidx.recyclerview.widget.RecyclerView.LayoutParams {
        return  androidx.recyclerview.widget.RecyclerView.LayoutParams(
            androidx.recyclerview.widget.RecyclerView.LayoutParams.WRAP_CONTENT,
            androidx.recyclerview.widget.RecyclerView.LayoutParams.WRAP_CONTENT)
    }

    var offsetX = 0
    var mLeftX = 0

    private val maxCache = 3 //最大的缓存数量

    private val mLeftIndex = 0 //缓存最左边的卡片的下标

    private val mRightIndex = 0 //缓存最右边的卡片的下标

    private val mCenterIndex = 0 //中间的卡片的下标
    
    private val mOffsetX = 0 //水平位移

    private val mItemWidth = 1 //单个卡片宽度



    override fun onLayoutChildren(recycler: androidx.recyclerview.widget.RecyclerView.Recycler, state: androidx.recyclerview.widget.RecyclerView.State) {
        if(itemCount==0){
            //没有数据就不处理
            removeAndRecycleAllViews(recycler)
            return
        }
         detachAndScrapAttachedViews(recycler)

        offsetX = 0
        for (i in 0 until  itemCount) {
            var view = recycler.getViewForPosition(i)
            addView(view)
            measureAndLayout(view,i)
        }

    }

    private fun measureAndLayout(view: View, i: Int) {
        measureChildWithMargins(view,0,0)

        var width = getDecoratedMeasuredWidth(view)
        var height  =  getDecoratedMeasuredHeight(view)

        layoutDecoratedWithMargins(view,offsetX,0,offsetX+width,height)
        offsetX +=width
    }

    override fun canScrollHorizontally(): Boolean {
        return true
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: androidx.recyclerview.widget.RecyclerView.Recycler?,
        state: androidx.recyclerview.widget.RecyclerView.State?
    ): Int {
        offsetChildrenHorizontal(-dx)//移动元素
        offsetX -=dx
        return  -dx
    }

}