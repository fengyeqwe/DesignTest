package com.example.wangfeng.test.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.wangfeng.test.Adapter.TabAdapter;
import com.example.wangfeng.test.Fragment.TabFragment;
import com.example.wangfeng.test.R;

import java.util.ArrayList;
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
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        ArrayList<String> tabTitles= new ArrayList<>();
        for (int i=0;i<4;i++){
            TabFragment fragment = TabFragment.newInstance(i);
            fragments.add(fragment);
            tabTitles.add("第"+(i+1)+"页");
        }
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(),fragments,tabTitles);
        designPager.setAdapter(adapter);
        designTab.setupWithViewPager(designPager);
    }
}
