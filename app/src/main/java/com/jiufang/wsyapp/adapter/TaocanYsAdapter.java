package com.jiufang.wsyapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.bean.GetComboCategoryListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/5/13.
 */

public class TaocanYsAdapter extends RecyclerView.Adapter<TaocanYsAdapter.ViewHolder> {

    private Context context;
    private List<GetComboCategoryListBean.DataBean> data;

    public TaocanYsAdapter(List<GetComboCategoryListBean.DataBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_taocan_ys, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.tvTitle.setText("【"+data.get(i).getName()+"】"+data.get(i).getExplain());
        List<GetComboCategoryListBean.DataBean.ComboVoListBean> list;
        list = data.get(i).getComboVoList();
        TaocanYsItemAdapter itemAdapter = new TaocanYsItemAdapter(list);
        GridLayoutManager manager = new GridLayoutManager(context, 3);
        viewHolder.recyclerView.setLayoutManager(manager);
        viewHolder.recyclerView.setAdapter(itemAdapter);

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView recyclerView;
        private TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.rv);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }

}
