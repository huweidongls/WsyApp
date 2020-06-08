package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/6/8.
 */

public class GetBindDeviceStatusInfoBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"deviceId":8,"snCode":null,"brandId":2,"deviceStatus":1,"cloudStorageStatus":0,"nativeStorageStatus":1,"isHaveNewMessage":null,"haveCloudConsole":0}
     * time : 2020-06-08 10:44:01
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
         * deviceId : 8
         * snCode : null
         * brandId : 2
         * deviceStatus : 1
         * cloudStorageStatus : 0
         * nativeStorageStatus : 1
         * isHaveNewMessage : null
         * haveCloudConsole : 0
         */

        private int deviceId;
        private Object snCode;
        private int brandId;
        private int deviceStatus;
        private int cloudStorageStatus;
        private int nativeStorageStatus;
        private int isHaveNewMessage;
        private int haveCloudConsole;

        public int getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(int deviceId) {
            this.deviceId = deviceId;
        }

        public Object getSnCode() {
            return snCode;
        }

        public void setSnCode(Object snCode) {
            this.snCode = snCode;
        }

        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public int getDeviceStatus() {
            return deviceStatus;
        }

        public void setDeviceStatus(int deviceStatus) {
            this.deviceStatus = deviceStatus;
        }

        public int getCloudStorageStatus() {
            return cloudStorageStatus;
        }

        public void setCloudStorageStatus(int cloudStorageStatus) {
            this.cloudStorageStatus = cloudStorageStatus;
        }

        public int getNativeStorageStatus() {
            return nativeStorageStatus;
        }

        public void setNativeStorageStatus(int nativeStorageStatus) {
            this.nativeStorageStatus = nativeStorageStatus;
        }

        public int getIsHaveNewMessage() {
            return isHaveNewMessage;
        }

        public void setIsHaveNewMessage(int isHaveNewMessage) {
            this.isHaveNewMessage = isHaveNewMessage;
        }

        public int getHaveCloudConsole() {
            return haveCloudConsole;
        }

        public void setHaveCloudConsole(int haveCloudConsole) {
            this.haveCloudConsole = haveCloudConsole;
        }
    }
}
