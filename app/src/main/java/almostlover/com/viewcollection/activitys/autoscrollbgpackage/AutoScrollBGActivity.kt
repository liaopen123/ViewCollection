package almostlover.com.viewcollection.activitys.autoscrollbgpackage

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.utils.RvUtils
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_auto_scroll_bg.*
import java.util.concurrent.TimeUnit


class AutoScrollBGActivity : AppCompatActivity() {

    private var mScreenHeight: Int = 0
    private var mHeight: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_scroll_bg)
        RvUtils.loadImageData(rv, this)
        rv.isNestedScrollingEnabled = false

        Observable.interval(33, TimeUnit.MILLISECONDS)
            .subscribe(object : Observer<Long> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Long) {
                    rv.smoothScrollBy(0, 6)
                }

                override fun onError(e: Throwable) {
                }
            })



    }
}
