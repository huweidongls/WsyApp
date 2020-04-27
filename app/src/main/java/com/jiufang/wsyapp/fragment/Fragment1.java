package com.jiufang.wsyapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseFragment;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.DESUtil;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.ViseUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2020/4/27.
 */

public class Fragment1 extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, null);

        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick({R.id.btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn:
                sendMsg();
                break;
        }
    }

    private void sendMsg() {

        String phone = "18686817319";
        String key = "Hvc.2020";
        String s1 = DESUtil.encryptDES(phone, key);
        Logger.e("123123", "加密"+s1);
        String s2 = DESUtil.decryptDES(s1, key);
        Logger.e("123123", "解密"+s2);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("encryptionData", s1);
        map.put("phone", phone);
        ViseUtil.Post(getContext(), NetUrl.sendCaptchaCodeWithSMS, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {

            }
        });

    }

}
