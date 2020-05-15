package com.jiufang.wsyapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.bean.GetComboCategoryListBean;
import com.jiufang.wsyapp.ui.LcTaocanDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2020/5/13.
 */

public class TaocanLcItemAdapter extends RecyclerView.Adapter<TaocanLcItemAdapter.ViewHolder> {

    private Context context;
    private List<GetComboCategoryListBean.DataBean.ComboVoListBean> data;

    public TaocanLcItemAdapter(List<GetComboCategoryListBean.DataBean.ComboVoListBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_taocan_lc_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        if(i%4 == 0){
            viewHolder.ll.setBackgroundResource(R.mipmap.bg_taocan_yellow);
        }else if(i%4 == 1){
            viewHolder.ll.setBackgroundResource(R.mipmap.bg_taocan_blue);
        }else if(i%4 == 2){
            viewHolder.ll.setBackgroundResource(R.mipmap.bg_taocan_orange);
        }else if(i%4 == 3){
            viewHolder.ll.setBackgroundResource(R.mipmap.bg_taocan_pink);
        }

        viewHolder.tvTitle.setText(data.get(i).getComboName());
        viewHolder.tvContent.setText(data.get(i).getComboContent());
        viewHolder.tvName.setText(data.get(i).getComboName());
        viewHolder.tvPrice.setText(data.get(i).getRetailPrice()+"");
        String money = "<small><small>¥</small></small>"+data.get(i).getRetailPrice();
        viewHolder.tvMoney.setText(Html.fromHtml(money));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, LcTaocanDetailsActivity.class);
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
        private TextView tvContent;
        private TextView tvName;
        private TextView tvPrice;
        private TextView tvMoney;
        private LinearLayout ll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvMoney = itemView.findViewById(R.id.tv_money);
            ll = itemView.findViewById(R.id.ll);
        }
    }

}
