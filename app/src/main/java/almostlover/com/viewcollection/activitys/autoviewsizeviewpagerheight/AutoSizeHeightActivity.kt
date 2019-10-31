package almostlover.com.viewcollection.activitys.autoviewsizeviewpagerheight

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.utils.lphLog
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.activity_auto_size_height.*
import java.lang.Exception


class AutoSizeHeightActivity : AppCompatActivity() {
    private var neverScrolled: Boolean =true
    private lateinit var mContext: AutoSizeHeightActivity
    var urls: ArrayList<String> = ArrayList()
    var heights: ArrayList<Int> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_size_height)

        urls.add("https://ss0.baidu.com/73x1bjeh1BF3odCf/it/u=3310479589,761573373&fm=85&s=3A813160802793A77F92609C0300C081")
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1572435131207&di=c914bed06a0287d30047324bfb47fc2e&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201704%2F05%2F20170405114345_2G5BN.thumb.700_0.jpeg")
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1572451563402&di=835be097bf199a26d0794c82bf987b9a&imgtype=0&src=http%3A%2F%2Fimg8.zol.com.cn%2Fbbs%2Fupload%2F19025%2F19024566.JPG")
        urls.add("https://ss0.baidu.com/73x1bjeh1BF3odCf/it/u=3310479589,761573373&fm=85&s=3A813160802793A77F92609C0300C081")
        urls.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3683194211,1520759142&fm=26&gp=0.jpg")
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1572435131207&di=c914bed06a0287d30047324bfb47fc2e&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201704%2F05%2F20170405114345_2G5BN.thumb.700_0.jpeg")
        mContext = this






        viewpager.adapter = AdAdapter()

        viewpager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrolled(p0: Int, positionOffset: Float, p2: Int) {
          lphLog("onPageScrolled:positionOffset:$positionOffset,,position:$p0,heights:$heights")
                try {
                    if(heights.size>0) {


                        val height =
                            ((if (heights[p0] === 0) 100 else heights[p0]) * (1 - positionOffset) + (if (heights[p0+1] === 0) 100 else heights[p0+1]) * positionOffset).toInt()

                        var params: ViewGroup.LayoutParams = viewpager.layoutParams
                        params.height =height
                        viewpager.layoutParams = params
                    }
                }catch(e:Exception){

                }

            }

            override fun onPageSelected(p0: Int) {

            }

            override fun onPageScrollStateChanged(p0: Int) {
            }

        })

        initIndicator()
    }

    private fun initIndicator() {
        if (urls.size<2) {
            indicator.visibility=View.GONE
            return
        }else{
            indicator.visibility=View.VISIBLE
            indicator.setViewPager(viewpager)
        }
    }


    internal inner class AdAdapter : PagerAdapter() {

        override fun getCount(): Int {
            return urls.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val advInfo = urls[position]

            val rootView = mContext.layoutInflater.inflate(R.layout.viewpager_item, null)
            val simpleDraweeView = rootView.findViewById(R.id.simpleDraweeView) as ImageView
            val params = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            container.addView(rootView, params)


            val simpleTarget = object : SimpleTarget<Drawable>() {
               override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    //这里可以做复杂的图片变换处理，如下只是简单的显示在imageView上
                   simpleDraweeView.setImageDrawable(resource)
//                   var bitmapDrawable:BitmapDrawable;
//                   bitmapDrawable.

                   val height =     resource.intrinsicHeight
                   lphLog("position:$position,,,height:$height")
                   if(heights.size>position&&heights[position]!=0){

                   }else {
                       heights.add(position, height)
                   }
                   if (neverScrolled) {
                       notifyViewPageHeight()
                       neverScrolled =false
                   }

               }
            }
            Glide.with(mContext)
                .load(advInfo)
                .into(simpleTarget)


            return rootView
        }
    }

    private fun notifyViewPageHeight() {

        var params: ViewGroup.LayoutParams = viewpager.layoutParams
        params.height =heights[0]
        viewpager.layoutParams = params
    }


}
