package com.jiufang.wsyapp.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.adapter.CloudLcVideoAdapter;
import com.jiufang.wsyapp.adapter.LocalYsVideoAdapter;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetBindDeviceDetailBean;
import com.jiufang.wsyapp.bean.GetLcCloudStorageRecordListBean;
import com.jiufang.wsyapp.dialog.DialogMsgDelete;
import com.jiufang.wsyapp.dialog.DialogSelectStartEndTime;
import com.jiufang.wsyapp.mediaplay.MediaPlayActivity;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.zyyoona7.popup.EasyPopup;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CloudLcVideoActivity extends BaseActivity {

    private Context context = CloudLcVideoActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.ll_type)
    LinearLayout llType;
    @BindView(R.id.rl_sure)
    RelativeLayout rlSure;
    @BindView(R.id.rl_edit)
    RelativeLayout rlEdit;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.rl_all)
    RelativeLayout rlAll;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.ll_time)
    LinearLayout llTime;
    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private CloudLcVideoAdapter adapter;
    private List<GetLcCloudStorageRecordListBean.DataBean> mList;

    private int mYear;
    private int mMonth;
    private int mDay;

    private EasyPopup easyPopup;

    private String id = "";

    private String days = "";

    private String startTime = "";
    private String endTime = "";

    private GetBindDeviceDetailBean mBean;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_lc_video);

        id = getIntent().getStringExtra("id");
        mBean = (GetBindDeviceDetailBean) getIntent().getSerializableExtra("bean");
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        StatusBarUtils.setStatusBar(CloudLcVideoActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(CloudLcVideoActivity.this);
        initData();

    }

    private void initData() {

        tvTitle.setText(mBean.getData().getDeviceName());

        String time = mYear+"-"+ StringUtils.getBuling(mMonth+1)+"-"+StringUtils.getBuling(mDay);
        tvTime.setText(time);

        startTime = mYear+"-"+(mMonth+1)+"-"+mDay+" 00:00:00";
        endTime = mYear+"-"+(mMonth+1)+"-"+mDay+" 23:59:59";

        days = mYear+"-"+StringUtils.getBuling((mMonth+1))+"-"+StringUtils.getBuling(mDay);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("userId", SpUtils.getUserId(context));
        map.put("deviceId", id);
        map.put("startTime", StringUtils.dateTimeFormat(startTime));
        map.put("endTime", StringUtils.dateTimeFormat(endTime));
        ViseUtil.Post(context, NetUrl.getLcCloudStorageRecordList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                GetLcCloudStorageRecordListBean bean = gson.fromJson(s, GetLcCloudStorageRecordListBean.class);
                mList = bean.getData();
                adapter = new CloudLcVideoAdapter(mList, new CloudLcVideoAdapter.ClickListener() {
                    @Override
                    public void onClick(int pos) {
                        Intent intent = new Intent(context, MediaPlayActivity.class);
                        intent.putExtra("type", "0");
                        intent.putExtra("mbean", mBean);
                        intent.putExtra("bean", mList.get(pos));
                        intent.putExtra("TYPE", MediaPlayActivity.IS_VIDEO_REMOTE_CLOUD_RECORD);
                        intent.putExtra("MEDIA_TITLE", R.string.live_play_name);
                        context.startActivity(intent);
                    }
                });
                GridLayoutManager manager = new GridLayoutManager(context, 3);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onElse(String s) {
                Logger.e("123123", s);
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.ll_calendar, R.id.ll_type, R.id.rl_edit, R.id.rl_sure, R.id.rl_all, R.id.tv_delete, R.id.tv_start,
    R.id.tv_end, R.id.tv_sure})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_sure:
                String start = days+" "+tvStart.getText().toString();
                String end = days+" "+tvEnd.getText().toString();
                int i = StringUtils.getTimeCompareSize(start, end);
                if(i == 3){
                    dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("userId", SpUtils.getUserId(context));
                    map.put("deviceId", id);
                    map.put("startTime", StringUtils.dateTimeFormat(start));
                    map.put("endTime", StringUtils.dateTimeFormat(end));
                    ViseUtil.Post(context, NetUrl.getLcCloudStorageRecordList, map, dialog, new ViseUtil.ViseListener() {
                        @Override
                        public void onReturn(String s) {
                            Logger.e("123123", s);
                            Gson gson = new Gson();
                            GetLcCloudStorageRecordListBean bean = gson.fromJson(s, GetLcCloudStorageRecordListBean.class);
                            mList = bean.getData();
                            adapter = new CloudLcVideoAdapter(mList, new CloudLcVideoAdapter.ClickListener() {
                                @Override
                                public void onClick(int pos) {
                                    Intent intent = new Intent(context, MediaPlayActivity.class);
                                    intent.putExtra("type", "0");
                                    intent.putExtra("mbean", mBean);
                                    intent.putExtra("bean", mList.get(pos));
                                    intent.putExtra("TYPE", MediaPlayActivity.IS_VIDEO_REMOTE_CLOUD_RECORD);
                                    intent.putExtra("MEDIA_TITLE", R.string.live_play_name);
                                    context.startActivity(intent);
                                }
                            });
                            GridLayoutManager manager = new GridLayoutManager(context, 3);
                            recyclerView.setLayoutManager(manager);
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onElse(String s) {
                            Logger.e("123123", s);
                        }
                    });
                }else {
                    ToastUtil.showShort(context, "截止时间需大于开始时间");
                }
                break;
            case R.id.tv_start:
                //时间选择器
                TimePickerView pvTime = new TimePickerBuilder(context, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        tvStart.setText(StringUtils.dateToString(date));
                    }
                }).setType(new boolean[]{false, false, false, true, true, true})// 默认全部显示
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确认")//确认按钮文字
                        .setTitleSize(20)//标题文字大小
                        .setTitleText("选择开始时间")//标题文字
                        .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                        .isCyclic(true)//是否循环滚动
                        .setTitleColor(Color.BLACK)//标题文字颜色
                        .setSubmitColor(0xFFFFA16F)//确定按钮文字颜色
                        .setCancelColor(0xFFFFA16F)//取消按钮文字颜色
                        .setTitleBgColor(0xFFffffff)//标题背景颜色 Night mode
                        .setBgColor(0xFFffffff)//滚轮背景颜色 Night mode
                        .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .isDialog(false)//是否显示为对话框样式
                        .build();
                pvTime.show();
                break;
            case R.id.tv_end:
                //时间选择器
                TimePickerView pvTime1 = new TimePickerBuilder(context, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        tvEnd.setText(StringUtils.dateToString(date));
                    }
                }).setType(new boolean[]{false, false, false, true, true, true})// 默认全部显示
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确认")//确认按钮文字
                        .setTitleSize(20)//标题文字大小
                        .setTitleText("选择截止时间")//标题文字
                        .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                        .isCyclic(true)//是否循环滚动
                        .setTitleColor(Color.BLACK)//标题文字颜色
                        .setSubmitColor(0xFFFFA16F)//确定按钮文字颜色
                        .setCancelColor(0xFFFFA16F)//取消按钮文字颜色
                        .setTitleBgColor(0xFFffffff)//标题背景颜色 Night mode
                        .setBgColor(0xFFffffff)//滚轮背景颜色 Night mode
                        .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .isDialog(false)//是否显示为对话框样式
                        .build();
                pvTime1.show();
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_calendar:
                new DatePickerDialog(context, onDateSetListener, mYear, mMonth, mDay).show();
                break;
            case R.id.ll_type:
                showPop();
                break;
            case R.id.rl_edit:
                showEdit();
                break;
            case R.id.rl_sure:
                hideEdit();
                break;
            case R.id.rl_all:

                break;
            case R.id.tv_delete:
                DialogMsgDelete dialogMsgDelete = new DialogMsgDelete(context, "确认删除吗？", R.mipmap.lajitong, new DialogMsgDelete.ClickListener() {
                    @Override
                    public void onSure() {

                    }

                    @Override
                    public void onCancel() {

                    }
                });
                dialogMsgDelete.show();
                break;
        }
    }

    /**
     * 隐藏编辑状态
     */
    private void hideEdit() {

        rlSure.setVisibility(View.GONE);
        rlEdit.setVisibility(View.VISIBLE);
        ll.setVisibility(View.VISIBLE);
        rlBack.setVisibility(View.VISIBLE);
        rlAll.setVisibility(View.GONE);
        llBottom.setVisibility(View.GONE);
        adapter.setEdit(false);

    }

    /**
     * 显示编辑状态
     */
    private void showEdit() {

        rlSure.setVisibility(View.VISIBLE);
        rlEdit.setVisibility(View.GONE);
        ll.setVisibility(View.GONE);
        rlBack.setVisibility(View.GONE);
        rlAll.setVisibility(View.VISIBLE);
        llBottom.setVisibility(View.VISIBLE);
        adapter.setEdit(true);

    }

    private void showPop() {

        easyPopup = EasyPopup.create(CloudLcVideoActivity.this)
                .setContentView(R.layout.popupwindow_msg_shebei_list)
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
                .setDimValue(0.5f)
                //变暗的背景颜色
                .setDimColor(Color.BLACK)
                .apply();
        easyPopup.showAtAnchorView(llType, YGravity.BELOW, XGravity.ALIGN_RIGHT, 0, DensityUtil.dp2px(1));

        LinearLayout llAll = easyPopup.findViewById(R.id.ll_all);
        LinearLayout llPopTime = easyPopup.findViewById(R.id.ll_time);

        llAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llTime.setVisibility(View.GONE);
                easyPopup.dismiss();
                startTime = days+" 00:00:00";
                endTime = days+" 23:59:59";
                dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                Map<String, String> map = new LinkedHashMap<>();
                map.put("userId", SpUtils.getUserId(context));
                map.put("deviceId", id);
                map.put("startTime", StringUtils.dateTimeFormat(startTime));
                map.put("endTime", StringUtils.dateTimeFormat(endTime));
                ViseUtil.Post(context, NetUrl.getLcCloudStorageRecordList, map, dialog, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Logger.e("123123", s);
                        Gson gson = new Gson();
                        GetLcCloudStorageRecordListBean bean = gson.fromJson(s, GetLcCloudStorageRecordListBean.class);
                        mList = bean.getData();
                        adapter = new CloudLcVideoAdapter(mList, new CloudLcVideoAdapter.ClickListener() {
                            @Override
                            public void onClick(int pos) {
                                Intent intent = new Intent(context, MediaPlayActivity.class);
                                intent.putExtra("type", "0");
                                intent.putExtra("mbean", mBean);
                                intent.putExtra("bean", mList.get(pos));
                                intent.putExtra("TYPE", MediaPlayActivity.IS_VIDEO_REMOTE_CLOUD_RECORD);
                                intent.putExtra("MEDIA_TITLE", R.string.live_play_name);
                                context.startActivity(intent);
                            }
                        });
                        GridLayoutManager manager = new GridLayoutManager(context, 3);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onElse(String s) {
                        Logger.e("123123", s);
                    }
                });
            }
        });
        llPopTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llTime.setVisibility(View.VISIBLE);
                easyPopup.dismiss();
            }
        });

    }

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
//            final String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }

            }
            tvTime.setText(days);

            startTime = days+" 00:00:00";
            endTime = days+" 23:59:59";
            dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
            Map<String, String> map = new LinkedHashMap<>();
            map.put("userId", SpUtils.getUserId(context));
            map.put("deviceId", id);
            map.put("startTime", StringUtils.dateTimeFormat(startTime));
            map.put("endTime", StringUtils.dateTimeFormat(endTime));
            ViseUtil.Post(context, NetUrl.getLcCloudStorageRecordList, map, dialog, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Logger.e("123123", s);
                    Gson gson = new Gson();
                    GetLcCloudStorageRecordListBean bean = gson.fromJson(s, GetLcCloudStorageRecordListBean.class);
                    mList = bean.getData();
                    adapter = new CloudLcVideoAdapter(mList, new CloudLcVideoAdapter.ClickListener() {
                        @Override
                        public void onClick(int pos) {
                            Intent intent = new Intent(context, MediaPlayActivity.class);
                            intent.putExtra("type", "0");
                            intent.putExtra("mbean", mBean);
                            intent.putExtra("bean", mList.get(pos));
                            intent.putExtra("TYPE", MediaPlayActivity.IS_VIDEO_REMOTE_CLOUD_RECORD);
                            intent.putExtra("MEDIA_TITLE", R.string.live_play_name);
                            context.startActivity(intent);
                        }
                    });
                    GridLayoutManager manager = new GridLayoutManager(context, 3);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onElse(String s) {
                    Logger.e("123123", s);
                }
            });

        }
    };

}
