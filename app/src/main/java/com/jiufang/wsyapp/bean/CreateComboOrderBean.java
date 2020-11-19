package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/11/19.
 */

public class CreateComboOrderBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"id":4,"orderSn":"779001450062675968","createTime":"2020-11-19 15:13:38","actualAmount":null,"payMethod":1,"sysUserName":"张经理","sysUserPhone":"15645108888","comboCategoryName":"7天套餐","storageDays":7,"comboName":"云存储7天-半年","comboCode":null,"comboIsOpen":null,"comboStartTime":null,"comboExpireTime":null,"brandId":null,"brandName":"萤石","deviceName":"呵呵","deviceModel":"CS-C1HC-1D1WFR","snCode":"C78047095","previousName":"无","previousEndTime":null}
     * time : 2020-11-19 15:13:38
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
         * id : 4
         * orderSn : 779001450062675968
         * createTime : 2020-11-19 15:13:38
         * actualAmount : null
         * payMethod : 1
         * sysUserName : 张经理
         * sysUserPhone : 15645108888
         * comboCategoryName : 7天套餐
         * storageDays : 7
         * comboName : 云存储7天-半年
         * comboCode : null
         * comboIsOpen : null
         * comboStartTime : null
         * comboExpireTime : null
         * brandId : null
         * brandName : 萤石
         * deviceName : 呵呵
         * deviceModel : CS-C1HC-1D1WFR
         * snCode : C78047095
         * previousName : 无
         * previousEndTime : null
         */

        private int id;
        private String orderSn;
        private String createTime;
        private Object actualAmount;
        private int payMethod;
        private String sysUserName;
        private String sysUserPhone;
        private String comboCategoryName;
        private int storageDays;
        private String comboName;
        private Object comboCode;
        private Object comboIsOpen;
        private Object comboStartTime;
        private Object comboExpireTime;
        private Object brandId;
        private String brandName;
        private String deviceName;
        private String deviceModel;
        private String snCode;
        private String previousName;
        private Object previousEndTime;

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

        public Object getActualAmount() {
            return actualAmount;
        }

        public void setActualAmount(Object actualAmount) {
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

        public Object getComboCode() {
            return comboCode;
        }

        public void setComboCode(Object comboCode) {
            this.comboCode = comboCode;
        }

        public Object getComboIsOpen() {
            return comboIsOpen;
        }

        public void setComboIsOpen(Object comboIsOpen) {
            this.comboIsOpen = comboIsOpen;
        }

        public Object getComboStartTime() {
            return comboStartTime;
        }

        public void setComboStartTime(Object comboStartTime) {
            this.comboStartTime = comboStartTime;
        }

        public Object getComboExpireTime() {
            return comboExpireTime;
        }

        public void setComboExpireTime(Object comboExpireTime) {
            this.comboExpireTime = comboExpireTime;
        }

        public Object getBrandId() {
            return brandId;
        }

        public void setBrandId(Object brandId) {
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

        public String getPreviousName() {
            return previousName;
        }

        public void setPreviousName(String previousName) {
            this.previousName = previousName;
        }

        public Object getPreviousEndTime() {
            return previousEndTime;
        }

        public void setPreviousEndTime(Object previousEndTime) {
            this.previousEndTime = previousEndTime;
        }
    }
}
