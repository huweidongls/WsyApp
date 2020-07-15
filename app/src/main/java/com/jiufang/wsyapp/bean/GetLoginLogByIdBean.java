package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/7/15.
 */

public class GetLoginLogByIdBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"id":38,"userId":2,"deviceType":1,"deviceName":"","appVersion":"1.0.25","osName":"Meizu","osVersion":"m3 note","loginTime":"2020-07-15"}
     * time : 2020-07-15 09:32:12
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
         * id : 38
         * userId : 2
         * deviceType : 1
         * deviceName :
         * appVersion : 1.0.25
         * osName : Meizu
         * osVersion : m3 note
         * loginTime : 2020-07-15
         */

        private int id;
        private int userId;
        private int deviceType;
        private String deviceName;
        private String appVersion;
        private String osName;
        private String osVersion;
        private String loginTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(int deviceType) {
            this.deviceType = deviceType;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }

        public String getOsName() {
            return osName;
        }

        public void setOsName(String osName) {
            this.osName = osName;
        }

        public String getOsVersion() {
            return osVersion;
        }

        public void setOsVersion(String osVersion) {
            this.osVersion = osVersion;
        }

        public String getLoginTime() {
            return loginTime;
        }

        public void setLoginTime(String loginTime) {
            this.loginTime = loginTime;
        }
    }
}
