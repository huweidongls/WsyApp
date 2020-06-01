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
 * Created by Administrator on 2020/6/1.
 */

public class DialogBaojingSuccess extends Dialog {

    private Context context;
    private TextView tvSure;

    public DialogBaojingSuccess(@NonNull Context context) {
        super(context, R.style.RoundCornerDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_baojing_success, null);
        setContentView(view);

        tvSure = view.findViewById(R.id.tv_sure);

        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

}
