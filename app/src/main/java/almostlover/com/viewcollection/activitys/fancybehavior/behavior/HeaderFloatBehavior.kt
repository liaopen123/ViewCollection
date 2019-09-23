package almostlover.com.viewcollection.activitys.fancybehavior.behavior

import almostlover.com.viewcollection.R
import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import java.lang.ref.WeakReference

class HeaderFloatBehavior(context: Context, attr: AttributeSet)  :CoordinatorLayout.Behavior<LinearLayout>(context, attr){


    lateinit var dependentView:WeakReference<View>


    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: LinearLayout,
        dependency: View
    ): Boolean {
        if(dependency?.id== R.id.scrolling_header){
            dependentView = WeakReference(dependency)
            return true
        }
        return false
    }


    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: LinearLayout,
        layoutDirection: Int
    ): Boolean {
        val layoutParams = child.layoutParams as CoordinatorLayout.LayoutParams
        child.layout(0, 0,parent.width, child.resources.getDimension(R.dimen.ll_height).toInt())
        return true
    }


    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: LinearLayout,
        dependency: View
    ): Boolean {

        child.translationY = dependency.height.toFloat()-child.height


        return true
    }

    private fun getDependentView(): View? {
        return dependentView.get()
    }
}