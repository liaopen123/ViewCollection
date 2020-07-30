package almostlover.com.viewcollection.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View


class Snapshot(view: View, factor: Float) {
    var view: View
    var memoryFactor = 0.5f

    constructor(view: View) : this(view, 0.5f) {}

    fun apply(): Bitmap? {
        val mode = chooseMode(view) ?: return null
        val target = Bitmap.createBitmap(mode.mWidth, mode.mHeight, mode.mConfig)
        val canvas = Canvas(target)
        if (mode.mWidth != mode.mSourceWidth) {
            val scale = 1f * mode.mWidth / mode.mSourceWidth
            canvas.scale(scale, scale)
        }
        view.draw(canvas)
        return target
    }

    fun chooseMode(view: View): Mode {
        return chooseMode(view.getWidth(), view.getHeight())
    }

    fun chooseMode(width: Int, height: Int): Mode {
        val mode: Mode
        val max = Runtime.getRuntime().maxMemory()
        val total = Runtime.getRuntime().totalMemory()
        var remain = max - total // 剩余可用内存
        remain = (memoryFactor * remain).toLong()
        var w = width
        var h = height
        while (true) {

            // 尝试4个字节
            var memory = 4 * w * h.toLong()
            if (memory <= remain) {
                if (memory <= remain / 3) { // 优先保证保存后的图片文件不会过大，有利于分享
                    mode = Mode(Bitmap.Config.ARGB_8888, w, h, width, height)
                    break
                }
            }

            // 尝试2个字节
            memory = 2 * w * h.toLong()
            if (memory <= remain) {
                mode = Mode(Bitmap.Config.RGB_565, w, h, width, height)
                break
            }

            // 判断是否可以继续
            if (w % 3 != 0) {
                h = (remain / 2 / w).toInt() // 计算出最大高度
                h = h / 2 * 2 // 喜欢偶数
                mode = Mode(Bitmap.Config.RGB_565, w, h, width, height)
                break
            }

            // 缩减到原来的2/3
            w = w * 2 / 3
            h = h * 2 / 3
        }
        return mode
    }

    /**
     *
     */
    class Mode internal constructor(
        var mConfig: Bitmap.Config,
        var mWidth: Int,
        var mHeight: Int,
        var mSourceWidth: Int,
        var mSourceHeight: Int
    )

    init {
        this.view = view
        memoryFactor = if (factor > 0.9f || factor < 0.1f) 0.5f else factor
    }
}

