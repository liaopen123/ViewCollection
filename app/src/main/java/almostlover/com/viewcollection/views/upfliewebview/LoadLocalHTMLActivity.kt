package almostlover.com.viewcollection.views.upfliewebview

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.utils.AssetsFileUtil
import android.R.attr.path
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_load_local_html.*
import java.net.URI


class LoadLocalHTMLActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_local_html)
        wb.getSettings().setJavaScriptEnabled(true)
        wb.loadUrl("file:///android_asset/bargraph.html")
        WebChromeClient( )
        wb.setWebChromeClient(object :WebChromeClient(){
            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                val fileFromAssetsFile =
                    AssetsFileUtil.getFileFromAssetsFile(this@LoadLocalHTMLActivity, "example.csv")
                val uri = Uri.parse(fileFromAssetsFile.path)
                 var uris: Array<Uri> = arrayOf(uri)
                // 获得权限，启动Service开始录制


                uris[0] =uri
                filePathCallback?.onReceiveValue(uris)
                return true
            }
        })
//        webview.setWebChromeClient(new WebChromeClient() {
//
//            // For Android < 3.0
//            public void openFileChooser(ValueCallback<Uri> valueCallback) {
//                ***
//            }
//
//            // For Android  >= 3.0
//            public void openFileChooser(ValueCallback valueCallback, String acceptType) {
//                ***
//            }
//
//            //For Android  >= 4.1
//            public void openFileChooser(ValueCallback<Uri> valueCallback,
//                String acceptType, String capture) {
//                ***
//            }
//
//            // For Android >= 5.0
//            @Override
//            public boolean onShowFileChooser(WebView webView,
//                ValueCallback<Uri[]> filePathCallback,
//                WebChromeClient.FileChooserParams fileChooserParams) {
//                ***
//                return true;
//            }
//        });


    }
}
