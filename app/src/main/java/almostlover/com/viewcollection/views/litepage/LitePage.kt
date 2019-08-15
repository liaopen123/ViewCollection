package almostlover.com.viewcollection.views.litepage

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

class LitePage(context: Context, attr: AttributeSet) : ConstraintLayout(context, attr), DeAttachListener {

    override fun detachViewFromParent1(child: View) {
        super.detachViewFromParent(child)
    }

    override fun attachViewToParent1(child: View, index: Int, params: ViewGroup.LayoutParams) {
        super.attachViewToParent(child, index, params)
    }



    private fun  isInTouchArea(view: View,points:FloatArray){


        points[0] = view.left.toFloat()
        points[1] = view.top.toFloat()

        //view对应的矩阵
        val matrix = view.matrix
        if (!matrix.isIdentity) {
            matrix.invert(matrix)
            //映射到坐标点上
            matrix.mapPoints(points)
        }

    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        childCount
        for (index in 0 until childCount){

        }
            return super.dispatchTouchEvent(ev)
    }

}