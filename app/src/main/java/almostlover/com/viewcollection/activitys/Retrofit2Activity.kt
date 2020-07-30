package almostlover.com.viewcollection.activitys

import almostlover.com.viewcollection.R
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.cxz.wanandroid.http.RetrofitHelper
import okhttp3.ResponseBody
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Retrofit2Activity : AppCompatActivity(),AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit2)

        val rawRequest:Call<ResponseBody> = RetrofitHelper.service.getRawHomeData()
        rawRequest.enqueue(object: Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                info { response.body().toString() }
            }

        })
    }
}
