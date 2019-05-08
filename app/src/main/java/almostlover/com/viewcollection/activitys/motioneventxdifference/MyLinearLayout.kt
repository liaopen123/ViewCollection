package almostlover.com.viewcollection.activitys.motioneventxdifference

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout

class MyLinearLayout(context: Context, attr:AttributeSet):LinearLayout(context,attr){


    private val TAG: String? = "MyLinearLayout"

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        Log.e(TAG,"dispatchTouchEvent:::event!!.rawX:${ev?.rawX},,event!!.xxxxxxxxxx${ev?.x}")

        return super.dispatchTouchEvent(ev)
    }


}