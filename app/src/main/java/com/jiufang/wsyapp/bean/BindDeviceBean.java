package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/5/21.
 */

public class BindDeviceBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"id":6,"brandId":null,"productCategoryId":null,"productCategoryName":null,"productImage":"202005022100276117296.png","snapImage":null,"snCode":"2F0476DPAL01070","securityCode":null,"deviceName":"乐橙摄像机","deviceAccessToken":"Ut_0000550580bebeba4291bf179e1dbe01","deviceStatus":1,"offlineTime":null,"cloudStorageStatus":0,"nativeStorageStatus":1}
     * time : 2020-05-21 17:20:44
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
         * id : 6
         * brandId : null
         * productCategoryId : null
         * productCategoryName : null
         * productImage : 202005022100276117296.png
         * snapImage : null
         * snCode : 2F0476DPAL01070
         * securityCode : null
         * deviceName : 乐橙摄像机
         * deviceAccessToken : Ut_0000550580bebeba4291bf179e1dbe01
         * deviceStatus : 1
         * offlineTime : null
         * cloudStorageStatus : 0
         * nativeStorageStatus : 1
         */

        private int id;
        private Object brandId;
        private Object productCategoryId;
        private Object productCategoryName;
        private String productImage;
        private Object snapImage;
        private String snCode;
        private Object securityCode;
        private String deviceName;
        private String deviceAccessToken;
        private int deviceStatus;
        private Object offlineTime;
        private int cloudStorageStatus;
        private int nativeStorageStatus;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getBrandId() {
            return brandId;
        }

        public void setBrandId(Object brandId) {
            this.brandId = brandId;
        }

        public Object getProductCategoryId() {
            return productCategoryId;
        }

        public void setProductCategoryId(Object productCategoryId) {
            this.productCategoryId = productCategoryId;
        }

        public Object getProductCategoryName() {
            return productCategoryName;
        }

        public void setProductCategoryName(Object productCategoryName) {
            this.productCategoryName = productCategoryName;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public Object getSnapImage() {
            return snapImage;
        }

        public void setSnapImage(Object snapImage) {
            this.snapImage = snapImage;
        }

        public String getSnCode() {
            return snCode;
        }

        public void setSnCode(String snCode) {
            this.snCode = snCode;
        }

        public Object getSecurityCode() {
            return securityCode;
        }

        public void setSecurityCode(Object securityCode) {
            this.securityCode = securityCode;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceAccessToken() {
            return deviceAccessToken;
        }

        public void setDeviceAccessToken(String deviceAccessToken) {
            this.deviceAccessToken = deviceAccessToken;
        }

        public int getDeviceStatus() {
            return deviceStatus;
        }

        public void setDeviceStatus(int deviceStatus) {
            this.deviceStatus = deviceStatus;
        }

        public Object getOfflineTime() {
            return offlineTime;
        }

        public void setOfflineTime(Object offlineTime) {
            this.offlineTime = offlineTime;
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
    }
}
