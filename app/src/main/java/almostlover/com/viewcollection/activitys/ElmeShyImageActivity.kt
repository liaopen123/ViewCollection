package almostlover.com.viewcollection.activitys

import almostlover.com.viewcollection.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_elme_shy_image.*

class ElmeShyImageActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName
    private val x: Float = 0.toFloat()
    internal var scrolling = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elme_shy_image)

        iv!!.setOnClickListener(View.OnClickListener {
            Toast.makeText(
                this,
                "HAHAHAHAH",
                Toast.LENGTH_SHORT
            ).show()
        })


        rv.setAdapter(object : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(p0: ViewGroup, p1: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
                return ViewHolder1(getLayoutInflater().inflate(R.layout.item_lv, p0, false))
            }

            override fun getItemCount(): Int {
                return 200
            }

            override fun onBindViewHolder(p0: androidx.recyclerview.widget.RecyclerView.ViewHolder, p1: Int) {
            }

        })

        //        AnimatorSet anim = (AnimatorSet)AnimatorInflater.loadAnimator(MainActivity.this, R.anim.translatex);
        //        iv.setPivotX(0);
        //        iv.setPivotY(0);
        //        //显示的调用invalidate
        //        iv.invalidate();
        //        anim.setTarget(iv);

        //        anim.start();


        rv.addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: androidx.recyclerview.widget.RecyclerView, newState: Int) {
                when (newState) {
                    //停止滚动
                    androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE//空闲状态
                    -> {
                        //执行显示的动画
                        //                        延迟500ms 如果又变成滚动状态 就取消showView
                        scrolling = false
                        iv!!.postDelayed(Runnable {
                            if (!scrolling) {
                                if (iv!!.isHasHide) {
                                    iv!!.showView()
                                }
                            }
                        }, 500)

                        Log.e(TAG, "空闲状态")
                    }
                    ////自动滚动开始
                    androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_SETTLING
                    -> scrolling = true

                    ////正在被外部拖拽,一般为用户正在用手指滚动
                    androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING
                    -> {
                        scrolling = true
                        if (!iv!!.isHasHide) {
                            iv!!.hideView()
                        }

                        Log.e(TAG, "触摸后滚动")
                    }
                }//执行隐藏的动画
                //    iv.hideView();
                //  Log.e(TAG,"滚动状态");


                super.onScrollStateChanged(recyclerView, newState)
            }
        })

    }

    internal inner class ViewHolder1(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    }
}
