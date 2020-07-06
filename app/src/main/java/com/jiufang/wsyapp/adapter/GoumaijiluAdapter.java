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
import com.jiufang.wsyapp.bean.GetComboPurchasePageListBean;
import com.jiufang.wsyapp.ui.GoumaijiluDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2020/6/3.
 */

public class GoumaijiluAdapter extends RecyclerView.Adapter<GoumaijiluAdapter.ViewHolder> {

    private Context context;
    private List<GetComboPurchasePageListBean.DataBean.RecordsBean> data;

    public GoumaijiluAdapter(List<GetComboPurchasePageListBean.DataBean.RecordsBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_goumaijilu, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.tvName.setText(data.get(i).getBrandName()+"品牌-"+data.get(i).getComboName()+"套餐");
        viewHolder.tvDevice.setText("设备（"+data.get(i).getSnCode()+"）");
        viewHolder.tvTime.setText(data.get(i).getPurchaseTime());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, GoumaijiluDetailsActivity.class);
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

        private TextView tvName;
        private TextView tvDevice;
        private TextView tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDevice = itemView.findViewById(R.id.tv_device);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

}
