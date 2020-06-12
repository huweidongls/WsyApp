package com.jiufang.wsyapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.bean.GetLcLocalStorageRecordListBean;
import com.jiufang.wsyapp.bean.GetYSLocalStorageRecordListBean;
import com.jiufang.wsyapp.utils.GlideUtils;
import com.jiufang.wsyapp.utils.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2020/6/1.
 */

public class LocalLcVideoAdapter extends RecyclerView.Adapter<LocalLcVideoAdapter.ViewHolder> {

    private Context context;
    private List<GetLcLocalStorageRecordListBean.DataBean> data;
    private ClickListener listener;
    private boolean isEdit = false;

    public LocalLcVideoAdapter(List<GetLcLocalStorageRecordListBean.DataBean> data, ClickListener listener) {
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
//        GlideUtils.into(context, data.get(i).getCoverPic().replaceAll("&amp;", "&"), viewHolder.ivTitle);
        String time = data.get(i).getStartTime();
        if(time.contains(" ")){
            viewHolder.tvTime.setText(time.split(" ")[1]);
        }
        viewHolder.tvTimeLength.setText(StringUtils.subDateTime(data.get(i).getStartTime(), data.get(i).getEndTime()));
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
        private ImageView ivTitle;
        private TextView tvTime;
        private TextView tvTimeLength;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSelect = itemView.findViewById(R.id.iv_select);
            ivTitle = itemView.findViewById(R.id.iv_title);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvTimeLength = itemView.findViewById(R.id.tv_time_length);
        }
    }

    public interface ClickListener{
        void onClick(int pos);
    }

}
