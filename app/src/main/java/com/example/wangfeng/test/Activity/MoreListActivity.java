package com.example.wangfeng.test.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangfeng.test.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.text)
    TextView mText;
    @BindView(R.id.list)
    RecyclerView mList;
    @BindView(R.id.activity_more_list)
    LinearLayout mActivityMoreList;
    static List<TextView> views = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_list);
        ButterKnife.bind(this);
        views.add(mText);
        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MoreListActivity.this, "click"+views.size(), Toast.LENGTH_SHORT).show();
                views.get(views.size()-1).setText(System.currentTimeMillis()+":");

            }
        });
    }
}
