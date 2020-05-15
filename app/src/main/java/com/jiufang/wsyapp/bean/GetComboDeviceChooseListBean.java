package com.jiufang.wsyapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/5/14.
 */

public class GetComboDeviceChooseListBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : [{"id":1,"deviceName":"走廊监控","productImage":"202005022100276117296.png","snapImage":"202005022100276117296.png","deviceStatus":1,"brandId":1,"brandName":"乐橙","isHaveCombo":true,"isExpire":false,"comboName":null,"comboExpireTime":"2020-05-17 20:17:28"}]
     * time : 2020-05-14 19:12:13
     */

    private int code;
    private boolean success;
    private String message;
    private String time;
    private List<DataBean> data;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * deviceName : 走廊监控
         * productImage : 202005022100276117296.png
         * snapImage : 202005022100276117296.png
         * deviceStatus : 1
         * brandId : 1
         * brandName : 乐橙
         * isHaveCombo : true
         * isExpire : false
         * comboName : null
         * comboExpireTime : 2020-05-17 20:17:28
         */

        private int id;
        private String deviceName;
        private String productImage;
        private String snapImage;
        private int deviceStatus;
        private int brandId;
        private String brandName;
        private boolean isHaveCombo;
        private boolean isExpire;
        private Object comboName;
        private String comboExpireTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public String getSnapImage() {
            return snapImage;
        }

        public void setSnapImage(String snapImage) {
            this.snapImage = snapImage;
        }

        public int getDeviceStatus() {
            return deviceStatus;
        }

        public void setDeviceStatus(int deviceStatus) {
            this.deviceStatus = deviceStatus;
        }

        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public boolean isIsHaveCombo() {
            return isHaveCombo;
        }

        public void setIsHaveCombo(boolean isHaveCombo) {
            this.isHaveCombo = isHaveCombo;
        }

        public boolean isIsExpire() {
            return isExpire;
        }

        public void setIsExpire(boolean isExpire) {
            this.isExpire = isExpire;
        }

        public Object getComboName() {
            return comboName;
        }

        public void setComboName(Object comboName) {
            this.comboName = comboName;
        }

        public String getComboExpireTime() {
            return comboExpireTime;
        }

        public void setComboExpireTime(String comboExpireTime) {
            this.comboExpireTime = comboExpireTime;
        }
    }
}
