package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.adapter.TaocanYsAdapter;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetComboCategoryListBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ViseUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YsTaocanActivity extends BaseActivity {

    private Context context = YsTaocanActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private TaocanYsAdapter adapter;
    private List<GetComboCategoryListBean.DataBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ys_taocan);

        StatusBarUtils.setStatusBar(YsTaocanActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(YsTaocanActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("brandId", "2");
        ViseUtil.Post(context, NetUrl.getComboCategoryList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                GetComboCategoryListBean bean = gson.fromJson(s, GetComboCategoryListBean.class);
                mList = bean.getData();
                adapter = new TaocanYsAdapter(mList);
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
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
