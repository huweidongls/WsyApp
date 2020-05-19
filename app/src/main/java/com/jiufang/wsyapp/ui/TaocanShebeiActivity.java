package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.adapter.TaocanShebeiAdapter;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetComboDeviceChooseListBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaocanShebeiActivity extends BaseActivity {

    private Context context = TaocanShebeiActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.ll_msg)
    LinearLayout llMsg;

    private TaocanShebeiAdapter adapter;
    private List<GetComboDeviceChooseListBean.DataBean> mList;

    private String id = "";
    private String type = "";//乐橙 1   萤石 2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taocan_shebei);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(TaocanShebeiActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(TaocanShebeiActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("brandId", id);
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.getComboDeviceChooseList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                if(!StringUtils.isEmpty(s)){
                    recyclerView.setVisibility(View.VISIBLE);
                    llMsg.setVisibility(View.GONE);
                    Gson gson = new Gson();
                    GetComboDeviceChooseListBean bean = gson.fromJson(s, GetComboDeviceChooseListBean.class);
                    mList = bean.getData();
                    adapter = new TaocanShebeiAdapter(mList);
                    LinearLayoutManager manager = new LinearLayoutManager(context);
                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                }else {
                    recyclerView.setVisibility(View.GONE);
                    llMsg.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onElse(String s) {
                recyclerView.setVisibility(View.GONE);
                llMsg.setVisibility(View.VISIBLE);
            }
        });

    }

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
