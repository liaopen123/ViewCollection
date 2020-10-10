package almostlover.com.viewcollection.activitys.fancybehavior.behavior;

import android.content.Context;
import android.os.Handler;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Scroller;


import java.lang.ref.WeakReference;

import almostlover.com.viewcollection.R;

/**
 * Created by cyandev on 2016/11/3.
 */
public class HeaderScrollingBehavior1 extends CoordinatorLayout.Behavior<RecyclerView> {

    private final Context context;
    private boolean isExpanded = false;
    private boolean isScrolling = false;

    private WeakReference<View> dependentView;
    private Scroller scroller;
    private Handler handler;
    private String TAG = getClass().getSimpleName();

    public HeaderScrollingBehavior1(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
        handler = new Handler();
        this.context = context;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
        if (dependency != null && dependency.getId() == R.id.scrolling_header) {
            dependentView = new WeakReference<>(dependency);
            return true;
        }
        return false;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, RecyclerView child, int layoutDirection) {
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        if (lp.height == CoordinatorLayout.LayoutParams.MATCH_PARENT) {
            //先这么写,理论上会从左上角开始绘制RV。但是紧接着会调用onDependentViewChanged，在onDependentViewChanged方法中
            //会通过setTranslationY修改偏移量。
            child.layout(0, 0, parent.getWidth(), (int) (parent.getHeight()-getDependentViewCollapsedHeight()));
            return true;
        }
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child, View dependency) {
//        Toast.makeText(context, "onDependentViewChanged", Toast.LENGTH_SHORT).show();
//        Resources resources = getDependentView().getResources();
//        final float progress = 1.f -
//                Math.abs(dependency.getTranslationY() / (dependency.getHeight() - resources.getDimension(R.dimen.collapsed_header_height)));

        child.setTranslationY(dependency.getHeight() + dependency.getTranslationY());
//
//        float scale = 1 + 0.4f * (1.f - progress);
//        dependency.setScaleX(scale);
//        dependency.setScaleY(scale);
//
//        dependency.setAlpha(progress);

        return true;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, RecyclerView child, View directTargetChild, View target, int nestedScrollAxes) {
        scroller.abortAnimation();
        isScrolling = false;

        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, int dx, int dy, int[] consumed) {
        if (dy < 0) {
            return;
        }
        Log.e(TAG,"向上滑才会调用");
        View dependentView = getDependentView();
        float newTranslateY = dependentView.getTranslationY() - dy;
        float minHeaderTranslate = -(dependentView.getHeight() - getDependentViewCollapsedHeight());//实际可用滑动的高度

        if (newTranslateY > minHeaderTranslate) {
            dependentView.setTranslationY(newTranslateY);
            consumed[1] = dy;
        }
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (dyUnconsumed > 0) {
            return;
        }
        Log.e(TAG,"向下滑才会调用");
        View dependentView = getDependentView();
        float newTranslateY = dependentView.getTranslationY() - dyUnconsumed;
        final float maxHeaderTranslate = 0;

        if (newTranslateY < maxHeaderTranslate) {
            dependentView.setTranslationY(newTranslateY);
        }
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, float velocityX, float velocityY) {
        return onUserStopDragging(velocityY);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target) {
        if (!isScrolling) {
            onUserStopDragging(800);
        }
    }

    private boolean onUserStopDragging(float velocity) {
        View dependentView = getDependentView();
        float translateY = dependentView.getTranslationY();//得到当前头部的位置
        float minHeaderTranslate = -(dependentView.getHeight() - getDependentViewCollapsedHeight());

        if (translateY == 0 || translateY == minHeaderTranslate) {
            //如果偏移量是0或者minHeaderTranslate 表明head现在处于完全折叠或者完全展开的状态，不需要做任何操作。
            return false;
        }

        boolean targetState; // Flag indicates whether to expand the content.
        if (Math.abs(velocity) <= 800) {
            //如果滑动速度很小
//            if (Math.abs(translateY) < Math.abs(translateY - minHeaderTranslate)) {
            if (Math.abs(translateY) < (Math.abs(minHeaderTranslate)/2)) {
                //已经位移的量 <
                targetState = false;
            } else {
                targetState = true;
            }
            velocity = 800; // Limit velocity's minimum value.
        } else {
            if (velocity > 0) {
                //如果滑动速度特别大
                //上滑动   应该变成关闭状态
                targetState = true;
            } else {
                //下滑动  应该变成展开状态
                targetState = false;
            }
        }
        //确定最终的位移量
        float targetTranslateY = targetState ? minHeaderTranslate : 0;

        scroller.startScroll(0, (int) translateY, 0, (int) (targetTranslateY - translateY), (int) (1000000 / Math.abs(velocity)));
        handler.post(flingRunnable);
        isScrolling = true;
        return true;
    }

    private float getDependentViewCollapsedHeight() {
        return getDependentView().getResources().getDimension(R.dimen.collapsed_header_height);
    }

    private View getDependentView() {
        return dependentView.get();
    }

    private Runnable flingRunnable = new Runnable() {
        @Override
        public void run() {
            if (scroller.computeScrollOffset()) {
                getDependentView().setTranslationY(scroller.getCurrY());
                handler.post(this);
            } else {
                isExpanded = getDependentView().getTranslationY() != 0;
                isScrolling = false;
            }
        }
    };

}
