package com.example.wangfeng.test.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangfeng.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengye on 2016/9/9.
 * <p/>
 * CollActivity 中recyclerView 的瀑布流适配器
 */
public class CollAdapter extends RecyclerView.Adapter <CollAdapter.MyViewHolder>{
    private List<String> mdate;
    private List<Integer> mHeights;
    public CollAdapter(List<String> mdate) {
        this.mdate = mdate;
        mHeights = new ArrayList<>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview_coll_activity, parent, false);

        return new MyViewHolder(item);
    }


    public void onBindViewHolder(MyViewHolder holder, int position) {
        // 随机高度, 模拟瀑布效果.
        if (mHeights.size() <= position) {
            mHeights.add((int) (100 + Math.random() * 300));
        }

        ViewGroup.LayoutParams lp = holder.getTextView().getLayoutParams();
        lp.height = mHeights.get(position);
        holder.getTextView().setText(mdate.get(position));
        holder.getTextView().setLayoutParams(lp);
    }

    @Override
    public int getItemCount() {
        return mdate.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
private TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return mTextView;
        }

    }

}
