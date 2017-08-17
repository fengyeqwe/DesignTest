package com.example.wangfeng.test.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.wangfeng.test.Adapter.ClickListen;
import com.example.wangfeng.test.Adapter.CollItemDecoration;
import com.example.wangfeng.test.Adapter.MainAdapter;
import com.example.wangfeng.test.Bean.ActivityItem;
import com.example.wangfeng.test.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list)
    RecyclerView mList;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final ArrayList<ActivityItem> data = getData();
        MainAdapter adapter = new MainAdapter(data);
        adapter.setOnClickListener(new ClickListen() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(MainActivity.this, data.get(position).getUrl()));
            }
        });
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(adapter);
        mList.addItemDecoration(new CollItemDecoration(this));
        mList.setElevation(1);
        mToolbar.setElevation(2);
    }

    public ArrayList<ActivityItem> getData() {
        ArrayList<ActivityItem> data = new ArrayList<>();
        data.add(new ActivityItem("ViewPager,TabLayout联动", DesignActivity.class));
        data.add(new ActivityItem("花束直播点赞效果(贝塞尔曲线)", BesselActivity.class));
        data.add(new ActivityItem("可折叠toolbar", CollActivity.class));
        data.add(new ActivityItem("刷新加载", FitstActivity.class));
        data.add(new ActivityItem("权限6.0",RequestActivity.class));
        data.add(new ActivityItem("snackbar",SnackbarActivity.class));
        data.add(new ActivityItem("图片OOM处理",ImageActivity.class));
        data.add(new ActivityItem("多级列表(unfinished)",MoreListActivity.class));
        return data;
    }
}
