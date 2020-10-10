package almostlover.com.viewcollection.activitys.nestedscrolling.nestedscorlling01.nestedview

import almostlover.com.viewcollection.utils.lphLog
import android.content.Context
import androidx.core.view.NestedScrollingParent2
import androidx.core.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout

class NestedLayout @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? =null,
    style:Int = 0):RelativeLayout(context,attributeSet),NestedScrollingParent2{

    private var image_head: View? = null
    private var recyclerview: View? = null
    var top_height = 0


    override fun onFinishInflate() {
        super.onFinishInflate()
         image_head = getChildAt(0)
         recyclerview = getChildAt(1)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        top_height = image_head!!.measuredHeight
    }

    override fun onStartNestedScroll(p0: View, p1: View, p2: Int, p3: Int): Boolean {
        return true //表示父类容器接受嵌套滚动  如果为false 其他方法则不会被调用
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        lphLog("onNestedPreScrollonNestedPreScrollonNestedPreScroll")
        val hiddenTop=dy>0&&scrollY<top_height
        val showTop = dy<0&& scrollY>=0&&!ViewCompat.canScrollVertically(target,-1)
        if (hiddenTop || showTop) {
            scrollBy(0,dy)
            consumed[1]= dy
        }

    }

    override fun onStopNestedScroll(p0: View, p1: Int) {
    }

    override fun onNestedScrollAccepted(p0: View, p1: View, p2: Int, p3: Int) {
    }

    override fun onNestedScroll(p0: View, p1: Int, p2: Int, p3: Int, p4: Int, p5: Int) {
    }


    override fun scrollTo(x: Int, y: Int) {
        var y = y

        if (y<0) {
            y=0
        }
        if (y>top_height) {
            y=top_height
        }
        if (y!=scrollY) {
            super.scrollTo(x, y)
        }

    }


}
