package cn.com.almostlover.pushtest

import almostlover.com.viewcollection.activitys.push.ViewUtils
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.TaskStackBuilder
import android.widget.Toast


class ParseIntentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(ViewUtils.isLaunchedActivity(this@ParseIntentActivity,MainActivity::class.java)){
            Toast.makeText(this@ParseIntentActivity,"已经开启过",Toast.LENGTH_SHORT).show()
            val intent = Intent(this@ParseIntentActivity, DetailsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }else {
            Toast.makeText(this@ParseIntentActivity,"没有开启",Toast.LENGTH_SHORT).show()
            var intent1 = Intent(this@ParseIntentActivity, DetailsActivity::class.java)
            val stackBuilder = TaskStackBuilder.create(this)
            stackBuilder.addParentStack(intent1.getComponent())
            stackBuilder.addNextIntent(intent1)
            stackBuilder.startActivities()
        }

        finish()
    }
}
