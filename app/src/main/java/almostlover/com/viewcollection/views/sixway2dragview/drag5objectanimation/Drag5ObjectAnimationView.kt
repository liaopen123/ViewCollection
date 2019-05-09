package almostlover.com.viewcollection.views.sixway2dragview.drag5objectanimation

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.animation.AnimatorSet
import android.animation.ObjectAnimator

//通过event.x进行位移的 二者有区别
class Drag5ObjectAnimationView : ImageView {


    private val TAG: String?  ="Drag1OnLayoutXView"
    private var lastX: Int = 0
    private var lastY: Int = 0

    private val set :AnimatorSet = AnimatorSet()
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context) : super(context) {}


    init {
        setOnClickListener {
            startMove()
        }
    }


    fun startMove(){
        set.playTogether(
            ObjectAnimator.ofFloat(this, "translationX", 50f),
            ObjectAnimator.ofFloat(this,"translationY", 100f)
        )
        set.duration = 500
        set.start()
    }

}