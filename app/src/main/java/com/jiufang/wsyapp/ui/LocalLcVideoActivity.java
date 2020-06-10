package com.jiufang.wsyapp.ui;

import android.app.DatePickerDialog;
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

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.adapter.LocalLcVideoAdapter;
import com.jiufang.wsyapp.adapter.LocalYsVideoAdapter;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetBindDeviceDetailBean;
import com.jiufang.wsyapp.bean.GetLcLocalStorageRecordListBean;
import com.jiufang.wsyapp.dialog.DialogMsgDelete;
import com.jiufang.wsyapp.mediaplay.MediaPlayActivity;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocalLcVideoActivity extends BaseActivity {

    private Context context = LocalLcVideoActivity.this;

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

    private LocalLcVideoAdapter adapter;
    private List<GetLcLocalStorageRecordListBean.DataBean> mList;

    private int mYear;
    private int mMonth;
    private int mDay;

    private EasyPopup easyPopup;

    private String id = "";

    private String startTime = "";
    private String endTime = "";

    private GetBindDeviceDetailBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_lc_video);

        id = getIntent().getStringExtra("id");
        mBean = (GetBindDeviceDetailBean) getIntent().getSerializableExtra("bean");
        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        StatusBarUtils.setStatusBar(LocalLcVideoActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(LocalLcVideoActivity.this);
        initData();

    }

    private void initData() {

        String time = mYear+"-"+ StringUtils.getBuling(mMonth+1)+"-"+StringUtils.getBuling(mDay);
        tvTime.setText(time);

        startTime = mYear+"-"+(mMonth+1)+"-"+mDay+" 00:00:00";
        endTime = mYear+"-"+(mMonth+1)+"-"+mDay+" 08:59:59";

        Map<String, String> map = new LinkedHashMap<>();
        map.put("userId", SpUtils.getUserId(context));
        map.put("deviceId", id);
        map.put("startTime", StringUtils.dateTimeFormat(startTime));
        map.put("endTime", StringUtils.dateTimeFormat(endTime));
        ViseUtil.Post(context, NetUrl.getLcLocalStorageRecordList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                GetLcLocalStorageRecordListBean bean = gson.fromJson(s, GetLcLocalStorageRecordListBean.class);
                mList = bean.getData();
                adapter = new LocalLcVideoAdapter(mList, new LocalLcVideoAdapter.ClickListener() {
                    @Override
                    public void onClick(int pos) {
                        Intent intent = new Intent(context, MediaPlayActivity.class);
                        intent.putExtra("type", "1");
                        intent.putExtra("mbean", mBean);
                        intent.putExtra("bean", mList.get(pos));
                        intent.putExtra("TYPE", MediaPlayActivity.IS_VIDEO_REMOTE_RECORD);
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

    @OnClick({R.id.rl_back, R.id.ll_calendar, R.id.ll_type, R.id.rl_edit, R.id.rl_sure, R.id.rl_all, R.id.tv_delete})
    public void onClick(View view){
        switch (view.getId()){
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

        easyPopup = EasyPopup.create(LocalLcVideoActivity.this)
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
        llAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showShort(context, "11");
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
            final String days;
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
        }
    };

}
