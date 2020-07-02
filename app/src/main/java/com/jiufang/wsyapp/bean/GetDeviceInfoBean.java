package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/7/2.
 */

public class GetDeviceInfoBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"brandId":2,"deviceName":"萤石7095","deviceModel":"CS-C1HC-1D1WFR","deviceSn":"C78047095","securityCode":"TQJATV","currentVersion":"V5.2.8 build 190925","snapImage":"/device/2020/07/202007021712290970759.png"}
     * time : 2020-07-02 17:15:26
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
         * brandId : 2
         * deviceName : 萤石7095
         * deviceModel : CS-C1HC-1D1WFR
         * deviceSn : C78047095
         * securityCode : TQJATV
         * currentVersion : V5.2.8 build 190925
         * snapImage : /device/2020/07/202007021712290970759.png
         */

        private int brandId;
        private String deviceName;
        private String deviceModel;
        private String deviceSn;
        private String securityCode;
        private String currentVersion;
        private String snapImage;

        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceModel() {
            return deviceModel;
        }

        public void setDeviceModel(String deviceModel) {
            this.deviceModel = deviceModel;
        }

        public String getDeviceSn() {
            return deviceSn;
        }

        public void setDeviceSn(String deviceSn) {
            this.deviceSn = deviceSn;
        }

        public String getSecurityCode() {
            return securityCode;
        }

        public void setSecurityCode(String securityCode) {
            this.securityCode = securityCode;
        }

        public String getCurrentVersion() {
            return currentVersion;
        }

        public void setCurrentVersion(String currentVersion) {
            this.currentVersion = currentVersion;
        }

        public String getSnapImage() {
            return snapImage;
        }

        public void setSnapImage(String snapImage) {
            this.snapImage = snapImage;
        }
    }
}
