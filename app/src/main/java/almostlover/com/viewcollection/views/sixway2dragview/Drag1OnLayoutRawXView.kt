package almostlover.com.viewcollection.views.sixway2dragview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView
//通过rawX 来进行位移的
class Drag1OnLayoutRawXView(context: Context, attrs: AttributeSet) : ImageView(context, attrs) {


    private val TAG: String? = "Drag1OnLayoutView"
    private var starX: Float = 0.0f
    private var starY: Float = 0.0f


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        var x = event!!.rawX
        var y = event.rawY



        when (event.action) {

            MotionEvent.ACTION_DOWN -> {
                starX = x
                starY = y
            }

            MotionEvent.ACTION_MOVE -> {
                val offsetX = x - starX
                val offsetY = y - starY

                    this.layout(
                        (left + offsetX).toInt(), (top + offsetY).toInt(), (right + offsetX).toInt(),
                        (bottom + offsetY).toInt()
                    )
                starX = x
                starY = y
            }
        }


        return true
    }
}