package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/7/9.
 */

public class GetComboOrderByDeviceIdBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"id":2,"orderSn":"20200608","createTime":"2020-06-08 13:36:35","actualAmount":58,"payMethod":1,"sysUserName":"张经理","sysUserPhone":"15645108888","comboCategoryName":"7天套餐","storageDays":7,"comboName":"云存储7天-1个月","comboCode":"YS001","comboIsOpen":1,"comboStartTime":"2020-06-08","comboExpireTime":"2020-07-07","brandId":2,"brandName":"萤石","deviceName":"萤石7095","deviceModel":"CS-C1HC-1D1WFR","snCode":"C78047095"}
     * time : 2020-07-09 15:57:51
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
         * id : 2
         * orderSn : 20200608
         * createTime : 2020-06-08 13:36:35
         * actualAmount : 58
         * payMethod : 1
         * sysUserName : 张经理
         * sysUserPhone : 15645108888
         * comboCategoryName : 7天套餐
         * storageDays : 7
         * comboName : 云存储7天-1个月
         * comboCode : YS001
         * comboIsOpen : 1
         * comboStartTime : 2020-06-08
         * comboExpireTime : 2020-07-07
         * brandId : 2
         * brandName : 萤石
         * deviceName : 萤石7095
         * deviceModel : CS-C1HC-1D1WFR
         * snCode : C78047095
         */

        private int id;
        private String orderSn;
        private String createTime;
        private int actualAmount;
        private int payMethod;
        private String sysUserName;
        private String sysUserPhone;
        private String comboCategoryName;
        private int storageDays;
        private String comboName;
        private String comboCode;
        private int comboIsOpen;
        private String comboStartTime;
        private String comboExpireTime;
        private int brandId;
        private String brandName;
        private String deviceName;
        private String deviceModel;
        private String snCode;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderSn() {
            return orderSn;
        }

        public void setOrderSn(String orderSn) {
            this.orderSn = orderSn;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getActualAmount() {
            return actualAmount;
        }

        public void setActualAmount(int actualAmount) {
            this.actualAmount = actualAmount;
        }

        public int getPayMethod() {
            return payMethod;
        }

        public void setPayMethod(int payMethod) {
            this.payMethod = payMethod;
        }

        public String getSysUserName() {
            return sysUserName;
        }

        public void setSysUserName(String sysUserName) {
            this.sysUserName = sysUserName;
        }

        public String getSysUserPhone() {
            return sysUserPhone;
        }

        public void setSysUserPhone(String sysUserPhone) {
            this.sysUserPhone = sysUserPhone;
        }

        public String getComboCategoryName() {
            return comboCategoryName;
        }

        public void setComboCategoryName(String comboCategoryName) {
            this.comboCategoryName = comboCategoryName;
        }

        public int getStorageDays() {
            return storageDays;
        }

        public void setStorageDays(int storageDays) {
            this.storageDays = storageDays;
        }

        public String getComboName() {
            return comboName;
        }

        public void setComboName(String comboName) {
            this.comboName = comboName;
        }

        public String getComboCode() {
            return comboCode;
        }

        public void setComboCode(String comboCode) {
            this.comboCode = comboCode;
        }

        public int getComboIsOpen() {
            return comboIsOpen;
        }

        public void setComboIsOpen(int comboIsOpen) {
            this.comboIsOpen = comboIsOpen;
        }

        public String getComboStartTime() {
            return comboStartTime;
        }

        public void setComboStartTime(String comboStartTime) {
            this.comboStartTime = comboStartTime;
        }

        public String getComboExpireTime() {
            return comboExpireTime;
        }

        public void setComboExpireTime(String comboExpireTime) {
            this.comboExpireTime = comboExpireTime;
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

        public String getSnCode() {
            return snCode;
        }

        public void setSnCode(String snCode) {
            this.snCode = snCode;
        }
    }
}
