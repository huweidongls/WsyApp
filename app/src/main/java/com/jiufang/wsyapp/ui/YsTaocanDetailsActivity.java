package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetComboByIdBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.GlideUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YsTaocanDetailsActivity extends BaseActivity {

    private Context context = YsTaocanDetailsActivity.this;

    @BindView(R.id.iv_agree)
    ImageView ivAgree;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private boolean isAgree = false;

    private String id = "";
    private GetComboByIdBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ys_taocan_details);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(YsTaocanDetailsActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(YsTaocanDetailsActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("comboId", id);
        ViseUtil.Post(context, NetUrl.getComboById, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                bean = gson.fromJson(s, GetComboByIdBean.class);
                tvTitle.setText(bean.getData().getComboName());
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.iv_agree, R.id.ll_buy, R.id.ll_xieyi})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.iv_agree:
                isAgree = true;
                ivAgree.setImageResource(R.mipmap.duihao_xieyi);
                break;
            case R.id.ll_buy:
                if(isAgree){
                    intent.putExtra("id", bean.getData().getId()+"");
                    intent.putExtra("type", "2");
                    intent.setClass(context, TaocanShebeiActivity.class);
                    startActivity(intent);
                }else {
                    ToastUtil.showShort(context, "请勾选萤石云服务协议");
                }
                break;
            case R.id.ll_xieyi:
                isAgree = true;
                ivAgree.setImageResource(R.mipmap.duihao_xieyi);
                break;
        }
    }

}
