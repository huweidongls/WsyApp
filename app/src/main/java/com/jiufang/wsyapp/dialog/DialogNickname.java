package com.jiufang.wsyapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;

/**
 * Created by Administrator on 2020/6/5.
 */

public class DialogNickname extends Dialog {

    private Context context;
    private ClickListener listener;

    private EditText et;
    private TextView tvCancel;
    private TextView tvSure;

    public DialogNickname(@NonNull Context context, ClickListener listener) {
        super(context, R.style.RoundCornerDialog);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_nickname, null);
        setContentView(view);

        et = view.findViewById(R.id.et);
        tvCancel = view.findViewById(R.id.tv_cancel);
        tvSure = view.findViewById(R.id.tv_sure);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = et.getText().toString();
                if(!StringUtils.isEmpty(s)){
                    listener.onSure(s);
                    dismiss();
                }else {
                    ToastUtil.showShort(context, "昵称不能为空");
                }
            }
        });

    }

    public interface ClickListener{
        void onSure(String nickname);
    }

}
