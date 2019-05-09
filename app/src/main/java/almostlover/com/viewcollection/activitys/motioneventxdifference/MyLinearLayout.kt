package almostlover.com.viewcollection.activitys.motioneventxdifference

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.RelativeLayout

class MyLinearLayout(context: Context, attr:AttributeSet):RelativeLayout(context,attr){


    private val TAG: String? = "MyLinearLayout"

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        Log.e(TAG,"dispatchTouchEvent:::event!!.rawX:${ev?.rawX},,event!!.xxxxxxxxxx${ev?.x}")

        return super.dispatchTouchEvent(ev)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        Log.e(TAG,"onLayout")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.e(TAG,"onMeasure")
    }


}