package almostlover.com.viewcollection.activitys.markdown

import almostlover.com.viewcollection.R
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import com.vladsch.flexmark.util.ast.Node
import com.vladsch.flexmark.util.data.MutableDataSet
import kotlinx.android.synthetic.main.activity_mark_down.*


/**
 * markdown转html
 */
class MarkDownActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mark_down)
        wb.getSettings().setJavaScriptEnabled(true) //启动js
        wb.setWebViewClient(object : WebViewClient() {
            //页面加载完成处理
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)

            }
        })



        val options = MutableDataSet()
        val parser: Parser = Parser.builder(options).build()
        val renderer = HtmlRenderer.builder(options).build()
        btn_md2html.setOnClickListener {
            var content =et_markdown.text.toString()
            val document: Node = parser.parse(content)
            val html = renderer.render(document) // "<p>This is <em>Sparta</em></p>\n"
            wb.loadData(html, "text/html;charset=utf-8","utf-8");
        }

        // uncomment to convert soft-breaks to hard breaks
        //options.set(HtmlRenderer.SOFT_BREAK, "<br />\n");


        // You can re-use parser and renderer instances

        // You can re-use parser and renderer instances

    }



}



