package almostlover.com.viewcollection.activitys.myviewgroup

import almostlover.com.viewcollection.R
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Scroller
import kotlinx.android.synthetic.main.activity_main5.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import kotlin.math.abs


class MyViewPager1 @JvmOverloads constructor(var mContext:Context, attributeSet: AttributeSet, style:Int =0):ViewGroup(mContext,attributeSet,style),AnkoLogger{

    private var mScrollX: Int = 0
    var imageRes = arrayOf<Int>(R.mipmap.bg_1,R.mipmap.bg_header,R.mipmap.bg_1)
    private var gestureDetector:GestureDetector
    private  var mScroller:Scroller

    private var startX=0f
    private var startY=0f
    private var mPosition  = 0

    init {
        var value = 120/100
     info {  "valuevaluevaluevaluevalue$value"}
        for (i in imageRes.indices){
            var imageView = ImageView(mContext)
            imageView.setImageResource(imageRes[i])
            addView(imageView)
        }
       var view =  View.inflate(mContext,R.layout.viewpager_text,null)
        addView(view,1)

         gestureDetector = GestureDetector(object :GestureDetector.SimpleOnGestureListener(){
            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                scrollBy(distanceX.toInt(),0)
                return super.onScroll(e1, e2, distanceX, distanceY)
            }
        })
        mScroller = Scroller(mContext)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec)
        for (i in 0 until childCount){
            getChildAt(i).measure(widthMeasureSpec,heightMeasureSpec)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var count = childCount
        for (i in 0 until count){
            getChildAt(i).layout(i*width,0,(i+1)*width,height)
        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)

        when (event.action) {
            MotionEvent.ACTION_DOWN ->{

            }
            MotionEvent.ACTION_MOVE ->{
                mScrollX = scrollX
                mPosition  =   (scrollX/(width/2))/2
                info { "mPositionmPosition$mPosition" }

            }

            MotionEvent.ACTION_UP,
                MotionEvent.ACTION_CANCEL ->{
                mScroller.startScroll(mScrollX,0,-(mScrollX-mPosition*width),0)
                invalidate()
            }
        }
        return true
    }


    override fun computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.currX, 0)
            Log.e("CurrX", "mScroller.getCurrX()=" + mScroller.currX)
            postInvalidate()
        }
    }


    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(ev)

        when(ev.action) {
            MotionEvent.ACTION_DOWN->{
                startX = x
                startY = y
            }

            MotionEvent.ACTION_MOVE -> {
                var dx = x - startX
                var dy = y - startY
                if (abs(dy) > abs(dx)) {
                    return true
                }
            }
        }



        return false
    }
}