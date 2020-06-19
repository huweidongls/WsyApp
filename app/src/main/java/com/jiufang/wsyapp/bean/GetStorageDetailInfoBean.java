package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/6/19.
 */

public class GetStorageDetailInfoBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"userId":2,"deviceId":31,"brandId":1,"isHaveCloud":1,"days":0,"cloudCount":0,"isHaveNative":1,"nativeCount":34}
     * time : 2020-06-19 10:07:05
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
         * userId : 2
         * deviceId : 31
         * brandId : 1
         * isHaveCloud : 1
         * days : 0
         * cloudCount : 0
         * isHaveNative : 1
         * nativeCount : 34
         */

        private int userId;
        private int deviceId;
        private int brandId;
        private int isHaveCloud;
        private int days;
        private int cloudCount;
        private int isHaveNative;
        private int nativeCount;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(int deviceId) {
            this.deviceId = deviceId;
        }

        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public int getIsHaveCloud() {
            return isHaveCloud;
        }

        public void setIsHaveCloud(int isHaveCloud) {
            this.isHaveCloud = isHaveCloud;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public int getCloudCount() {
            return cloudCount;
        }

        public void setCloudCount(int cloudCount) {
            this.cloudCount = cloudCount;
        }

        public int getIsHaveNative() {
            return isHaveNative;
        }

        public void setIsHaveNative(int isHaveNative) {
            this.isHaveNative = isHaveNative;
        }

        public int getNativeCount() {
            return nativeCount;
        }

        public void setNativeCount(int nativeCount) {
            this.nativeCount = nativeCount;
        }
    }
}
