package com.example.wangfeng.test.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.example.wangfeng.test.Fragment.TabFragment;
import com.example.wangfeng.test.R;

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
        return getThirdTabTitle(titles[position]);
    }

    public CharSequence getThirdTabTitle(String title) {
        Drawable image =context. getResources().getDrawable(R.drawable.first_select);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        // Replace blank spaces with image icon
        SpannableString sb = new SpannableString(title + "   ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, title.length(), title.length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }
}
