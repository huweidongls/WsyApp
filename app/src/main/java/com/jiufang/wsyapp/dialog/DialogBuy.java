package com.jiufang.wsyapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.jiufang.wsyapp.R;

/**
 * Created by Administrator on 2020/6/18.
 */

public class DialogBuy extends Dialog {

    private Context context;

    private Button btnSure;
    private Button btnCancel;

    public DialogBuy(@NonNull Context context) {
        super(context, R.style.RoundCornerDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_buy, null);
        setContentView(view);

        btnSure = view.findViewById(R.id.btn_sure);
        btnCancel = view.findViewById(R.id.btn_cancel);

        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = "18245026640";
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phone);
                intent1.setData(data);
                context.startActivity(intent1);
                dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }

}
