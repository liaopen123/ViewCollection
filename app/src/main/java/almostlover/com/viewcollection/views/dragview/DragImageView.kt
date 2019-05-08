package almostlover.com.viewcollection.views.dragview

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast

class DragImageView(context: Context, attrs: AttributeSet) : ImageView(context, attrs) {


    private var viewConfiguration: ViewConfiguration? = ViewConfiguration.get(context)
    private var downX: Float = 0.0f
    private var downY: Float = 0.0f
    private var mImageHeight: Int = 0
    private var mImageWidth: Int = 0
    private var mParentWidth: Int = 0 //父视图的宽度和高度 主要为了边界控制
    private var mParentHeight: Int = 0
    private val TAG: String = "DragImageView"
    private var finalTop: Int = 0
    private var finalBottom: Int = 0
    private var finalRight: Int = 0
    private var finalLeft: Int = 0
    private var starX: Float = 0.0f
    private var starY: Float = 0.0f



    init {
        setOnClickListener {
            Toast.makeText(context,"被点击",Toast.LENGTH_LONG).show()
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        val viewGroup = parent as ViewGroup
        mParentWidth =  viewGroup.width
        mParentHeight =  viewGroup.height

        mImageWidth = width
        mImageHeight = height
    }





    override fun onTouchEvent(event: MotionEvent?): Boolean {

        var x = event!!.rawX
        var y = event.rawY

        when (event!!.action) {

            MotionEvent.ACTION_DOWN -> {
                downX = x
                downY = y


                starX = x
                starY = y
            }

            MotionEvent.ACTION_MOVE -> {
                val offsetX = x - starX
                val offsetY = y - starY
                val leftside = left + offsetX
                val rightSide = right + offsetX
                val topSide = top + offsetY
                val bottomSide = bottom + offsetY
                if (leftside < 0 || topSide < 0||rightSide>mParentWidth||bottomSide>mParentHeight) {

                } else {
                    finalLeft = (left + offsetX).toInt()
                    finalTop = (top + offsetY).toInt()
                    finalRight = (right + offsetX).toInt()
                    finalBottom = (bottom + offsetY).toInt()

                    this.layout(
                        finalLeft, finalTop, finalRight,
                        finalBottom
                    )
                }
                starX = x
                starY = y
            }


            MotionEvent.ACTION_UP -> {
                //当抬手的时候,与按下的时候的坐标进行对比，二者的偏差小于阈值 就视为未移动  并且认为点击事件
                if ( Math.abs(event.rawX-downX)< viewConfiguration!!.scaledTouchSlop) {
                    performClick()
                    return true
                }
                if (finalLeft < mParentWidth / 2) {
                    //左移
                    tanslateY2Side(finalLeft,0)
                } else {
                    //右移
                    tanslateY2Side(finalLeft,mParentWidth-mImageWidth)
                }
            }
        }


        return true
    }

    private fun tanslateY2Side(starValue: Int, endValue: Int) {
        val animator = ValueAnimator.ofInt(starValue, endValue)
        animator.addUpdateListener {
            val finalLeft = it.animatedValue as Int
            this.layout(
                finalLeft, finalTop, finalLeft+mImageWidth,
                finalBottom
            )
        }

        animator.duration = 500
        animator.start()
    }
}