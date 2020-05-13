package com.jiufang.wsyapp.fragment;

import android.content.Intent;
import android.view.View;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.LazyFragment;
import com.jiufang.wsyapp.ui.LcTaocanActivity;
import com.jiufang.wsyapp.ui.YsTaocanActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2020/4/27.
 */

public class Fragment3 extends LazyFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment3;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @OnClick({R.id.iv1, R.id.iv2})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.iv1:
                intent.setClass(getContext(), LcTaocanActivity.class);
                startActivity(intent);
                break;
            case R.id.iv2:
                intent.setClass(getContext(), YsTaocanActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
    }

    @Override
    protected void onFragmentVisible() {
        super.onFragmentVisible();
    }

    @Override
    protected void onFragmentHide() {
        super.onFragmentHide();
    }

}
