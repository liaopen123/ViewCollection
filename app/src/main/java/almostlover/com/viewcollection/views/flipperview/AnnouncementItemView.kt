package almostlover.com.viewcollection.views.flipperview

import almostlover.com.viewcollection.R
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView

class AnnouncementItemView(context:Context, attrs: AttributeSet?):FrameLayout(context,attrs){
constructor(context:Context):this(context,null)

    private var tv_content: TextView?

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.item_annoucement, this, true)
         tv_content = view.findViewById<TextView>(R.id.tv_content)
    }


    public fun setText(content:String){
        tv_content?.text = content
    }


}