package com.example.wangfeng.test.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.wangfeng.test.Adapter.FirstAdapter;
import com.example.wangfeng.test.MyViews.URecyclerView;
import com.example.wangfeng.test.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FitstActivity extends AppCompatActivity {


    @BindView(R.id.list)
    URecyclerView mList;
    private ArrayList<String> mData;
    private FirstAdapter adapter;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitst);
        ButterKnife.bind(this);
        initailData();
        initView();

    }

    private void initailData() {
        mData = new ArrayList<>();
        addData();
        adapter =new FirstAdapter(mData);
    }

    private void addData() {
        for (int i = 0; i < 10; i++) {
            mData.add("NO." + num);
            num++;
        }
    }

    private void initView() {
        mList.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));
        mList.getRecyclerView().setAdapter(adapter);
       mList.setonRefreshListener(new URecyclerView.IOnRefreshListener() {
           @Override
           public void refresh() {
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       mData.clear();
                       num =0;
                       addData();
                       adapter.notifyDataSetChanged();
                       mList.setOnRefreshComplete();
                   }
               }, 2000);

           }

           @Override
           public void loadMore() {
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       addData();
                       adapter.notifyDataSetChanged();
                       mList.setOnRefreshComplete();
                   }
               }, 2000);


           }
       });

    }
}
