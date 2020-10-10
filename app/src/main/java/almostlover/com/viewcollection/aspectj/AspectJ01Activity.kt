package almostlover.com.viewcollection.aspectj

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.aspectj.inject.DeBugTrace
import almostlover.com.viewcollection.aspectj01.Animal
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class AspectJ01Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aspect_j01)
        LogUtils.log()
        Toast.makeText(this, "11111111", Toast.LENGTH_LONG).show()
        val animal = Animal()
        animal.fly()
    }

    @DeBugTrace
    fun testAnnotatedMethod() {
        try {
            Thread.sleep(10)
        } catch (e: Exception) {
            e.printStackTrace()

        }
    }


}
