package com.jiufang.wsyapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.adapter.ZhenceZhouqiAdapter;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.ZhenceZhouqiBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhenceZhouqiActivity extends BaseActivity {

    private Context context = ZhenceZhouqiActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private ZhenceZhouqiAdapter adapter;
    private List<ZhenceZhouqiBean> mList;

    private String type = "";
    private String id = "";
    private String start = "";
    private String end = "";

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhence_zhouqi);

        type = getIntent().getStringExtra("type");
        id = getIntent().getStringExtra("id");
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        StatusBarUtils.setStatusBar(ZhenceZhouqiActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(ZhenceZhouqiActivity.this);
        initData();

    }

    private void initData() {

        mList = new ArrayList<>();
        mList.add(new ZhenceZhouqiBean("周一"));
        mList.add(new ZhenceZhouqiBean("周二"));
        mList.add(new ZhenceZhouqiBean("周三"));
        mList.add(new ZhenceZhouqiBean("周四"));
        mList.add(new ZhenceZhouqiBean("周五"));
        mList.add(new ZhenceZhouqiBean("周六"));
        mList.add(new ZhenceZhouqiBean("周日"));
        adapter = new ZhenceZhouqiAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.rl_back, R.id.rl_save})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_save:
                if(type.equals("1")){
                    setLcZhouqi();
                }else if(type.equals("2")){
                    setYsZhouqi();
                }
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

    /**
     * 设置乐橙周期
     */
    private void setLcZhouqi() {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        String zhouqi = "";
        for (int i = 0; i<mList.size(); i++){
            if(mList.get(i).getIsSelect() == 1){
                zhouqi = zhouqi+mList.get(i).getTime()+",";
            }
        }
        if(!StringUtils.isEmpty(zhouqi)){
            zhouqi = zhouqi.substring(0, zhouqi.length()-1);
        }
        zhouqi = getLcZhouqiStr(zhouqi);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("deviceId", id);
        map.put("period", zhouqi);
        map.put("beginTime", start);
        map.put("endTime", end);
        map.put("userId", SpUtils.getUserId(context));
        Logger.e("123123", start+"--"+end+"--"+zhouqi+"--"+id+"--"+SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.setLcSchedule, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                ToastUtil.showShort(context, "设置成功");
                finish();
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    /**
     * 设置萤石周期
     */
    private void setYsZhouqi() {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        String zhouqi = "";
        for (int i = 0; i<mList.size(); i++){
            if(mList.get(i).getIsSelect() == 1){
                zhouqi = zhouqi+mList.get(i).getTime()+",";
            }
        }
        if(!StringUtils.isEmpty(zhouqi)){
            zhouqi = zhouqi.substring(0, zhouqi.length()-1);
        }
        zhouqi = getYsZhouqiStr(zhouqi);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("deviceId", id);
        map.put("period", zhouqi);
        map.put("startTime", start);
        map.put("stopTime", end);
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.setYsSchedule, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                ToastUtil.showShort(context, "设置成功");
                finish();
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    private String getYsZhouqiStr(String str){
        str = str.replace("周一", "0");
        str = str.replace("周二", "1");
        str = str.replace("周三", "2");
        str = str.replace("周四", "3");
        str = str.replace("周五", "4");
        str = str.replace("周六", "5");
        str = str.replace("周日", "6");
        return str;
    }

    private String getLcZhouqiStr(String str){
        str = str.replace("周一", "Monday");
        str = str.replace("周二", "Tuesday");
        str = str.replace("周三", "Wednesday");
        str = str.replace("周四", "Thursday");
        str = str.replace("周五", "Friday");
        str = str.replace("周六", "Saturday");
        str = str.replace("周日", "Sunday");
        return str;
    }

}
