package com.jiufang.wsyapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/6/8.
 */

public class GetYSLocalStorageRecordListBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : [{"deviceId":9,"brandId":2,"deviceSN":"C78047095","channelId":"1","recordId":null,"recordRegionId":null,"encryptMode":null,"crypt":0,"startTime":"2020-06-06 09:03:03","endTime":"2020-06-06 09:04:30","fileSize":"0","coverPic":null,"fileIndex":null,"locked":null,"keyChecksum":null,"storageType":"ALARM","recordPath":null},{"deviceId":9,"brandId":2,"deviceSN":"C78047095","channelId":"1","recordId":null,"recordRegionId":null,"encryptMode":null,"crypt":0,"startTime":"2020-06-06 09:07:10","endTime":"2020-06-06 09:13:11","fileSize":"0","coverPic":null,"fileIndex":null,"locked":null,"keyChecksum":null,"storageType":"ALARM","recordPath":null}]
     * time : 2020-06-08 14:21:56
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
         * deviceId : 9
         * brandId : 2
         * deviceSN : C78047095
         * channelId : 1
         * recordId : null
         * recordRegionId : null
         * encryptMode : null
         * crypt : 0
         * startTime : 2020-06-06 09:03:03
         * endTime : 2020-06-06 09:04:30
         * fileSize : 0
         * coverPic : null
         * fileIndex : null
         * locked : null
         * keyChecksum : null
         * storageType : ALARM
         * recordPath : null
         */

        private int deviceId;
        private int brandId;
        private String deviceSN;
        private String channelId;
        private Object recordId;
        private Object recordRegionId;
        private Object encryptMode;
        private int crypt;
        private String startTime;
        private String endTime;
        private String fileSize;
        private Object coverPic;
        private Object fileIndex;
        private Object locked;
        private Object keyChecksum;
        private String storageType;
        private Object recordPath;

        public int getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(int deviceId) {
            this.deviceId = deviceId;
        }

        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public String getDeviceSN() {
            return deviceSN;
        }

        public void setDeviceSN(String deviceSN) {
            this.deviceSN = deviceSN;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public Object getRecordId() {
            return recordId;
        }

        public void setRecordId(Object recordId) {
            this.recordId = recordId;
        }

        public Object getRecordRegionId() {
            return recordRegionId;
        }

        public void setRecordRegionId(Object recordRegionId) {
            this.recordRegionId = recordRegionId;
        }

        public Object getEncryptMode() {
            return encryptMode;
        }

        public void setEncryptMode(Object encryptMode) {
            this.encryptMode = encryptMode;
        }

        public int getCrypt() {
            return crypt;
        }

        public void setCrypt(int crypt) {
            this.crypt = crypt;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getFileSize() {
            return fileSize;
        }

        public void setFileSize(String fileSize) {
            this.fileSize = fileSize;
        }

        public Object getCoverPic() {
            return coverPic;
        }

        public void setCoverPic(Object coverPic) {
            this.coverPic = coverPic;
        }

        public Object getFileIndex() {
            return fileIndex;
        }

        public void setFileIndex(Object fileIndex) {
            this.fileIndex = fileIndex;
        }

        public Object getLocked() {
            return locked;
        }

        public void setLocked(Object locked) {
            this.locked = locked;
        }

        public Object getKeyChecksum() {
            return keyChecksum;
        }

        public void setKeyChecksum(Object keyChecksum) {
            this.keyChecksum = keyChecksum;
        }

        public String getStorageType() {
            return storageType;
        }

        public void setStorageType(String storageType) {
            this.storageType = storageType;
        }

        public Object getRecordPath() {
            return recordPath;
        }

        public void setRecordPath(Object recordPath) {
            this.recordPath = recordPath;
        }
    }
}
