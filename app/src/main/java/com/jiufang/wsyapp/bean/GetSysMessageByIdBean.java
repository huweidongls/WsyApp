package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/5/12.
 */

public class GetSysMessageByIdBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"id":1,"sysType":1,"messageTitle":"系统升级公告","messageTime":"2020-05-09 17:50:38","messageContent":"各位客户请注意：2020年5月30日 早02:00至 04:00系统升级","isRead":0}
     * time : 2020-05-12 09:43:43
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
         * id : 1
         * sysType : 1
         * messageTitle : 系统升级公告
         * messageTime : 2020-05-09 17:50:38
         * messageContent : 各位客户请注意：2020年5月30日 早02:00至 04:00系统升级
         * isRead : 0
         */

        private int id;
        private int sysType;
        private String messageTitle;
        private String messageTime;
        private String messageContent;
        private int isRead;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSysType() {
            return sysType;
        }

        public void setSysType(int sysType) {
            this.sysType = sysType;
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

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }
    }
}
