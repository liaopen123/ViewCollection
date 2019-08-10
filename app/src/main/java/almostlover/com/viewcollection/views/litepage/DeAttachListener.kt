package almostlover.com.viewcollection.views.litepage

import android.view.View
import android.view.ViewGroup

public interface DeAttachListener {

    fun detachViewFromParent1(child: View)


    fun attachViewToParent1(child: View, index: Int, params: ViewGroup.LayoutParams)

}