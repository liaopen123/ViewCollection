package almostlover.com.viewcollection.activitys


import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.utils.ImageUtils
import almostlover.com.viewcollection.utils.PuzzlePathUtils
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.SeekBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.shunguang.vip.view.swipepuzzle2unlock.SwipeImageView
import com.shunguang.vip.view.swipepuzzle2unlock.seekbar.CanNotTouchSeekBar
import kotlinx.android.synthetic.main.activity_drag_verify.*


class DragVerifyActivity : AppCompatActivity() {
    private lateinit var mMaskShadowPaint: Paint //凹槽的painter
    private var mCaptchaWidth: Int = 0
    private lateinit var puzzlePath: Path


    //验证码的左上角(起点)的x y
    private var mCaptchaX: Int = 0
    private var mCaptchaY: Int = 0
    private var mResource: Drawable? = null
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
            override fun onResult(progress: Int) {

            }

            override fun onProgressChange(progress: Int) {
                Log.e(TAG, "progressprogress" + progress)
                seekbar_pazzle.progress = progress
            }
        })

        iv_bg.setYChangeListener(object : SwipeImageView.YchangeListener {
            override fun onYchanged(x: Int, y: Int, width: Int, randomProgress: Int) {

            }




        })

        Glide.with(this)
            .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555869647256&di=53ee0f3c3447c24b4f8c74d793ffdff6&imgtype=0&src=http%3A%2F%2Fattachments.gfan.net.cn%2Fforum%2F201806%2F05%2F145829js0kwvpt00w7sw0q.jpg")
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    mResource = resource


                    iv_bg.postDelayed({ setThumb() }, 100)

//                    setThumb()
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


        val vto = seekbar_pazzle.getViewTreeObserver()
        vto.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
//                val res = resources
//                val thumb = res.getDrawable(R.drawable.thumb)
//                val h = mySeekBar.getMeasuredHeight() * 1.5 // 8 * 1.5 = 12
//                val bmpOrg = (thumb as BitmapDrawable).bitmap
//                val bmpScaled = Bitmap.createScaledBitmap(bmpOrg, h, h, true)
//                val newThumb = BitmapDrawable(res, bmpScaled)
//                newThumb.setBounds(0, 0, newThumb.intrinsicWidth, newThumb.intrinsicHeight)
//                mySeekBar.setThumb(newThumb)
//
//                mySeekBar.getViewTreeObserver().removeOnPreDrawListener(this)

                Log.e(TAG, "addOnPreDrawListeneraddOnPreDrawListeneraddOnPreDrawListener");
                return true
            }
        })

        btn_next.setOnClickListener {
            setThumb()
        }
    }


    fun getCirleBitmap(bmp: Bitmap): Bitmap {
        //获取bmp的宽高 小的一个做为圆的直径r

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

        return newBitmap
    }


    private fun setThumb() {


        if (mResource != null && mCaptchaWidth > 0 && mCaptchaX > 0 && mCaptchaY > 0) {
            Log.e(
                TAG,
                "setThumbsetThumbsetThumbsetThumbmCaptchaWidth:$mCaptchaWidth,mCaptchaX:$mCaptchaX,mCaptchaY:$mCaptchaY"
            )
//            setPuzzlePath()


            puzzlePath = PuzzlePathUtils.getPuzzlePath(0, 0, mCaptchaWidth)


            val bitmapDrawable = mResource as BitmapDrawable
            val bitmap = bitmapDrawable.bitmap
            Log.e(TAG, "bitmapbitmapbitmapbitmapbitmap" + bitmap.width + "bitmap.height" + bitmap.height)
//


            val clipBitmap = ImageUtils.clip(bitmap, mCaptchaX, mCaptchaY, mCaptchaWidth, mCaptchaWidth)
            val cirleBitmap = getCirleBitmap(clipBitmap)

            var newThumb = BitmapDrawable(resources, cirleBitmap)
            seekbar_pazzle.thumb = newThumb
            seekbar_pazzle.thumbOffset = 50
            seekbar_pazzle.requestLayout()

            val layoutParams = seekbar_pazzle.layoutParams as FrameLayout.LayoutParams
            layoutParams.setMargins(0, mCaptchaY, 0, 0)
            seekbar_pazzle.layoutParams = layoutParams
        }

    }

}
