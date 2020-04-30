package com.jiufang.wsyapp.fragment;

import android.view.View;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.LazyFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2020/4/27.
 */

public class Fragment4 extends LazyFragment {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment4;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
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
