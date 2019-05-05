package almostlover.com.viewcollection.utils

import almostlover.com.viewcollection.views.swipepuzzle2unlock.util.DrawHelperUtils
import android.graphics.Path
import android.graphics.Point
import android.graphics.PointF
import android.util.Log

class PuzzlePathUtils {

    companion object{
        private val TAG: String? = "PuzzlePathUtils"

        private val part = 7  //水平 竖直方向平分的等分




        fun  getPuzzlePath(mCaptchaX:Int, mCaptchaY:Int, mCaptchaWidth:Int): Path {
          var  puzzlePath = Path()
            var gap = mCaptchaWidth / part
            Log.e(TAG, "puzzlePath中的mCaptchaX：$mCaptchaX")
            //水平线1
            //起点的point
            val startPint = Point(mCaptchaX, mCaptchaY + gap)
            val startArcPoint1 = PointF((mCaptchaX + (2 * gap)).toFloat(), (mCaptchaY + gap).toFloat())//水平方向  第一个圆弧起点
            val endArcPoint1 = PointF((mCaptchaX + (4 * gap)).toFloat(), (mCaptchaY + gap).toFloat())//水平方向  第一个圆弧起点
            val lin1EndPonit = Point(mCaptchaX + (6 * gap), mCaptchaY + gap)//水平方向  第一个拐点


            //数值线1
            val lin2MiddlePonit = PointF((mCaptchaX + (6 * gap)).toFloat(), (mCaptchaY + (3 * gap)).toFloat())//水平方向  第一个拐点
            val endArcPoint2 = PointF((mCaptchaX + (6 * gap)).toFloat(), (mCaptchaY + 5 * gap).toFloat())//水平方向  第一个圆弧起点

            val lin2EndPonit = PointF((mCaptchaX + (6 * gap)).toFloat(), (mCaptchaY + (7 * gap)).toFloat())//右边的圆


            //底部线终点
            val lin3EndPonit = Point(mCaptchaX, mCaptchaY + (7 * gap))//右边的圆


            //左边的线
            val leftMiddlePint = PointF(mCaptchaX.toFloat(), (mCaptchaY + (5 * gap)).toFloat())
            val leftendCirclePint3 = PointF(mCaptchaX.toFloat(), (mCaptchaY + (3 * gap)).toFloat())

            puzzlePath.reset()
            puzzlePath.moveTo(startPint.x.toFloat(), startPint.y.toFloat()) //挪到起点
            puzzlePath.lineTo(startArcPoint1.x.toFloat(), startArcPoint1.y.toFloat()) //挪到第一个起点
            //开始画弧度
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




            return puzzlePath





        }

    }






}