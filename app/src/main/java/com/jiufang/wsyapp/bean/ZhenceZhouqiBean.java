package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/7/2.
 */

public class ZhenceZhouqiBean {

    private String time;
    private int isSelect = 0;

    public ZhenceZhouqiBean(String time) {
        this.time = time;
    }

    public ZhenceZhouqiBean(String time, int isSelect) {
        this.time = time;
        this.isSelect = isSelect;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(int isSelect) {
        this.isSelect = isSelect;
    }

}
