package almostlover.com.viewcollection.activitys.layoutmanager.myLayoutManager

import androidx.recyclerview.widget.RecyclerView

class MyLinearLayoutManager() :RecyclerView.LayoutManager(){
    var verticalScrollOffset = 0  //最上面的偏移量
    var totalHeight = 0 //所有itemview加一起的总高度
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        //在布局之前，将所有的子View先Detach掉，放入到Scrap缓存中
        detachAndScrapAttachedViews(recycler)
        var offsetY=0
        for (i in 0 until itemCount) {
            var view  = recycler.getViewForPosition(i)
            addView(view)
            measureChildWithMargins(view,0,0)
            var width = getDecoratedMeasuredWidth(view)
            var height = getDecoratedMeasuredHeight(view)
            //最后，将View布局
            layoutDecorated(view, 0, offsetY, width, offsetY + height);
            //将竖直方向偏移量增大height
            offsetY += height
            totalHeight +=height
        }
        //
        totalHeight = Math.max(totalHeight,getVerticalSpace())//如果rv的高度没有填满rv的高度 则将高度设置为rv的高度

    }


    override fun canScrollVertically(): Boolean {
        return true
    }

    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        var travel = dy



        if (verticalScrollOffset+dy<0) {
            travel=-verticalScrollOffset
        }else if (verticalScrollOffset+dy>totalHeight-getVerticalSpace()) {
            travel= totalHeight-getVerticalSpace()-verticalScrollOffset
        }



        verticalScrollOffset+=travel

        offsetChildrenVertical(-travel)

        return travel
    }


    private fun getVerticalSpace(): Int {
        return height - paddingBottom - paddingTop
    }

}