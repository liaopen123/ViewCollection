package almostlover.com.viewcollection.views.postview

import almostlover.com.viewcollection.R
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_post_with_text.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import kotlin.math.roundToInt

class PostView @JvmOverloads constructor(
    var mContext: Context,
    private var attributeSet: AttributeSet? = null, style: Int = 0
) : FrameLayout(mContext, attributeSet, style), AnkoLogger,
    ViewTreeObserver.OnGlobalLayoutListener {


    private var ivObserver: ViewTreeObserver?=null

    init {
        initView()
    }

    private fun initView() {
        View.inflate(mContext, R.layout.view_post_with_text, this)
         ivObserver = iv_post.viewTreeObserver
        ivObserver?.addOnGlobalLayoutListener(this)
    }


    override fun onGlobalLayout() {
        val height = iv_post.measuredHeight
        val width = iv_post.measuredWidth

        info { "图片宽:$width ,高:$height" }
        var marLeft = width * 0.12
        var marTop = height * 0.3043

        var fontSize = height*0.03
        tv_name.setTextSize(TypedValue.COMPLEX_UNIT_PX,fontSize.toFloat());
        val layoutParams = tv_name.layoutParams as RelativeLayout.LayoutParams
        layoutParams.leftMargin = marLeft.roundToInt()
        layoutParams.topMargin = marTop.roundToInt()
        tv_name.layoutParams = layoutParams
        iv_post.viewTreeObserver.removeOnGlobalLayoutListener(this@PostView)

    }
}