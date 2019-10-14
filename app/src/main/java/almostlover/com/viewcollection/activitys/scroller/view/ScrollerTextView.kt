package almostlover.com.viewcollection.activitys.scroller.view

import almostlover.com.viewcollection.utils.lphLog
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.TextView


class ScrollerTextView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    style: Int = 0
) : TextView(context, attributeSet) {

    private var mScreenHeight: Int

init {
    mScreenHeight  = context.resources.displayMetrics.heightPixels
}
    private var mLastY: Int = 0

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val y = event?.getY()?.toInt()
        val action = event?.getAction()
        when (action) {
            MotionEvent.ACTION_DOWN -> {mLastY = y!!
            lphLog("scrollY:$scrollY")
            }
            MotionEvent.ACTION_MOVE -> {
                val y1 = event.y
                val dy = y1-mLastY
                val finalY = (scrollY + dy).toInt()
                lphLog("这个是偏移量:dy$dy,最终scrollY = $finalY")
                scrollTo(100, -177)
                mLastY = y!!
            }
        }
        return true
    }


}