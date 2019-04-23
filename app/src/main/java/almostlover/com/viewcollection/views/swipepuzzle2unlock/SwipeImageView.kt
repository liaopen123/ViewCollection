package com.shunguang.vip.view.swipepuzzle2unlock

import almostlover.com.viewcollection.utils.MathUtils
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.shunguang.vip.view.swipepuzzle2unlock.util.PuzzlePathUtils
import java.util.*


class SwipeImageView(context: Context, attr: AttributeSet, def: Int) : ImageView(context, attr, def) {


    private lateinit var ychangeListener: YchangeListener
    private var mProgress: Int = 0
    private var randomHeightPercent: Int = 0
    private var mCaptchMaxXValues: Int = 0  //控件X的最大值
    private var mCaptchMaxYValues: Int = 0  //控件Y的最大值
    private var mCaptchMinYValues: Int = 0  //控件Y的最大值
    private var mViewWidth: Int = 0 //控件的宽度
    private var mViewHeight: Int = 0 //控件的高度
    private var mPaint: Paint //凹槽的painter
    private val mCaptchaWidth: Int = 200    //凹槽的宽度
    private lateinit var puzzlePath: Path

    //验证码的左上角(起点)的x y
    private var mCaptchaX: Int = 0
    private var mCaptchaY: Int = 300
    private val TAG: String? = "SwipeImageView"
    private var paint: Paint = Paint()

    constructor(context: Context, attr: AttributeSet) : this(context, attr, 0)


    init {

        mPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
        mPaint.color = Color.parseColor("#99000000")
        // 设置画笔遮罩滤镜
        mPaint.maskFilter = BlurMaskFilter(20f, BlurMaskFilter.Blur.SOLID)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        var heightSize = View.MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)

        setMeasuredDimension(widthSize, heightSize)

    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.e(
            TAG,
            "onLayoutonLayoutonLayoutonLayoutonLayoutonLayoutonLayoutonLayoutonLayoutonLayoutonLayoutonLayout$mCaptchaX"
        )
        //获取控件的宽度
        if ((mViewWidth == 0 && width != 0) || (mViewHeight == 0 && height != 0)) {
            this.mViewWidth = width
            this.mViewHeight = height
            mCaptchMaxXValues = mViewWidth - mCaptchaWidth  //凹槽X可以活动的范围:背景图控件的宽度-凹槽的宽度
            initRandomConfig()

        }
        initPuzzlePath()
    }



    private fun initRandomConfig() {
        val random = Random()
        mProgress = random.nextInt(100)
        mCaptchaX = mCaptchMaxXValues * mProgress / 100 //随机确定X的值


        mCaptchMaxYValues = mViewHeight - mCaptchaWidth

        mCaptchaY = MathUtils.getRangeOf(mCaptchMinYValues, mCaptchMaxYValues)

        if (mCaptchaY > 0) {
            ychangeListener?.onYchanged(mCaptchaX, mCaptchaY, mCaptchaWidth, mProgress)
        }
    }

    private fun initPuzzlePath() {
        puzzlePath = PuzzlePathUtils.getPuzzlePath(mCaptchaX, mCaptchaY, mCaptchaWidth)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)//放在最前 执行原有的draw图片的方法
        Log.e(TAG, "控件的宽度:$mViewWidth" + "高度:" + mViewHeight)
        if (puzzlePath != null) {
            canvas!!.drawPath(puzzlePath, mPaint)
        }


        Log.e(TAG, "width:" + width + "height:" + height);
        Log.e(TAG, "onDrawonDrawonDrawonDrawonDrawonDrawonDrawonDrawonDraw");


    }


    interface YchangeListener {
        fun onYchanged(x: Int, y: Int, width: Int, randomProgress: Int)
    }

    fun setYChangeListener(ychangeListener: YchangeListener) {
        this.ychangeListener = ychangeListener
    }



    fun refreshData(){
        initRandomConfig()
        initPuzzlePath()
        invalidate()
    }
}