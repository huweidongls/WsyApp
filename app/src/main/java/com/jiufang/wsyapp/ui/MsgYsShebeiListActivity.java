package com.jiufang.wsyapp.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.adapter.MsgLcShebeiListAdapter;
import com.jiufang.wsyapp.adapter.MsgYsShebeiListAdapter;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetBindDeviceListBean;
import com.jiufang.wsyapp.bean.GetDeviceAlarmLcPageBean;
import com.jiufang.wsyapp.bean.GetDeviceAlarmYsPageBean;
import com.jiufang.wsyapp.dialog.DialogMsgDelete;
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

public class MsgYsShebeiListActivity extends BaseActivity {

    private Context context = MsgYsShebeiListActivity.this;

    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
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
    @BindView(R.id.ll_msg)
    LinearLayout llMsg;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_time)
    LinearLayout llTime;
    @BindView(R.id.tv_start)
    TextView tvStart;
    @BindView(R.id.tv_end)
    TextView tvEnd;

    private MsgYsShebeiListAdapter adapter;
    private List<GetDeviceAlarmYsPageBean.DataBean.RecordsBean> mList;

    private int mYear;
    private int mMonth;
    private int mDay;

    private String days = "";

    private String startTime = "";
    private String endTime = "";

    private EasyPopup easyPopup;

//    private GetBindDeviceListBean.DataBean.RecordsBean bean;

    private String deviceName = "";
    private String id = "";

    private Dialog dialog;

    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_shebei_list);

        deviceName = getIntent().getStringExtra("name");
        id = getIntent().getStringExtra("id");
//        bean = (GetBindDeviceListBean.DataBean.RecordsBean) getIntent().getSerializableExtra("bean");
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        StatusBarUtils.setStatusBar(MsgYsShebeiListActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(MsgYsShebeiListActivity.this);
        initData();

    }

    private void initData() {

        tvTitle.setText(deviceName);

        String time = mYear+"-"+ StringUtils.getBuling(mMonth+1)+"-"+StringUtils.getBuling(mDay);
        tvTime.setText(time);

        startTime = mYear+"-"+(mMonth+1)+"-"+mDay+" 00:00:00";
        endTime = mYear+"-"+(mMonth+1)+"-"+mDay+" 23:59:59";

        days = mYear+"-"+StringUtils.getBuling((mMonth+1))+"-"+StringUtils.getBuling(mDay);

        smartRefreshLayout.setRefreshHeader(new MaterialHeader(context));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(context));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("userId", SpUtils.getUserId(context));
                map.put("deviceId", id);
                map.put("startTime", startTime);
                map.put("endTime", endTime);
                map.put("pageStart", "0");
                map.put("pageSize", "10");
                ViseUtil.Post(context, NetUrl.getDeviceAlarmYsPage, map, refreshLayout, 0, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Logger.e("123123", s);
                        recyclerView.setVisibility(View.VISIBLE);
                        llMsg.setVisibility(View.GONE);
                        Gson gson = new Gson();
                        GetDeviceAlarmYsPageBean ysPageBean = gson.fromJson(s, GetDeviceAlarmYsPageBean.class);
                        mList.clear();
                        mList.addAll(ysPageBean.getData().getRecords());
                        adapter.notifyDataSetChanged();
                        page = 1;
                    }

                    @Override
                    public void onElse(String s) {
                        Logger.e("123123", s);
                        recyclerView.setVisibility(View.GONE);
                        llMsg.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("userId", SpUtils.getUserId(context));
                map.put("deviceId", id);
                map.put("startTime", startTime);
                map.put("endTime", endTime);
                map.put("pageStart", page+"");
                map.put("pageSize", "10");
                ViseUtil.Post(context, NetUrl.getDeviceAlarmYsPage, map, refreshLayout, 1, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Logger.e("123123", s);
                        Gson gson = new Gson();
                        GetDeviceAlarmYsPageBean ysPageBean = gson.fromJson(s, GetDeviceAlarmYsPageBean.class);
                        mList.addAll(ysPageBean.getData().getRecords());
                        page = page+1;
                    }

                    @Override
                    public void onElse(String s) {
                        Logger.e("123123", s);
                    }
                });
            }
        });

        Map<String, String> map = new LinkedHashMap<>();
        map.put("userId", SpUtils.getUserId(context));
        map.put("deviceId", id);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("pageStart", "0");
        map.put("pageSize", "10");
        ViseUtil.Post(context, NetUrl.getDeviceAlarmYsPage, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                recyclerView.setVisibility(View.VISIBLE);
                llMsg.setVisibility(View.GONE);
                Gson gson = new Gson();
                GetDeviceAlarmYsPageBean ysPageBean = gson.fromJson(s, GetDeviceAlarmYsPageBean.class);
                mList = ysPageBean.getData().getRecords();
                adapter = new MsgYsShebeiListAdapter(mList, id);
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
                page = 1;
            }

            @Override
            public void onElse(String s) {
                Logger.e("123123", s);
                recyclerView.setVisibility(View.GONE);
                llMsg.setVisibility(View.VISIBLE);
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.ll_calendar, R.id.ll_type, R.id.rl_edit, R.id.rl_sure, R.id.rl_all, R.id.tv_delete, R.id.tv_start,
            R.id.tv_end, R.id.tv_sure})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_sure:
                startTime = days+" "+tvStart.getText().toString();
                endTime = days+" "+tvEnd.getText().toString();
                int i = StringUtils.getTimeCompareSize(startTime, endTime);
                if(i == 3){
                    dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("userId", SpUtils.getUserId(context));
                    map.put("deviceId", id);
                    map.put("startTime", startTime);
                    map.put("endTime", endTime);
                    map.put("pageStart", "0");
                    map.put("pageSize", "10");
                    ViseUtil.Post(context, NetUrl.getDeviceAlarmYsPage, map, dialog, new ViseUtil.ViseListener() {
                        @Override
                        public void onReturn(String s) {
                            Logger.e("123123", s);
                            recyclerView.setVisibility(View.VISIBLE);
                            llMsg.setVisibility(View.GONE);
                            Gson gson = new Gson();
                            GetDeviceAlarmYsPageBean ysPageBean = gson.fromJson(s, GetDeviceAlarmYsPageBean.class);
                            mList = ysPageBean.getData().getRecords();
                            adapter = new MsgYsShebeiListAdapter(mList, id);
                            LinearLayoutManager manager = new LinearLayoutManager(context);
                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(manager);
                            recyclerView.setAdapter(adapter);
                            page = 1;
                        }

                        @Override
                        public void onElse(String s) {
                            Logger.e("123123", s);
                            recyclerView.setVisibility(View.GONE);
                            llMsg.setVisibility(View.VISIBLE);
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

        easyPopup = EasyPopup.create(MsgYsShebeiListActivity.this)
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
                map.put("startTime", startTime);
                map.put("endTime", endTime);
                map.put("pageStart", "0");
                map.put("pageSize", "10");
                ViseUtil.Post(context, NetUrl.getDeviceAlarmYsPage, map, dialog, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Logger.e("123123", s);
                        recyclerView.setVisibility(View.VISIBLE);
                        llMsg.setVisibility(View.GONE);
                        Gson gson = new Gson();
                        GetDeviceAlarmYsPageBean ysPageBean = gson.fromJson(s, GetDeviceAlarmYsPageBean.class);
                        mList = ysPageBean.getData().getRecords();
                        adapter = new MsgYsShebeiListAdapter(mList, id);
                        LinearLayoutManager manager = new LinearLayoutManager(context);
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapter);
                        page = 1;
                    }

                    @Override
                    public void onElse(String s) {
                        Logger.e("123123", s);
                        recyclerView.setVisibility(View.GONE);
                        llMsg.setVisibility(View.VISIBLE);
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

//        View view = LayoutInflater.from(MsgYsShebeiListActivity.this).inflate(R.layout.popupwindow_msg_shebei_list, null);
//
//        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
//        popupWindow.setTouchable(true);
//        popupWindow.setFocusable(true);
//        // 设置点击窗口外边窗口消失
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        popupWindow.setOutsideTouchable(true);
////        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
//        popupWindow.showAsDropDown(llType, 0, DensityUtil.dp2px(1), Gravity.START);
//        // 设置popWindow的显示和消失动画
////        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.alpha = 0.5f;
//        getWindow().setAttributes(params);
//        popupWindow.update();
//
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//
//            // 在dismiss中恢复透明度
//            public void onDismiss() {
//                WindowManager.LayoutParams params = getWindow().getAttributes();
//                params.alpha = 1f;
//                getWindow().setAttributes(params);
//            }
//        });

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
            map.put("startTime", startTime);
            map.put("endTime", endTime);
            map.put("pageStart", "0");
            map.put("pageSize", "10");
            ViseUtil.Post(context, NetUrl.getDeviceAlarmYsPage, map, dialog, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Logger.e("123123", s);
                    recyclerView.setVisibility(View.VISIBLE);
                    llMsg.setVisibility(View.GONE);
                    Gson gson = new Gson();
                    GetDeviceAlarmYsPageBean ysPageBean = gson.fromJson(s, GetDeviceAlarmYsPageBean.class);
                    mList = ysPageBean.getData().getRecords();
                    adapter = new MsgYsShebeiListAdapter(mList, id);
                    LinearLayoutManager manager = new LinearLayoutManager(context);
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                    page = 1;
                }

                @Override
                public void onElse(String s) {
                    Logger.e("123123", s);
                    recyclerView.setVisibility(View.GONE);
                    llMsg.setVisibility(View.VISIBLE);
                }
            });

        }
    };

}
