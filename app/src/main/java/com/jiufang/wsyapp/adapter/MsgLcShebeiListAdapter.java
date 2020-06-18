package com.jiufang.wsyapp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.bean.GetBindDeviceDetailBean;
import com.jiufang.wsyapp.bean.GetDeviceAlarmLcPageBean;
import com.jiufang.wsyapp.bean.GetLcCloudStorageRecordListBean;
import com.jiufang.wsyapp.mediaplay.MediaPlayActivity;
import com.jiufang.wsyapp.mediaplay.util.TimeHelper;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/5/12.
 */

public class MsgLcShebeiListAdapter extends RecyclerView.Adapter<MsgLcShebeiListAdapter.ViewHolder> {

    private Context context;
    private List<GetDeviceAlarmLcPageBean.DataBean.AlarmsBean> data;
    private boolean isEdit = false;
    private String deviceId;
    private Dialog dialog;

    public MsgLcShebeiListAdapter(List<GetDeviceAlarmLcPageBean.DataBean.AlarmsBean> data, String deviceId) {
        this.data = data;
        this.deviceId = deviceId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_msg_shebei_list, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public void setEdit(boolean isEdit){
        this.isEdit = isEdit;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if(isEdit){
            viewHolder.ivSelect.setVisibility(View.VISIBLE);
        }else {
            viewHolder.ivSelect.setVisibility(View.GONE);
        }
        viewHolder.tvTime.setText(data.get(i).getLocalDate());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEdit){
                    viewHolder.ivSelect.setImageResource(R.mipmap.duihao);
                }else {
                    dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("bindDeviceId", deviceId);
                    map.put("userId", SpUtils.getUserId(context));
                    ViseUtil.Post(context, NetUrl.getBindDeviceDetail, map, new ViseUtil.ViseListener() {
                        @Override
                        public void onReturn(String s) {
                            Logger.e("123123", s);
                            Gson gson = new Gson();
                            GetBindDeviceDetailBean mBean = gson.fromJson(s, GetBindDeviceDetailBean.class);
                            String startTime = data.get(i).getLocalDate();
                            String endTime = TimeHelper.getDateEN((StringUtils.dateFormatToLong(startTime)+120000));
                            Map<String, String> map = new LinkedHashMap<>();
                            map.put("userId", SpUtils.getUserId(context));
                            map.put("deviceId", deviceId);
                            map.put("startTime", data.get(i).getLocalDate());
                            map.put("endTime", StringUtils.dateTimeFormat(endTime));
                            ViseUtil.Post(context, NetUrl.getLcCloudStorageRecordList, map, dialog, new ViseUtil.ViseListener() {
                                @Override
                                public void onReturn(String s) {
                                    Logger.e("123123", s);
                                    Gson gson = new Gson();
                                    GetLcCloudStorageRecordListBean bean = gson.fromJson(s, GetLcCloudStorageRecordListBean.class);
                                    if(bean.getData()==null||bean.getData().size()<=0){
                                        ToastUtil.showShort(context, "暂无报警录像");
                                    }else {
                                        Intent intent = new Intent(context, MediaPlayActivity.class);
                                        intent.putExtra("type", "0");
                                        intent.putExtra("mbean", mBean);
                                        intent.putExtra("bean", bean.getData().get(0));
                                        intent.putExtra("TYPE", MediaPlayActivity.IS_VIDEO_REMOTE_CLOUD_RECORD);
                                        intent.putExtra("MEDIA_TITLE", R.string.live_play_name);
                                        context.startActivity(intent);
                                    }
                                }

                                @Override
                                public void onElse(String s) {
                                    Logger.e("123123", s);
                                }
                            });
                        }

                        @Override
                        public void onElse(String s) {

                        }
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivSelect;
        private TextView tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSelect = itemView.findViewById(R.id.iv_select);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

}
