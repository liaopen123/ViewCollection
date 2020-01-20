package almostlover.com.viewcollection.views.upfliewebview

import android.webkit.ValueCallback




interface WebFileChoseListener {
    fun getFile(valueCallback: ValueCallback<*>?)
}