package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/5/14.
 */

public class GetComboByIdBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"id":3,"comboCategoryId":1,"comboName":"云存储7天-1年","comboCode":"LC003","comboImage":"202005022100276117296.png","comboContent":"云存储7天-1年","retailPrice":99}
     * time : 2020-05-14 16:29:22
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
         * id : 3
         * comboCategoryId : 1
         * comboName : 云存储7天-1年
         * comboCode : LC003
         * comboImage : 202005022100276117296.png
         * comboContent : 云存储7天-1年
         * retailPrice : 99
         */

        private int id;
        private int comboCategoryId;
        private String comboName;
        private String comboCode;
        private String comboImage;
        private String comboContent;
        private int retailPrice;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getComboCategoryId() {
            return comboCategoryId;
        }

        public void setComboCategoryId(int comboCategoryId) {
            this.comboCategoryId = comboCategoryId;
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

        public String getComboImage() {
            return comboImage;
        }

        public void setComboImage(String comboImage) {
            this.comboImage = comboImage;
        }

        public String getComboContent() {
            return comboContent;
        }

        public void setComboContent(String comboContent) {
            this.comboContent = comboContent;
        }

        public int getRetailPrice() {
            return retailPrice;
        }

        public void setRetailPrice(int retailPrice) {
            this.retailPrice = retailPrice;
        }
    }
}
