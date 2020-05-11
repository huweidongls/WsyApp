package com.jiufang.wsyapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/5/11.
 */

public class GetPersonalMessagePageListBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"total":1,"records":[{"id":2,"personalType":1,"messageTitle":"设备绑定成功","messageTime":"2020-05-09 17:50:56","messageContent":"您已于2020年5月9日14点16分绑定了以下设备","deviceModel":"产品型号A","deviceSn":"5L09076PAJ513BD","deviceShareTime":null,"isRead":0}],"pageIndex":1,"pageSize":10}
     * time : 2020-05-11 10:21:44
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
         * records : [{"id":2,"personalType":1,"messageTitle":"设备绑定成功","messageTime":"2020-05-09 17:50:56","messageContent":"您已于2020年5月9日14点16分绑定了以下设备","deviceModel":"产品型号A","deviceSn":"5L09076PAJ513BD","deviceShareTime":null,"isRead":0}]
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

        public static class RecordsBean {
            /**
             * id : 2
             * personalType : 1
             * messageTitle : 设备绑定成功
             * messageTime : 2020-05-09 17:50:56
             * messageContent : 您已于2020年5月9日14点16分绑定了以下设备
             * deviceModel : 产品型号A
             * deviceSn : 5L09076PAJ513BD
             * deviceShareTime : null
             * isRead : 0
             */

            private int id;
            private int personalType;
            private String messageTitle;
            private String messageTime;
            private String messageContent;
            private String deviceModel;
            private String deviceSn;
            private Object deviceShareTime;
            private int isRead;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPersonalType() {
                return personalType;
            }

            public void setPersonalType(int personalType) {
                this.personalType = personalType;
            }

            public String getMessageTitle() {
                return messageTitle;
            }

            public void setMessageTitle(String messageTitle) {
                this.messageTitle = messageTitle;
            }

            public String getMessageTime() {
                return messageTime;
            }

            public void setMessageTime(String messageTime) {
                this.messageTime = messageTime;
            }

            public String getMessageContent() {
                return messageContent;
            }

            public void setMessageContent(String messageContent) {
                this.messageContent = messageContent;
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

            public Object getDeviceShareTime() {
                return deviceShareTime;
            }

            public void setDeviceShareTime(Object deviceShareTime) {
                this.deviceShareTime = deviceShareTime;
            }

            public int getIsRead() {
                return isRead;
            }

            public void setIsRead(int isRead) {
                this.isRead = isRead;
            }
        }
    }
}
