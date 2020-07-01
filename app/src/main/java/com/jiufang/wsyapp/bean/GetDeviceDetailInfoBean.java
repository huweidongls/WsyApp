package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/6/30.
 */

public class GetDeviceDetailInfoBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"deviceId":31,"userId":2,"brandId":1,"deviceName":"乐橙带云台带本地存储","deviceModel":"TP2E","productImage":"202005022100276117296.png","snapImage":null,"snCode":"5K05D84PAU69A7C","deviceStatus":1,"cloudStorageStatus":1,"nativeStorageStatus":1,"isNotice":1,"areaId":651,"areaName":null,"areaFullName":"黑龙江省哈尔滨市","address":"汉广街41号-金华大厦","houseNumber":"408","personName":"呵呵","personPhone":"18686868686"}
     * time : 2020-06-30 09:55:52
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
         * deviceId : 31
         * userId : 2
         * brandId : 1
         * deviceName : 乐橙带云台带本地存储
         * deviceModel : TP2E
         * productImage : 202005022100276117296.png
         * snapImage : null
         * snCode : 5K05D84PAU69A7C
         * deviceStatus : 1
         * cloudStorageStatus : 1
         * nativeStorageStatus : 1
         * isNotice : 1
         * areaId : 651
         * areaName : null
         * areaFullName : 黑龙江省哈尔滨市
         * address : 汉广街41号-金华大厦
         * houseNumber : 408
         * personName : 呵呵
         * personPhone : 18686868686
         */

        private int deviceId;
        private int userId;
        private int brandId;
        private String deviceName;
        private String deviceModel;
        private String productImage;
        private Object snapImage;
        private String snCode;
        private int deviceStatus;
        private int cloudStorageStatus;
        private int nativeStorageStatus;
        private int isNotice;
        private int areaId;
        private Object areaName;
        private String areaFullName;
        private String address;
        private String houseNumber;
        private String personName;
        private String personPhone;
        private String securityCode;

        public String getSecurityCode() {
            return securityCode;
        }

        public void setSecurityCode(String securityCode) {
            this.securityCode = securityCode;
        }

        public int getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(int deviceId) {
            this.deviceId = deviceId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

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

        public int getIsNotice() {
            return isNotice;
        }

        public void setIsNotice(int isNotice) {
            this.isNotice = isNotice;
        }

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public Object getAreaName() {
            return areaName;
        }

        public void setAreaName(Object areaName) {
            this.areaName = areaName;
        }

        public String getAreaFullName() {
            return areaFullName;
        }

        public void setAreaFullName(String areaFullName) {
            this.areaFullName = areaFullName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getHouseNumber() {
            return houseNumber;
        }

        public void setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
        }

        public String getPersonName() {
            return personName;
        }

        public void setPersonName(String personName) {
            this.personName = personName;
        }

        public String getPersonPhone() {
            return personPhone;
        }

        public void setPersonPhone(String personPhone) {
            this.personPhone = personPhone;
        }
    }
}
