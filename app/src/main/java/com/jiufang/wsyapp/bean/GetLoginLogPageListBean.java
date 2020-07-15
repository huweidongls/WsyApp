package com.jiufang.wsyapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/7/15.
 */

public class GetLoginLogPageListBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"total":9,"totalPages":1,"records":[{"id":38,"userId":2,"deviceType":1,"deviceName":"","appVersion":"1.0.25","osName":"Meizu","osVersion":"m3 note","loginTime":"2020-07-15"},{"id":37,"userId":2,"deviceType":1,"deviceName":"","appVersion":"1.0.25","osName":"Meizu","osVersion":"m3 note","loginTime":"2020-07-15"},{"id":36,"userId":2,"deviceType":1,"deviceName":"","appVersion":"1.0.25","osName":"Meizu","osVersion":"m3 note","loginTime":"2020-07-14"},{"id":35,"userId":2,"deviceType":1,"deviceName":"","appVersion":"1.0.25","osName":"m3 note","osVersion":"","loginTime":"2020-07-14"},{"id":34,"userId":2,"deviceType":1,"deviceName":"","appVersion":"1.0.24","osName":"m3 note","osVersion":"","loginTime":"2020-07-14"},{"id":33,"userId":2,"deviceType":1,"deviceName":"","appVersion":"1.0.24","osName":"","osVersion":"","loginTime":"2020-07-14"},{"id":32,"userId":2,"deviceType":1,"deviceName":"","appVersion":"1.0.24","osName":"","osVersion":"","loginTime":"2020-07-14"},{"id":29,"userId":2,"deviceType":1,"deviceName":"","appVersion":"1.0.23","osName":"","osVersion":"","loginTime":"2020-07-09"},{"id":26,"userId":2,"deviceType":1,"deviceName":"","appVersion":"1.0.22","osName":"","osVersion":"","loginTime":"2020-07-08"}],"pageIndex":1,"pageSize":10}
     * time : 2020-07-15 09:26:17
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
         * total : 9
         * totalPages : 1
         * records : [{"id":38,"userId":2,"deviceType":1,"deviceName":"","appVersion":"1.0.25","osName":"Meizu","osVersion":"m3 note","loginTime":"2020-07-15"},{"id":37,"userId":2,"deviceType":1,"deviceName":"","appVersion":"1.0.25","osName":"Meizu","osVersion":"m3 note","loginTime":"2020-07-15"},{"id":36,"userId":2,"deviceType":1,"deviceName":"","appVersion":"1.0.25","osName":"Meizu","osVersion":"m3 note","loginTime":"2020-07-14"},{"id":35,"userId":2,"deviceType":1,"deviceName":"","appVersion":"1.0.25","osName":"m3 note","osVersion":"","loginTime":"2020-07-14"},{"id":34,"userId":2,"deviceType":1,"deviceName":"","appVersion":"1.0.24","osName":"m3 note","osVersion":"","loginTime":"2020-07-14"},{"id":33,"userId":2,"deviceType":1,"deviceName":"","appVersion":"1.0.24","osName":"","osVersion":"","loginTime":"2020-07-14"},{"id":32,"userId":2,"deviceType":1,"deviceName":"","appVersion":"1.0.24","osName":"","osVersion":"","loginTime":"2020-07-14"},{"id":29,"userId":2,"deviceType":1,"deviceName":"","appVersion":"1.0.23","osName":"","osVersion":"","loginTime":"2020-07-09"},{"id":26,"userId":2,"deviceType":1,"deviceName":"","appVersion":"1.0.22","osName":"","osVersion":"","loginTime":"2020-07-08"}]
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
             * id : 38
             * userId : 2
             * deviceType : 1
             * deviceName :
             * appVersion : 1.0.25
             * osName : Meizu
             * osVersion : m3 note
             * loginTime : 2020-07-15
             */

            private int id;
            private int userId;
            private int deviceType;
            private String deviceName;
            private String appVersion;
            private String osName;
            private String osVersion;
            private String loginTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getDeviceType() {
                return deviceType;
            }

            public void setDeviceType(int deviceType) {
                this.deviceType = deviceType;
            }

            public String getDeviceName() {
                return deviceName;
            }

            public void setDeviceName(String deviceName) {
                this.deviceName = deviceName;
            }

            public String getAppVersion() {
                return appVersion;
            }

            public void setAppVersion(String appVersion) {
                this.appVersion = appVersion;
            }

            public String getOsName() {
                return osName;
            }

            public void setOsName(String osName) {
                this.osName = osName;
            }

            public String getOsVersion() {
                return osVersion;
            }

            public void setOsVersion(String osVersion) {
                this.osVersion = osVersion;
            }

            public String getLoginTime() {
                return loginTime;
            }

            public void setLoginTime(String loginTime) {
                this.loginTime = loginTime;
            }
        }
    }
}
