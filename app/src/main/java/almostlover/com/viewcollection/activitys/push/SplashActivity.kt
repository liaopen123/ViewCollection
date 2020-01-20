package cn.com.almostlover.pushtest

import almostlover.com.viewcollection.R
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)



        findViewById<View>(R.id.tv_splash).postDelayed(object :Runnable{
            override fun run() {
                startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                finish()
            }
        },3000);
    }
}
