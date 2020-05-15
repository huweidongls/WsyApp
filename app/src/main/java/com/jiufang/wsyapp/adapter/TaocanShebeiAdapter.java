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
import com.jiufang.wsyapp.bean.GetComboDeviceChooseListBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.ui.TaocanBuyActivity;
import com.jiufang.wsyapp.utils.GlideUtils;

import java.util.List;

/**
 * Created by Administrator on 2020/5/14.
 */

public class TaocanShebeiAdapter extends RecyclerView.Adapter<TaocanShebeiAdapter.ViewHolder> {

    private Context context;
    private List<GetComboDeviceChooseListBean.DataBean> data;

    public TaocanShebeiAdapter(List<GetComboDeviceChooseListBean.DataBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_taocan_shebei, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        GlideUtils.into(context, NetUrl.BASE_IMG_URL+data.get(i).getProductImage(), viewHolder.iv);
        viewHolder.tvTitle.setText(data.get(i).getDeviceName());
//        viewHolder.tvContent.setText();
        viewHolder.tvTime.setText(data.get(i).getComboExpireTime());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, TaocanBuyActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView tvTitle;
        private TextView tvContent;
        private TextView tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

}
