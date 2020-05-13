package com.jiufang.wsyapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiufang.wsyapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/5/13.
 */

public class TaocanLcAdapter extends RecyclerView.Adapter<TaocanLcAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    public TaocanLcAdapter(List<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_taocan_lc, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        TaocanLcItemAdapter itemAdapter = new TaocanLcItemAdapter(list);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.rv);
        }
    }

}
