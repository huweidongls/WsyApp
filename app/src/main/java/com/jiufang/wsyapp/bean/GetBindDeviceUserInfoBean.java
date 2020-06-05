package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/6/5.
 */

public class GetBindDeviceUserInfoBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"id":7,"brandId":1,"areaId":651,"areaName":"哈尔滨市","areaFullName":"黑龙江省哈尔滨市","address":"汉广街41号-金华大厦","houseNumber":"408","personName":"呵呵","personPhone":"18686868686","deviceName":"乐橙带云台","snCode":"5K05D84PAU69A7C","securityCode":"L25C2542","liveToken":null,"liveUrl":null}
     * time : 2020-06-05 15:34:07
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
         * id : 7
         * brandId : 1
         * areaId : 651
         * areaName : 哈尔滨市
         * areaFullName : 黑龙江省哈尔滨市
         * address : 汉广街41号-金华大厦
         * houseNumber : 408
         * personName : 呵呵
         * personPhone : 18686868686
         * deviceName : 乐橙带云台
         * snCode : 5K05D84PAU69A7C
         * securityCode : L25C2542
         * liveToken : null
         * liveUrl : null
         */

        private int id;
        private int brandId;
        private int areaId;
        private String areaName;
        private String areaFullName;
        private String address;
        private String houseNumber;
        private String personName;
        private String personPhone;
        private String deviceName;
        private String snCode;
        private String securityCode;
        private Object liveToken;
        private Object liveUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public int getAreaId() {
            return areaId;
        }

        public void setAreaId(int areaId) {
            this.areaId = areaId;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
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

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getSnCode() {
            return snCode;
        }

        public void setSnCode(String snCode) {
            this.snCode = snCode;
        }

        public String getSecurityCode() {
            return securityCode;
        }

        public void setSecurityCode(String securityCode) {
            this.securityCode = securityCode;
        }

        public Object getLiveToken() {
            return liveToken;
        }

        public void setLiveToken(Object liveToken) {
            this.liveToken = liveToken;
        }

        public Object getLiveUrl() {
            return liveUrl;
        }

        public void setLiveUrl(Object liveUrl) {
            this.liveUrl = liveUrl;
        }
    }
}
