package almostlover.com.viewcollection.activitys.myviewgroup

import almostlover.com.viewcollection.R
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Scroller


class MyViewPager @JvmOverloads constructor(
    var mContext: Context,
    attributeSet: AttributeSet? = null,
    style: Int = 0
) : ViewGroup(mContext, attributeSet, style){
    val images:Array<Int> = arrayOf(R.mipmap.bg_1, R.mipmap.bg_header, R.mipmap.bg_1)
    lateinit var gestureDetector: GestureDetector
    var position = 0
    var mScroller:Scroller
    var mScrollX = 0
    var startDX=0f
    var startDY=0f
    init {

        for (i in images.indices){
            val image =  ImageView(getContext())
            image.setBackgroundResource(images[i])

            addView(image)
        }
        val testView = inflate(mContext, R.layout.viewpager_text, null)
        addView(testView, 2)



        gestureDetector = GestureDetector(object : GestureDetector.SimpleOnGestureListener() {
            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                scrollBy(distanceX.toInt(), 0)
                return super.onScroll(e1, e2, distanceX, distanceY)
            }
        })

        mScroller  = Scroller(getContext())
    }
    //onMeasure 主要是为了子view是vg的情况
    //   //由于ViewGroup默认只测量下面一层的子View(所以我们直接在ViewGroup里面添加ImageView是可以直接显示出来的)，所以基本自定义ViewGroup都会要重写onMeasure方法，否则无法测量第一层View（这里是ScrollView）中的view，无法正常显示里面的内容。
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)  //不写这行代码就会crash
        var count = childCount
        for (i in 0 until count){
                getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var count = childCount
        for (i in 0 until count){
            val childView = getChildAt(i)
            childView.layout(i * width, 0, (i + 1) * width, height)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {


        gestureDetector.onTouchEvent(event)

        when(event.action){
            MotionEvent.ACTION_DOWN -> {
            }
            MotionEvent.ACTION_MOVE -> {
                Log.e("ACTION_MOVE", "scrollX=$scrollX") //scrollX 相对于初始时候的移动的距离
                mScrollX = scrollX
                position = (getScrollX() + getWidth() / 2) / getWidth()
                //滑到最后一张的时候，不能出边界
                if (position >= images.size) {
                    position = images.size - 1;
                }
                if (position < 0) {
                    position = 0
                }
            }
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
//                scrollTo(position * width, 0)
                mScroller.startScroll(mScrollX, 0, -(scrollX - position * getWidth()), 0)
                invalidate();//使用invalidate这个方法会有执行一个回调方法computeScroll，我们来重写这个方法
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

        when(ev.action){

            MotionEvent.ACTION_DOWN ->{
                startDX = ev.x
                startDY = ev.y
                gestureDetector.onTouchEvent(ev)
            }
            MotionEvent.ACTION_MOVE -> {
                var dx = ev.x - startDX
                var dy = ev.y - startDY

                if (Math.abs(dx) > Math.abs(dy)) {
                    // 左右滑动
                    return true;// 中断事件传递, 不允许孩子响应事件了, 由父控件处理
                }
            }

        }
        return false
    }

}