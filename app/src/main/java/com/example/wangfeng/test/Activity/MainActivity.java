package com.example.wangfeng.test.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.wangfeng.test.R;

/*
* 动态申请权限
* */
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_READ_SMS = 123;//权限的标记符

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private  void requestContactPermission(){//需要使用权限时，先调用该方法
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED){
            //申请权限
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_SMS},REQUEST_CODE_READ_SMS);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {//动态申请权限后回调该方法
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        handleGrantResults(requestCode,grantResults);
    }
    private void handleGrantResults(int requestCode, int[] grantResults) {
        if (requestCode == REQUEST_CODE_READ_SMS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted 获得权限后执行xxx
                Toast.makeText(MainActivity.this, "getRequest", Toast.LENGTH_SHORT).show();
            } else {
                // Permission Denied 拒绝后xx的操作。
                Toast.makeText(MainActivity.this, "refuseRequest", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
