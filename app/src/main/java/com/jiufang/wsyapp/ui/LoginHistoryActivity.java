package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.adapter.LoginHistoryAdapter;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginHistoryActivity extends BaseActivity {

    private Context context = LoginHistoryActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private LoginHistoryAdapter adapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_history);

        StatusBarUtils.setStatusBar(LoginHistoryActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(LoginHistoryActivity.this);
        initData();

    }

    private void initData() {

        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        adapter = new LoginHistoryAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

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
