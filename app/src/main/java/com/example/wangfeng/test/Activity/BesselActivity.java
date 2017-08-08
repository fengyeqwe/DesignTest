package com.example.wangfeng.test.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wangfeng.test.MyViews.LoveLayout;
import com.example.wangfeng.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BesselActivity extends AppCompatActivity {

    @BindView(R.id.activity_bessel)
    LoveLayout mActivityBessel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bessel);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.start)
    public void onClick() {
        mActivityBessel.addLove();
    }
}
