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
import com.jiufang.wsyapp.bean.GetLoginLogPageListBean;
import com.jiufang.wsyapp.ui.LoginHistoryDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2020/7/10.
 */

public class LoginHistoryAdapter extends RecyclerView.Adapter<LoginHistoryAdapter.ViewHolder> {

    private Context context;
    private List<GetLoginLogPageListBean.DataBean.RecordsBean> data;

    public LoginHistoryAdapter(List<GetLoginLogPageListBean.DataBean.RecordsBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_login_history, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.tv.setText(data.get(i).getOsName()+"("+data.get(i).getOsVersion()+")");
        viewHolder.tvTime.setText(data.get(i).getLoginTime());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, LoginHistoryDetailsActivity.class);
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

        private TextView tv;
        private TextView tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

}
