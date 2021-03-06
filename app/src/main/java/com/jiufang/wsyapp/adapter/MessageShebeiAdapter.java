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
import com.jiufang.wsyapp.bean.GetBindDeviceListBean;
import com.jiufang.wsyapp.ui.MsgLcShebeiListActivity;
import com.jiufang.wsyapp.ui.MsgYsShebeiListActivity;
import com.jiufang.wsyapp.utils.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2020/5/9.
 */

public class MessageShebeiAdapter extends RecyclerView.Adapter<MessageShebeiAdapter.ViewHolder> {

    private Context context;
    private List<GetBindDeviceListBean.DataBean.RecordsBean> data;

    public MessageShebeiAdapter(List<GetBindDeviceListBean.DataBean.RecordsBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_shebei, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvName.setText(data.get(i).getDeviceName());
        String time = data.get(i).getLastAlarmTime();
        if(!StringUtils.isEmpty(time)){
            viewHolder.tvTime.setText(time);
        }
        int brandId = data.get(i).getBrandId();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(brandId == 1){
                    Intent intent = new Intent();
                    intent.setClass(context, MsgLcShebeiListActivity.class);
//                    intent.putExtra("bean", data.get(i));
                    intent.putExtra("name", data.get(i).getDeviceName());
                    intent.putExtra("id", data.get(i).getId()+"");
                    context.startActivity(intent);
                }else {
                    Intent intent = new Intent();
                    intent.setClass(context, MsgYsShebeiListActivity.class);
//                    intent.putExtra("bean", data.get(i));
                    intent.putExtra("name", data.get(i).getDeviceName());
                    intent.putExtra("id", data.get(i).getId()+"");
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName;
        private TextView tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

}
