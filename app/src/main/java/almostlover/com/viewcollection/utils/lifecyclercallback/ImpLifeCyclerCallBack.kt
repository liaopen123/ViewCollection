package almostlover.com.viewcollection.utils.lifecyclercallback

import android.app.Activity
import android.app.Application
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView


class ImpLifeCyclerCallBack : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity?) {

        try {
            val textView = TextView(activity)
            //这里的Textview的父layout是ListView，所以要用ListView.LayoutParams


            val layoutParams =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            textView.layoutParams = layoutParams
            textView.setPadding(20, 0, 20, 0)
            textView.text = "我是后期添加的文字"
            textView.setTextColor(Color.BLACK)
            textView.textSize = 40f
            val childAt = activity?.findViewById<ViewGroup>(android.R.id.content)
            val childAt1 = childAt?.getChildAt(0)
            val vg = childAt1 as ViewGroup
            vg.addView(textView)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onActivityResumed(activity: Activity?) {
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {


    }

}