package com.example.wangfeng.test.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangfeng.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengye on 2016/9/20.
 * email 1040441325@qq.com
 */
public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.FirstViewHolder> {
    private ArrayList<String> mDate;

    public FirstAdapter(ArrayList<String> date) {
        this.mDate = date;
    }

    @Override
    public FirstViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_first, parent, false);
        return new FirstViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FirstViewHolder holder, int position) {
         holder.mTextView.setText(mDate.get(position));
    }

    @Override
    public int getItemCount() {
        return mDate.size();
    }

    class FirstViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;

        public FirstViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.img);
            mTextView = (TextView) itemView.findViewById(R.id.text);
        }
    }

    public void addMore(List<String > newDatas){
        mDate.addAll(newDatas);
        notifyDataSetChanged();

    }
}

