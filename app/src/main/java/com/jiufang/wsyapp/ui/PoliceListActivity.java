package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.adapter.PoliceAdapter;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetPoliceStationPageListBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ViseUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PoliceListActivity extends BaseActivity {

    private Context context = PoliceListActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.et_search)
    EditText etSearch;

    private PoliceAdapter adapter;
    private List<GetPoliceStationPageListBean.DataBean.RecordsBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_list);

        StatusBarUtils.setStatusBar(PoliceListActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(PoliceListActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("pageIndex", "1");
        map.put("pageSize", "100");
        ViseUtil.Post(context, NetUrl.getPoliceStationPageList, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                GetPoliceStationPageListBean bean = gson.fromJson(s, GetPoliceStationPageListBean.class);
                mList = bean.getData().getRecords();
                adapter = new PoliceAdapter(mList, new PoliceAdapter.ClickListener() {
                    @Override
                    public void onItemClick(String id, String name) {
                        Intent intent = new Intent();
                        intent.putExtra("id", id);
                        intent.putExtra("name", name);
                        setResult(100001, intent);
                        finish();
                    }
                });
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
                etSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence sequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence sequence, int i, int i1, int i2) {
                        adapter.getFilter().filter(sequence.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    @OnClick(R.id.rl_back)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
