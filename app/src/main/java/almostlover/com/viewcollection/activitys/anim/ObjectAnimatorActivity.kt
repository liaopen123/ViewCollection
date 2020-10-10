package almostlover.com.viewcollection.activitys.anim

import almostlover.com.viewcollection.R
import android.animation.AnimatorInflater
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_object_animator.*
import android.animation.AnimatorSet
import android.animation.ObjectAnimator



class ObjectAnimatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(almostlover.com.viewcollection.R.layout.activity_object_animator)

        from_xml.setOnClickListener {
            val alphaAnimator = AnimatorInflater.loadAnimator(this, almostlover.com.viewcollection.R.animator.objectalpha)
            alphaAnimator.setTarget(from_xml)
            alphaAnimator.start()
        }

        set_from_xml.setOnClickListener {
            val alphaAnimator = AnimatorInflater.loadAnimator(this, almostlover.com.viewcollection.R.animator.objectset)
            alphaAnimator.setTarget(set_from_xml)
            alphaAnimator.start()
        }
        set_from_code.setOnClickListener {
            //沿x轴放大
            val scaleXAnimator = ObjectAnimator.ofFloat(set_from_code, "scaleX", 1f, 2f, 1f)
//沿y轴放大
            val scaleYAnimator = ObjectAnimator.ofFloat(set_from_code, "scaleY", 1f, 2f, 1f)
//移动
            val translationXAnimator = ObjectAnimator.ofFloat(set_from_code, "translationX", 0f, 200f, 0f)
//透明动画
            val animator = ObjectAnimator.ofFloat(set_from_code, "alpha", 1f, 0f, 1f)
            val set = AnimatorSet()
//同时沿X,Y轴放大，且改变透明度，然后移动
            set.play(scaleXAnimator).with(scaleYAnimator).with(animator).before(translationXAnimator)
//都设置3s，也可以为每个单独设置
            set.duration = 3000
            set.start()
        }
    }
}
