package almostlover.com.viewcollection.views.sixway2dragview.drag2offsetapi

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView

//添加边界判断 和 onclick方法
class Drag2OffsetLeftAndRightOffsetTopAndBottomUpdateView : ImageView {


    private var parentWidth: Int = 0
    private var parentHeight: Int = 0
    private val TAG: String? = "Drag1OnLayoutXView"
    private var lastX: Int = 0
    private var lastY: Int = 0


    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context) : super(context) {}


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        val viewGroup = parent as ViewGroup
         parentWidth = viewGroup.width
        parentHeight = viewGroup.height
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {


        //获取到手指处的横坐标和纵坐标
        val x = event.x.toInt()  //
        val y = event.y.toInt()
        Log.e(TAG, "event.x${event.x},event.Y${event.y}")

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

                lastX = x
                lastY = y
            }

            MotionEvent.ACTION_MOVE -> {

                //计算移动的距离
                val offX = x - lastX
                val offY = y - lastY
                //边界判断条件:如果view的getLeft 已经贴边，并且还有往下发展的趋势，就return  使用前提是offsetLeftAndRight和offsetTopAndBottom 会改变getLeft getRight 等方法的值
                if (left <= 0 && offX < 0 || top <= 0 && offY < 0 || right >parentWidth&&offX>0||bottom>parentHeight&&offY>0) {
                    return true
                }


                offsetLeftAndRight(offX)
                offsetTopAndBottom(offY)

            }

            MotionEvent.ACTION_UP ->{

            }


        }

        return true
    }
}