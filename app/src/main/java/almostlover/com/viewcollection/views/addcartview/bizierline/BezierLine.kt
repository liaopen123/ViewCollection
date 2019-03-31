package almostlover.com.viewcollection.views.addcartview.bizierline

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class BezierLine(context: Context, attr: AttributeSet?, def:Int):View(context,attr,def){
   constructor(context:Context):this(context,null,0)
   constructor(context:Context,attr: AttributeSet?):this(context,attr,0)


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        val path = Path()
        path.moveTo(100F, 100F)
        path.quadTo(400F,200F,400F, 400F)

        val paint = Paint()
        paint.color = Color.RED
        paint.setStyle(Paint.Style.STROKE)
        paint.setStrokeWidth(10F)
        canvas?.drawPath(path,paint)


    }
}