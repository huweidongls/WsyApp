package com.jiufang.wsyapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.app.MyApplication;
import com.jiufang.wsyapp.bean.GetDeviceAlarmYsPageBean;
import com.jiufang.wsyapp.mediaplay.util.TimeHelper;
import com.jiufang.wsyapp.ui.YsPlayActivity;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;
import com.videogo.openapi.bean.EZCloudRecordFile;

import java.util.Calendar;
import java.util.List;

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
    private List<GetDeviceAlarmYsPageBean.DataBean.AlramsBean> data;
    private boolean isEdit = false;
    private String deviceId;

    public MsgYsShebeiListAdapter(List<GetDeviceAlarmYsPageBean.DataBean.AlramsBean> data, String deviceId) {
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
                    String startTime = data.get(i).getAlarmTime();
                    String endTime = TimeHelper.getDateEN((StringUtils.dateFormatToLong(startTime)+120000));

                    Calendar mStartTime = Calendar.getInstance();
                    Calendar mEndTime = Calendar.getInstance();

                    mStartTime.setTime(StringUtils.toDate(startTime));
                    mEndTime.setTime(StringUtils.toDate(endTime));

                    Observable<List<EZCloudRecordFile>> observable = Observable.create(new ObservableOnSubscribe<List<EZCloudRecordFile>>() {
                        @Override
                        public void subscribe(ObservableEmitter<List<EZCloudRecordFile>> e) throws Exception {
//                            List<EZCloudRecordFile> result = MyApplication.getOpenSDK().searchRecordFileFromCloud(code, cameraNo, mStartTime,
//                                    mEndTime);
//                            e.onNext(result);
                        }
                    });
                    Observer<List<EZCloudRecordFile>> observer = new Observer<List<EZCloudRecordFile>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(List<EZCloudRecordFile> value) {
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
