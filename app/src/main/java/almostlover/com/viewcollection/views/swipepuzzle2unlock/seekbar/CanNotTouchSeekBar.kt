package com.shunguang.vip.view.swipepuzzle2unlock.seekbar

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import androidx.appcompat.widget.AppCompatSeekBar
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.SeekBar

class CanNotTouchSeekBar(context: Context, attr: AttributeSet, def: Int) : AppCompatSeekBar(context, attr, def) {

    private var currentProgress: Int = 0
    private val TAG: String? = "CanNotTouchSeekBar"
    private var xchangeListener: XchangeListener? = null

    private var currentState: SeekBarState = SeekBarState.IDLE


    private val SHORT_DURATION = 200L  //当进度条小于50的时候，返回动画的duration
    private val LONG_DURATION = 500L //当进度条大于50的时候，返回动画的duration



    constructor(context: Context, attr: AttributeSet) : this(context, attr, 0)


    init {
        initListener()
    }

    private fun initListener() {
        setOnSeekBarChangeListener(object :OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                currentProgress = progress
                Log.e(TAG, "currentProgress$currentProgress")
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
                //返回动画过程中 不允许拖动
                if (currentState==SeekBarState.BACKING) {
                    return false
                }
            }



            MotionEvent.ACTION_UP -> {
                Log.e(TAG, "我要返回了")

                xchangeListener?.onResult(progress)
//                startBackingAnimation()
                return false
            }


        }

        return super.dispatchTouchEvent(event)
    }


     enum class SeekBarState {
        BACKING,//返回途中
         IDLE///空闲


    }


    fun startBackingAnimation(){
        val animator = ValueAnimator.ofInt(currentProgress,0)
        if(currentProgress<50){
            animator.duration = SHORT_DURATION
        }else {
            animator.duration = LONG_DURATION
        }
        animator.addUpdateListener {
            val animatedValue = it.animatedValue as Int
            progress = animatedValue
        }


        animator.addListener(object: Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                currentState = SeekBarState.IDLE
                animator.cancel()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {

                currentState = SeekBarState.BACKING
            }


        })
        animator.start()


    }



     interface XchangeListener{
        fun onProgressChange(progress: Int)
        //抬起手的时候，得到的最终的progress
        fun onResult(progress: Int)

    }


     fun setXchangeListener(xchangeListener: XchangeListener){
        this.xchangeListener = xchangeListener
    }

    fun verifyFailed() {
        startBackingAnimation()
    }

}