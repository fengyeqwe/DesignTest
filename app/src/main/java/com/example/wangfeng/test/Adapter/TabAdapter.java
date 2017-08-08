package com.example.wangfeng.test.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by fengye on 2016/9/1.
 *
 * tabLayout 布局中的适配器
 */


public class TabAdapter extends FragmentPagerAdapter{
    private List<Fragment> mFragments;
    private List<String> mTabTitles;
    public TabAdapter(FragmentManager fm,List<Fragment> fragments,List<String> tabTitles) {
        super(fm);
        this.mFragments = fragments;
        this.mTabTitles =tabTitles;

    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
    public CharSequence getPageTitle(int position) {
        return mTabTitles.get(position);
    }
}
