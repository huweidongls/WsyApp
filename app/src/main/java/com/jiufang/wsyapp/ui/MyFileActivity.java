package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.adapter.MyFileAdapter;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.utils.GetFilesUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFileActivity extends BaseActivity {

    private Context context = MyFileActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private MyFileAdapter adapter;
    private List<Map<String, Object>> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_file);

        StatusBarUtils.setStatusBar(MyFileActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(MyFileActivity.this);
        initData();

    }

    private void initData() {

        mList = new ArrayList<>();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Wsy/Captures";
        String path1 = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Wsy/Records";
        mList.addAll(GetFilesUtils.getInstance().getSonNode(path));
        mList.addAll(GetFilesUtils.getInstance().getSonNode(path1));
        adapter = new MyFileAdapter(mList);
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
