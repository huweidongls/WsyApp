package com.jiufang.wsyapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2020/4/27.
 */

public class Fragment4 extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4, null);

        ButterKnife.bind(this, view);

        return view;
    }
}
