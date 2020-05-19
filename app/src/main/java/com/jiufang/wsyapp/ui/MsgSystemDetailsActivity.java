package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetSysMessageByIdBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ViseUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MsgSystemDetailsActivity extends BaseActivity {

    private Context context = MsgSystemDetailsActivity.this;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_system_details);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(MsgSystemDetailsActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(MsgSystemDetailsActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("messageId", id);
        ViseUtil.Post(context, NetUrl.getSysMessageById, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                GetSysMessageByIdBean bean = gson.fromJson(s, GetSysMessageByIdBean.class);
                int type = bean.getData().getSysType();
                if(type == 1){
                    tvTitle.setText("【通知】"+bean.getData().getMessageTitle());
                }else {
                    tvTitle.setText("【公告】"+bean.getData().getMessageTitle());
                }
                tvTime.setText(bean.getData().getMessageTime());
                tvContent.setText(bean.getData().getMessageContent());
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
