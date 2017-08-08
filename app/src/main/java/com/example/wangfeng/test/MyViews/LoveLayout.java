package com.example.wangfeng.test.MyViews;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
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

    public void addLove() {
        final ImageView loveIv = new ImageView(getContext());
        //loveIv.setImageDrawable(mDrawables[mRandom.nextInt(mDrawables.length)]);
        loveIv.setImageDrawable(mRed);
        loveIv.setLayoutParams(params);
        addView(loveIv);
        AnimatorSet finalSet = getAnimationSet(loveIv);
        finalSet.start();
    }

    private AnimatorSet getAnimationSet(ImageView iv) {
        //alpha动画
        ObjectAnimator alpha = ObjectAnimator.ofFloat(iv, "alpha", .3f, 1f);
        //缩放
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(iv, "scaleX", .2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(iv, "scaleY", 0.2f, 1f);
        //
        AnimatorSet enter = new AnimatorSet();
        enter.setDuration(500);
        enter.playTogether(alpha,scaleX,scaleY);
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
        mRed = getResources().getDrawable(R.drawable.love);
        mDrawables[0] = mRed;
        mDrawables[1] = mRed;
        mDrawables[2] = mRed;
        mDrawableWidth = mRed.getIntrinsicWidth();
        mDrawableHeight = mRed.getIntrinsicHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }
}
