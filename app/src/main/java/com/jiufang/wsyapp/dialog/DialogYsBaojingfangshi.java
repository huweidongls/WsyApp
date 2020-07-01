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

public class DialogYsBaojingfangshi extends Dialog {

    private Context context;
    private TextView tv0;
    private TextView tv1;
    private TextView tv2;
    private ClickListener listener;

    public DialogYsBaojingfangshi(@NonNull Context context, ClickListener listener) {
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

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_ys_baojingfangshi, null);
        setContentView(view);

        tv0 = view.findViewById(R.id.tv0);
        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);

        tv0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick("0");
                dismiss();
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick("1");
                dismiss();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick("2");
                dismiss();
            }
        });

    }

    public interface ClickListener{
        void onClick(String type);
    }

}
