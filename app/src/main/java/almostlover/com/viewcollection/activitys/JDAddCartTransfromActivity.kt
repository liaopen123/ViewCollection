package almostlover.com.viewcollection.activitys

import almostlover.com.viewcollection.views.addcartview.cart_type_evaluator.AddCartTypeEnvaluator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.graphics.PointF
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.view.animation.AnticipateOvershootInterpolator
import android.view.animation.BounceInterpolator
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_jdadd_cart_transfrom.*


class JDAddCartTransfromActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(almostlover.com.viewcollection.R.layout.activity_jdadd_cart_transfrom)
//        Glide.with(this).load("http://img14.360buyimg.com/n0/jfs/t1/32472/22/3371/192059/5c74b147Ee2eaefb4/8a9eaa0ea1365ed0.jpg").into(iv_bg)
        iv_cart.setOnClickListener {

            Glide.with(this).load("http://img14.360buyimg.com/n0/jfs/t1/32472/22/3371/192059/5c74b147Ee2eaefb4/8a9eaa0ea1365ed0.jpg").apply(
                RequestOptions()
                    .circleCrop()).into(iv_move)
            iv_move.visibility = View.VISIBLE

//            val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_hide)
//            iv_move.animation = scaleAnimation
//            iv_move.startAnimation(scaleAnimation)

            val animatorSet = AnimatorSet()

            val animatorX = ObjectAnimator.ofFloat(iv_move, "scaleX", 1f, 0.1f)
            val animatorY = ObjectAnimator.ofFloat(iv_move, "scaleY", 1f, 0.1f)
            animatorX.setInterpolator (AnticipateInterpolator() as TimeInterpolator?)
            animatorY.setInterpolator ( AnticipateInterpolator() )
            val controlPonit = PointF(0f, 0F)
            val ofObject = ObjectAnimator.ofObject(
                iv_move,
                "mPointF",
                AddCartTypeEnvaluator(controlPonit),
                PointF(iv_move.x, iv_move.y),
                PointF(iv_cart.x, iv_cart.y)
            )
            animatorX.duration =10000
            animatorY.duration =10000
            ofObject.duration = 10000

            animatorSet.play(animatorX).with(animatorY).before(ofObject)
            animatorSet.start()
        }
    }
}
