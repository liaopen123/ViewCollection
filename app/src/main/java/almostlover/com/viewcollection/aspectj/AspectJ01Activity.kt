package almostlover.com.viewcollection.aspectj

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.aspectj.inject.DeBugTrace
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class AspectJ01Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aspect_j01)
        LogUtils.log()
        Toast.makeText(this,"11111111",Toast.LENGTH_LONG).show()
    }

    @DeBugTrace
    fun testAnnotatedMethod(){
        try{
            Thread.sleep(10)
        }catch (e:Exception ){
            e.printStackTrace()

        }
    }





}
