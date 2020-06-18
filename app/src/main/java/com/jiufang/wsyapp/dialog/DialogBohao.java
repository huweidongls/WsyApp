package com.jiufang.wsyapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jiufang.wsyapp.R;

/**
 * Created by Administrator on 2020/6/5.
 */

public class DialogBohao extends Dialog {

    private Context context;
    private ClickListener listener;

    private TextView tvCancel;
    private TextView tvSure;

    public DialogBohao(@NonNull Context context, ClickListener listener) {
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

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_shiming, null);
        setContentView(view);

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
                listener.onSure();
                dismiss();
            }
        });

    }

    public interface ClickListener{
        void onSure();
    }

}
