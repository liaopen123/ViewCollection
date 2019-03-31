package almostlover.com.viewcollection.views.addcartview.cart_type_evaluator

import android.animation.TypeEvaluator
import android.graphics.PointF

class AddCartTypeEnvaluator:TypeEvaluator<PointF>{
    lateinit var control:PointF
    internal var mPointF = PointF()

    constructor(controlPointF: PointF){
        this.control = controlPointF;
    }


    override fun evaluate(t: Float, start: PointF?, end: PointF?): PointF {


        mPointF.x = (1 - t) * (1 - t) * start?.x!! + 2f * t * (1 - t) * control.x + t * t * end?.x!!
        mPointF.y = (1 - t) * (1 - t) * start.y + 2f * t * (1 - t) * control.y + t * t * end.y
        return mPointF

    }

}