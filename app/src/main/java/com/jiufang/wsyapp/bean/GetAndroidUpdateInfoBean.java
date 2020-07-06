package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/7/6.
 */

public class GetAndroidUpdateInfoBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"version":"1.0.22","code":22,"isNeedUpdate":"yes","describe":"本次更新优化了设备绑定"}
     * time : 2020-07-06 15:32:37
     */

    private int code;
    private boolean success;
    private String message;
    private DataBean data;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class DataBean {
        /**
         * version : 1.0.22
         * code : 22
         * isNeedUpdate : yes
         * describe : 本次更新优化了设备绑定
         */

        private String version;
        private int code;
        private String isNeedUpdate;
        private String describe;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getIsNeedUpdate() {
            return isNeedUpdate;
        }

        public void setIsNeedUpdate(String isNeedUpdate) {
            this.isNeedUpdate = isNeedUpdate;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }
    }
}
