package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/5/25.
 */

public class GetAreaByNameBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"id":651,"createTime":"2020-04-29 20:25:53","updateTime":"2020-04-29 20:25:53","name":"哈尔滨市","fullName":"黑龙江省哈尔滨市","orders":null,"treePath":",650,","parent":650,"isCity":1,"isHot":0,"chinesePhonetic":"haerbinshi","deleted":0}
     * time : 2020-05-25 16:41:27
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
         * id : 651
         * createTime : 2020-04-29 20:25:53
         * updateTime : 2020-04-29 20:25:53
         * name : 哈尔滨市
         * fullName : 黑龙江省哈尔滨市
         * orders : null
         * treePath : ,650,
         * parent : 650
         * isCity : 1
         * isHot : 0
         * chinesePhonetic : haerbinshi
         * deleted : 0
         */

        private int id;
        private String createTime;
        private String updateTime;
        private String name;
        private String fullName;
        private Object orders;
        private String treePath;
        private int parent;
        private int isCity;
        private int isHot;
        private String chinesePhonetic;
        private int deleted;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public Object getOrders() {
            return orders;
        }

        public void setOrders(Object orders) {
            this.orders = orders;
        }

        public String getTreePath() {
            return treePath;
        }

        public void setTreePath(String treePath) {
            this.treePath = treePath;
        }

        public int getParent() {
            return parent;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }

        public int getIsCity() {
            return isCity;
        }

        public void setIsCity(int isCity) {
            this.isCity = isCity;
        }

        public int getIsHot() {
            return isHot;
        }

        public void setIsHot(int isHot) {
            this.isHot = isHot;
        }

        public String getChinesePhonetic() {
            return chinesePhonetic;
        }

        public void setChinesePhonetic(String chinesePhonetic) {
            this.chinesePhonetic = chinesePhonetic;
        }

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }
    }
}
