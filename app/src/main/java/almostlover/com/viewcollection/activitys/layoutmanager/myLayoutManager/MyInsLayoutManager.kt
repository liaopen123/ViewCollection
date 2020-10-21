package almostlover.com.viewcollection.activitys.layoutmanager.myLayoutManager

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.LinkedBlockingQueue

class MyInsLayoutManager(var span: Int = 3, var space: Int = 7) : RecyclerView.LayoutManager() {
    var verticalScrollOffset = 0  //最上面的偏移量
    var totalHeight = 0 //所有itemview加一起的总高度
//    var waitFillBlock: ArrayList<Rect> = ArrayList()  //专门用来填补空白的小块
    var waitFillBlock = LinkedBlockingQueue<Rect>() //改成了队列
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        //在布局之前，将所有的子View先Detach掉，放入到Scrap缓存中
        detachAndScrapAttachedViews(recycler)
        var offsetY = 0
        var offsetX = 0
        var colCount = 0
        for (i in 0 until itemCount) {
            var view = recycler.getViewForPosition(i)
            addView(view)
            measureChildWithMargins(view, 0, 0)
            var width = getDecoratedMeasuredWidth(view)
            var height = getDecoratedMeasuredHeight(view)
            //专门仿造ins的话
            //□
            //□
            //□□□
            //□□□

            //  □
            //  □
            //□□□
            //□□□

            if (i % 18 === 1) {
                //就填补大的 然后看看有没有空缺的位置
                //位置固定  只用补左下角的位置

                layoutDecorated(view, offsetX, offsetY, offsetX + width * 2, offsetY + height * 2)
                var rect: Rect = Rect()
                rect.set(0, offsetY + height,  width, offsetY + height + height)
                waitFillBlock.put(rect)
                //
                offsetX = 0
                offsetY += height * 2
                totalHeight += height*2
                colCount = 0
            } else if (i % 18 === 9) {
                //就填补大的 然后看看有没有空缺的位置
                //位置固定  需要补右侧 上下排布的2个各种的位置

                layoutDecorated(view, offsetX, offsetY, offsetX + width * 2, offsetY + height * 2)
                var rect1: Rect = Rect()  //右上
                rect1.set(offsetX + width * 2, offsetY, offsetX + width*3, offsetY + height)
                var rect2: Rect = Rect()  //右下
                rect2.set(
                    offsetX + width * 2,
                    offsetY + height,
                    offsetX + width*3,
                    offsetY + height + height
                )
                waitFillBlock.add(rect1)
                waitFillBlock.add(rect2)
                //
                offsetX = 0
                offsetY += height * 2
                totalHeight += height*2
                colCount = 0

            } else {
                //如果有空位先填补空位   并且另起一行继续
                var rect = waitFillBlock.poll()
                if ( rect!=null) {
                    layoutDecorated(view, rect.left, rect.top, rect.right, rect.bottom)
                }else{
                    //最后，将View布局
                    layoutDecorated(view, offsetX, offsetY, offsetX + width, offsetY + height)
                    if (colCount == 2) {
                        //另起一行 重新归零
                        colCount = 0
                        offsetX = 0
                        offsetY += height
                        totalHeight += height
                    } else {
                        colCount++
                        offsetX += width
                    }
                }



            }
//            //最后，将View布局
//            layoutDecorated(view,offsetX,offsetY,offsetX+width,offsetY+height)
//            if (colCount==2) {
//                //另起一行 重新归零
//                colCount=0
//                offsetX=0
//                offsetY+=height
//                totalHeight +=height
//            }else{
//                colCount++
//                offsetX+=width
//            }

        }
        //
        totalHeight = Math.max(totalHeight, getVerticalSpace())//如果rv的高度没有填满rv的高度 则将高度设置为rv的高度

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



        if (verticalScrollOffset + dy < 0) {
            travel = -verticalScrollOffset
        } else if (verticalScrollOffset + dy > totalHeight - getVerticalSpace()) {
            travel = totalHeight - getVerticalSpace() - verticalScrollOffset
        }



        verticalScrollOffset += travel

        offsetChildrenVertical(-travel)

        return travel
    }


    private fun getVerticalSpace(): Int {
        return height - paddingBottom - paddingTop
    }

}