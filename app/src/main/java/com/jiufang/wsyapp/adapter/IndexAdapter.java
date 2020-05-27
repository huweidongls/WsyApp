package com.jiufang.wsyapp.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.app.MyApplication;
import com.jiufang.wsyapp.bean.GetBindDeviceListBean;
import com.jiufang.wsyapp.dialog.DialogMsgDelete;
import com.jiufang.wsyapp.mediaplay.MediaPlayActivity;
import com.jiufang.wsyapp.mediaplay.entity.ChannelInfo;
import com.jiufang.wsyapp.net.NetUrl;
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

    public IndexAdapter(List<GetBindDeviceListBean.DataBean.RecordsBean> data) {
        this.data = data;
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
        if(deviceStatus == 0){
            //不在线
            holder.ll1.setVisibility(View.GONE);
            holder.ll2.setVisibility(View.VISIBLE);
            holder.tvDeviceName2.setText(data.get(position).getDeviceName());
        }else {
            //在线
            holder.ll1.setVisibility(View.VISIBLE);
            holder.ll2.setVisibility(View.GONE);
            holder.tvDeviceName.setText(data.get(position).getDeviceName());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = WeiboDialogUtils.createLoadingDialog(context, "正在跳转...");
                if(brandId == 1){
                    //乐橙
//                    Intent intent = new Intent();
//                    intent.setClass(context, LcPlayActivity.class);
//                    intent.putExtra("id", data.get(position).getId()+"");
//                    context.startActivity(intent);

                    Intent intent = new Intent(context, MediaPlayActivity.class);
//                    intent.putExtra("UUID", channelInfo.getUuid());
                    intent.putExtra("id", data.get(position).getId()+"");
                    intent.putExtra("TYPE", MediaPlayActivity.IS_VIDEO_ONLINE);
                    intent.putExtra("MEDIA_TITLE", R.string.live_play_name);
//                    DevicelistActivity.this.startActivityForResult(intent, 0);
                    context.startActivity(intent);
                    WeiboDialogUtils.closeDialog(dialog);

                }else {
                    //萤石
                    Observable<EZDeviceInfo> observable = Observable.create(new ObservableOnSubscribe<EZDeviceInfo>() {
                        @Override
                        public void subscribe(ObservableEmitter<EZDeviceInfo> e) throws Exception {
                            EZDeviceInfo mDeviceInfo = MyApplication.getOpenSDK().getDeviceInfo(data.get(position).getSnCode());
                            e.onNext(mDeviceInfo);
                        }
                    });
                    Observer<EZDeviceInfo> observer = new Observer<EZDeviceInfo>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(EZDeviceInfo value) {
                            EZCameraInfo mCameraInfo = EZUtils.getCameraInfoFromDevice(value,0);
                            Intent intent;
                            MyApplication.getOpenSDK().setAccessToken(data.get(position).getDeviceAccessToken());
                            intent = new Intent(context, EZRealPlayActivity.class);
                            intent.putExtra(IntentConsts.EXTRA_CAMERA_INFO, mCameraInfo);
                            intent.putExtra(IntentConsts.EXTRA_DEVICE_INFO, value);
                            intent.putExtra("code", data.get(position).getSecurityCode());
                            context.startActivity(intent);
                            WeiboDialogUtils.closeDialog(dialog);
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
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DialogMsgDelete dialogMsgDelete = new DialogMsgDelete(context, "确定解绑设备吗？", R.mipmap.lajitong, new DialogMsgDelete.ClickListener() {
                    @Override
                    public void onSure() {
                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("bindDeviceId", data.get(position).getId()+"");
                        ViseUtil.Post(context, NetUrl.unBindDevice, map, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                ToastUtil.showShort(context, "设备解绑成功");
                            }

                            @Override
                            public void onElse(String s) {

                            }
                        });
                    }

                    @Override
                    public void onCancel() {

                    }
                });
                dialogMsgDelete.show();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout ll1;
        private LinearLayout ll2;
        private TextView tvDeviceName;
        private TextView tvDeviceName2;

        public ViewHolder(View itemView) {
            super(itemView);
            ll1 = itemView.findViewById(R.id.ll1);
            ll2 = itemView.findViewById(R.id.ll2);
            tvDeviceName = itemView.findViewById(R.id.tv_device_name);
            tvDeviceName2 = itemView.findViewById(R.id.tv_device_name2);
        }
    }

}
