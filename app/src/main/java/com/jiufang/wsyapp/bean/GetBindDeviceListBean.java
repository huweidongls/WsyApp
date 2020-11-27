package com.jiufang.wsyapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2020/5/18.
 */

public class GetBindDeviceListBean implements Serializable {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"total":1,"records":[{"id":1,"brandId":1,"productCategoryId":1,"productCategoryName":"摄像机","productImage":"202005022100276117296.png","snapImage":null,"snCode":"5K05D84PAU69A7C","securityCode":"L25C2542","deviceName":"萤石带云台","deviceModel":"CS-C1HC-1D1WFR","haveCloudConsole":1,"offlineTime":null}],"pageIndex":1,"pageSize":10}
     * time : 2020-06-04 13:58:28
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

    public static class DataBean implements Serializable {
        /**
         * total : 1
         * records : [{"id":1,"brandId":1,"productCategoryId":1,"productCategoryName":"摄像机","productImage":"202005022100276117296.png","snapImage":null,"snCode":"5K05D84PAU69A7C","securityCode":"L25C2542","deviceName":"萤石带云台","deviceModel":"CS-C1HC-1D1WFR","haveCloudConsole":1,"offlineTime":null}]
         * pageIndex : 1
         * pageSize : 10
         */

        private int total;
        private int pageIndex;
        private int pageSize;
        private List<RecordsBean> records;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean implements Serializable {
            /**
             * id : 1
             * brandId : 1
             * productCategoryId : 1
             * productCategoryName : 摄像机
             * productImage : 202005022100276117296.png
             * snapImage : null
             * snCode : 5K05D84PAU69A7C
             * securityCode : L25C2542
             * deviceName : 萤石带云台
             * deviceModel : CS-C1HC-1D1WFR
             * haveCloudConsole : 1
             * offlineTime : null
             */

            private int id;
            private int brandId;
            private int productCategoryId;
            private String productCategoryName;
            private String productImage;
            private String snapImage;
            private String snCode;
            private String securityCode;
            private String deviceName;
            private String deviceModel;
            private int haveCloudConsole;
            private String offlineTime;
            private int deviceStatus = 0;//设备在线状态  0不在线  1在线
            private int cloudStorageStatus = 0;//云存储状态：0 未开启 1已开启 2即将到期 3已过期
            private int isHaveNewMessage = 0;//是否有新消息：0没有 1有
            private int nativeStorageStatus = 0;//本地存储状态：0 没有 1有
            private String lastAlarmTime;

            public String getLastAlarmTime() {
                return lastAlarmTime;
            }

            public void setLastAlarmTime(String lastAlarmTime) {
                this.lastAlarmTime = lastAlarmTime;
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

            public int getIsHaveNewMessage() {
                return isHaveNewMessage;
            }

            public void setIsHaveNewMessage(int isHaveNewMessage) {
                this.isHaveNewMessage = isHaveNewMessage;
            }

            public int getNativeStorageStatus() {
                return nativeStorageStatus;
            }

            public void setNativeStorageStatus(int nativeStorageStatus) {
                this.nativeStorageStatus = nativeStorageStatus;
            }

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

            public int getProductCategoryId() {
                return productCategoryId;
            }

            public void setProductCategoryId(int productCategoryId) {
                this.productCategoryId = productCategoryId;
            }

            public String getProductCategoryName() {
                return productCategoryName;
            }

            public void setProductCategoryName(String productCategoryName) {
                this.productCategoryName = productCategoryName;
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

            public int getHaveCloudConsole() {
                return haveCloudConsole;
            }

            public void setHaveCloudConsole(int haveCloudConsole) {
                this.haveCloudConsole = haveCloudConsole;
            }

            public String getOfflineTime() {
                return offlineTime;
            }

            public void setOfflineTime(String offlineTime) {
                this.offlineTime = offlineTime;
            }
        }
    }
}
