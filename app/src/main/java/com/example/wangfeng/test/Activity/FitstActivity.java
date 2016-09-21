package com.example.wangfeng.test.Activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.example.wangfeng.test.Adapter.FirstAdapter;
import com.example.wangfeng.test.Adapter.FirstItemDecoration;
import com.example.wangfeng.test.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FitstActivity extends AppCompatActivity {


    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.swipe)
    SwipeRefreshLayout mSwipe;
    private ArrayList<String> mData;
    private FirstAdapter adapter;
    DisplayMetrics metric;
    int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitst);
        ButterKnife.bind(this);
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        height=wm.getDefaultDisplay().getHeight();
        Log.i("TAG", "onCreate: "+height);

        initailData();
        initView();
    }

    private void initailData() {
        mData=new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            mData.add("NO."+i);
        }
    }

    private void initView() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter=new FirstAdapter(mData);
        mRecycler.setAdapter(adapter);
        mRecycler.addItemDecoration(new FirstItemDecoration(this));

        mSwipe.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        //下拉刷新
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Snackbar.make(mRecycler,"刷新成功",Snackbar.LENGTH_SHORT)
                        .setDuration(2000).show();
                mSwipe.setRefreshing(false);
            }
        });

        //下拉加载
        mRecycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCROLL_STATE_IDLE&&
                        lastVisibleItem+1==adapter.getItemCount()){
                   /* mSwipe.setProgressViewOffset(false,
                            height-150,height-50);*/
                    mSwipe.setRefreshing(true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<String> mData=new ArrayList<String>();
                            for (int i = 0; i < 5; i++) {
                                mData.add("NO."+i);
                            }
                            adapter.addMore(mData);
                            mRecycler.smoothScrollToPosition(adapter.getItemCount()-1);
                            mSwipe.setRefreshing(false);
                        }
                    },3000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager manager=(LinearLayoutManager)recyclerView.getLayoutManager();
                lastVisibleItem=manager.findLastCompletelyVisibleItemPosition();
            }
        });
    }


}
