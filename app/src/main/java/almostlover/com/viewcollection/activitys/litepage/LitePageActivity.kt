package almostlover.com.viewcollection.activitys.litepage

import almostlover.com.viewcollection.R
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_lite_page.*

class LitePageActivity : AppCompatActivity() {

    val TAG = "LitePageActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lite_page)


        view1.setOnClickListener {
            moveToTop(it)
        }
        view2.setOnClickListener {
            moveToTop(it)
        }

        view3.setOnClickListener {
            moveToTop(it)
        }

        view4.setOnClickListener {
            moveToTop(it)
        }

        for(item in 0..10){
            Log.e(TAG, "..$item")
        }

        for(item in 0 until 10){
            Log.e(TAG, "until:$item")
        }

    }

    private fun moveToTop(it: View) {
        //先确定现在在哪个位置
        val startIndex = viewgroup.indexOfChild(it)
        val count = viewgroup.childCount - 1 - startIndex

        for (index in 0 until  count) {
            val fromIndex = viewgroup.indexOfChild(it)

            val toIndex = fromIndex + 1
            //先获取需要交换的2个view
            var from = it
            var to = viewgroup.getChildAt(toIndex)

            //先把它们拿出来
            viewgroup.detachViewFromParent1(from)
            viewgroup.detachViewFromParent1(to)

            //再放回去
            viewgroup.attachViewToParent1(to, fromIndex, to.layoutParams)
            viewgroup.attachViewToParent1(from, toIndex, from.layoutParams)

        }
        //刷新
        viewgroup.invalidate();
    }
}
