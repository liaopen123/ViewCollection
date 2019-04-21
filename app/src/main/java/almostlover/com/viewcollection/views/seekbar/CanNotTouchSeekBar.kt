package almostlover.com.viewcollection.views.seekbar

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.SeekBar

class CanNotTouchSeekBar(context: Context, attr: AttributeSet, def: Int) : SeekBar(context, attr, def) {

    private var currentProgress: Int = 0
    private val TAG: String? = "CanNotTouchSeekBar"
    private var xchangeListener: XchangeListener? = null

    private var currentState: SeekBarState = SeekBarState.Backing

    constructor(context: Context, attr: AttributeSet) : this(context, attr, 0)


    init {
        initListener()
    }

    private fun initListener() {
        setOnSeekBarChangeListener(object :OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                currentProgress = progress
                Log.e(TAG,"currentProgress"+currentProgress)
                xchangeListener?.onProgressChange(progress)
            }
            //监听开始拖动滚动条时的操作
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            // 停止拖动滚动条的操作
            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        val left = thumb.bounds.left
        val right = thumb.bounds.right
        val bottom = thumb.bounds.bottom
        val top = thumb.bounds.top
        val x = event?.x
        val y = event?.y
        Log.e(TAG, "left:" + left + ",right:" + right + "X:" + x)


        when (event?.action) {

            MotionEvent.ACTION_DOWN -> {
                //初始化不在范围内就
                if ((x!! <= left) or (x >= right)) {
                    return false
                }
            }



            MotionEvent.ACTION_UP -> {
                Log.e(TAG, "我要返回了")
                startBackingAnimation()
                return false
            }


        }

        return super.dispatchTouchEvent(event)
    }


     enum class SeekBarState {
        Backing;//返回途中


    }


    fun startBackingAnimation(){
        val animator = ValueAnimator.ofInt(currentProgress,0);
        animator.duration = 1000
        animator.addUpdateListener {
            val animatedValue = it.animatedValue as Int
            progress = animatedValue
        }
        animator.start()
    }



    private var starX: Float = 0.0f
    private var starY: Float = 0.0f



    public interface XchangeListener{
        fun onProgressChange(progress: Int)
    }


    public fun setXchangeListener(xchangeListener: XchangeListener){
        this.xchangeListener = xchangeListener
    }

}