package almostlover.com.viewcollection.utils

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream


object AssetsFileUtil {
    /**
     * 获取assets目录下的图片
     *
     * @param context
     * @param fileName
     * @return
     */
    fun getImageFromAssetsFile(context: Context, fileName: String?): Bitmap? {
        var image: Bitmap? = null
        val am: AssetManager = context.getResources().getAssets()
        try {
            val `is`: InputStream = am.open(fileName)
            image = BitmapFactory.decodeStream(`is`)
            `is`.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return image
    }

    /**
     * 获取assets目录下的单个文件
     *
     * @param context
     * @param fileName
     * @return
     */
    fun getFileFromAssetsFile(
        context: Context?,
        fileName: String
    ): File { //这种方式不能用，只能用于webview加载，直接取路径是不行的
        val path = "file:///android_asset/$fileName"
        return File(path)
    }

    /**
     * 获取所有文件
     *
     * @param path
     * @return
     */
    fun getfilesFromAssets(context: Context, path: String?): Array<String>? {
        val assetManager: AssetManager = context.getAssets()
        var files: Array<String>? = null
        try {
            files = assetManager.list(path)
        } catch (e: IOException) { // TODO Auto-generated catch block
            e.printStackTrace()
        }
        return files
    }

    /**
     * 将assets下的文件放到sd指定目录下
     *
     * @param context    上下文
     * @param assetsPath assets下的路径
     * @param sdCardPath sd卡的路径
     */
    fun putAssetsToSDCard(
        context: Context, assetsPath: String,
        sdCardPath: String
    ) {
        var sdCardPath = sdCardPath
        try {
            val mString: Array<String> = context.getAssets().list(assetsPath)
            if (mString.size == 0) { // 说明assetsPath为空,或者assetsPath是一个文件
                val mIs: InputStream = context.getAssets().open(assetsPath) // 读取流
                val mByte = ByteArray(1024)
                var bt = 0
                val file = File(
                    sdCardPath + File.separator
                        .toString() + assetsPath.substring(assetsPath.lastIndexOf('/'))
                )
                if (!file.exists()) {
                    file.createNewFile() // 创建文件
                } else {
                    return  //已经存在直接退出
                }
                val fos = FileOutputStream(file) // 写入流
                while (mIs.read(mByte).also({ bt = it }) != -1) { // assets为文件,从文件中读取流
                    fos.write(mByte, 0, bt) // 写入流到文件中
                }
                fos.flush() // 刷新缓冲区
                mIs.close() // 关闭读取流
                fos.close() // 关闭写入流
            } else { // 当mString长度大于0,说明其为文件夹
                sdCardPath = sdCardPath + File.separator.toString() + assetsPath
                val file = File(sdCardPath)
                if (!file.exists()) file.mkdirs() // 在sd下创建目录
                for (stringFile in mString) { // 进行递归
                    putAssetsToSDCard(
                        context, assetsPath + File.separator
                            .toString() + stringFile, sdCardPath
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}