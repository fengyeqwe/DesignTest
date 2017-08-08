package com.example.wangfeng.test.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_margin);
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
    }

    public ArrayList<ActivityItem> getData() {
        ArrayList<ActivityItem> data = new ArrayList<>();
        data.add(new ActivityItem("ViewPager,TabLayout联动", DesignActivity.class));
        data.add(new ActivityItem("花束直播点赞效果(贝塞尔曲线)",BesselActivity.class));
        return data;
    }
}
