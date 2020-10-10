package almostlover.com.viewcollection.activitys

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.adapter.AdapterFlipperViewAdapter
import almostlover.com.viewcollection.views.flipperview.AnnouncementItemView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.TranslateAnimation
import android.widget.AdapterViewFlipper
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_view_flipper.*
import org.jetbrains.anko.toast

class ViewFlipperActivity : AppCompatActivity() {
    var start = true
    private lateinit var mActivity: ViewFlipperActivity
    var mStrList = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_flipper)
        mActivity = this
        initData()
        initViewFlipper()
        initAdapterViewFlipper()
        btn.setOnClickListener {
            if(start){
                adapterViewFlipper.stopFlipping()
            }else{
                adapterViewFlipper.startFlipping()
            }
            start = !start
        }
    }



    private fun initData() {
        for (i in 1..15) {
            mStrList.add("这是第" + i + "条公告")
        }
    }

    private fun initViewFlipper() {

        for((index,str)in mStrList.withIndex()){
            val textView = AnnouncementItemView(this)
            textView.setText(str)
            textView.setOnClickListener {
                toast(str)
            }

            viewFlipper.addView(textView)
        }


        setViewFlipperAnimation()
    }

    private fun setViewFlipperAnimation() {
        val inAnimation = TranslateAnimation(0f, 0f, 100f, 0f)
        inAnimation.duration = 1500
        //CnPeng 2018/11/30 4:20 PM 此处不需要调用start。viewFlipper在展示child的时候会主动触发动画。但是，调用了在界面上也看不出特殊
        //        inAnimation.start()

        val outAnimation = TranslateAnimation(0f, 0f, 0f, -100f)
        outAnimation.duration = 1500
        //        outAnimation.start()

        viewFlipper.inAnimation = inAnimation
        viewFlipper.outAnimation = outAnimation
        viewFlipper.startFlipping()
    }



    private fun initAdapterViewFlipper() {
        val adapterFlipperViewAdapter = AdapterFlipperViewAdapter(mStrList, mActivity)
        adapterViewFlipper.adapter = adapterFlipperViewAdapter
        adapterViewFlipper.stopFlipping()

    }


}
