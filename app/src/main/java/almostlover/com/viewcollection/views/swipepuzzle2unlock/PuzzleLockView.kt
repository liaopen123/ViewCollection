package com.shunguang.vip.view.swipepuzzle2unlock

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.utils.ImageUtils
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.shunguang.vip.view.swipepuzzle2unlock.seekbar.CanNotTouchSeekBar
import com.shunguang.vip.view.swipepuzzle2unlock.util.PuzzlePathUtils
import kotlinx.android.synthetic.main.view_puzzle_lock.view.*
import com.bumptech.glide.request.RequestOptions



class PuzzleLockView(context: Context, attr: AttributeSet) : LinearLayout(context, attr) {
    private var mProgress: Int = 0  //随机生成的进度
    var sb_progress: CanNotTouchSeekBar   //底部滑动的进度条
    var iv_bg: SwipeImageView
    var seekbar_pazzle: SeekBar
    val TAG = "PuzzleLockView"

    private var mCaptchaX: Int = 0
    private var mCaptchaY: Int = 0
    private var mResource: Drawable? = null
    private var mCaptchaWidth: Int = 0

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_puzzle_lock, this) as ViewGroup
        sb_progress = view.findViewById(R.id.sb_progress)
        iv_bg = view.findViewById(R.id.iv_bg)
        seekbar_pazzle = view.findViewById(R.id.seekbar_pazzle)
        seekbar_pazzle.isEnabled = false
        initListener()
    }


    private fun initListener() {
        //拖动seekbar 的progress 回调给 图片中的seekbar
        sb_progress.setXchangeListener(object : CanNotTouchSeekBar.XchangeListener {
            override fun onResult(progress: Int) {
                if (mProgress - 1 <= progress && progress <= mProgress + 1) {
                    //认证成功
                    Toast.makeText(context, "认证成功", Toast.LENGTH_LONG).show()
                } else {
                    //认证失败
                    Toast.makeText(context, "认证失败,请重新认证", Toast.LENGTH_LONG).show()
                    sb_progress.verifyFailed()
                }
            }

            override fun onProgressChange(progress: Int) {
                seekbar_pazzle.progress = progress
            }
        })

        //背景图  随机生成凹槽部位的图片后  把图片的凹槽位置回调过来 再设置seekbar的拼图样式
        iv_bg.setYChangeListener(object : SwipeImageView.YchangeListener {
            override fun onYchanged(x: Int, y: Int, width: Int, progress: Int) {

                mCaptchaX = x
                mCaptchaY = y
                mCaptchaWidth = width
                mProgress = progress
                //延迟100ms去生成thumb
                iv_bg.postDelayed({ setThumb() }, 100)
            }


        })



        loadImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555869647256&di=53ee0f3c3447c24b4f8c74d793ffdff6&imgtype=0&src=http%3A%2F%2Fattachments.gfan.net.cn%2Fforum%2F201806%2F05%2F145829js0kwvpt00w7sw0q.jpg")

        //刷新
        iv_refresh.setOnClickListener {


            refreshData("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555932694064&di=01d3dc34afcfd28bc7da8e3a800ab58a&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F014a8d5655b2f232f87512f6cb9650.jpg%402o.jpg")
        }


    }

    private fun loadImage(url: String) {

        val options = RequestOptions().transforms(CenterCrop(),RoundedCorners(8))

        Glide.with(this)
            .load(url)
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
            }).apply(options)
            .into(iv_bg)
    }

    private fun refreshData(url: String) {
        loadImage(url)//加载图片
        iv_bg.refreshData() //刷新控件

    }


    private fun setThumb() {
        if (mResource != null && mCaptchaWidth > 0 && mCaptchaX > 0 && mCaptchaY > 0) {
            var puzzlePath = PuzzlePathUtils.getPuzzlePath(0, 0, mCaptchaWidth)
            val bitmapDrawable = mResource as BitmapDrawable
            val bitmap = bitmapDrawable.bitmap
            Log.e(TAG, "bitmap" + bitmap.width + "bitmap.height" + bitmap.height)
            val puzzleBitmap = ImageUtils.clipAndGetPuzzleBitmap(
                bitmap,
                mCaptchaX,
                mCaptchaY,
                mCaptchaWidth,
                mCaptchaWidth,
                puzzlePath
            )

            var newThumb = BitmapDrawable(resources, puzzleBitmap)
            seekbar_pazzle.thumb = newThumb
            seekbar_pazzle.thumbOffset = 50

            val layoutParams = seekbar_pazzle.layoutParams as FrameLayout.LayoutParams
            layoutParams.setMargins(0, mCaptchaY, 0, 0)
            seekbar_pazzle.layoutParams = layoutParams
        }
    }
}