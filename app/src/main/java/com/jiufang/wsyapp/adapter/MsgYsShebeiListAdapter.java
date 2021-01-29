package com.jiufang.wsyapp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.app.MyApplication;
import com.jiufang.wsyapp.bean.GetBindDeviceDetailBean;
import com.jiufang.wsyapp.bean.GetDeviceAlarmYsPageBean;
import com.jiufang.wsyapp.mediaplay.util.TimeHelper;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.ui.YsPlayActivity;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;
import com.jiufang.wsyapp.ysmediaplay.util.EZUtils;
import com.videogo.openapi.bean.EZCameraInfo;
import com.videogo.openapi.bean.EZCloudRecordFile;
import com.videogo.openapi.bean.EZDeviceInfo;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2020/5/12.
 */

public class MsgYsShebeiListAdapter extends RecyclerView.Adapter<MsgYsShebeiListAdapter.ViewHolder> {

    private Context context;
    private List<GetDeviceAlarmYsPageBean.DataBean.RecordsBean> data;
    private boolean isEdit = false;
    private String deviceId;
    private EZCameraInfo mCameraInfo;
    private Dialog dialog;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    WeiboDialogUtils.closeDialog(dialog);
                    ToastUtil.showShort(context, "网络异常");
                    break;
            }
        }
    };

    public MsgYsShebeiListAdapter(List<GetDeviceAlarmYsPageBean.DataBean.RecordsBean> data, String deviceId) {
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
        viewHolder.tvTime.setText(data.get(i).getAlarmTime());
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
                            GetBindDeviceDetailBean bean = gson.fromJson(s, GetBindDeviceDetailBean.class);

                            Timer timer = new Timer();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    Message msg = Message.obtain();
                                    msg.what = 1;
                                    handler.sendMessage(msg);
                                }
                            }, 120000);
                            MyApplication.getOpenSDK().setAccessToken(bean.getData().getDeviceAccessToken());

                            String startTime = data.get(i).getAlarmTime();
                            String endTime = TimeHelper.getDateEN((StringUtils.dateFormatToLong(startTime)+120000));

                            Calendar mStartTime = Calendar.getInstance();
                            Calendar mEndTime = Calendar.getInstance();

                            mStartTime.setTime(StringUtils.toDate(startTime));
                            mEndTime.setTime(StringUtils.toDate(endTime));

                            Observable<List<EZCloudRecordFile>> observable = Observable.create(new ObservableOnSubscribe<List<EZCloudRecordFile>>() {
                                @Override
                                public void subscribe(ObservableEmitter<List<EZCloudRecordFile>> e) throws Exception {
                                    EZDeviceInfo mDeviceInfo = MyApplication.getOpenSDK().getDeviceInfo(bean.getData().getSnCode());
                                    mCameraInfo = EZUtils.getCameraInfoFromDevice(mDeviceInfo, 0);
                                    List<EZCloudRecordFile> result = MyApplication.getOpenSDK().searchRecordFileFromCloud(bean.getData().getSnCode(), mCameraInfo.getCameraNo(), mStartTime,
                                            mEndTime);
                                    e.onNext(result);
                                }
                            });
                            Observer<List<EZCloudRecordFile>> observer = new Observer<List<EZCloudRecordFile>>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(List<EZCloudRecordFile> value) {
                                    WeiboDialogUtils.closeDialog(dialog);
                                    if(value == null||value.size()<=0){
                                        ToastUtil.showShort(context, "暂无报警录像");
                                    }else {
                                        Intent intent = new Intent();
                                        intent.setClass(context, YsPlayActivity.class);
                                        intent.putExtra("code", bean.getData().getSnCode());
                                        intent.putExtra("id", deviceId);
                                        intent.putExtra("cameraNo", mCameraInfo.getCameraNo());
                                        intent.putExtra("yanzheng", bean.getData().getSecurityCode());
                                        intent.putExtra("bean", value.get(0));
                                        intent.putExtra("type", "0");
                                        intent.putExtra("title", bean.getData().getDeviceName());
                                        context.startActivity(intent);
                                    }
//                            adapter = new CloudYsVideoAdapter(value, new CloudYsVideoAdapter.ClickListener() {
//                                @Override
//                                public void onClick(int pos) {
//                                    Intent intent = new Intent();
//                                    intent.setClass(context, YsPlayActivity.class);
//                                    intent.putExtra("code", code);
//                                    intent.putExtra("id", id);
//                                    intent.putExtra("cameraNo", cameraNo);
//                                    intent.putExtra("yanzheng", yanzheng);
//                                    intent.putExtra("bean", value.get(pos));
//                                    intent.putExtra("type", "0");
//                                    intent.putExtra("title", title);
//                                    startActivity(intent);
//                                }
//                            });
//                            GridLayoutManager manager = new GridLayoutManager(context, 3);
//                            recyclerView.setLayoutManager(manager);
//                            recyclerView.setAdapter(adapter);
//                            WeiboDialogUtils.closeDialog(dialog);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            };
                            observable.subscribeOn(Schedulers.newThread())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(observer);
                        }

                        @Override
                        public void onElse(String s) {
                            Logger.e("123123", s);
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
