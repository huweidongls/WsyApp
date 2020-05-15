package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetPersonalMessageByIdBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ViseUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MsgPersonDetailsActivity extends BaseActivity {

    private Context context = MsgPersonDetailsActivity.this;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_xinghao)
    TextView tvXinghao;
    @BindView(R.id.tv_xlh)
    TextView tvXlh;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_person_details);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(MsgPersonDetailsActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(MsgPersonDetailsActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("messageId", id);
        ViseUtil.Post(context, NetUrl.getPersonalMessageById, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                GetPersonalMessageByIdBean bean = gson.fromJson(s, GetPersonalMessageByIdBean.class);
                tvTitle.setText(bean.getData().getMessageTitle());
                tvTime.setText(bean.getData().getMessageTime());
                tvContent.setText(bean.getData().getMessageContent());
                tvXinghao.setText(bean.getData().getDeviceModel());
                tvXlh.setText(bean.getData().getDeviceSn());
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
