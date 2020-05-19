package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.utils.StatusBarUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IsBindingActivity extends BaseActivity {

    private Context context = IsBindingActivity.this;

    @BindView(R.id.tv)
    TextView tv;

    private String s = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_is_binding);

        s = getIntent().getStringExtra("s");
        StatusBarUtils.setStatusBar(IsBindingActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(IsBindingActivity.this);
        initData();

    }

    private void initData() {

        try {
            JSONObject jsonObject = new JSONObject(s);
            tv.setText(jsonObject.optString("message"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
