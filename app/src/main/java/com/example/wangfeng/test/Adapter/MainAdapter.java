package com.example.wangfeng.test.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wangfeng.test.Bean.ActivityItem;
import com.example.wangfeng.test.R;

import java.util.List;

/**
 * Created by fengye on 2017/8/8.
 * email 1040441325@qq.com
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{
    private List<ActivityItem> mTitles;
    private ClickListen  mListen;

    public MainAdapter(List<ActivityItem> titles) {
        mTitles = titles;
    }
    public void setOnClickListener(ClickListen clickListen){
        this.mListen =clickListen;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText((position+1)+".  "+mTitles.get(position).getTitle());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView  text ;
        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListen!=null){
                mListen.onClick(v, (Integer) itemView.getTag());
            }
        }
    }
}
