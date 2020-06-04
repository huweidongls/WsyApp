package com.jiufang.wsyapp.utils;

import android.content.Context;

import com.vise.xsnow.cache.SpCache;

import java.util.Map;

/**
 * Created by Administrator on 2018/10/26.
 */

public class SpUtils {

    private static SpCache spCache;
    public static String USER_ID = "user_id";
    public static String PHONENUM = "phonenum";
    public static String TOKEN = "token";
    public static String SEARCH_HISTORY = "search_history";
    public static String WIFI = "wifi";

    public static void setWifi(Context context, Map<String, String> token){
        spCache = new SpCache(context, "user_info");
        spCache.put(WIFI, token);
    }

    public static Map<String, String> getWifi(Context context){
        spCache = new SpCache(context, "user_info");
        return (Map<String, String>) spCache.get(WIFI);
    }

    public static void setSearchHistory(Context context, String token){
        spCache = new SpCache(context, "user_info");
        spCache.put(SEARCH_HISTORY, token);
    }

    public static String getSearchHistory(Context context){
        spCache = new SpCache(context, "user_info");
        return spCache.get(SEARCH_HISTORY, "");
    }

    public static void setToken(Context context, String token){
        spCache = new SpCache(context, "user_info");
        spCache.put(TOKEN, token);
    }

    public static String getToken(Context context){
        spCache = new SpCache(context, "user_info");
        return spCache.get(TOKEN, "0");
    }

    public static void setPhoneNum(Context context, String phonenum){
        spCache = new SpCache(context, "user_info");
        spCache.put(PHONENUM, phonenum);
    }

    public static String getPhoneNum(Context context){
        spCache = new SpCache(context, "user_info");
        return spCache.get(PHONENUM, "0");
    }

    public static void setUserId(Context context, String userid){
        spCache = new SpCache(context, "user_info");
        spCache.put(USER_ID, userid);
    }

    public static String getUserId(Context context){
        spCache = new SpCache(context, "user_info");
        return spCache.get(USER_ID, "0");
    }

    public static void clear(Context context){
        spCache = new SpCache(context, "user_info");
        spCache.clear();
    }

}
