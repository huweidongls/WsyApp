package com.jiufang.wsyapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.bean.GetSysMessagePageListBean;
import com.jiufang.wsyapp.ui.MsgSystemDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2020/5/9.
 */

public class MessageSystemAdapter extends RecyclerView.Adapter<MessageSystemAdapter.ViewHolder> {

    private Context context;
    private List<GetSysMessagePageListBean.DataBean.RecordsBean> data;

    public MessageSystemAdapter(List<GetSysMessagePageListBean.DataBean.RecordsBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_system, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        int type = data.get(i).getSysType();
        if(type == 1){
            viewHolder.tvTitle.setText("【通知】"+data.get(i).getMessageTitle());
        }else if(type == 2){
            viewHolder.tvTitle.setText("【公告】"+data.get(i).getMessageTitle());
        }
        viewHolder.tvTime.setText(data.get(i).getMessageTime());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, MsgSystemDetailsActivity.class);
                intent.putExtra("id", data.get(i).getId()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle;
        private TextView tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

}
