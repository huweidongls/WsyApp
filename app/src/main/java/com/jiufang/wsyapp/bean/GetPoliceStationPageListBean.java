package com.jiufang.wsyapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/11/23.
 */

public class GetPoliceStationPageListBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"total":3,"totalPages":1,"records":[{"id":6,"name":"新阳路派出所","longitude":"126.62677924602127","latitude":"45.76709882837104"},{"id":5,"name":"清滨路派出所","longitude":"126.61999618782727","latitude":"45.73255273214555"},{"id":4,"name":"七政街派出所","longitude":"126.6256906506585","latitude":"45.742036623669999"}],"pageIndex":1,"pageSize":20}
     * time : 2020-11-23 10:49:10
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
         * total : 3
         * totalPages : 1
         * records : [{"id":6,"name":"新阳路派出所","longitude":"126.62677924602127","latitude":"45.76709882837104"},{"id":5,"name":"清滨路派出所","longitude":"126.61999618782727","latitude":"45.73255273214555"},{"id":4,"name":"七政街派出所","longitude":"126.6256906506585","latitude":"45.742036623669999"}]
         * pageIndex : 1
         * pageSize : 20
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
             * id : 6
             * name : 新阳路派出所
             * longitude : 126.62677924602127
             * latitude : 45.76709882837104
             */

            private int id;
            private String name;
            private String longitude;
            private String latitude;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }
        }
    }
}
