package com.jiufang.wsyapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.utils.GetFilesUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/6/15.
 */

public class MyFileAdapter extends RecyclerView.Adapter<MyFileAdapter.ViewHolder> {

    private Context context;
    private List<Map<String, Object>> data;

    public MyFileAdapter(List<Map<String, Object>> data) {
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
        viewHolder.tv.setText(String.valueOf(data.get(i).get(GetFilesUtils.FILE_INFO_NAME)));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

}
