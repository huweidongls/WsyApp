package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/7/7.
 */

public class GetAndroidDownloadUrlBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : https://hvc.jiufangkeji.com/resource/app/hvc.apk
     * time : 2020-07-07 10:27:44
     */

    private int code;
    private boolean success;
    private String message;
    private String data;
    private String time;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
