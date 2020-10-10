package almostlover.com.viewcollection.activitys

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.views.interpolator.EaseInOutBackInterpolator
import android.animation.Animator
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private var mScreenWidth: Int = 0
    private var mScreenHeight: Int = 0

    private var view2: View? = null//密码
    private var rl_register: RelativeLayout? = null
    internal var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1 -> {
                    val view = msg.obj as View
                    startRegistAnimation(view)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        for (i in 0 until rl.childCount) {
            val view = rl.getChildAt(i)
            Log.e(TAG, "" + i)
        }
        view2 = findViewById(R.id.editText1)
        getScreenHeight()
        findViewById<View>(R.id.btn_next).setOnClickListener {
            if (TextUtils.isEmpty(editText!!.text.toString())) {
                goToRigister()
            } else {
                goToPassword()
            }
        }


    }

    /**
     * 调到注册界面
     */
    private fun goToRigister() {
        //让外面的布局显示
        val objectAnimator = ObjectAnimator.ofFloat(editText, "translationX", 0.toFloat(), -mScreenWidth.toFloat())
        objectAnimator.setInterpolator(EaseInOutBackInterpolator())
        objectAnimator.setDuration(1000)
        objectAnimator.start()
        objectAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {

            }

            override fun onAnimationEnd(animator: Animator) {
                //结束的时候 隐藏手机号EditText  显示相对布局
                editText!!.visibility = View.INVISIBLE
                rl_register!!.visibility = View.VISIBLE
                for (i in 0 until rl_register!!.childCount) {
                    val view = rl_register!!.getChildAt(i)
                    val message = Message.obtain()
                    message.obj = view
                    message.what = 1
                    handler.sendMessageDelayed(message, (100 * i).toLong())
                }

            }

            override fun onAnimationCancel(animator: Animator) {

            }

            override fun onAnimationRepeat(animator: Animator) {

            }
        })


    }

    /**
     * 手机跳密码的动画
     */
    private fun goToPassword() {
        val objectAnimator = ObjectAnimator.ofFloat(editText, "translationX", 0f, -mScreenWidth.toFloat())
        objectAnimator.setInterpolator(EaseInOutBackInterpolator())
        objectAnimator.setDuration(1000)
        objectAnimator.start()
        objectAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {

            }

            override fun onAnimationEnd(animator: Animator) {
                //结束的时候 隐藏手机号EditText  显示密码的框 并执行
                editText!!.visibility = View.GONE
                view2!!.visibility = View.VISIBLE
                startPassWordInAnimation()

            }

            override fun onAnimationCancel(animator: Animator) {

            }

            override fun onAnimationRepeat(animator: Animator) {

            }
        })
    }

    /**
     * 密码进入的动画
     */
    private fun startPassWordInAnimation() {
        val objectAnimator = ObjectAnimator.ofFloat(view2, "translationX", mScreenWidth.toFloat(), 0.toFloat())
        objectAnimator.setInterpolator(EaseInOutBackInterpolator())
        objectAnimator.setDuration(300)
        objectAnimator.start()
    }


    fun getScreenHeight() {
        val wm = this.windowManager
        mScreenHeight = wm.defaultDisplay.height
        mScreenWidth = wm.defaultDisplay.width
        Log.e(TAG, "mScreenHeight:$mScreenHeight")
    }

    fun startRegistAnimation(view: View) {
        view.visibility = View.VISIBLE
        val objectAnimator = ObjectAnimator.ofFloat(view, "translationX", mScreenWidth.toFloat(), 0.toFloat())
        objectAnimator.setInterpolator(EaseInOutBackInterpolator())
        objectAnimator.setDuration(600)
        objectAnimator.start()
    }
}
