package com.jiufang.wsyapp.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/6/16.
 */

public class FileBean {

    private String time;
    private List<Map<String, Object>> maps;

    public FileBean(String time, List<Map<String, Object>> maps) {
        this.time = time;
        this.maps = maps;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Map<String, Object>> getMaps() {
        return maps;
    }

    public void setMaps(List<Map<String, Object>> maps) {
        this.maps = maps;
    }

}
