package com.jiufang.wsyapp.fragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.LazyFragment;
import com.jiufang.wsyapp.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2020/4/27.
 */

public class Fragment2 extends LazyFragment {

    @BindView(R.id.vp)
    ViewPager myViewPager;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.view3)
    View view3;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment2;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    private void initData() {

        MessageAdapter mainAdapter = new MessageAdapter(getChildFragmentManager());
        myViewPager.setOffscreenPageLimit(3);
        myViewPager.setAdapter(mainAdapter);
        myViewPager.addOnPageChangeListener(mainAdapter);

    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        initData();
    }

    @OnClick({R.id.rl1, R.id.rl2, R.id.rl3})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl1:
                tv1.setTextColor(getActivity().getResources().getColor(R.color.theme));
                tv2.setTextColor(Color.parseColor("#A6A6A6"));
                tv3.setTextColor(Color.parseColor("#A6A6A6"));
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
                myViewPager.setCurrentItem(0);
                break;
            case R.id.rl2:
                tv1.setTextColor(Color.parseColor("#A6A6A6"));
                tv2.setTextColor(getActivity().getResources().getColor(R.color.theme));
                tv3.setTextColor(Color.parseColor("#A6A6A6"));
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.GONE);
                myViewPager.setCurrentItem(1);
                break;
            case R.id.rl3:
                tv1.setTextColor(Color.parseColor("#A6A6A6"));
                tv2.setTextColor(Color.parseColor("#A6A6A6"));
                tv3.setTextColor(getActivity().getResources().getColor(R.color.theme));
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.VISIBLE);
                myViewPager.setCurrentItem(2);
                break;
        }
    }

    @Override
    protected void onFragmentVisible() {
        super.onFragmentVisible();
    }

    @Override
    protected void onFragmentHide() {
        super.onFragmentHide();
    }

    private class MessageAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

        private List<Fragment> fragments = new ArrayList<>();
        Fragment fragmentShebei = new FragmentShebei();
        Fragment fragmentPerson = new FragmentPerson();
        Fragment fragmentSystem = new FragmentSystem();

        public MessageAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(fragmentShebei);
            fragments.add(fragmentPerson);
            fragments.add(fragmentSystem);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (0 == position) {
                tv1.setTextColor(getActivity().getResources().getColor(R.color.theme));
                tv2.setTextColor(Color.parseColor("#A6A6A6"));
                tv3.setTextColor(Color.parseColor("#A6A6A6"));
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
            } else if (1 == position) {
                tv1.setTextColor(Color.parseColor("#A6A6A6"));
                tv2.setTextColor(getActivity().getResources().getColor(R.color.theme));
                tv3.setTextColor(Color.parseColor("#A6A6A6"));
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.VISIBLE);
                view3.setVisibility(View.GONE);
            } else if (2 == position) {
                tv1.setTextColor(Color.parseColor("#A6A6A6"));
                tv2.setTextColor(Color.parseColor("#A6A6A6"));
                tv3.setTextColor(getActivity().getResources().getColor(R.color.theme));
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
