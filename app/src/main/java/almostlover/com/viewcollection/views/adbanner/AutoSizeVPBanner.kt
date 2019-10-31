package almostlover.com.viewcollection.views.adbanner

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.utils.lphLog
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.flyco.pageindicator.indicator.FlycoPageIndicaor
import org.jetbrains.anko.layoutInflater
import java.lang.Exception

class AutoSizeVPBanner @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    style: Int = 0
) : FrameLayout(context, attributeSet, style) {

    private var neverScrolled: Boolean = true
    var viewpager: ViewPager? = null
    var indicator: FlycoPageIndicaor? = null
    var iv_close: ImageView? = null
    var heights: ArrayList<Int> = ArrayList()
    var urls: ArrayList<String>? = null

    init {
        initView()
    }

    private fun initView() {

        val view = LayoutInflater.from(context).inflate(R.layout.view_autosize_banner, this, true)
        viewpager = view.findViewById<ViewPager>(R.id.viewpager)
        indicator = view.findViewById<FlycoPageIndicaor>(R.id.indicator)
        iv_close = view.findViewById<ImageView>(R.id.iv_close)

    }

    fun setData(urls: ArrayList<String>) {
        this.urls = urls
        var viewpager = viewpager!!




        viewpager.adapter = AdAdapter()

        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(p0: Int, positionOffset: Float, p2: Int) {
                lphLog("onPageScrolled:positionOffset:$positionOffset,,position:$p0,heights:$heights")
                try {
                    if (heights.size > 0) {


                        val height =
                            ((if (heights[p0] === 0) 100 else heights[p0]) * (1 - positionOffset) + (if (heights[p0 + 1] === 0) 100 else heights[p0 + 1]) * positionOffset).toInt()

                        var params: ViewGroup.LayoutParams = viewpager.layoutParams
                        params.height = height
                        viewpager.layoutParams = params
                    }
                } catch (e: Exception) {

                }

            }

            override fun onPageSelected(p0: Int) {

            }

            override fun onPageScrollStateChanged(p0: Int) {
            }

        })

        initIndicator(urls)
    }


    private fun initIndicator(urls: ArrayList<String>) {
        if (urls.size < 2) {
            this.indicator!!.visibility = View.GONE
            return
        } else {
            indicator!!.visibility = View.VISIBLE
            indicator!!.setViewPager(viewpager)
        }
    }


    inner class AdAdapter(
    ) : PagerAdapter() {

        override fun getCount(): Int {
            return urls!!.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val advInfo = urls?.get(position)

            val rootView = context.layoutInflater.inflate(R.layout.viewpager_item, null)
            val simpleDraweeView = rootView.findViewById(R.id.simpleDraweeView) as ImageView
            val params = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            container.addView(rootView, params)


            val simpleTarget = object : SimpleTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    //这里可以做复杂的图片变换处理，如下只是简单的显示在imageView上
                    simpleDraweeView.setImageDrawable(resource)
//                   var bitmapDrawable:BitmapDrawable;
//                   bitmapDrawable.

                    val height = resource.intrinsicHeight
                    if (heights.size > position && heights[position] != 0) {

                    } else {
                        heights.add(position, height)
                    }
                    if (neverScrolled) {
                        notifyViewPageHeight()
                        neverScrolled = false
                    }

                }
            }
            Glide.with(context)
                .load(advInfo)
                .into(simpleTarget)


            return rootView
        }
    }

    private fun notifyViewPageHeight() {

        var params: ViewGroup.LayoutParams = viewpager?.layoutParams!!
        params.height = heights[0]
        viewpager!!.layoutParams = params
    }

}
