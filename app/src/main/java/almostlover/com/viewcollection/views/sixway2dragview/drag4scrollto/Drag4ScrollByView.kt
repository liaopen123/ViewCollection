package almostlover.com.viewcollection.views.sixway2dragview.drag4scrollto

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView


class Drag4ScrollByView : ImageView {



    private val TAG: String?  ="Drag1OnLayoutXView"
    private var lastX: Int = 0
    private var lastY: Int = 0


    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context) : super(context) {}


    override fun onTouchEvent(event: MotionEvent): Boolean {


        //获取到手指处的横坐标和纵坐标
        val x = event.x.toInt()  //
        val y = event.y.toInt()

        Log.e(TAG,"event.x${event.x},event.Y${event.y}")

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

                lastX = x
                lastY = y
            }

            MotionEvent.ACTION_MOVE -> {

                //计算移动的距离
                val offX = x - lastX
                val offY = y - lastY
                (parent as View).scrollBy(-offX, -offY)
            }
        }

        return true
    }
}