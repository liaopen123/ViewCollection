package almostlover.com.viewcollection.utils

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.view.View
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


/**
 * Author : Ziwen Lan
 * Date : 2019/10/23
 * Time : 15:11
 * Introduction : 截屏工具类
 */
object ScreenshotUtil {
    /**
     * 截取指定activity显示内容
     * 需要读写权限
     */
    fun saveScreenshotFromActivity(activity: Activity) {
        val view: View = activity.window.decorView
        view.setDrawingCacheEnabled(true)
        val bitmap: Bitmap = view.getDrawingCache()
        saveImageToGallery(bitmap, activity)
        //回收资源
        view.setDrawingCacheEnabled(false)
        view.destroyDrawingCache()
    }

    /**
     * 截取指定View显示内容
     * 需要读写权限
     */
    fun saveScreenshotFromView(bitmap: Bitmap, context: Activity) {
        saveImageToGallery(bitmap, context)
    }

    /**
     * 保存图片至相册
     * 需要读写权限
     */
    private fun saveImageToGallery(bmp: Bitmap, context: Activity) {
        val appDir = File(dCIM)
        if (!appDir.exists()) {
            appDir.mkdir()
        }
        val fileName = System.currentTimeMillis().toString() + ".jpg"
        val file = File(appDir, fileName)
        try {
            val fos = FileOutputStream(file)
            bmp.compress(Bitmap.CompressFormat.JPEG, 50, fos)
            fos.flush()
            fos.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        // 通知图库更新
        context.sendBroadcast(
            Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.parse("file://$dCIM")
            )
        )
    }

    /**
     * 获取相册路径
     */
    private val dCIM: String
        private get() {
            if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                return ""
            }
            var path: String =
                Environment.getExternalStorageDirectory().getPath().toString() + "/dcim/"
            if (File(path).exists()) {
                return path
            }
            path = Environment.getExternalStorageDirectory().getPath().toString() + "/DCIM/"
            val file = File(path)
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    return ""
                }
            }
            return path
        }
}