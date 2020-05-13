package com.jiufang.wsyapp.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2020/5/13.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(), viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public abstract int getLayoutId();

    public abstract RecyclerView.ViewHolder setViewHolder();

    public abstract List<?> getData();

    @Override
    public int getItemCount() {
        return getData() == null ? 0 : getData().size();
    }



}
