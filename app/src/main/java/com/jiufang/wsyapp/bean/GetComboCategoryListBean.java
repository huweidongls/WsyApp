package com.jiufang.wsyapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/5/14.
 */

public class GetComboCategoryListBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : [{"id":1,"name":"7天套餐","explain":"循环保存7天内动检录像","comboVoList":[{"id":1,"comboCategoryId":1,"comboName":"云存储7天-1个月","comboCode":"LC001","comboImage":"202005022100276117296.png","comboContent":"云存储7天-1个月","retailPrice":11},{"id":2,"comboCategoryId":1,"comboName":"云存储7天-半年","comboCode":"LC002","comboImage":"202005022100276117296.png","comboContent":"云存储7天-半年","retailPrice":60},{"id":3,"comboCategoryId":1,"comboName":"云存储7天-1年","comboCode":"LC003","comboImage":"202005022100276117296.png","comboContent":"云存储7天-1年","retailPrice":99}]},{"id":2,"name":"30天套餐","explain":"循环保存30天内动检录像","comboVoList":[{"id":4,"comboCategoryId":2,"comboName":"云存储30天-1个月","comboCode":"LC004","comboImage":"202005022100276117296.png","comboContent":"云存储30天-1个月","retailPrice":26},{"id":5,"comboCategoryId":2,"comboName":"云存储30天-半年","comboCode":"LC005","comboImage":"202005022100276117296.png","comboContent":"云存储30天-半年","retailPrice":150},{"id":6,"comboCategoryId":2,"comboName":"云存储30天-1年","comboCode":"LC006","comboImage":"202005022100276117296.png","comboContent":"云存储30天-1年","retailPrice":199}]},{"id":3,"name":"3天套餐","explain":"循环保存3天内动检录像","comboVoList":[{"id":7,"comboCategoryId":3,"comboName":"云存储3天-1年","comboCode":"LC007","comboImage":"202005022100276117296.png","comboContent":"云存储3天-1年","retailPrice":88}]}]
     * time : 2020-05-14 14:04:15
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
         * id : 1
         * name : 7天套餐
         * explain : 循环保存7天内动检录像
         * comboVoList : [{"id":1,"comboCategoryId":1,"comboName":"云存储7天-1个月","comboCode":"LC001","comboImage":"202005022100276117296.png","comboContent":"云存储7天-1个月","retailPrice":11},{"id":2,"comboCategoryId":1,"comboName":"云存储7天-半年","comboCode":"LC002","comboImage":"202005022100276117296.png","comboContent":"云存储7天-半年","retailPrice":60},{"id":3,"comboCategoryId":1,"comboName":"云存储7天-1年","comboCode":"LC003","comboImage":"202005022100276117296.png","comboContent":"云存储7天-1年","retailPrice":99}]
         */

        private int id;
        private String name;
        private String explain;
        private List<ComboVoListBean> comboVoList;

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

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public List<ComboVoListBean> getComboVoList() {
            return comboVoList;
        }

        public void setComboVoList(List<ComboVoListBean> comboVoList) {
            this.comboVoList = comboVoList;
        }

        public static class ComboVoListBean {
            /**
             * id : 1
             * comboCategoryId : 1
             * comboName : 云存储7天-1个月
             * comboCode : LC001
             * comboImage : 202005022100276117296.png
             * comboContent : 云存储7天-1个月
             * retailPrice : 11
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
}
