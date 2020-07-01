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
 * Created by Administrator on 2020/7/1.
 */

public class DialogCustom extends Dialog {

    private Context context;
    private TextView tvCancel;
    private TextView tvSure;
    private TextView tvTitle;
    private ClickListener listener;
    private String title;

    public DialogCustom(@NonNull Context context, String title, ClickListener listener) {
        super(context, R.style.RoundCornerDialog);
        this.context = context;
        this.title = title;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_custom, null);
        setContentView(view);

        tvCancel = view.findViewById(R.id.tv_cancel);
        tvSure = view.findViewById(R.id.tv_sure);
        tvTitle = view.findViewById(R.id.tv_title);

        tvTitle.setText(title);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                listener.onSure();
            }
        });

    }

    public interface ClickListener{
        void onSure();
    }

}
