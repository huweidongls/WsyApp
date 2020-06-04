package com.jiufang.wsyapp.utils;

import com.jiufang.wsyapp.R;

/**
 * Created by Administrator on 2020/6/4.
 */

public class UtilsDevicePic {

    public static int getDevicePic(String xinghao){
        if(xinghao.equals("TC2E")){
            return R.mipmap.tc2e;
        }else if(xinghao.equals("TP2E")){
            return R.mipmap.tp2e;
        }else if(xinghao.equals("CS-C1HC-1D1WFR")){
            return R.mipmap.c1hc;
        }else if(xinghao.equals("CS-C3HC-3H2WFRL")){
            return R.mipmap.c3hc;
        }else if(xinghao.equals("CS-XP1-1C2WFR")){
            return R.mipmap.xp1;
        }
        return R.mipmap.banner_default;
    }

}
