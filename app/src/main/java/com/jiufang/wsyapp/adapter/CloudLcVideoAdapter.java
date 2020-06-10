package com.jiufang.wsyapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.bean.GetLcCloudStorageRecordListBean;

import java.util.List;

/**
 * Created by Administrator on 2020/6/1.
 */

public class CloudLcVideoAdapter extends RecyclerView.Adapter<CloudLcVideoAdapter.ViewHolder> {

    private Context context;
    private List<GetLcCloudStorageRecordListBean.DataBean> data;
    private ClickListener listener;
    private boolean isEdit = false;

    public CloudLcVideoAdapter(List<GetLcCloudStorageRecordListBean.DataBean> data, ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_cloud_video, viewGroup, false);
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
                    viewHolder.ivSelect.setImageResource(R.mipmap.duihao);
                }else {
                    listener.onClick(i);
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

    public interface ClickListener{
        void onClick(int pos);
    }

}
