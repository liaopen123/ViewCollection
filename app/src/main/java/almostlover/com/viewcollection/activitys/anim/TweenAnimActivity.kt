package almostlover.com.viewcollection.activitys.anim

import almostlover.com.viewcollection.R
import android.animation.AnimatorSet
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_tween_anim.*

class TweenAnimActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tween_anim)


        scale_xml.setOnClickListener {
            val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_show)
            scale_xml.startAnimation(scaleAnimation)
        }


        translate_xml.setOnClickListener {
            val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.translate)
            translate_xml.startAnimation(scaleAnimation)

        }

        alpha_xml.setOnClickListener {
            val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha)
            alpha_xml.startAnimation(scaleAnimation)

        }

        rotate_xml.setOnClickListener {
            val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate)
            rotate_xml.startAnimation(scaleAnimation)

        }

        set_xml.setOnClickListener {
            val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.set_anim)
            set_xml.startAnimation(scaleAnimation)

        }

        set_code.setOnClickListener {
            val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_show)
            val transAnim = AnimationUtils.loadAnimation(this, R.anim.translate)

            val animationSet = AnimationSet(true)
            animationSet.addAnimation(scaleAnimation)
            animationSet.addAnimation(transAnim)
            animationSet.fillAfter =false
            set_code.startAnimation(animationSet)
        }


    }
}
