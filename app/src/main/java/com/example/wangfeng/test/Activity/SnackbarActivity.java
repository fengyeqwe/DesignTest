package com.example.wangfeng.test.Activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;

import com.example.wangfeng.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
*test ：snackbar
*       textInputLayout textOnputEdittext
*       FloatingActionButton (ps: 定义背景色等属性时，需已app：开头的属性定义 如： app:backgroundTint="@color/Green")
*
* */
public class SnackbarActivity extends AppCompatActivity {

    @BindView(R.id.snackbar_btn)
    Button mSnackbarBtn;
    @BindView(R.id.edit2)
    TextInputEditText mEdit2;
    @BindView(R.id.floatBtn)
    FloatingActionButton mFloatBtn;
    @BindView(R.id.cloud)
    View mCloud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        final TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.snackbar_edit);
        EditText editText = textInputLayout.getEditText();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence sequence, int i, int i1, int i2) {
                if (sequence.length() > 4) {
                    textInputLayout.setError("error");
                    textInputLayout.setErrorEnabled(true);
                } else {
                    textInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence sequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mEdit2.setScaleX(1.7f);//设置字间隔 (实际上是拉伸字体，且比例不是成比例增加)
        mEdit2.setHint("password");
        mEdit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence sequence, int i, int i1, int i2) {
                if (sequence.length() > 4) {
                    textInputLayout.setError("error");
                    textInputLayout.setErrorEnabled(true);
                } else {
                    textInputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence sequence, int i, int i1, int i2) {
                Snackbar.make(mEdit2, "writing", Snackbar.LENGTH_LONG).setDuration(2000).show();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    @OnClick({R.id.snackbar_btn, R.id.floatBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.snackbar_btn:
                Snackbar.make(mSnackbarBtn, "click Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("delete", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mSnackbarBtn.setBackgroundColor(getResources().getColor(R.color.Green));
                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.Green))
                        .setDuration(6000)
                        .show();
                break;
            case R.id.floatBtn:
                animator();
                break;
        }
    }

    private void animator() {//雾化动画
        ObjectAnimator animator=ObjectAnimator.ofFloat(mFloatBtn,"rotation",0,-155,-135);
        animator.setDuration(500);
        animator.start();
        mCloud.setVisibility(View.VISIBLE);
        AlphaAnimation animation=new AlphaAnimation(0,0.7f);
        animation.setDuration(1000);
        animation.setFillAfter(false);
        mCloud.startAnimation(animation);
        mCloud.setVisibility(View.INVISIBLE);
    }
}
