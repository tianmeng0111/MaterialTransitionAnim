package com.example.tm.materialtransitionanim;

import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/23.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private List<AnimalBean> list;

    public MyRecyclerAdapter(List<AnimalBean> list){
        this.list = list;
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , int data);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, (int)v.getTag());
            }
        }
    };

    @Override
    public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        view.findViewById(R.id.iv_item).setOnClickListener(onClickListener);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRecyclerAdapter.MyViewHolder holder, int position) {
        holder.iv.setImageResource(list.get(position).getImage());
        holder.tv.setText(list.get(position).getName());
        holder.iv.setTag(list.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView iv;
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_item);
            tv = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
}
