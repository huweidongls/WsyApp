package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.adapter.MyDeviceAdapter;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetBindDeviceListBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.zxing.activity.CaptureActivity;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyDeviceActivity extends BaseActivity {

    private Context context = MyDeviceActivity.this;

    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private MyDeviceAdapter adapter;
    private List<GetBindDeviceListBean.DataBean.RecordsBean> mList;

    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_device);

        StatusBarUtils.setStatusBar(MyDeviceActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(MyDeviceActivity.this);
        initData();

    }

    private void initData() {

        smartRefreshLayout.setRefreshHeader(new MaterialHeader(context));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(context));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("pageIndex", "1");
                map.put("pageSize", "10");
                map.put("userId", SpUtils.getUserId(context));
                ViseUtil.Post(context, NetUrl.getBindDeviceList, map, refreshLayout, 0, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        GetBindDeviceListBean bean = gson.fromJson(s, GetBindDeviceListBean.class);
                        mList.clear();
                        mList.addAll(bean.getData().getRecords());
                        adapter.notifyDataSetChanged();
                        page = 2;
                    }

                    @Override
                    public void onElse(String s) {

                    }
                });
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("pageIndex", page+"");
                map.put("pageSize", "10");
                map.put("userId", SpUtils.getUserId(context));
                ViseUtil.Post(context, NetUrl.getBindDeviceList, map, refreshLayout, 1, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        GetBindDeviceListBean bean = gson.fromJson(s, GetBindDeviceListBean.class);
                        mList.addAll(bean.getData().getRecords());
                        adapter.notifyDataSetChanged();
                        page = page+1;
                    }

                    @Override
                    public void onElse(String s) {

                    }
                });
            }
        });

        Map<String, String> map = new LinkedHashMap<>();
        map.put("pageIndex", "1");
        map.put("pageSize", "10");
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.getBindDeviceList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                GetBindDeviceListBean bean = gson.fromJson(s, GetBindDeviceListBean.class);
                mList = bean.getData().getRecords();
                adapter = new MyDeviceAdapter(mList);
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
                page = 2;
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    @OnClick({R.id.rl_back, R.id.rl_add, R.id.rl_search})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_add:
                startActivityForResult(new Intent(context, CaptureActivity.class), 1001);
                break;
            case R.id.rl_search:
                intent.setClass(context, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

}
