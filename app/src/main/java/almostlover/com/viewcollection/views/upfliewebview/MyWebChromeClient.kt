package almostlover.com.viewcollection.views.upfliewebview

import android.net.Uri
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView


class MyWebChromeClient : WebChromeClient(){
    private var webFileChoseListener: WebFileChoseListener? = null
    // 3.0 + 调用这个方法
    fun openFileChooser(
        filePathCallback: ValueCallback<*>?,
        acceptType: String?
    ) {
        webFileChoseListener?.getFile(filePathCallback)
    }

    // Android > 4.1.1 调用这个方法
    fun openFileChooser(
        filePathCallback: ValueCallback<Uri?>?,
        acceptType: String?,
        capture: String?
    ) {
        if (webFileChoseListener != null) {
            webFileChoseListener!!.getFile(filePathCallback)
        }
    }

    // Android > 5.0 调用这个方法
    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri?>?>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        if (webFileChoseListener != null) {
            webFileChoseListener!!.getFile(filePathCallback)
        }
        return true
    }

    fun setBnWebFileChoseListener(webFileChoseListener: WebFileChoseListener?) {
        this.webFileChoseListener = webFileChoseListener
    }


}