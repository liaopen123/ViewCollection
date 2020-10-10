package almostlover.com.viewcollection.activitys

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.utils.ScreenshotUtil
import almostlover.com.viewcollection.utils.Snapshot
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_image_in.*
import org.jetbrains.anko.AnkoLogger


/**
 * 主要测试图片
 */
class ImageInActivity : AppCompatActivity(),AnkoLogger {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_in)
        tv_circule.setOnClickListener {  }
        tv_friend.setOnClickListener {  }
        tv_save.setOnClickListener {

            val bitmap = Snapshot(view_post).apply()
            ScreenshotUtil.saveScreenshotFromView(bitmap!!,this)
        }

//        img.setOnClickListener {
//            val DM: DisplayMetrics = resources.displayMetrics
//            info { "当前手机dpi:${DM.densityDpi},宽度:${DM.widthPixels},,高度:${DM.heightPixels},密度:${DM.density}" }
//            info { " 当前手机img.width:${ img.width},, img.height:${ img.height}" }
//        }
    }
}