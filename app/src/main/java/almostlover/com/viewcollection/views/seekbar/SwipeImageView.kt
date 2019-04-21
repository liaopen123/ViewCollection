package almostlover.com.viewcollection.views.seekbar

import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import java.util.*


class SwipeImageView(context: Context, attr: AttributeSet, def: Int) : ImageView(context, attr, def) {


    private var mPaint: Paint //凹槽的painter
    private val mCaptchaHeight: Int = 200
    private val mCaptchaWidth: Int = 200
    private var mCaptchaPath: Path
    private lateinit var puzzlePath: Path
    private var mRandom: Random
    private val mHeight: Int = 200
    private val mWidth: Int = 200

    //验证码的左上角(起点)的x y
    private var mCaptchaX: Int = 300
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
//        createCaptchaPath()
        setPuzzlePath()

    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)//放在最前 执行原有的draw图片的方法


        if (mCaptchaPath != null) {
            canvas!!.drawPath(puzzlePath, mPaint)
        }


        Log.e(TAG, "width:" + width + "height:" + height);
        Log.e(TAG, "onDrawonDrawonDrawonDrawonDrawonDrawonDrawonDrawonDraw");


    }


    fun setPuzzlePath() {
        puzzlePath = Path()
        val random = Random(System.nanoTime())

        var gap = mCaptchaWidth / 7


        //水平线1
        //起点的point
        val startPint = Point(mCaptchaX, mCaptchaY + gap)
        val startArcPoint1 = PointF((mCaptchaX + (2 * gap)).toFloat(), (mCaptchaY + gap).toFloat())//水平方向  第一个圆弧起点
        val endArcPoint1 = PointF((mCaptchaX + (4 * gap)).toFloat(), (mCaptchaY + gap).toFloat())//水平方向  第一个圆弧起点
        val circleCenterPoint1 = Point(mCaptchaX + (3 * gap), mCaptchaY + gap)//上面的圆
        val lin1EndPonit = Point(mCaptchaX + (6 * gap), mCaptchaY + gap)//水平方向  第一个拐点


        //数值线1
        val lin2MiddlePonit = PointF((mCaptchaX + (6 * gap)).toFloat(), (mCaptchaY + (3 * gap)).toFloat())//水平方向  第一个拐点
        val circleCenterPoint2 = Point(mCaptchaX + (6 * gap), mCaptchaY + (4 * gap))//右边的圆
        val endArcPoint2 = PointF((mCaptchaX + (6 * gap)).toFloat(), (mCaptchaY + 5 * gap).toFloat())//水平方向  第一个圆弧起点

        val lin2EndPonit = PointF((mCaptchaX + (6 * gap)).toFloat(), (mCaptchaY + (7 * gap)).toFloat())//右边的圆


        //底部线终点
        val lin3EndPonit = Point(mCaptchaX, mCaptchaY + (7 * gap))//右边的圆


        //左边的线
        val leftMiddlePint = PointF(mCaptchaX.toFloat(), (mCaptchaY + (5 * gap)).toFloat())
        val leftendCirclePint3 = PointF(mCaptchaX.toFloat(), (mCaptchaY + (3 * gap)).toFloat())
        val circleCenterPoint3 = Point(mCaptchaX, mCaptchaY + (4 * gap))//左边的圆

        puzzlePath.reset()
        puzzlePath.moveTo(startPint.x.toFloat(), startPint.y.toFloat()) //挪到起点
        puzzlePath.lineTo(startArcPoint1.x.toFloat(), startArcPoint1.y.toFloat()) //挪到第一个起点
        //开始画弧度
//        puzzlePath.addCircle(circleCenterPoint1.x.toFloat(), circleCenterPoint1.y.toFloat(), gap.toFloat(), Path.Direction.CCW)
        DrawHelperUtils.drawPartCircle(startArcPoint1, endArcPoint1, puzzlePath, true)
        //画完后
        puzzlePath.lineTo(lin1EndPonit.x.toFloat(), lin1EndPonit.y.toFloat()) //挪到第一条大线的终点


        //画竖直线1
        puzzlePath.lineTo(lin2MiddlePonit.x.toFloat(), lin2MiddlePonit.y.toFloat()) //画到竖线 准备画圆
        //开始画弧度
//        puzzlePath.addCircle(circleCenterPoint2.x.toFloat(), circleCenterPoint2.y.toFloat(), gap.toFloat(), Path.Direction.CW)

        DrawHelperUtils.drawPartCircle(lin2MiddlePonit, endArcPoint2, puzzlePath, true)


        //竖线终点1
        puzzlePath.lineTo(lin2EndPonit.x.toFloat(), lin2EndPonit.y.toFloat())


        //画底部线
        puzzlePath.lineTo(lin3EndPonit.x.toFloat(), lin3EndPonit.y.toFloat())


        //画左边的线
        puzzlePath.lineTo(leftMiddlePint.x.toFloat(), leftMiddlePint.y.toFloat())
        DrawHelperUtils.drawPartCircle(leftMiddlePint, leftendCirclePint3, puzzlePath, false)

        puzzlePath.close()

    }



}