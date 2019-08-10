package almostlover.com.viewcollection.views.litepage

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class LitePage(context: Context, attr: AttributeSet) : ConstraintLayout(context, attr), DeAttachListener {

    override fun detachViewFromParent1(child: View) {
        super.detachViewFromParent(child)
    }

    override fun attachViewToParent1(child: View, index: Int, params: ViewGroup.LayoutParams) {
        super.attachViewToParent(child, index, params)
    }

}