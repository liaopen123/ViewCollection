package almostlover.com.viewcollection.activitys.listen_sms.boardcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class SmsBroadcast  : BroadcastReceiver(){




    override fun onReceive(context: Context?, intent: Intent?) {
        val content = StringBuilder()  //短信内容
        var sender: String   //对方手机号
        val format = intent!!.getStringExtra("format")

        intent?.extras?.apply {
            val pdus = get("pdus") as Array<*>
            for (item in pdus){
                val message =
                    android.telephony.gsm.SmsMessage.createFromPdu(item as ByteArray)

                sender = message.originatingAddress
                content.append(message.messageBody)
            }
        }
    }


}