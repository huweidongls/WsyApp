package com.jiufang.wsyapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.jiufang.wsyapp.R;

/**
 * Created by Administrator on 2020/5/12.
 */

public class DialogMsgDelete extends Dialog {

    private Context context;
    private Button btnSure;
    private Button btnCancel;
    private ClickListener listener;

    public DialogMsgDelete(@NonNull Context context, ClickListener listener) {
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

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_msg_delete, null);
        setContentView(view);

        btnSure = view.findViewById(R.id.btn_sure);
        btnCancel = view.findViewById(R.id.btn_cancel);

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
