package almostlover.com.viewcollection.views;

import almostlover.com.viewcollection.R;
import almostlover.com.viewcollection.bean.CakeBean;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Pony on 2016/11/26.
 */

public class CakeView extends View {
    public ArrayList<CakeBean> cakeViews = new ArrayList();
    private int mScreenWidth;
    private int mScreenHeight;
    private boolean enough = false;
    private int mSpeed = 5;//每60ms View Y方向的位移量
    private Paint mPaint;
    private Random random;
    private static final int TIMECOUNT = 40;//通过这个数字可以控制添加Cake数量的速度   数值越大   添加蛋糕的速度就越慢
    private int mTimeCount = 0;
    private String TAG = "CakeView";
    private int cakeBitmapWidth;
    private Runnable addCakeAndSpeedRunnale;
    private boolean Running = true;


    public CakeView(Context context) {
        super(context);
        initView();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mScreenWidth = getWidth();
        mScreenHeight = getHeight();
    }

    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        mPaint = new Paint();
        initRunnable();
      //  init();
    }

    private void initRunnable() {
        addCakeAndSpeedRunnale = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "addCakeAndSpeedRunnale走了");
                if (!enough) {
                    addCakeView();
                    refreshCakeBean();
                    invalidateAndDoAgain();
                } else {
                    refreshCakeBean();
                    invalidateAndDoAgain();
                }
            }
        };
    }


    private void init() {
        postDelayed(addCakeAndSpeedRunnale, 6);
    }

    private void invalidateAndDoAgain() {
        invalidate();
        if (Running) {
            //Running 用来跳出递归  停止view
            init();
        }
    }


    /**
     * 向集合添加蛋糕view
     */
    private void addCakeView() {
        if (mTimeCount %TIMECOUNT ==0) {
            mTimeCount = 0;
            CakeBean cakeBean = new CakeBean();
            Bitmap cakeBitmap = getBitmapFor(R.mipmap.cake_cherry_cream);
            cakeBitmapWidth = cakeBitmap.getWidth();
            if (null == random) {
                random = new Random();
            }
            int cakePointX = random.nextInt(mScreenWidth) - cakeBitmapWidth / 2;//蛋糕随机出现的X坐标
            cakeBean.pointX = cakePointX < 0 ? 0 : cakePointX;
            cakeBean.pointY = -80;
            cakeBean.bitmap = cakeBitmap;
            cakeViews.add(cakeBean);
        }
    }

    /**
     * 刷新cakeView的位置
     */
    private void refreshCakeBean() {
        mTimeCount += 1;

        Log.d(TAG, "mTimeCount" + mTimeCount);
        Log.d(TAG, "cakeVieListsize" + cakeViews.size());

        for (CakeBean cakebean : cakeViews) {
            cakebean.pointY += mSpeed;
            if (cakebean.pointY >= mScreenHeight) {
                enough = true;
                cakebean.pointY = 0;
                if (null == random) {
                    random = new Random();
                }
                int cakePointX = random.nextInt(mScreenWidth) - cakeBitmapWidth / 2;//蛋糕随机出现的X坐标
                cakebean.pointX = cakePointX < 0 ? 0 : cakePointX;
            }
        }
    }

    private Bitmap getBitmapFor(int resId) {
        return BitmapFactory.decodeResource(getContext().getResources(), resId);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (CakeBean cakeBean : cakeViews) {
            canvas.drawBitmap(cakeBean.bitmap, cakeBean.pointX, cakeBean.pointY, mPaint);
        }
    }

    public void stop() {
            Running = false;
        cakeViews.clear();
        setVisibility(GONE);
        invalidate();
    };



    public void start(){

        Running = true;
        setVisibility(VISIBLE);
        enough = false;
        mTimeCount = 0;//不重新归零的话  会出现占满一屏幕后   stop 再start 不会addview  因为mTimeCount>5 不会等于5了
        init();

    };

}


