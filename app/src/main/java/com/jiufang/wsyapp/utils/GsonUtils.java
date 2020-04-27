package com.jiufang.wsyapp.utils;

import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by Administrator on 2020/4/27.
 */

public class GsonUtils {

    public static String map2json(Map<Object, Object> map){
        Gson gson = new Gson();
        return gson.toJson(map);
    }

}
