package almostlover.com.viewcollection.views.path

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.Point
import android.util.AttributeSet
import android.view.View
import java.util.*

class PathView(context: Context,attributes: AttributeSet,def :Int ): View(context,attributes,def){

    private lateinit var puzzlePath: Path
    private val mCaptchaHeight: Int = 200
    private val mCaptchaWidth: Int = 200
    private lateinit var mRandom: Random
    private val mHeight: Int = 200
    private val mWidth: Int = 200

    //验证码的左上角(起点)的x y
    private var mCaptchaX: Int = 0
    private var mCaptchaY: Int = 0


    constructor(context: Context, attributes: AttributeSet):this(context,attributes,0)


init {

    setPuzzlePath()
}



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }




    fun setPuzzlePath(){
        puzzlePath = Path()
        val random = Random(System.nanoTime())

        var gap = mCaptchaWidth/7


        //水平线1
        //起点的point
        val startPint = Point(mCaptchaX, mCaptchaY + gap)
        val startArcPoint1 = Point(mCaptchaX+(2*gap), mCaptchaY + gap)//水平方向  第一个圆弧起点
        val circleCenterPoint1 = Point(mCaptchaX+(3*gap), mCaptchaY + gap)//上面的圆
        val lin1EndPonit = Point(mCaptchaX+(6*gap), mCaptchaY + gap)//水平方向  第一个拐点


        //数值线1
        val lin2MiddlePonit = Point(mCaptchaX+(6*gap), mCaptchaY + (3*gap))//水平方向  第一个拐点
        val circleCenterPoint2 = Point(mCaptchaX+(6*gap), mCaptchaY + (4*gap))//右边的圆
        val lin2EndPonit = Point(mCaptchaX+(6*gap), mCaptchaY + (7*gap))//右边的圆


        //底部线终点
        val lin3EndPonit = Point(mCaptchaX, mCaptchaY + (7*gap))//右边的圆



        //左边的线
        val leftMiddlePint = Point(mCaptchaX, mCaptchaY + (5*gap))
        val circleCenterPoint3 = Point(mCaptchaX, mCaptchaY + (4*gap))//左边的圆

        puzzlePath.reset()
        puzzlePath.moveTo(startPint.x.toFloat(), startPint.y.toFloat()) //挪到起点
        puzzlePath.lineTo(startArcPoint1.x.toFloat(), startArcPoint1.y.toFloat()) //挪到第一个起点
        //开始画弧度
        puzzlePath.addCircle(circleCenterPoint1.x.toFloat(), circleCenterPoint1.y.toFloat(), gap.toFloat(), Path.Direction.CCW)
        //画完后
        puzzlePath.lineTo(lin1EndPonit.x.toFloat(), lin1EndPonit.y.toFloat()) //挪到第一条大线的终点



        //画竖直线1
        puzzlePath.lineTo(lin2MiddlePonit.x.toFloat(), lin2MiddlePonit.y.toFloat()) //画到竖线 准备画圆
        //开始画弧度
        puzzlePath.addCircle(circleCenterPoint2.x.toFloat(), circleCenterPoint2.y.toFloat(), gap.toFloat(), Path.Direction.CW)
        //竖线终点1
        puzzlePath.lineTo(lin2EndPonit.x.toFloat(), lin2EndPonit.y.toFloat())


        //画底部线
        puzzlePath.lineTo(lin3EndPonit.x.toFloat(), lin3EndPonit.y.toFloat())


        //画左边的线
        puzzlePath.lineTo(leftMiddlePint.x.toFloat(), leftMiddlePint.y.toFloat())
        //开始画弧度
        puzzlePath.addCircle(circleCenterPoint3.x.toFloat(), circleCenterPoint3.y.toFloat(), gap.toFloat(), Path.Direction.CW)

        puzzlePath.close()

    }


}