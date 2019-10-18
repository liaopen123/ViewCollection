package almostlover.com.viewcollection.views

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent

class CanNotTouchScrollRecycler @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    style:Int=0):RecyclerView(context,attributeSet){

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        return false
    }


}


