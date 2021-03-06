package com.jiufang.wsyapp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
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
import com.jiufang.wsyapp.bean.GetBindDeviceListBean;
import com.jiufang.wsyapp.mediaplay.MediaPlayActivity;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.GlideUtils;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;
import com.jiufang.wsyapp.ysmediaplay.EZRealPlayActivity;
import com.jiufang.wsyapp.ysmediaplay.util.EZUtils;
import com.videogo.constant.IntentConsts;
import com.videogo.openapi.bean.EZCameraInfo;
import com.videogo.openapi.bean.EZDeviceInfo;

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
 * Created by Administrator on 2020/6/1.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context context;
    private List<GetBindDeviceListBean.DataBean.RecordsBean> data;
    private Dialog dialog;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    WeiboDialogUtils.closeDialog(dialog);
                    ToastUtil.showShort(context, "网络异常");
                    break;
            }
        }
    };

    public SearchAdapter(List<GetBindDeviceListBean.DataBean.RecordsBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_search, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.tvName.setText(data.get(i).getDeviceName());
        viewHolder.tvCode.setText(data.get(i).getSnCode());
        int brandId = data.get(i).getBrandId();
        int deviceStatus = data.get(i).getDeviceStatus();
        if(brandId == 1){
            GlideUtils.into(context, R.mipmap.lc, viewHolder.iv);
        }else if(brandId == 2){
            GlideUtils.into(context, R.mipmap.ys, viewHolder.iv);
        }
        int haveCloud = data.get(i).getHaveCloudConsole();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(brandId == 1){
                    if(deviceStatus == 0){
                        ToastUtil.showShort(context, "设备不在线");
                    }else {
                        dialog = WeiboDialogUtils.createLoadingDialog(context, "正在跳转...");
                        //乐橙
                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("bindDeviceId", data.get(i).getId() + "");
                        map.put("userId", SpUtils.getUserId(context));
                        ViseUtil.Post(context, NetUrl.getBindDeviceDetail, map, dialog, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                Logger.e("123123", s);
                                Gson gson = new Gson();
                                GetBindDeviceDetailBean bean = gson.fromJson(s, GetBindDeviceDetailBean.class);
                                Intent intent = new Intent(context, MediaPlayActivity.class);
                                intent.putExtra("bean", bean);
                                intent.putExtra("cloud", haveCloud);
                                intent.putExtra("id", data.get(i).getId()+"");
                                intent.putExtra("TYPE", MediaPlayActivity.IS_VIDEO_ONLINE);
                                intent.putExtra("MEDIA_TITLE", R.string.live_play_name);
                                context.startActivity(intent);
                            }

                            @Override
                            public void onElse(String s) {

                            }
                        });
                    }

                }else {
                    if(deviceStatus == 0){
                        ToastUtil.showShort(context, "设备不在线");
                    }else {
                        dialog = WeiboDialogUtils.createLoadingDialog(context, "正在跳转...");
                        //萤石
                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("bindDeviceId", data.get(i).getId() + "");
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
                                Observable<EZDeviceInfo> observable = Observable.create(new ObservableOnSubscribe<EZDeviceInfo>() {
                                    @Override
                                    public void subscribe(ObservableEmitter<EZDeviceInfo> e) throws Exception {
                                        Logger.e("123123", "1");
                                        EZDeviceInfo mDeviceInfo = MyApplication.getOpenSDK().getDeviceInfo(bean.getData().getSnCode());
                                        e.onNext(mDeviceInfo);
                                        Logger.e("123123", "2");
                                    }
                                });
                                Observer<EZDeviceInfo> observer = new Observer<EZDeviceInfo>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(EZDeviceInfo value) {
                                        Logger.e("123123", "3");
                                        EZCameraInfo mCameraInfo = EZUtils.getCameraInfoFromDevice(value, 0);
                                        Intent intent;
                                        Logger.e("123123", "4");
                                        timer.cancel();
                                        intent = new Intent(context, EZRealPlayActivity.class);
                                        intent.putExtra(IntentConsts.EXTRA_CAMERA_INFO, mCameraInfo);
                                        intent.putExtra(IntentConsts.EXTRA_DEVICE_INFO, value);
                                        intent.putExtra("id", data.get(i).getId()+"");
                                        intent.putExtra("cloud", haveCloud);
                                        intent.putExtra("code", bean.getData().getSecurityCode());
                                        intent.putExtra("title", bean.getData().getDeviceName());
                                        context.startActivity(intent);
                                        WeiboDialogUtils.closeDialog(dialog);
                                        Logger.e("123123", "5");
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
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView tvName;
        private TextView tvCode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCode = itemView.findViewById(R.id.tv_code);
        }
    }

}
