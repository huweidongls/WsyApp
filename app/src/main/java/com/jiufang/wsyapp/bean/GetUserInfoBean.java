package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/6/5.
 */

public class GetUserInfoBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"userId":2,"phone":"18686817319","nickName":"用户186****7319","headPicture":"202005111512575063087.png","realName":"胡伟东","areaId":null,"areaName":null,"areaFullName":null,"address":null,"houseNumber":null,"longitude":null,"latitude":null}
     * time : 2020-06-05 09:59:06
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
         * phone : 18686817319
         * nickName : 用户186****7319
         * headPicture : 202005111512575063087.png
         * realName : 胡伟东
         * areaId : null
         * areaName : null
         * areaFullName : null
         * address : null
         * houseNumber : null
         * longitude : null
         * latitude : null
         */

        private int userId;
        private String phone;
        private String nickName;
        private String headPicture;
        private String realName;
        private int areaId;
        private String areaName;
        private String areaFullName;
        private String address;
        private String houseNumber;
        private Object longitude;
        private Object latitude;
        private int authentication;

        public int getAuthentication() {
            return authentication;
        }

        public void setAuthentication(int authentication) {
            this.authentication = authentication;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getHeadPicture() {
            return headPicture;
        }

        public void setHeadPicture(String headPicture) {
            this.headPicture = headPicture;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
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

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }
    }
}
