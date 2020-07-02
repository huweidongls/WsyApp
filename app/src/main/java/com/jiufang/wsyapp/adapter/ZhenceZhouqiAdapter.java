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
import com.jiufang.wsyapp.bean.ZhenceZhouqiBean;

import java.util.List;

/**
 * Created by Administrator on 2020/7/2.
 */

public class ZhenceZhouqiAdapter extends RecyclerView.Adapter<ZhenceZhouqiAdapter.ViewHolder> {

    private Context context;
    private List<ZhenceZhouqiBean> data;

    public ZhenceZhouqiAdapter(List<ZhenceZhouqiBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_zhence_zhouqi, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        int isSelect = data.get(i).getIsSelect();
        if(isSelect == 0){
            viewHolder.iv.setImageResource(R.mipmap.duihao_null);
        }else {
            viewHolder.iv.setImageResource(R.mipmap.duihao_blue);
        }
        viewHolder.tv.setText(data.get(i).getTime());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.get(i).getIsSelect() == 0){
                    data.get(i).setIsSelect(1);
                    viewHolder.iv.setImageResource(R.mipmap.duihao_blue);
                }else {
                    data.get(i).setIsSelect(0);
                    viewHolder.iv.setImageResource(R.mipmap.duihao_null);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;
        private ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            iv = itemView.findViewById(R.id.iv);
        }

    }

}
