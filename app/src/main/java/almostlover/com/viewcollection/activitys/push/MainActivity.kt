package cn.com.almostlover.pushtest

import almostlover.com.viewcollection.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push_main)
        findViewById<View>(R.id.tv_hello).setOnClickListener {
            startActivity(Intent(this@MainActivity,DetailsActivity::class.java))
        }
    }
}
