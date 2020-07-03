package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/7/3.
 */

public class GetDeviceUpdateInfoBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"brandId":1,"deviceSn":"5K05D84PAU69A7C","currentVersion":"2.680.0000000.3.R.190814","canBeUpgrade":1,"isNeedUpgrade":null,"nextVersion":"V2.680.0000000.8.R.200317","description":"1、解决重置后软AP无法配网的问题，强烈建议升级\n2、增加onvif功能\n3、其他一些小问题修复"}
     * time : 2020-07-03 10:01:13
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
         * brandId : 1
         * deviceSn : 5K05D84PAU69A7C
         * currentVersion : 2.680.0000000.3.R.190814
         * canBeUpgrade : 1
         * isNeedUpgrade : null
         * nextVersion : V2.680.0000000.8.R.200317
         * description : 1、解决重置后软AP无法配网的问题，强烈建议升级
         2、增加onvif功能
         3、其他一些小问题修复
         */

        private int brandId;
        private String deviceSn;
        private String currentVersion;
        private int canBeUpgrade;
        private int isNeedUpgrade;
        private String nextVersion;
        private String description;

        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public String getDeviceSn() {
            return deviceSn;
        }

        public void setDeviceSn(String deviceSn) {
            this.deviceSn = deviceSn;
        }

        public String getCurrentVersion() {
            return currentVersion;
        }

        public void setCurrentVersion(String currentVersion) {
            this.currentVersion = currentVersion;
        }

        public int getCanBeUpgrade() {
            return canBeUpgrade;
        }

        public void setCanBeUpgrade(int canBeUpgrade) {
            this.canBeUpgrade = canBeUpgrade;
        }

        public int getIsNeedUpgrade() {
            return isNeedUpgrade;
        }

        public void setIsNeedUpgrade(int isNeedUpgrade) {
            this.isNeedUpgrade = isNeedUpgrade;
        }

        public String getNextVersion() {
            return nextVersion;
        }

        public void setNextVersion(String nextVersion) {
            this.nextVersion = nextVersion;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
