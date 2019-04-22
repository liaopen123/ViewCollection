package almostlover.com.viewcollection.views.seekbar

import almostlover.com.viewcollection.utils.MathUtils
import almostlover.com.viewcollection.utils.PuzzlePathUtils
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import java.util.*


class SwipeImageView(context: Context, attr: AttributeSet, def: Int) : ImageView(context, attr, def) {


    private lateinit var ychangeListener: YchangeListener
    private var mProgress: Int
    private var randomHeightPercent: Int = 0
    private var mCaptchMaxXValues: Int = 0  //控件X的最大值
    private var mCaptchMaxYValues: Int = 0  //控件Y的最大值
    private var mCaptchMinYValues: Int = 0  //控件Y的最大值
    private var mViewWidth: Int = 0 //控件的宽度
    private var mViewHeight: Int = 0 //控件的高度
    private var mPaint: Paint //凹槽的painter
    private val mCaptchaWidth: Int = 200    //凹槽的宽度
    private var mCaptchaPath: Path
    private lateinit var puzzlePath: Path
    private var mRandom: Random

    //验证码的左上角(起点)的x y
    private var mCaptchaX: Int = 0
    private var mCaptchaY: Int = 300
    private val TAG: String? = "SwipeImageView"
    private var paint: Paint = Paint()
    private var mMaskShadowPaint: Paint

    constructor(context: Context, attr: AttributeSet) : this(context, attr, 0)


    init {

        mPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
        mPaint.setColor(0x77000000)
        //mPaint.setStyle(Paint.Style.STROKE);
        // 设置画笔遮罩滤镜
        mPaint.setMaskFilter(BlurMaskFilter(20f, BlurMaskFilter.Blur.SOLID))



        paint.setStrokeWidth(5F)//：设置画笔宽度
        paint.setAntiAlias(true)//：设置是否使用抗锯齿功能，如果使用，会导致绘图速度变慢
        paint.setStyle(Paint.Style.FILL)//：设置绘图样式，对于设置文字和几何图形都有效，可取值有三种 ：1、Paint.Style.FILL：填充内部 2、Paint.Style.FILL_AND_STROKE：填充内部和描边 3、Paint.Style.STROKE：仅描边
        paint.setTextAlign(Paint.Align.CENTER)//：设置文字对齐方式
        paint.color = Color.RED
        paint.setTextSize(12F)//：设置文字大小


        // 实例化阴影画笔
        mMaskShadowPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
        mMaskShadowPaint.setColor(Color.BLACK)
/*        mMaskShadowPaint.setStrokeWidth(50);
        mMaskShadowPaint.setTextSize(50);
        mMaskShadowPaint.setStyle(Paint.Style.FILL_AND_STROKE);*/
        mMaskShadowPaint.setMaskFilter(BlurMaskFilter(10f, BlurMaskFilter.Blur.SOLID))
        mRandom = Random(System.nanoTime())


        //绘制凹槽块的path
        mCaptchaPath = Path()


        val random = Random()
        mProgress = random.nextInt(100)

        Log.e(TAG, "mProgress:$mProgress")

    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.e(
            TAG,
            "onLayoutonLayoutonLayoutonLayoutonLayoutonLayoutonLayoutonLayoutonLayoutonLayoutonLayoutonLayout$mCaptchaX"
        )
        //获取控件的宽度
        if ((mViewWidth == 0 &&width!=0)||(mViewHeight == 0 &&height!=0)) {
            this.mViewWidth = width
            this.mViewHeight = height
            mCaptchMaxXValues = mViewWidth - mCaptchaWidth  //凹槽X可以活动的范围:背景图控件的宽度-凹槽的宽度
            mCaptchaX = mCaptchMaxXValues * mProgress / 100 //随机确定X的值


            mCaptchMaxYValues =  mViewHeight - mCaptchaWidth

            mCaptchaY = MathUtils.getRangeOf(mCaptchMinYValues, mCaptchMaxYValues)
            Log.e(TAG, "11111111111mCaptchMinYValues$mCaptchMinYValues"+"mCaptchMaxYValues:"+mCaptchMaxYValues+",mCaptchaY:"+mCaptchaY)

            Log.e(TAG, "随机的mCaptchaX$mCaptchaX")
            if(mCaptchaY>0) {
                Log.e(TAG, "222222222222mCaptchMinYValues$mCaptchMinYValues"+"mCaptchMaxYValues:"+mCaptchMaxYValues+",mCaptchaY:"+mCaptchaY)
                ychangeListener?.onYchanged(mCaptchaX, mCaptchaY, mCaptchaWidth)
            }

        }


        puzzlePath = PuzzlePathUtils.getPuzzlePath(mCaptchaX, mCaptchaY, mCaptchaWidth)

//        setPuzzlePath()

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)//放在最前 执行原有的draw图片的方法
        Log.e(TAG, "控件的宽度:$mViewWidth" + "高度:" + mViewHeight)
            if(puzzlePath!=null) {
                canvas!!.drawPath(puzzlePath, mPaint)
            }


        Log.e(TAG, "width:" + width + "height:" + height);
        Log.e(TAG, "onDrawonDrawonDrawonDrawonDrawonDrawonDrawonDrawonDraw");


    }


    interface  YchangeListener{
        fun onYchanged(x:Int,y:Int,width:Int)
    }

    public fun setYChangeListener(ychangeListener: YchangeListener){
        this.ychangeListener = ychangeListener

    }
}