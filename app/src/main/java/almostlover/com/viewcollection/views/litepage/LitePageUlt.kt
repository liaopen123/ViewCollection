package almostlover.com.viewcollection.views.litepage

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class LitePageUlt(context: Context, attributeSet: AttributeSet) : ViewGroup(context, attributeSet) {


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var maxHeight = 0
        for (childIndex in 0 until childCount) {
            val child = getChildAt(childIndex)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            if (maxHeight < child.getMeasuredHeight()) {
                maxHeight = child.getMeasuredHeight()
            }
        }
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), maxHeight)
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        val viewGroupWidth = r - l
        val viewGroupHeight = b - t

        var leftAxis = viewGroupWidth / 4


        for (childIndex in 0 until childCount) {
            val child = getChildAt(childIndex)
            if (child == null) {

            } else {
                val measuredHeight = child.measuredHeight
                val measuredWidth = child.measuredWidth

                val leftStart = leftAxis * (childIndex + 1) - (measuredWidth / 2)
                val startTop = viewGroupHeight / 2 - measuredHeight / 2
                child.layout(leftStart, startTop, leftStart + measuredWidth, startTop + measuredHeight)
            }


        }
    }

    override fun addView(child: View?, params: LayoutParams?) {
        val lp = if (params is LayoutParams) params else LayoutParams(params)
        super.addView(child, params)
    }


}