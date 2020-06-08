package com.jiufang.wsyapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.bean.GetYSCloudStorageRecordListBean;
import com.jiufang.wsyapp.bean.GetYSLocalStorageRecordListBean;
import com.jiufang.wsyapp.utils.GlideUtils;
import com.jiufang.wsyapp.utils.Logger;
import com.videogo.openapi.bean.EZAlarmInfo;

import java.util.List;

/**
 * Created by Administrator on 2020/6/1.
 */

public class CloudYsVideoAdapter extends RecyclerView.Adapter<CloudYsVideoAdapter.ViewHolder> {

    private Context context;
//    private List<GetYSCloudStorageRecordListBean.DataBean> data;
    private List<EZAlarmInfo> data;
    private boolean isEdit = false;

    public CloudYsVideoAdapter(List<EZAlarmInfo> data) {
        this.data = data;
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
//        Logger.e("CloudYsVideoAdapter", data.get(i).getCoverPic().replaceAll("&amp;", "&"));
//        GlideUtils.into(context, data.get(i).getCoverPic().replaceAll("&amp;", "&"), viewHolder.ivTitle);
//        String time = data.get(i).getStartTime();
//        if(time.contains(" ")){
//            viewHolder.tvTime.setText(time.split(" ")[1]);
//        }
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
        private ImageView ivTitle;
        private TextView tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSelect = itemView.findViewById(R.id.iv_select);
            ivTitle = itemView.findViewById(R.id.iv_title);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

}
