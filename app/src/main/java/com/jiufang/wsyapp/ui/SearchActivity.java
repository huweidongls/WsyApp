package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.adapter.SearchAdapter;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetBindDeviceListBean;
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

public class SearchActivity extends BaseActivity {

    private Context context = SearchActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;

    private SearchAdapter adapter;
    private List<GetBindDeviceListBean.DataBean.RecordsBean> mList;

    private InputMethodManager manager;//输入法管理器
    private String search = "";
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        StatusBarUtils.setStatusBar(SearchActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(SearchActivity.this);
        initData();
        manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        search();

    }

    private void initData() {

        smartRefreshLayout.setRefreshHeader(new MaterialHeader(SearchActivity.this));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(SearchActivity.this));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("pageIndex", "1");
                map.put("pageSize", "10");
                map.put("userId", SpUtils.getUserId(context));
                map.put("keyword", search);
                ViseUtil.Post(context, NetUrl.getBindDeviceList, map, refreshLayout, 0, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Logger.e("123123", s);
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
                map.put("keyword", search);
                ViseUtil.Post(context, NetUrl.getBindDeviceList, map, refreshLayout, 1, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Logger.e("123123", s);
                        Gson gson = new Gson();
                        GetBindDeviceListBean bean = gson.fromJson(s, GetBindDeviceListBean.class);
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

        mList = new ArrayList<>();
        adapter = new SearchAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    private void search() {
        etSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    //先隐藏键盘
                    if (manager.isActive()) {
                        manager.hideSoftInputFromWindow(etSearch.getApplicationWindowToken(), 0);
                    }
                    //自己需要的操作
                    search = etSearch.getText().toString();
                    if(!TextUtils.isEmpty(search)){
                        onSearch(search);
                    }
                }
                //记得返回false
                return false;
            }
        });
    }

    private void onSearch(String search) {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("pageIndex", "1");
        map.put("pageSize", "10");
        map.put("userId", SpUtils.getUserId(context));
        map.put("keyword", search);
        ViseUtil.Post(context, NetUrl.getBindDeviceList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
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

    @OnClick({R.id.rl_cancel})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_cancel:
                finish();
                break;
        }
    }

}
