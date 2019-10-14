package cn.com.almostlover.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.util.Log


class NetWorkUtils {


    private val _TAG_ = "NetWorkUtils"

    @SuppressLint("MissingPermission")
    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm == null) {
            return false
        } else {
            // 打印所有的网络状态
            val infos = cm.allNetworkInfo
            if (infos != null) {
                for (i in infos.indices) {
                    // Log.d(TAG, "isNetworkAvailable - info: " +
                    // infos[i].toString());
                    if (infos[i].state == NetworkInfo.State.CONNECTED) {
                        // Log.d(AppConstants.LOG_TAG,
                        // "isNetworkAvailable -  I "
                        // + i);
                    }
                }
            }

            // 如果仅仅是用来判断网络连接　　　　　　
            // 则可以使用 cm.getActiveNetworkInfo().isAvailable();
            val networkInfo = cm.activeNetworkInfo
            if (networkInfo != null) {
                Log.d(_TAG_, "isNetworkAvailable - 是否有网络： " + networkInfo.isAvailable)
            } else {
                Log.d(_TAG_, "isNetworkAvailable - 完成没有网络！")
                return false
            }

            // 1、判断是否有3G网络
            if (networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                Log.d(_TAG_, "isNetworkAvailable - 有3G网络")
                return true
            } else {
                Log.d(_TAG_, "isNetworkAvailable - 没有3G网络")
            }

            // 2、判断是否有wifi连接
            if (networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                Log.d(_TAG_, "isNetworkAvailable - 有wifi连接")
                return true
            } else {
                Log.d(_TAG_, "isNetworkAvailable - 没有wifi连接")
            }
        }
        return false
    }



}