package almostlover.com.viewcollection.views

import almostlover.com.viewcollection.utils.DensityUtil
import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView

/**
 * Created by liaopenghui on 16/7/26.
 */
class ShyImageView : ImageView {
    private var imagePositionX = 8888888f
    private var screenWidth: Float = 0.toFloat()
    var isHasHide = false
        private set



    constructor(context: Context) : super(context) {
        init()
    }

    private fun init() {
        val densityUtil = DensityUtil(context!!)
        screenWidth = densityUtil.screenWidth.toFloat()
        Log.e(TAG, "屏幕:$screenWidth")
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }


    fun hideView() {
        if (imagePositionX == 8888888f) {
            imagePositionX = x
        }
        Log.e(TAG, "屏幕:$screenWidth")
        Log.e(TAG, "icon位置:" + imagePositionX + "滑动位置:" + (screenWidth - width / 2))
        val moveIn = ObjectAnimator.ofFloat(this, "x", imagePositionX, screenWidth - width / 2)

        moveIn.start()
        moveIn.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {

            }

            override fun onAnimationEnd(animator: Animator) {
                isHasHide = true
            }

            override fun onAnimationCancel(animator: Animator) {

            }

            override fun onAnimationRepeat(animator: Animator) {

            }
        })
    }


    fun showView() {
        val moveout = ObjectAnimator.ofFloat(this, "x", screenWidth - width / 2, imagePositionX)
        moveout.startDelay = 500
        moveout.start()
        moveout.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {

            }

            override fun onAnimationEnd(animator: Animator) {
                isHasHide = false
            }

            override fun onAnimationCancel(animator: Animator) {

            }

            override fun onAnimationRepeat(animator: Animator) {

            }
        })
    }

    companion object {
        private val TAG = "ShyImageView"
    }
}
