package com.jiufang.wsyapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.ui.PreviewActivity;
import com.jiufang.wsyapp.utils.GetFilesUtils;
import com.jiufang.wsyapp.utils.GlideUtils;
import com.jiufang.wsyapp.utils.Logger;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/6/16.
 */

public class MyFileItemAdapter extends RecyclerView.Adapter<MyFileItemAdapter.ViewHolder> {

    private Context context;
    private List<Map<String, Object>> data;

    public MyFileItemAdapter(List<Map<String, Object>> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_my_file_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Logger.e("123123", String.valueOf(data.get(i).get(GetFilesUtils.FILE_INFO_TYPE)));
        String fileType = String.valueOf(data.get(i).get(GetFilesUtils.FILE_INFO_TYPE));
        String path = String.valueOf(data.get(i).get(GetFilesUtils.FILE_INFO_PATH));
        if(fileType.equals("mp4")){
            viewHolder.ivPlay.setVisibility(View.VISIBLE);
            GlideUtils.into(context, "file://"+path, viewHolder.iv);
        }else {
            viewHolder.ivPlay.setVisibility(View.GONE);
            GlideUtils.into(context, "file://"+path, viewHolder.iv);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fileType.equals("mp4")){
                    Intent intent = new Intent();
                    intent.setClass(context, PreviewActivity.class);
                    intent.putExtra("type", "1");
                    intent.putExtra("path", path);
                    context.startActivity(intent);
                }else {
                    Intent intent = new Intent();
                    intent.setClass(context, PreviewActivity.class);
                    intent.putExtra("type", "0");
                    intent.putExtra("path", path);
                    context.startActivity(intent);
                }
            }
        });
    }

    // 获取视频缩略图
    public Bitmap getVideoThumbnail(String filePath) {
        Bitmap b=null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            b=retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();

        } finally {
            try {
                retriever.release();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return b;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private ImageView ivPlay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            ivPlay = itemView.findViewById(R.id.iv_play);
        }
    }

}
