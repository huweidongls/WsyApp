package com.jiufang.wsyapp.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.adapter.MessageSystemAdapter;
import com.jiufang.wsyapp.base.LazyFragment;
import com.jiufang.wsyapp.bean.GetSysMessagePageListBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.ViseUtil;
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

/**
 * Created by Administrator on 2020/5/9.
 */

public class FragmentSystem extends LazyFragment {

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.ll_msg)
    LinearLayout llMsg;
    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;

    private MessageSystemAdapter adapter;
    private List<GetSysMessagePageListBean.DataBean.RecordsBean> mList;

    private int page = 1;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_system;
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
                ViseUtil.Post(getContext(), NetUrl.getSysMessagePageList, map, refreshLayout, 0, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        GetSysMessagePageListBean bean = gson.fromJson(s, GetSysMessagePageListBean.class);
                        mList.clear();
                        mList.addAll(bean.getData().getRecords());
                        adapter.notifyDataSetChanged();
                        if(mList.size()>0){
                            recyclerView.setVisibility(View.VISIBLE);
                            llMsg.setVisibility(View.GONE);
                        }else {
                            recyclerView.setVisibility(View.GONE);
                            llMsg.setVisibility(View.VISIBLE);
                        }
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
                ViseUtil.Post(getContext(), NetUrl.getSysMessagePageList, map, refreshLayout, 1, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Gson gson = new Gson();
                        GetSysMessagePageListBean bean = gson.fromJson(s, GetSysMessagePageListBean.class);
                        mList.addAll(bean.getData().getRecords());
                        adapter.notifyDataSetChanged();
                        if(mList.size()>0){
                            recyclerView.setVisibility(View.VISIBLE);
                            llMsg.setVisibility(View.GONE);
                        }else {
                            recyclerView.setVisibility(View.GONE);
                            llMsg.setVisibility(View.VISIBLE);
                        }
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
        ViseUtil.Post(getContext(), NetUrl.getSysMessagePageList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                GetSysMessagePageListBean bean = gson.fromJson(s, GetSysMessagePageListBean.class);
                mList = bean.getData().getRecords();
                adapter = new MessageSystemAdapter(mList);
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
                if(mList.size()>0){
                    recyclerView.setVisibility(View.VISIBLE);
                    llMsg.setVisibility(View.GONE);
                }else {
                    recyclerView.setVisibility(View.GONE);
                    llMsg.setVisibility(View.VISIBLE);
                }
                page = 2;
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

}
