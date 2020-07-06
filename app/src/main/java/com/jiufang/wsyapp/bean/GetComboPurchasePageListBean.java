package com.jiufang.wsyapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/7/6.
 */

public class GetComboPurchasePageListBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"total":1,"totalPages":1,"records":[{"id":2,"comboName":"云存储7天-1个月","brandId":2,"brandName":"萤石","deviceName":null,"snCode":null,"purchaseTime":"2020-06-08 13:36:35"}],"pageIndex":1,"pageSize":10}
     * time : 2020-07-06 10:10:34
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
         * total : 1
         * totalPages : 1
         * records : [{"id":2,"comboName":"云存储7天-1个月","brandId":2,"brandName":"萤石","deviceName":null,"snCode":null,"purchaseTime":"2020-06-08 13:36:35"}]
         * pageIndex : 1
         * pageSize : 10
         */

        private int total;
        private int totalPages;
        private int pageIndex;
        private int pageSize;
        private List<RecordsBean> records;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
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

        public static class RecordsBean {
            /**
             * id : 2
             * comboName : 云存储7天-1个月
             * brandId : 2
             * brandName : 萤石
             * deviceName : null
             * snCode : null
             * purchaseTime : 2020-06-08 13:36:35
             */

            private int id;
            private String comboName;
            private int brandId;
            private String brandName;
            private String deviceName;
            private String snCode;
            private String purchaseTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getComboName() {
                return comboName;
            }

            public void setComboName(String comboName) {
                this.comboName = comboName;
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

            public String getSnCode() {
                return snCode;
            }

            public void setSnCode(String snCode) {
                this.snCode = snCode;
            }

            public String getPurchaseTime() {
                return purchaseTime;
            }

            public void setPurchaseTime(String purchaseTime) {
                this.purchaseTime = purchaseTime;
            }
        }
    }
}
