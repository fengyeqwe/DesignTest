package com.example.wangfeng.test;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.wangfeng.test.Adapter.TabAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DesignActivity extends AppCompatActivity {
    @BindView(R.id.design_tab)
    TabLayout designTab;
    @BindView(R.id.design_pager)
    ViewPager designPager;
    private List<View> list_fragment;                         //fragment列表
    private List<String> list_Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), this);
        designPager.setAdapter(adapter);
        designTab.setupWithViewPager(designPager);
    }
}
