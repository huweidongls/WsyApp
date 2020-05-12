package com.jiufang.wsyapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.utils.GlideUtils;

import java.util.List;

/**
 * Created by Administrator on 2020/5/12.
 */

public class MsgShebeiListAdapter extends RecyclerView.Adapter<MsgShebeiListAdapter.ViewHolder> {

    private Context context;
    private List<String> data;
    private boolean isEdit = false;

    public MsgShebeiListAdapter(List<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_msg_shebei_list, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void setEdit(boolean isEdit){
        this.isEdit = isEdit;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if(isEdit){
            viewHolder.ivSelect.setVisibility(View.VISIBLE);
        }else {
            viewHolder.ivSelect.setVisibility(View.GONE);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEdit){
                    GlideUtils.into(context, R.mipmap.duihao, viewHolder.ivSelect);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivSelect;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSelect = itemView.findViewById(R.id.iv_select);
        }
    }

}
