package com.jiufang.wsyapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2020/6/10.
 */

public class GetLcLocalStorageRecordListBean implements Serializable {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : [{"deviceId":7,"brandId":1,"deviceSN":"5K05D84PAU69A7C","channelId":null,"recordId":"/mnt/sd/mmcblk0p0/2020-06-10/001/dav/08/08.56.57-08.57.45[M][0@0][0].dav","recordRegionId":null,"encryptMode":null,"crypt":null,"startTime":"2020-06-10 08:56:57","endTime":"2020-06-10 08:57:45","fileSize":"6535174","coverPic":null,"fileIndex":null,"locked":null,"keyChecksum":null,"storageType":"videomotion","recordPath":null},{"deviceId":7,"brandId":1,"deviceSN":"5K05D84PAU69A7C","channelId":null,"recordId":"/mnt/sd/mmcblk0p0/2020-06-10/001/dav/08/08.58.35-09.00.48[M][0@0][0].dav","recordRegionId":null,"encryptMode":null,"crypt":null,"startTime":"2020-06-10 08:58:35","endTime":"2020-06-10 09:00:48","fileSize":"17804297","coverPic":null,"fileIndex":null,"locked":null,"keyChecksum":null,"storageType":"videomotion","recordPath":null}]
     * time : 2020-06-10 14:41:17
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

    public static class DataBean implements Serializable {
        /**
         * deviceId : 7
         * brandId : 1
         * deviceSN : 5K05D84PAU69A7C
         * channelId : null
         * recordId : /mnt/sd/mmcblk0p0/2020-06-10/001/dav/08/08.56.57-08.57.45[M][0@0][0].dav
         * recordRegionId : null
         * encryptMode : null
         * crypt : null
         * startTime : 2020-06-10 08:56:57
         * endTime : 2020-06-10 08:57:45
         * fileSize : 6535174
         * coverPic : null
         * fileIndex : null
         * locked : null
         * keyChecksum : null
         * storageType : videomotion
         * recordPath : null
         */

        private int deviceId;
        private int brandId;
        private String deviceSN;
        private Object channelId;
        private String recordId;
        private Object recordRegionId;
        private Object encryptMode;
        private Object crypt;
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

        public Object getChannelId() {
            return channelId;
        }

        public void setChannelId(Object channelId) {
            this.channelId = channelId;
        }

        public String getRecordId() {
            return recordId;
        }

        public void setRecordId(String recordId) {
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

        public Object getCrypt() {
            return crypt;
        }

        public void setCrypt(Object crypt) {
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
