package almostlover.com.viewcollection.views.sixway2dragview.drag1onLayout

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView
//通过rawX 来进行位移的
class Drag1OnLayoutRawXDSView(context: Context, attrs: AttributeSet) : ImageView(context, attrs) {


    private var offsetX: Float = 0.0f
    private var offsetY: Float = 0.0f
    private val TAG: String? = "Drag1OnLayoutView"
    private var starX: Float = 0.0f
    private var starY: Float = 0.0f


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.e(TAG,"onLayoutonLayoutonLayoutonLayout"+offsetX)

        if(offsetX!=0.toFloat()){
            this.layout(
                (left + offsetX).toInt(), (top + offsetY).toInt(), (right + offsetX).toInt(),
                (bottom + offsetY).toInt()
            )
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {


        Log.e(TAG,"dispatchTouchEventdispatchTouchEvent")

        var x = event!!.rawX
        var y = event.rawY



        when (event.action) {

            MotionEvent.ACTION_DOWN -> {
                starX = x
                starY = y
            }

            MotionEvent.ACTION_MOVE -> {
                 offsetX = x - starX
                 offsetY = y - starY

                    this.layout(
                        (left + offsetX).toInt(), (top + offsetY).toInt(), (right + offsetX).toInt(),
                        (bottom + offsetY).toInt()
                    )
                starX = x
                starY = y
            }
        }


        return false
    }
}