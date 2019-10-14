package almostlover.com.viewcollection.utils

import android.app.Activity
import android.util.Log
import android.view.View

val Any.TAG:String
    get() = this.javaClass.simpleName

fun Activity.lphLog(content:String,tag:String =TAG){
    Log.e(tag,content)
}


fun View.lphLog(content:String,tag:String =TAG){
    Log.e(tag,content)
}