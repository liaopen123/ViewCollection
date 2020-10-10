package almostlover.com.viewcollection.views

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent

class CanNotTouchScrollRecycler @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    style:Int=0): androidx.recyclerview.widget.RecyclerView(context,attributeSet){

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        return false
    }


}


