package almostlover.com.viewcollection.webrtc

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.bean.CakeBean
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.toast
import org.webrtc.PeerConnectionFactory
import java.lang.StringBuilder

class Class3ClientActivity : AppCompatActivity() {


    var bools:ArrayList<Boolean> = ArrayList();
    var beans:ArrayList<CakeBean> = ArrayList();
    var numbsss:ArrayList<Int> = ArrayList();
    var strs:ArrayList<StringBuilder> = ArrayList();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class3_client)
        var  first :Boolean = true
        var  second :Boolean = false
        var  third :Boolean = false
        bools.add(first)
        bools.add(second)
        bools.add(third)

        bools[2] = true

var name :StringBuilder= StringBuilder("lph")
        strs.add(name)
        strs[0]=StringBuilder("gx")
var one = 111
        numbsss.add(one)
        numbsss[0]=222;
        var bean1= CakeBean(233)
        beans.add(bean1)
        beans[0].pointX=444
        toast("$third,和bools[2]：$bools[2]")
        toast("bean1${bean1.pointX}")
        toast("one${one}")

        toast(name)

    }
}
