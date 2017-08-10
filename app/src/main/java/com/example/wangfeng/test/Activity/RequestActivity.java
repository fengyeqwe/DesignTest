package com.example.wangfeng.test.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CheckableImageButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.wangfeng.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* 动态申请权限
* */
public class RequestActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_READ_SMS = 123;//权限的标记符
    @BindView(R.id.float_btn)
    CheckableImageButton mFloatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        ButterKnife.bind(this);

    }

    private void requestContactPermission() {//需要使用权限时，先调用该方法
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, REQUEST_CODE_READ_SMS);
        } else {
            Toast.makeText(RequestActivity.this, "已开启权限", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {//动态申请权限后回调该方法
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        handleGrantResults(requestCode, grantResults);
    }

    private void handleGrantResults(int requestCode, int[] grantResults) {
        if (requestCode == REQUEST_CODE_READ_SMS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted 获得权限后执行xxx
                Snackbar.make(mFloatBtn, "getRequest", Snackbar.LENGTH_LONG).setDuration(2000).show();
            } else {
                // Permission Denied 拒绝后xx的操作。
                Snackbar.make(mFloatBtn, "refuseRequest", Snackbar.LENGTH_LONG).setDuration(2000).show();
            }
        }
    }

    @OnClick(R.id.float_btn)
    public void onClick() {
        Snackbar.make(mFloatBtn, "click", Snackbar.LENGTH_LONG).setDuration(2000).show();
        requestContactPermission();
    }
}
