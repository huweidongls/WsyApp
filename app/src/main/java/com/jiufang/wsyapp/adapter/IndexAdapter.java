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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.app.MyApplication;
import com.jiufang.wsyapp.bean.GetBindDeviceDetailBean;
import com.jiufang.wsyapp.bean.GetBindDeviceListBean;
import com.jiufang.wsyapp.dialog.DialogMsgDelete;
import com.jiufang.wsyapp.mediaplay.MediaPlayActivity;
import com.jiufang.wsyapp.mediaplay.entity.ChannelInfo;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.ui.HelpActivity;
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
 * Created by Administrator on 2020/4/30.
 */

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ViewHolder> {

    private Context context;
    private List<GetBindDeviceListBean.DataBean.RecordsBean> data;
    private ChannelInfo channelInfo;
    private Dialog dialog;
    private ClickListener listener;

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

    public IndexAdapter(List<GetBindDeviceListBean.DataBean.RecordsBean> data, ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_index, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int deviceStatus = data.get(position).getDeviceStatus();
        int brandId = data.get(position).getBrandId();
        if(brandId == 1){
            holder.tvBrand.setText("乐橙");
            holder.tvBrand2.setText("乐橙");
        }else {
            holder.tvBrand.setText("萤石");
            holder.tvBrand2.setText("萤石");
        }
        if(deviceStatus == 0){
            //不在线
            holder.ll1.setVisibility(View.GONE);
            holder.ll2.setVisibility(View.VISIBLE);
            holder.tvDeviceName2.setText(data.get(position).getDeviceName());
            holder.tvOffTime.setText(data.get(position).getOfflineTime());
        }else {
            //在线
            holder.ll1.setVisibility(View.VISIBLE);
            holder.ll2.setVisibility(View.GONE);
            holder.tvDeviceName.setText(data.get(position).getDeviceName());
        }
        //云存储状态
        int cloudStorageStatus = data.get(position).getCloudStorageStatus();
        if(cloudStorageStatus == 0){
            //未开启
            holder.ivYuncunchu.setVisibility(View.GONE);
            holder.ivYuncunchu2.setVisibility(View.GONE);
        }else if(cloudStorageStatus == 1){
            //1已开启
            holder.ivYuncunchu.setImageResource(R.mipmap.index_iv1);
            holder.ivYuncunchu2.setImageResource(R.mipmap.index_iv1);
            holder.ivYuncunchu.setVisibility(View.VISIBLE);
            holder.ivYuncunchu2.setVisibility(View.VISIBLE);
        }else if(cloudStorageStatus == 2){
            //2即将到期
            holder.ivYuncunchu.setImageResource(R.mipmap.index_iv1_red1);
            holder.ivYuncunchu2.setImageResource(R.mipmap.index_iv1_red1);
            holder.ivYuncunchu.setVisibility(View.VISIBLE);
            holder.ivYuncunchu2.setVisibility(View.VISIBLE);
        }else if(cloudStorageStatus == 3){
            //3已过期
            holder.ivYuncunchu.setImageResource(R.mipmap.index_iv1_red2);
            holder.ivYuncunchu2.setImageResource(R.mipmap.index_iv1_red2);
            holder.ivYuncunchu.setVisibility(View.VISIBLE);
            holder.ivYuncunchu2.setVisibility(View.VISIBLE);
        }
        int nativeStorageStatus = data.get(position).getNativeStorageStatus();//本地存储状态：0 没有 1有
        if(nativeStorageStatus == 0){
            holder.ivSd.setVisibility(View.GONE);
            holder.ivSd2.setVisibility(View.GONE);
        }else {
            holder.ivSd.setImageResource(R.mipmap.index_iv2);
            holder.ivSd2.setImageResource(R.mipmap.index_iv2);
            holder.ivSd.setVisibility(View.VISIBLE);
            holder.ivSd2.setVisibility(View.VISIBLE);
        }
        int isHaveNewMessage = data.get(position).getIsHaveNewMessage();//是否有新消息：0没有 1有
        if(isHaveNewMessage == 0){
            holder.ivMsg.setVisibility(View.GONE);
            holder.ivMsg2.setVisibility(View.GONE);
        }else {
            holder.ivMsg.setVisibility(View.VISIBLE);
            holder.ivMsg2.setVisibility(View.VISIBLE);
        }

        holder.llMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onMore(position);
            }
        });

        holder.llMore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onMore(position);
            }
        });

        int haveCloud = data.get(position).getHaveCloudConsole();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(deviceStatus == 0){
                    ToastUtil.showShort(context, "设备不在线");
                }else {
                    dialog = WeiboDialogUtils.createLoadingDialog(context, "正在跳转...");
                    if (brandId == 1) {
                        //乐橙
//                    Intent intent = new Intent();
//                    intent.setClass(context, LcPlayActivity.class);
//                    intent.putExtra("id", data.get(position).getId()+"");
//                    context.startActivity(intent);

                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("bindDeviceId", data.get(position).getId() + "");
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
                                intent.putExtra("id", data.get(position).getId()+"");
                                intent.putExtra("TYPE", MediaPlayActivity.IS_VIDEO_ONLINE);
                                intent.putExtra("MEDIA_TITLE", R.string.live_play_name);
                                context.startActivity(intent);
                            }

                            @Override
                            public void onElse(String s) {

                            }
                        });
                    } else {
                        //萤石

                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("bindDeviceId", data.get(position).getId() + "");
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
                                        intent.putExtra("id", data.get(position).getId()+"");
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
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                DialogMsgDelete dialogMsgDelete = new DialogMsgDelete(context, "确定解绑设备吗？", R.mipmap.lajitong, new DialogMsgDelete.ClickListener() {
//                    @Override
//                    public void onSure() {
//                        Map<String, String> map = new LinkedHashMap<>();
//                        map.put("bindDeviceId", data.get(position).getId() + "");
//                        ViseUtil.Post(context, NetUrl.unBindDevice, map, new ViseUtil.ViseListener() {
//                            @Override
//                            public void onReturn(String s) {
//                                ToastUtil.showShort(context, "设备解绑成功");
//                            }
//
//                            @Override
//                            public void onElse(String s) {
//
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onCancel() {
//
//                    }
//                });
//                dialogMsgDelete.show();
//                return false;
//            }
//        });
        holder.tvHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, HelpActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout ll1;
        private LinearLayout ll2;
        private TextView tvDeviceName;
        private TextView tvDeviceName2;
        private LinearLayout llMore;
        private ImageView ivYuncunchu;
        private ImageView ivSd;
        private ImageView ivShengji;
        private ImageView ivShare;
        private ImageView ivMsg;
        private ImageView ivYuncunchu2;
        private ImageView ivSd2;
        private ImageView ivShengji2;
        private ImageView ivShare2;
        private ImageView ivMsg2;
        private TextView tvBrand;
        private TextView tvBrand2;
        private LinearLayout llMore1;
        private TextView tvHelp;
        private TextView tvOffTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ll1 = itemView.findViewById(R.id.ll1);
            ll2 = itemView.findViewById(R.id.ll2);
            tvDeviceName = itemView.findViewById(R.id.tv_device_name);
            tvDeviceName2 = itemView.findViewById(R.id.tv_device_name2);
            llMore = itemView.findViewById(R.id.ll_more);
            ivYuncunchu = itemView.findViewById(R.id.iv_yuncunchu);
            ivSd = itemView.findViewById(R.id.iv_sd);
            ivShengji = itemView.findViewById(R.id.iv_shengji);
            ivShare = itemView.findViewById(R.id.iv_share);
            ivMsg = itemView.findViewById(R.id.iv_msg);
            ivYuncunchu2 = itemView.findViewById(R.id.iv_yuncunchu2);
            ivSd2 = itemView.findViewById(R.id.iv_sd2);
            ivShengji2 = itemView.findViewById(R.id.iv_shengji2);
            ivShare2 = itemView.findViewById(R.id.iv_share2);
            ivMsg2 = itemView.findViewById(R.id.iv_msg2);
            tvBrand = itemView.findViewById(R.id.tv_brand);
            tvBrand2 = itemView.findViewById(R.id.tv_brand2);
            llMore1 = itemView.findViewById(R.id.ll_more1);
            tvHelp = itemView.findViewById(R.id.tv_help);
            tvOffTime = itemView.findViewById(R.id.tv_off_time);
        }
    }

    public interface ClickListener {
        void onMore(int pos);
    }

}
