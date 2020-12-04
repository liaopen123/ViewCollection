package almostlover.com.viewcollection.activitys.anim

import almostlover.com.viewcollection.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateDecelerateInterpolator
import kotlinx.android.synthetic.main.activity_circular_reveal.*

class CircularRevealActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_reveal)

      root.setOnClickListener {
          root.showRevealView()
      }
    }


    fun View.showRevealView(){
        val centerX = (this.left+this.right)/2
        val centerY = (this.top+this.bottom)/2
        val radius = Math.max(this.width,this.height)
        val anim =  ViewAnimationUtils.createCircularReveal(this,this.width/2,
            this.height/2,
            0f,
            Math.hypot(this.width.toDouble(), this.height.toDouble()).toFloat()
        )
        visibility = View.VISIBLE
        anim.duration = 2000
        anim.interpolator = AccelerateDecelerateInterpolator()
        anim.start()
    }

    fun View.hideRevealView(){

    }
}