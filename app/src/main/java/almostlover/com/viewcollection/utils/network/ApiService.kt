package cn.almostlover.kaaosibase.constant

import almostlover.com.viewcollection.bean.HomeList
import cn.almostlover.liaomvp.mvp.model.bean.BaseBean
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by chenxz on 2018/4/21.
 */
interface ApiService {

    /**
     * 获取轮播图
     * http://www.wanandroid.com/banner/json
     */
    @GET("index/data?token=")
    fun getHomeData(): Observable<HomeList>

    @GET("index/data?token=")
    fun getRawHomeData(): Call<ResponseBody>
    @GET("index/data?token=")
    suspend fun getCHomeData(): HomeList
    /**
     * 获取轮播图
     * http://www.wanandroid.com/banner/json
     */
    @GET("index1/data?token=")
    fun commitFeedback(): Observable<BaseBean>


}