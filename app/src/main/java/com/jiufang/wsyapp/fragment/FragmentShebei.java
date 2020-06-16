package com.jiufang.wsyapp.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.adapter.IndexAdapter;
import com.jiufang.wsyapp.adapter.IndexGridAdapter;
import com.jiufang.wsyapp.adapter.MessageShebeiAdapter;
import com.jiufang.wsyapp.base.LazyFragment;
import com.jiufang.wsyapp.bean.GetBindDeviceListBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
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

/**
 * Created by Administrator on 2020/5/9.
 */

public class FragmentShebei extends LazyFragment {

    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private MessageShebeiAdapter adapter;
    private List<GetBindDeviceListBean.DataBean.RecordsBean> mList;

    private int page = 1;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_shebei;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        initData();
    }

    private void initData() {

        smartRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("pageIndex", "1");
                map.put("pageSize", "10");
                map.put("userId", SpUtils.getUserId(getContext()));
                ViseUtil.Post(getContext(), NetUrl.getBindDeviceList, map, refreshLayout, 0, new ViseUtil.ViseListener() {
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
                map.put("userId", SpUtils.getUserId(getContext()));
                ViseUtil.Post(getContext(), NetUrl.getBindDeviceList, map, refreshLayout, 1, new ViseUtil.ViseListener() {
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
        map.put("userId", SpUtils.getUserId(getContext()));
        ViseUtil.Post(getContext(), NetUrl.getBindDeviceList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                GetBindDeviceListBean bean = gson.fromJson(s, GetBindDeviceListBean.class);
                mList = bean.getData().getRecords();
                adapter = new MessageShebeiAdapter(mList);
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
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

}
