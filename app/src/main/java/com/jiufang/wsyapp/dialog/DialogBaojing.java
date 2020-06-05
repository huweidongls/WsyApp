package com.jiufang.wsyapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.ViseUtil;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2020/6/1.
 */

public class DialogBaojing extends Dialog {

    private Context context;
    private TextView tvCancel;
    private TextView tvSure;
    private ClickListener listener;

    private TextView tvName;
    private TextView tvPhone;
    private TextView tvAddress;

    private String name;
    private String phone;
    private String address;
    private String men;
    private String id;

    public DialogBaojing(@NonNull Context context, String id, String name, String phone, String address, String men, ClickListener listener) {
        super(context, R.style.RoundCornerDialog);
        this.context = context;
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.men = men;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_baojing, null);
        setContentView(view);

        tvCancel = view.findViewById(R.id.tv_cancel);
        tvSure = view.findViewById(R.id.tv_sure);
        tvName = view.findViewById(R.id.tv_name);
        tvPhone = view.findViewById(R.id.tv_phone);
        tvAddress = view.findViewById(R.id.tv_address);

        tvName.setText("联系人："+name);
        tvPhone.setText("手机号："+phone);
        tvAddress.setText("地址："+address+"-"+men);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> map = new LinkedHashMap<>();
                map.put("bindDeviceId", id);
                map.put("userId", SpUtils.getUserId(context));
                ViseUtil.Post(context, NetUrl.createUserAlarm, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        listener.onClick();
                        dismiss();
                    }

                    @Override
                    public void onElse(String s) {

                    }
                });
            }
        });

    }

    public interface ClickListener{
        void onClick();
    }

}
