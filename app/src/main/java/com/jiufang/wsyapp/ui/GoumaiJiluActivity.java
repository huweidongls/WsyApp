package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.adapter.GoumaijiluAdapter;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetComboPurchasePageListBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
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

public class GoumaiJiluActivity extends BaseActivity {

    private Context context = GoumaiJiluActivity.this;

    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private GoumaijiluAdapter adapter;
    private List<GetComboPurchasePageListBean.DataBean.RecordsBean> mList;

    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goumai_jilu);

        StatusBarUtils.setStatusBar(GoumaiJiluActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(GoumaiJiluActivity.this);
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
                ViseUtil.Post(context, NetUrl.getComboPurchasePageList, map, refreshLayout, 0, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Logger.e("123123", s);
                        Gson gson = new Gson();
                        GetComboPurchasePageListBean bean = gson.fromJson(s, GetComboPurchasePageListBean.class);
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
                ViseUtil.Post(context, NetUrl.getComboPurchasePageList, map, refreshLayout, 1, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Logger.e("123123", s);
                        Gson gson = new Gson();
                        GetComboPurchasePageListBean bean = gson.fromJson(s, GetComboPurchasePageListBean.class);
                        mList.addAll(bean.getData().getRecords());
                        adapter.notifyDataSetChanged();
                        page = page + 1;
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
        ViseUtil.Post(context, NetUrl.getComboPurchasePageList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                GetComboPurchasePageListBean bean = gson.fromJson(s, GetComboPurchasePageListBean.class);
                mList = bean.getData().getRecords();
                adapter = new GoumaijiluAdapter(mList);
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

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
