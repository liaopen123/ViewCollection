package almostlover.com.viewcollection.activitys

import almostlover.com.viewcollection.R
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_five_way_time_count.*
import java.lang.ref.WeakReference

/**
 * 5种倒计时实现的方式
 */
class FiveWayTimeCountActivity : AppCompatActivity() {

    companion object{
        var maxTime = 10
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_five_way_time_count)
//        val looperHandler = LooperHandler(this)
        tv_handler.setOnClickListener {
            //            looperHandler.postDelayed(object:Runnable{
//                override fun run() {
//                    val obtainMessage = looperHandler.obtainMessage(0)
//                    looperHandler.sendMessage(obtainMessage)
//                }
//            },1000)
//        }

            timerTask.setOnClickListener {

            }

            ScheduledExecutorService.setOnClickListener {

            }

            rxjava.setOnClickListener {

            }

            countDownTimer.setOnClickListener {

            }
        }


//
//    class LooperHandler:Handler{
//
//        private lateinit var weakReference: WeakReference<Context>
//
//        constructor(context:Context){
//             weakReference = WeakReference<Context>(context)
//        }
//
//
//        override fun handleMessage(msg: Message?) {
//            super.handleMessage(msg)
//            val context = weakReference.get()
//
//            when(msg?.what) {
//
//                0 ->{
//
//                    maxTime --
//                    if(maxTime<0){
//                        this.postDelayed(mRunnable, ONECE_TIME);
//                    }
//
//                }
//
//
//                1 ->{
//
//                }
//                1 ->{
//
//                }
//                1 ->{
//
//                }
//                1 ->{
//
//                }
//            }
//
//        }
//
//    }
    }
}
