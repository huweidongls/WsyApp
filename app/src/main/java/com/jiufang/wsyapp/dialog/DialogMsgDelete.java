package com.jiufang.wsyapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiufang.wsyapp.R;

/**
 * Created by Administrator on 2020/5/12.
 */

public class DialogMsgDelete extends Dialog {

    private Context context;
    private Button btnSure;
    private Button btnCancel;
    private ClickListener listener;
    private ImageView iv;
    private TextView tv;
    private String title;
    private int res;

    public DialogMsgDelete(@NonNull Context context, String title, int res, ClickListener listener) {
        super(context, R.style.RoundCornerDialog);
        this.context = context;
        this.listener = listener;
        this.title = title;
        this.res = res;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_msg_delete, null);
        setContentView(view);

        btnSure = view.findViewById(R.id.btn_sure);
        btnCancel = view.findViewById(R.id.btn_cancel);
        iv = view.findViewById(R.id.iv);
        tv = view.findViewById(R.id.tv);
        iv.setImageResource(res);
        tv.setText(title);

        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSure();
                dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCancel();
                dismiss();
            }
        });

    }

    public interface ClickListener{
        void onSure();
        void onCancel();
    }

}
