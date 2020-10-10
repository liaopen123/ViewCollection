package almostlover.com.viewcollection.activitys.bezierline

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.views.addcartview.cart_type_evaluator.AddCartTypeEnvaluator
import android.animation.ObjectAnimator
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_bezier_line.*

class BezierLineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bezier_line)
        val controlPonit = PointF(200F, 200F)
        iv_cart.setOnClickListener {
            val ofObject = ObjectAnimator.ofObject(
                iv_cart,
                "mPointF",
                AddCartTypeEnvaluator(controlPonit),
                PointF(iv_cart.x, iv_cart.y),
                PointF(iv_end.x, iv_end.y)
            )

            ofObject.duration = 10000
            ofObject.start()
        }
    }
}
