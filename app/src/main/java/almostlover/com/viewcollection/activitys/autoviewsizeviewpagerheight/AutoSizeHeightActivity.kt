package almostlover.com.viewcollection.activitys.autoviewsizeviewpagerheight

import almostlover.com.viewcollection.R
import almostlover.com.viewcollection.utils.lphLog
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.activity_auto_size_height.*
import java.lang.Exception


class AutoSizeHeightActivity : AppCompatActivity() {
    private var neverScrolled: Boolean = true
    private lateinit var mContext: AutoSizeHeightActivity
    var urls: ArrayList<String> = ArrayList()
    var heights: ArrayList<Int> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_size_height)

        urls.add("https://ss0.baidu.com/73x1bjeh1BF3odCf/it/u=3310479589,761573373&fm=85&s=3A813160802793A77F92609C0300C081")
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1572435131207&di=c914bed06a0287d30047324bfb47fc2e&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201704%2F05%2F20170405114345_2G5BN.thumb.700_0.jpeg")
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1572451563402&di=835be097bf199a26d0794c82bf987b9a&imgtype=0&src=http%3A%2F%2Fimg8.zol.com.cn%2Fbbs%2Fupload%2F19025%2F19024566.JPG")
        urls.add("https://ss0.baidu.com/73x1bjeh1BF3odCf/it/u=3310479589,761573373&fm=85&s=3A813160802793A77F92609C0300C081")
        urls.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3683194211,1520759142&fm=26&gp=0.jpg")
        urls.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1572435131207&di=c914bed06a0287d30047324bfb47fc2e&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201704%2F05%2F20170405114345_2G5BN.thumb.700_0.jpeg")
        mContext = this


        avb.setData(urls)

    }

}
