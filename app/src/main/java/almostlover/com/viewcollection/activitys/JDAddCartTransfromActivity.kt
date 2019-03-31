package almostlover.com.viewcollection.activitys

import almostlover.com.viewcollection.R
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_jdadd_cart_transfrom.*

class JDAddCartTransfromActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jdadd_cart_transfrom)
        Glide.with(this).load("http://img14.360buyimg.com/n0/jfs/t1/32472/22/3371/192059/5c74b147Ee2eaefb4/8a9eaa0ea1365ed0.jpg").into(iv_bg)
        iv_cart.setOnClickListener {

            Glide.with(this).load("http://img14.360buyimg.com/n0/jfs/t1/32472/22/3371/192059/5c74b147Ee2eaefb4/8a9eaa0ea1365ed0.jpg").apply(
                RequestOptions.circleCropTransform()).into(iv_move)
            iv_move.visibility = View.VISIBLE




        }
    }
}
