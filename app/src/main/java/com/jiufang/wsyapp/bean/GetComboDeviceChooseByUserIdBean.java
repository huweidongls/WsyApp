package com.jiufang.wsyapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/7/9.
 */

public class GetComboDeviceChooseByUserIdBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : [{"id":27,"deviceName":"萤石7095","deviceModel":"CS-C1HC-1D1WFR","deviceSn":"C78047095","productImage":"202005022100276117296.png","snapImage":"/device/2020/07/202007031016397429908.png","brandId":2,"brandName":"萤石","isHaveCombo":true,"isExpire":true,"comboName":null,"comboExpireTime":"2020-07-07 13:44:15"},{"id":31,"deviceName":"乐橙带云台带本地存储","deviceModel":"TP2E","deviceSn":"5K05D84PAU69A7C","productImage":"202005022100276117296.png","snapImage":"/device/2020/07/202007031012282823466.png","brandId":1,"brandName":"乐橙","isHaveCombo":false,"isExpire":true,"comboName":null,"comboExpireTime":null}]
     * time : 2020-07-09 11:02:53
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
         * id : 27
         * deviceName : 萤石7095
         * deviceModel : CS-C1HC-1D1WFR
         * deviceSn : C78047095
         * productImage : 202005022100276117296.png
         * snapImage : /device/2020/07/202007031016397429908.png
         * brandId : 2
         * brandName : 萤石
         * isHaveCombo : true
         * isExpire : true
         * comboName : null
         * comboExpireTime : 2020-07-07 13:44:15
         */

        private int id;
        private String deviceName;
        private String deviceModel;
        private String deviceSn;
        private String productImage;
        private String snapImage;
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
