package com.jiufang.wsyapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.bean.GetBindDeviceListBean;
import com.jiufang.wsyapp.ui.LcPlayActivity;

import java.util.List;

/**
 * Created by Administrator on 2020/4/30.
 */

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ViewHolder> {

    private Context context;
    private List<GetBindDeviceListBean.DataBean.RecordsBean> data;

    public IndexAdapter(List<GetBindDeviceListBean.DataBean.RecordsBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_index, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int deviceStatus = data.get(position).getDeviceStatus();
        int brandId = data.get(position).getBrandId();
        if(deviceStatus == 0){
            //不在线
            holder.ll1.setVisibility(View.GONE);
            holder.ll2.setVisibility(View.VISIBLE);
            holder.tvDeviceName2.setText(data.get(position).getDeviceName());
        }else {
            //在线
            holder.ll1.setVisibility(View.VISIBLE);
            holder.ll2.setVisibility(View.GONE);
            holder.tvDeviceName.setText(data.get(position).getDeviceName());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(brandId == 1){
                    //乐橙
                    Intent intent = new Intent();
                    intent.setClass(context, LcPlayActivity.class);
                    intent.putExtra("id", data.get(position).getId()+"");
                    context.startActivity(intent);
                }else {
                    //萤石
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout ll1;
        private LinearLayout ll2;
        private TextView tvDeviceName;
        private TextView tvDeviceName2;

        public ViewHolder(View itemView) {
            super(itemView);
            ll1 = itemView.findViewById(R.id.ll1);
            ll2 = itemView.findViewById(R.id.ll2);
            tvDeviceName = itemView.findViewById(R.id.tv_device_name);
            tvDeviceName2 = itemView.findViewById(R.id.tv_device_name2);
        }
    }

}
