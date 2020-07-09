package com.jiufang.wsyapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.bean.GetComboDeviceChooseByUserIdBean;
import com.jiufang.wsyapp.ui.CloudDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2020/6/24.
 */

public class CloudAdapter extends RecyclerView.Adapter<CloudAdapter.ViewHolder> {

    private Context context;
    private List<GetComboDeviceChooseByUserIdBean.DataBean> data;

    public CloudAdapter(List<GetComboDeviceChooseByUserIdBean.DataBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_cloud, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        int brandId = data.get(i).getBrandId();
        if(brandId == 1){
            viewHolder.iv.setImageResource(R.mipmap.lc);
        }else if(brandId == 2){
            viewHolder.iv.setImageResource(R.mipmap.ys);
        }
        viewHolder.tvModel.setText(data.get(i).getDeviceModel());
        viewHolder.tvSn.setText(data.get(i).getDeviceSn());
        boolean isHave = data.get(i).isIsHaveCombo();
        boolean isGq = data.get(i).isIsExpire();
        if(isHave){
            if(isGq){
                viewHolder.tvTime.setText("已过期");
            }else {
                viewHolder.tvTime.setText("到期时间"+data.get(i).getComboExpireTime());
            }
        }else {
            viewHolder.tvTime.setText("未开启");
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(brandId == 1){
                    Intent intent = new Intent();
                    intent.setClass(context, CloudDetailsActivity.class);
                    intent.putExtra("type", "1");
                    intent.putExtra("id", data.get(i).getId()+"");
                    context.startActivity(intent);
                }else if(brandId == 2){
                    Intent intent = new Intent();
                    intent.setClass(context, CloudDetailsActivity.class);
                    intent.putExtra("type", "2");
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

        private ImageView iv;
        private TextView tvModel;
        private TextView tvSn;
        private TextView tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tvModel = itemView.findViewById(R.id.tv_model);
            tvSn = itemView.findViewById(R.id.tv_sn);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

}
