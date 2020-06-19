package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.VersionUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {

    private Context context = AboutActivity.this;

    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        StatusBarUtils.setStatusBar(AboutActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(AboutActivity.this);
        initData();

    }

    private void initData() {

        tvVersion.setText("V "+ VersionUtils.packageName(context));

    }

    @OnClick({R.id.rl_back, R.id.btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn:
                ToastUtil.showShort(context, "暂无更新");
                break;
        }
    }

}
