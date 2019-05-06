package almostlover.com.viewcollection.activitys

import almostlover.com.viewcollection.R
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_title_transform.*


class TitleTransformActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_transform)


        tv_first_title.setOnClickListener {
            upTransView()
        }

        tv_second_title.setOnClickListener {
            downTransView()
        }

    }

    private fun upTransView() {
        val animator = ObjectAnimator.ofFloat(tv_first_title, "translationY", 0f, -300f)
        animator.duration = 1000
        animator.start()
        val animator1 = ObjectAnimator.ofFloat(tv_second_title, "translationY", 300f, 0f)
        animator1.duration = 1000
        animator1.start()
    }

    private fun downTransView() {
        val animator = ObjectAnimator.ofFloat(tv_first_title, "translationY", -300f, 0f)
        animator.duration = 1000
        animator.start()
        val animator1 = ObjectAnimator.ofFloat(tv_second_title, "translationY", 0f, 300f)
        animator1.duration = 1000
        animator1.start()
    }


    override fun onStart() {
        super.onStart()

        upTransView()
    }



}

