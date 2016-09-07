package com.example.wangfeng.test.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangfeng.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fengye on 2016/9/1.
 */
public class TabFragment extends Fragment {

    @BindView(R.id.textView)
    TextView textView;
    private View view;
    public static final String ARGS_PAGE = "args_page";
    private int page;

    public static TabFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARGS_PAGE, page);
        TabFragment fragment = new TabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt(ARGS_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab, null, false);
        ButterKnife.bind(this, view);
        switch (page){
            case 1:
                textView.setBackgroundColor(Color.parseColor("#FF0000"));
                break;
              case 2:
                textView.setBackgroundColor(Color.parseColor("#00FF00"));
                break;
              case 3:
                textView.setBackgroundColor(Color.parseColor("#0000FF"));
                break;
              case 4:
                textView.setBackgroundColor(Color.parseColor("#F0FF00"));
                break;
            default:
                break;
        }

        return view;
    }


}
