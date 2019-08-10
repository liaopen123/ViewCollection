package almostlover.com.viewcollection.utils.adjust

import android.app.Activity
import android.app.Application

class ByteDanceAdjust{


    companion object{

        fun setCustomDensity(activity:Activity,application: Application){
            val appDisplayMetrics = application.resources.displayMetrics
            val targetDensity = appDisplayMetrics.widthPixels / 360
            val targetDensityDpi = (160 * targetDensity) as Int


        }

    }

}