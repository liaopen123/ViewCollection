package almostlover.com.viewcollection.activitys.wallpaper

import almostlover.com.viewcollection.R
import android.app.WallpaperManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.widget.TextView


class WallPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.decorView.setWillNotDraw(true)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER)
        setContentView(almostlover.com.viewcollection.R.layout.activity_wall_pager)
        val tv = findViewById<TextView>(R.id.tv)
        val root_view = findViewById<ConstraintLayout>(R.id.root_view)
        tv.postDelayed({
            runOnUiThread {

                tv.text = "hahahahahahaha"
            }
        }, 2000)


        //方式二 获取壁纸  但是只能获取到静态壁纸  动态壁纸是一张错图
        val bg = WallpaperManager.getInstance(this@WallPagerActivity).drawable
        root_view.background = bg

    }

    //方式3 如果是动态就获取thumb   如果静态 直接获取
    private fun getWallpaperDrawable(): Drawable {
        val wallpaperDrawable: Drawable
        val pm = applicationContext.packageManager
        val wallpaperManager = WallpaperManager.getInstance(this)
        if (wallpaperManager.wallpaperInfo != null) {
            /*
             * Wallpaper info is not equal to null, that is if the live wallpaper
             * is set, then get the drawable image from the package for the
             * live wallpaper
             */
            wallpaperDrawable = wallpaperManager
                .wallpaperInfo.loadThumbnail(pm)
        } else {
            /*
             * Else, if static wallpapers are set, then directly get the
             * wallpaper image
             */
            wallpaperDrawable = wallpaperManager.drawable
        }
        return wallpaperDrawable
    }

}
