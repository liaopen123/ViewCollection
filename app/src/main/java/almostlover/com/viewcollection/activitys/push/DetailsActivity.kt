package cn.com.almostlover.pushtest

import almostlover.com.viewcollection.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast


class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Toast.makeText(this@DetailsActivity,"C已经存在，再startActivity就会调用onNewIntent方法",Toast.LENGTH_SHORT).show()
    }
}
