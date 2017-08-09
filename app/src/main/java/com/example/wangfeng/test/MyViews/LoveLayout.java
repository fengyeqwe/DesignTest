package com.example.wangfeng.test.MyViews;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.wangfeng.test.R;

import java.util.Random;

/**
 * Created by fengye on 2017/8/8.
 * email 1040441325@qq.com
 */

public class LoveLayout extends RelativeLayout {

    public static final String TAG = LoveLayout.class.getName();
    private Drawable mRed, mYellow, mBlue;
    private Drawable[] mDrawables;
    private Interpolator[] mInterpolators;
    private int mDrawableHeight, mDrawableWidth;
    private int mWidth, mHeight;
    private LayoutParams params;
    private Random mRandom = new Random();

    public LoveLayout(Context context) {
        super(context);
        init();
        Log.i(TAG, "LoveLayout: 1");
    }

    public LoveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        Log.i(TAG, "LoveLayout: 2");
    }

    public LoveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        Log.i(TAG, "LoveLayout: 3");
    }

    private void init() {
        initDrawable();
        initInterpolator();
        params = new LayoutParams(mDrawableWidth, mDrawableHeight);
        params.addRule(CENTER_HORIZONTAL, TRUE);
        params.addRule(ALIGN_PARENT_BOTTOM, TRUE);
    }

    public void addLove(final int count) {
        final int[] number = {0};
        final Handler handler =new Handler();
        final Runnable runnable =new Runnable() {
            @Override
            public void run() {
                final ImageView loveIv = new ImageView(getContext());
                loveIv.setImageDrawable(mDrawables[mRandom.nextInt(mDrawables.length)]);
                loveIv.setLayoutParams(params);
                addView(loveIv);
                AnimatorSet finalSet = getAnimationSet(loveIv);
                finalSet.start();
                number[0]++;
                if (number[0] <count)
                handler.postDelayed(this,100);
            }
        };
        handler.post(runnable);

    }

    private AnimatorSet getAnimationSet(ImageView iv) {
        //alpha动画
        ObjectAnimator alpha = ObjectAnimator.ofFloat(iv, "alpha", .3f, 1f);
        alpha.setDuration(500);
        //缩放
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(iv, "scaleX", .2f, 1f);
        scaleX.setDuration(500);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(iv, "scaleY", 0.2f, 1f);
        scaleY.setDuration(500);

        AnimatorSet enter = new AnimatorSet();
        enter.play(alpha).with(scaleX).with(scaleY).before(getBezierValueAnimator(iv));
        enter.setTarget(iv);
        return enter;
    }


    /**
     * 初始化几种插补器
     */
    private void initInterpolator() {
        mInterpolators = new Interpolator[4];
        mInterpolators[0] = new LinearInterpolator();// 线性
        mInterpolators[1] = new AccelerateDecelerateInterpolator();// 先加速后减速
        mInterpolators[2] = new AccelerateInterpolator();// 加速
        mInterpolators[3] = new DecelerateInterpolator();// 减速
    }

    private void initDrawable() {
        mDrawables =new Drawable[3];
        mRed = getResources().getDrawable(R.drawable.love_pink);
        mBlue =getResources().getDrawable(R.drawable.love_blue);
        mYellow =getResources().getDrawable(R.drawable.love_yellow);
        mDrawables[0] = mRed;
        mDrawables[1] = mBlue;
        mDrawables[2] = mYellow;
        //mDrawableWidth = mRed.getIntrinsicWidth();
        //mDrawableHeight = mRed.getIntrinsicHeight();
        //由于图片偏大,固定其大小
        mDrawableWidth = 64;
        mDrawableHeight = 64;
    }
    private ValueAnimator getBezierValueAnimator(final ImageView lv){
        //贝塞尔曲线的两个顶点
        PointF p1 = getPonitF(2);
        PointF p2 = getPonitF(1);
        PointF p0 = new PointF((mWidth-mDrawableWidth)/2,(mHeight-mDrawableHeight));
        //随机终点
        PointF p3 = new PointF((mRandom.nextInt(mWidth)),0);
        BezierEvaluator evaluator =new BezierEvaluator(p1,p2);
        ValueAnimator animator = ValueAnimator.ofObject(evaluator,p0,p3);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF p= (PointF) animation.getAnimatedValue();
                lv.setX(p.x);
                lv.setY(p.y);
                lv.setAlpha(1 - animation.getAnimatedFraction() + 0.1f);
            }
        });
        animator.setTarget(lv);
        animator.setInterpolator(mInterpolators[1]);
        animator.setDuration(3*1000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
               removeView(lv);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return animator;
    }

    private PointF getPonitF(int i) {
        return new PointF((mRandom.nextInt(mWidth)),(mRandom.nextInt(mHeight)));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }
}
