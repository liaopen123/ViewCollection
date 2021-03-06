package almostlover.com.viewcollection.activitys.recyclerviewviewpagertest2

import android.content.Context
import androidx.viewpager.widget.ViewPager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView

class MyScrollView(context: Context,attr:AttributeSet ):ScrollView(context,attr){

    private var intercept: Boolean = false
    var mContext = context
        var TAG = "MyScrollView"


    enum class RVState{
        top,
        center,
        bottom,
    }


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if(intercept){
            return true
        }else{
            return super.onInterceptTouchEvent(ev)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        val ll = getChildAt(0)  as LinearLayout//LinearLayout
        val recyclerView = ll.getChildAt(0) as androidx.recyclerview.widget.RecyclerView //第一个是recyclerview
        val viewpager = ll.getChildAt(1) as androidx.viewpager.widget.ViewPager //第一个是recyclerview



    }


    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        Log.e(TAG,"L$l,t$t,oldl$oldl,oldt$oldt")
        intercept = l<100
    }


}