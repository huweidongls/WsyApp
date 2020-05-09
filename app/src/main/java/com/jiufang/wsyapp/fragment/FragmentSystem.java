package com.jiufang.wsyapp.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.adapter.MessageSystemAdapter;
import com.jiufang.wsyapp.base.LazyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2020/5/9.
 */

public class FragmentSystem extends LazyFragment {

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private MessageSystemAdapter adapter;
    private List<String> mList;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_system;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
        initData();
    }

    private void initData() {

        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        adapter = new MessageSystemAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

}
