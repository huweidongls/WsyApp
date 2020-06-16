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
import com.jiufang.wsyapp.bean.FileBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/6/15.
 */

public class MyFileAdapter extends RecyclerView.Adapter<MyFileAdapter.ViewHolder> {

    private Context context;
    private List<FileBean> data;

    public MyFileAdapter(List<FileBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_my_file, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.tv.setText(data.get(i).getTime());
        List<Map<String, Object>> list = data.get(i).getMaps();
        MyFileItemAdapter itemAdapter = new MyFileItemAdapter(list);
        GridLayoutManager manager = new GridLayoutManager(context, 3);
        viewHolder.rv.setLayoutManager(manager);
        viewHolder.rv.setAdapter(itemAdapter);

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;
        private RecyclerView rv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            rv = itemView.findViewById(R.id.rv);
        }
    }

}
