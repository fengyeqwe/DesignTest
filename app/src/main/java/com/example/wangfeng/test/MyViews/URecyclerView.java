package com.example.wangfeng.test.MyViews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wangfeng.test.R;

/**
 * Created by fengye on 2017/8/10.
 * email 1040441325@qq.com
 * 带刷新加载功能的RecyclerView
 */

public class URecyclerView extends LinearLayout {
    private static final int LOAD_NORMAL = 0;//默认状态
    private static final int LOAD_CAN = 1;// 松开后执行加载操作
    private static final int LOADING = 2;//刷新中...
    private static final int LOAD_COMPLETE = 3;//刷新完成
    private static final int REFRESH = 0;
    private static final int LOAD_MORE = 1;
    private int loadType;//加载类型
    private int currentState = LOAD_COMPLETE;//当前状态
    private boolean isLoading;//是否在加载中
    private RotateAnimation upAnimation;
    private RotateAnimation downAnimation;
    private RelativeLayout layout;
    private RelativeLayout headview;
    private ImageView headImage;
    private ProgressBar headProgress;
    private TextView headText;
    private RelativeLayout footerView;
    private ImageView footImage;
    private ProgressBar footProgress;
    private TextView footText;
    private RecyclerView recyclerView;
    private int headerHeigh;
    private int footerHeigh;
    private float startY;//触摸操作起始位置
    private OnTouchListener recyclerViewTouch = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startY = event.getRawY();
                    break;
                case MotionEvent.ACTION_UP:
                    if (!isLoading) {//如果正在加载,不做任何操作;
                        setState(LOADING);
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (startY == 0 || startY == -1) {
                        startY = event.getRawY();
                    }
                    float endY = event.getRawY();
                    //滑动距离 超过50(头部的高度)出现提示
                    int des = (int) Math.abs(endY - startY);
                    if (!isLoading) {//如果正在加载,不做任何操作;
                        //canScollVerically(-1) 能否的值表示是否能向下滚动，false表示已经滚动到顶部
                        if (!recyclerView.canScrollVertically(-1) && endY - startY > 56) {
                            loadType = REFRESH;
                            int padding = des - headerHeigh;
                            layout.setPadding(0, padding, 0, 0);
                            recyclerView.scrollToPosition(0);//没有这个操作,会导致滑动卡顿
                            if (padding > 0) {
                                //松开刷新
                                setState(LOAD_CAN);
                            } else if (padding < 0) {
                                //下拉刷新
                                setState(LOAD_NORMAL);
                            }
                        } else if (!recyclerView.canScrollVertically(1) && endY - startY < -56) {
                            loadType = LOAD_MORE;
                            int padding = des - footerHeigh;
                            layout.setPadding(0, 0, 0, padding);
                            recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
                            if (padding > 0) {
                                setState(LOAD_CAN);
                            } else if (padding < 0) {
                                setState(LOAD_NORMAL);
                            }

                        }
                    }
                    break;

            }
            return false;
        }
    };
    private IOnRefreshListener onRefreshListener;


    public URecyclerView(Context context) {
        super(context);
    }

    public URecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public URecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setonRefreshListener(IOnRefreshListener listener) {
        this.onRefreshListener = listener;
    }

    public void setOnRefreshComplete() {
        setState(LOAD_COMPLETE);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.u_recyclerview, this, true);
        bindView(view);
        initAnim();
        recyclerView.setOnTouchListener(recyclerViewTouch);
        headview.measure(0, 0);
        headerHeigh = headview.getMeasuredHeight();
        footerView.measure(0, 0);
        footerHeigh = footerView.getMeasuredHeight();
    }

    private void bindView(View view) {
        layout = (RelativeLayout) view.findViewById(R.id.layout);
        headview = (RelativeLayout) view.findViewById(R.id.headview);
        headImage = (ImageView) view.findViewById(R.id.headImage);
        headProgress = (ProgressBar) view.findViewById(R.id.headprogress);
        headText = (TextView) view.findViewById(R.id.headerText);
        footerView = (RelativeLayout) view.findViewById(R.id.footerview);
        footImage = (ImageView) view.findViewById(R.id.footImage);
        footProgress = (ProgressBar) view.findViewById(R.id.footprogress);
        footText = (TextView) view.findViewById(R.id.footText);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
    }

    private void initAnim() {
        upAnimation = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF,
                .5f, RotateAnimation.RELATIVE_TO_SELF, .5f);
        upAnimation.setDuration(500);
        upAnimation.setFillAfter(true);
        downAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF,
                0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        downAnimation.setDuration(500);
        downAnimation.setFillAfter(true);
    }

    private void doState(int state) {
        switch (loadType) {
            case REFRESH:
                reFreshState(state);
                break;
            case LOAD_MORE:
                loadMoreState(state);
                break;
        }
    }

    private void reFreshState(int state) {
        switch (state) {
            case LOAD_NORMAL:
                headview.setVisibility(View.VISIBLE);
                headText.setText("下拉刷新");
                headImage.setVisibility(View.VISIBLE);
                headProgress.setVisibility(View.GONE);
                break;
            case LOAD_CAN:
                headText.setText("松开刷新");
                headImage.setVisibility(View.VISIBLE);
                headProgress.setVisibility(View.GONE);
                break;
            case LOADING:
                isLoading = true;
                headText.setText("正在刷新中...");
                headImage.setVisibility(View.GONE);
                headProgress.setVisibility(View.VISIBLE);
                headImage.clearAnimation();
                if (onRefreshListener != null) {
                    onRefreshListener.refresh();
                }
                break;
            case LOAD_COMPLETE:
                isLoading = false;
                headText.setText("下拉刷新");
                headview.setVisibility(View.GONE);
                headImage.setVisibility(View.GONE);
                headProgress.setVisibility(View.GONE);
                layout.setPadding(0, 0, 0, 0);
                break;

        }
    }

    private void loadMoreState(int state) {
        switch (state) {
            case LOAD_NORMAL:
                footText.setText("上拉加载更多");
                footerView.setVisibility(View.VISIBLE);
                footImage.setVisibility(View.VISIBLE);
                footProgress.setVisibility(View.GONE);
                break;
            case LOAD_CAN:
                footText.setText("松开加载更多...");
                footImage.setVisibility(View.VISIBLE);
                footProgress.setVisibility(View.GONE);
                break;
            case LOADING:
                isLoading = true;
                footText.setText("正在加载中...");
                footImage.setVisibility(View.GONE);
                footProgress.setVisibility(View.VISIBLE);
                footImage.clearAnimation();
                if (onRefreshListener != null) {
                    onRefreshListener.loadMore();
                }
                break;
            case LOAD_COMPLETE:
                isLoading = false;
                footText.setText("上拉加载更多");
                footImage.setVisibility(View.GONE);
                footProgress.setVisibility(View.GONE);
                layout.setPadding(0, 0, 0, 0);
                footerView.setVisibility(View.GONE);
                break;

        }
    }

    /**
     * 设置状态:当状态改变时执行操作
     *
     * @param state 将要改变的状态
     */
    private void setState(int state) {
        if (this.currentState == state) return;
        if (state == LOADING) {
            if (currentState == LOAD_CAN) {
                //正常执行
            } else if (currentState == LOAD_NORMAL) {
                //跳过执行,恢复原状
                state = LOAD_COMPLETE;
            } else if (currentState == LOAD_COMPLETE) {
                //无需执行
                return;
            }
        } else if (state == LOAD_NORMAL) {
            if (currentState ==LOAD_COMPLETE){
                //正常执行
            }else if(currentState ==LOAD_CAN){
                //正常执行,添加动画
                headImage.startAnimation(downAnimation);
                footImage.startAnimation(downAnimation);
            }
        } else if (state == LOAD_CAN){
            if (currentState ==LOAD_COMPLETE){
                //添加中间缺少的操作正常执行(ps:一般不会直接走完成状态到可完成状态)
                doState(LOAD_NORMAL);
            }else if(currentState ==LOAD_NORMAL){
                //正常执行,添加动画
                headImage.startAnimation(upAnimation);
                footImage.startAnimation(upAnimation);
            }
        }
            doState(state);
        this.currentState = state;

    }

    public interface IOnRefreshListener {
        void refresh();

        void loadMore();
    }
}
