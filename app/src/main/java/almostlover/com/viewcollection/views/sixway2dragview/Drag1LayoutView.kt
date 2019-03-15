package almostlover.com.viewcollection.views.sixway2dragview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView

class Drag1LayoutView(context: Context, attrs: AttributeSet) : ImageView(context, attrs) {


    private var starX: Float = 0.0f
    private var starY: Float = 0.0f

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        var x = event!!.rawX
        var y = event!!.rawY

        when (event!!.action) {

            MotionEvent.ACTION_DOWN -> {
                starX = x
                starY = y
            }

            MotionEvent.ACTION_MOVE -> {
                val offsetX = x - starX
                val offsetY = y - starY
                val leftside = left + offsetX
                val rightSide = right + offsetX
                val topSide = top + offsetY
                val bottomSide = bottom + offsetY
                if (leftside < 0||topSide<0) {

                } else {
                    this.layout(
                        (left + offsetX).toInt(), (top + offsetY).toInt(), (right + offsetX).toInt(),
                        (bottom + offsetY).toInt()
                    )
                }
                starX = x
                starY = y
            }
        }


        return true
    }
}