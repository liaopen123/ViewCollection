package almostlover.com.viewcollection.activitys

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.utils.ImageUtils
import almostlover.com.viewcollection.views.seekbar.CanNotTouchSeekBar
import almostlover.com.viewcollection.views.seekbar.DrawHelperUtils
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.FrameLayout
import android.widget.SeekBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import kotlinx.android.synthetic.main.activity_drag_verify.*
import java.util.*


class DragVerifyActivity : AppCompatActivity() {
    private lateinit var mMaskShadowPaint: Paint //凹槽的painter
    private val mCaptchaHeight: Int = 200
    private val mCaptchaWidth: Int = 200
    private lateinit var puzzlePath: Path
    private lateinit var mRandom: Random
    private val mHeight: Int = 200
    private val mWidth: Int = 200

    //验证码的左上角(起点)的x y
    private var mCaptchaX: Int = 0
    private var mCaptchaY: Int = 0
    val TAG = "DragVerifyActivity"
    private var isSeekBarClick: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_verify)


        // 实例化阴影画笔
        mMaskShadowPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
        mMaskShadowPaint.setColor(Color.BLACK)
        mMaskShadowPaint.setStrokeWidth(50F);
        mMaskShadowPaint.setTextSize(50F);
        mMaskShadowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mMaskShadowPaint.setMaskFilter(BlurMaskFilter(10f, BlurMaskFilter.Blur.SOLID))






        sb_progress.setXchangeListener(object : CanNotTouchSeekBar.XchangeListener {
            override fun onProgressChange(progress: Int) {
                Log.e(TAG, "progressprogress" + progress)
                seekbar_pazzle.progress = progress
            }

            override fun onXchange(offsetX: Float) {
//                iv_image.layout(
//                    (iv_image.left + offsetX).toInt(),
//                    iv_image.top,
//                    (iv_image.right + offsetX).toInt(),
//                    iv_image.bottom
//                )
            }

        })



        Glide.with(this)
            .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555525273577&di=00179c40c429f37f458d3f95f813ee16&imgtype=0&src=http%3A%2F%2Fscimg.jb51.net%2Fallimg%2F170615%2F106-1F615104352441.jpg")
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {

                    Log.e(TAG, "GlideGlideGlideGlideGlideGlideonDrawonDrawonDrawonDrawonDrawonDrawonDrawonDrawonDraw");
//                    val clipBitmap = ImageUtils.clip(bitmap, 200, 200, 200, 200)
//
                    val bitmapDrawable = resource as BitmapDrawable
                    val bitmap = bitmapDrawable.bitmap
                    Log.e(TAG, "bitmapbitmapbitmapbitmapbitmap" + bitmap.width + "bitmap.height" + bitmap.height);
//


                    val clipBitmap = ImageUtils.clip(bitmap, 300, 300, 200, 200)
                    val cirleBitmap = getCirleBitmap(clipBitmap)
////
                    val newThumb = BitmapDrawable(resources, cirleBitmap)

                    seekbar_pazzle.thumb = newThumb
                    seekbar_pazzle.thumbOffset = 50

                    val layoutParams = seekbar_pazzle.layoutParams as FrameLayout.LayoutParams
                    layoutParams.setMargins(0, 300, 0, 0)
                    seekbar_pazzle.layoutParams = layoutParams







                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {


                    return false
                }


            })
            .into(iv_bg)



        seekbar_pazzle.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.e(TAG, "seekbar_pazzleprogressprogress" + progress + ",isSeekBarClick" + isSeekBarClick)
                if (fromUser) {
                    isSeekBarClick = true
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//               ischanging = true;// 停止time
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        setPuzzlePath()
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
        val circleCenterPoint3 = Point(mCaptchaX, mCaptchaY + (4 * gap))//左边的圆

        puzzlePath.reset()
        puzzlePath.moveTo(startPint.x.toFloat(), startPint.y.toFloat()) //挪到起点
        puzzlePath.lineTo(startArcPoint1.x.toFloat(), startArcPoint1.y.toFloat()) //挪到第一个起点
        //开始画弧度
        DrawHelperUtils.drawPartCircle(startArcPoint1, endArcPoint1, puzzlePath, true)
        //画完后
        puzzlePath.lineTo(lin1EndPonit.x.toFloat(), lin1EndPonit.y.toFloat()) //挪到第一条大线的终点


        //画竖直线1
        puzzlePath.lineTo(lin2MiddlePonit.x.toFloat(), lin2MiddlePonit.y.toFloat()) //画到竖线 准备画圆

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





    fun getCirleBitmap(bmp: Bitmap): Bitmap {
        //获取bmp的宽高 小的一个做为圆的直径r
        var w = 200;
        var h = 200;
        var r = 200;

        //创建一个paint
        var paint = Paint();
        paint.isAntiAlias = true;

        var newBitmap = Bitmap.createBitmap(bmp.width, bmp.height, Bitmap.Config.ARGB_8888)
        var canvas = Canvas(newBitmap);

        //canvas画一个圆形
//        canvas.drawCircle((r / 2).toFloat(), (r / 2).toFloat(), (r / 2).toFloat(), paint)
        canvas.drawPath(puzzlePath, paint)
        //然后 paint要设置Xfermode 模式为SRC_IN 显示上层图像（后绘制的一个）的相交部分
        paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        //canvas调用drawBitmap直接将bmp对象画在画布上 因为paint设置了Xfermode，所以最终只会显示这个bmp的一部分 也就
        //是bmp的和下层圆形相交的一部分圆形的内容
//        canvas.drawBitmap(bmp, 0f, 0f, paint)
        canvas.drawBitmap(bmp, Matrix(), paint)  //在画布上画一个和bitmap一模一样的图

        return newBitmap;
    }

}
