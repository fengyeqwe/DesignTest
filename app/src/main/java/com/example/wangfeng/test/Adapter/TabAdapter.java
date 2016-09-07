package com.example.wangfeng.test.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.wangfeng.test.Fragment.TabFragment;

/**
 * Created by fengye on 2016/9/1.
 *
 * tabLayout 布局中的适配器
 */


public class TabAdapter extends FragmentPagerAdapter{
    private final  int COUNT=4;
    private  String[] titles=new String[]{"first","second","third","fourth"};
    private  Context context;

    public TabAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        return TabFragment.newInstance(position+1);
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
