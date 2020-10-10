package almostlover.com.viewcollection.views.rv

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView


class VerticalRecyclerView   @JvmOverloads
constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attributeSet, defStyleAttr){
    private var mOffsetX = 0f
    private  var mOffsetY:kotlin.Float = 0f
    private var mLastPosX = 0f
    private  var mLastPosY:kotlin.Float = 0f
    val TAG= "VerticalRecyclerView"

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        var result = false
        when (ev.getAction()) {
            MotionEvent.ACTION_DOWN -> {
                mOffsetX = 0.0f
                mOffsetY = 0.0f
                mLastPosX = ev.getX()
                mLastPosY = ev.getY()
                result = super.onInterceptTouchEvent(ev) //false手势传递给子控件
            }
            else -> {
                val thisPosX: Float = ev.getX()
                val thisPosY: Float = ev.getY()
                Log.i(TAG, "onInterceptTouchEvent: thisposx,y：($thisPosX,$thisPosY）")
                Log.i(TAG, "onInterceptTouchEvent: 初始mOffsetX，Y：($mOffsetX,$mOffsetY）")
                mOffsetX += Math.abs(thisPosX - mLastPosX) //x偏移
                mOffsetY += Math.abs(thisPosY - mLastPosY) //y轴偏移
                Log.i(TAG, "onInterceptTouchEvent: 偏移后 mOffsetX，Y：($mOffsetX,$mOffsetY）")
                Log.i(TAG, "onInterceptTouchEvent: 初始mLastPosX，Y：($mLastPosX,$mLastPosY）")
                mLastPosY = thisPosY
                mLastPosX = thisPosX
                Log.i(TAG, "onInterceptTouchEvent: 之后mLastPosX，Y：($mLastPosX,$mLastPosY）")
                result = if (mOffsetY < 3 &&  mOffsetX< 3) false //传给子控件
                else if (mOffsetX < mOffsetY) true //不传给子控件，自己水平滑动
                else false //传给子控件
            }
        }
        Log.i(TAG, "111111111111onInterceptTouchEvent: result:$result")
        return result
    }
}


