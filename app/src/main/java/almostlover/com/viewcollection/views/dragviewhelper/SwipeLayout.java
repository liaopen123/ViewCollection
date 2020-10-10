package almostlover.com.viewcollection.views.dragviewhelper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

import java.util.function.Function;

public class SwipeLayout extends FrameLayout {

    ViewDragHelper viewDragHelper;

    ViewDragHelper.Callback callback;
    View dragView;
    RelativeLayout hideView; //隐藏在下面的view

    int maxLeftPointReverse; //左边所能到达的最左边的距离的绝对值


    boolean canSwipe = true; //是否可以滑动删除
    int mHorizontalDragRange; //水平可以拖动的距离最大范围
    Function<Void, Void> animOpenFunction;//打开后的回调
    Function<Void, Void> animCloseFunction;//关闭后的回调
    float downX = 0, downY = 0;
    private int mInitX;
    private int mLeft;
    private int mInitTop;

    public SwipeLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public SwipeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public SwipeLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    public void setCanSwipe(boolean canSwipe) {
        this.canSwipe = canSwipe;
    }

    public void setAnimCloseFunction(Function<Void, Void> animCloseFunction) {
        this.animCloseFunction = animCloseFunction;
    }

    public void setAnimOpenFunction(Function<Void, Void> animOpenFunction) {
        this.animOpenFunction = animOpenFunction;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() == 2) {
            hideView = (RelativeLayout) getChildAt(0);
            dragView = getChildAt(1);
            hideView.post(new Runnable() {
                @Override
                public void run() {

                    mHorizontalDragRange = hideView.getWidth();


                }
            });
            dragView.addOnLayoutChangeListener(new OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    mInitX = (int) dragView.getX();
                    mInitTop = dragView.getTop();
                    maxLeftPointReverse = hideView.getWidth() - mInitX;
                    dragView.removeOnLayoutChangeListener(this);
                }
            });

        }
    }

    public void swipeToNormal() {
        dragView.post(new Runnable() {
            @Override
            public void run() {

                if (dragView.getX() != mInitX) {
                    boolean result = viewDragHelper.smoothSlideViewTo(dragView, mInitX, mInitTop);
                    ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);

                }
            }
        });

    }

    public void toOpen() {

        dragView.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (dragView.getX() != -maxLeftPointReverse) {
                    viewDragHelper.smoothSlideViewTo(dragView, -maxLeftPointReverse, mInitTop);
                }
                dragView.removeOnLayoutChangeListener(this);
            }
        });

    }

    public void toNormal() {
        dragView.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (dragView.getX() != mInitX) {
                    viewDragHelper.smoothSlideViewTo(dragView, mInitX, mInitTop);

                }
                dragView.removeOnLayoutChangeListener(this);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (canSwipe) {
            viewDragHelper.processTouchEvent(ev);
            return true;
        } else {
            return super.onTouchEvent(ev);
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {


        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getRawX();
                downY = ev.getRawY();

                break;
            case MotionEvent.ACTION_MOVE:
                float disX = ev.getRawX() - downX;
                float disY = ev.getRawY() - downY;
                ViewParent viewGroup = getParent();

                if (Math.abs(disX) > Math.abs(disY)) {
                    viewGroup.requestDisallowInterceptTouchEvent(true);//水平滑动时请求reycleView不要拦截事件
                } else {
                    viewGroup.requestDisallowInterceptTouchEvent(false);
                }
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                viewDragHelper.cancel();
                break;
        }
        if (canSwipe) {
            return viewDragHelper.shouldInterceptTouchEvent(ev);
        } else {
            return super.onInterceptTouchEvent(ev);
        }

    }


    private void init(Context context) {
        callback = new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == dragView;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {

                mLeft = 0;
                if (left < 0 && Math.abs(left) > maxLeftPointReverse) { //限定向左最大到达位置
                    mLeft = -maxLeftPointReverse;
                    return mLeft;
                }
                if (left > mInitX) { //设置向右最大位置
                    mLeft = mInitX;
                    return mLeft;
                }
                mLeft = left;

                return mLeft;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return mInitTop;
            }

            @Override
            public void onEdgeTouched(int edgeFlags, int pointerId) {
                super.onEdgeTouched(edgeFlags, pointerId);
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                return mHorizontalDragRange;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                if (Math.abs(mInitX - mLeft) >= mHorizontalDragRange / 3) {//open

                    if (viewDragHelper.settleCapturedViewAt(-maxLeftPointReverse, mInitTop)) {
                        ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);

                    }

                    if (animOpenFunction != null) {
                        try {
//                            animOpenFunction.apply(null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {//close
                    if (viewDragHelper.settleCapturedViewAt(mInitX, mInitTop)) {
                        ViewCompat.postInvalidateOnAnimation(SwipeLayout.this);

                    }
                    if (animCloseFunction != null) {
                        try {
//                            animCloseFunction.apply(null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                }
            }

        };

        viewDragHelper = ViewDragHelper.create(this, 1.0f, callback);


    }


    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }


}
