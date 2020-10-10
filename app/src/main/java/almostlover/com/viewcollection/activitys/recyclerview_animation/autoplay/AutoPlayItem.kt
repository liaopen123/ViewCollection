package almostlover.com.viewcollection.activitys.recyclerview_animation.autoplay

import android.view.View

interface AutoPlayItem {
    fun setActive()
    fun deactivate()
    val autoplayView: View
}