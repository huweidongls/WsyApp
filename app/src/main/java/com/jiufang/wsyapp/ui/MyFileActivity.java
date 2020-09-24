package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.adapter.MyFileAdapter;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.FileBean;
import com.jiufang.wsyapp.utils.GetFilesUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFileActivity extends BaseActivity {

    private Context context = MyFileActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv)
    TextView tv;

    private MyFileAdapter adapter;
    private List<Map<String, Object>> mList;
    private List<FileBean> fileBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_file);

        StatusBarUtils.setStatusBar(MyFileActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(MyFileActivity.this);
        initData();

    }

    private void initData() {

        fileBeanList = new ArrayList<>();
        mList = new ArrayList<>();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Wsy/Captures";
        String path1 = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Wsy/Records";
        List<Map<String, Object>> mList1 = GetFilesUtils.getInstance().getSonNode(path);
        List<Map<String, Object>> mList2 = GetFilesUtils.getInstance().getSonNode(path1);
        if(mList1 != null){
            mList.addAll(mList1);
        }
        if(mList2 != null){
            mList.addAll(mList2);
        }

        Map<String, List<Map<String, Object>>> stringMapMap = new LinkedHashMap<>();

        for(int i = 0; i<mList.size(); i++){

            Map<String, Object> map = mList.get(i);
            String time = String.valueOf(map.get(GetFilesUtils.FILE_INFO_TIME));
            if(stringMapMap.get(time) == null){
                List<Map<String, Object>> list = new ArrayList<>();
                list.add(mList.get(i));
                stringMapMap.put(time, list);
            }else {
                List<Map<String, Object>> list = stringMapMap.get(time);
                list.add(mList.get(i));
                stringMapMap.put(time, list);
            }

        }

        for (String key : stringMapMap.keySet()) {
            fileBeanList.add(new FileBean(key, stringMapMap.get(key)));
        }

        Collections.sort(fileBeanList, new Comparator<FileBean>() {
            @Override
            public int compare(FileBean fileBean, FileBean t1) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date dt1 = df.parse(fileBean.getTime());
                    Date dt2 = df.parse(t1.getTime());
                    if (dt1.getTime() > dt2.getTime()) {
                        return -1;
                    } else if (dt1.getTime() < dt2.getTime()) {
                        return 1;
                    } else {
                        return 0;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });

        adapter = new MyFileAdapter(fileBeanList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        String storage = Environment.getExternalStorageDirectory().getAbsolutePath();
        StatFs fs = new StatFs(storage);
        //可用的blocks的数量
        long availableBolocks=fs.getAvailableBlocksLong();
        //单个block的大小
        long blockSize=fs.getBlockSize();
        //sd卡的剩余空间
        long available=availableBolocks*blockSize;
        long a = available/1024/1024/1024;
        tv.setText(a+"G");

    }

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
