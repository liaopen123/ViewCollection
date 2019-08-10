package almostlover.com.viewcollection.utils.adjust

import android.content.res.Resources
import android.util.DisplayMetrics

class ResourceWrapper(resource:Resources,autoSize:AutoSize):Resources(resource.assets,resource.displayMetrics,resource.configuration){
    val autoSize = autoSize
    var targetDensity:Float = 0f
    var targetScaledDensity:Float = 0f
    var targetDensityDpi:Int = 0


    override fun getDisplayMetrics(): DisplayMetrics {
        val displayMetrics = super.getDisplayMetrics()
        initValue(displayMetrics)
        autoSize(displayMetrics)
        return super.getDisplayMetrics()
    }

    private fun initValue(displayMetrics: DisplayMetrics?) {
        if(targetDensity ==0f){

        }

    }

    private fun autoSize(displayMetrics: DisplayMetrics?) {

    }

}