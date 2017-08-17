package com.example.wangfeng.test.MyViews.MoreList;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by fengye on 2017/8/14.
 * email 1040441325@qq.com
 */

public class TreeListViewAdapter<T> extends RecyclerView.Adapter {
    protected List<Node> mNodes;
    protected List<Node> mAllNodes;
    private  OnTreeNodeClickListener mListener;

    public TreeListViewAdapter(List<T> datas) {

    }

    public void setOnTreeNodeClickListener(OnTreeNodeClickListener listener){
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    private interface OnTreeNodeClickListener{
        void onClick(Node node ,int position);
    }
}
