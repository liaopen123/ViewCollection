package almostlover.com.viewcollection.activitys.scroller.view

import almostlover.com.viewcollection.utils.lphLog
import android.content.Context
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView

class CustomScrollView : ScrollView {

    private var mLastY: Float = 0.0f
    //回调监听接口
    private var mOnScrollChangeListener: OnScrollChangeListener? = null


    companion object{

        private val CODE_TO_START = 0x001
        private val CODE_TO_END = 0x002
        //标识是否滑动到顶部
        private var isScrollToStart = false
        //标识是否滑动到底部
        private var isScrollToEnd = false


        private var isTop =false
        private var isBottom =false


        private val mHandler = object : Handler() {

            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when (msg.what) {
                    CODE_TO_START ->
                        //重置标志“滑动到顶部”时的标志位
                        isScrollToStart = false
                    CODE_TO_END ->
                        //重置标志“滑动到底部”时的标志位
                        isScrollToEnd = false
                    else -> {
                    }
                }
            }
        }
    }



    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        var ev = ev!!
        val y = ev.y
        if (ev.actionMasked==MotionEvent.ACTION_DOWN) {
             mLastY = ev.y
            parent.requestDisallowInterceptTouchEvent(true)
        }

        if (ev.actionMasked==MotionEvent.ACTION_MOVE) {
            lphLog("isTop：$isTop ,,isBottom$isBottom  ,往上滑${(y-mLastY)<0} ,往下滑动${(y-mLastY)>0}")
                if(isTop&&(y-mLastY)>0 ){
                    //到达顶部往上滑动
                    lphLog("parent 拦截")
                    parent.requestDisallowInterceptTouchEvent(false)
                }else if(isBottom&& (y-mLastY)<0){
                    //到达底部往下滑动
                    lphLog("parent 拦截")
                    parent.requestDisallowInterceptTouchEvent(false)
                }else{

                }


            mLastY = y
        }


        return super.dispatchTouchEvent(ev)
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        if (mOnScrollChangeListener != null) {
           lphLog("CustomScrollView", "scrollY:$scrollY")
            //滚动到顶部，ScrollView存在回弹效果效应（这里只会调用两次，如果用<=0,会多次触发）
            if (scrollY == 0) {
                //过滤操作，优化为一次调用
                if (!isScrollToStart) {
                    isScrollToStart = true
                    mHandler.sendEmptyMessageDelayed(CODE_TO_START, 200)
                    lphLog("在顶部111111111")
                    isTop = true
                    mOnScrollChangeListener!!.onScrollToStart()

                }
            } else {
                val contentView = getChildAt(0)
                if (contentView != null && contentView.measuredHeight === scrollY + height) {
                    //滚动到底部，ScrollView存在回弹效果效应
                    //优化，只过滤第一次
                    if (!isScrollToEnd) {
                        isScrollToEnd = true
                        mHandler.sendEmptyMessageDelayed(CODE_TO_END, 200)
                        isBottom = true
                        lphLog("CustomScrollView", "在底部1111111")
                        mOnScrollChangeListener!!.onScrollToEnd()
                    }

                }else{
                    lphLog("CustomScrollView", "在中间1111111")
                    isBottom = false
                    isTop = false
                    mOnScrollChangeListener!!.middlePositon()
                }
            }
        }

    }

    //滑动监听接口
    interface OnScrollChangeListener {

        //滑动到顶部时的回调
        fun onScrollToStart()

        //滑动到底部时的回调
        fun onScrollToEnd()
        //中间位置
        fun middlePositon()
    }

    fun setOnScrollChangeListener(onScrollChangeListener: OnScrollChangeListener) {
        mOnScrollChangeListener = onScrollChangeListener
    }


    override fun scrollBy(x: Int, y: Int) {
        if ((y > 0 && isTop) || (y < 0 && isBottom)) {
            (parent as View).scrollBy(x, y)
        } else {
            super.scrollBy(x, y)
        }
    }

}