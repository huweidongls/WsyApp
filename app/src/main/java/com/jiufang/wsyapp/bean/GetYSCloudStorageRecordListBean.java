package com.jiufang.wsyapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/6/8.
 */

public class GetYSCloudStorageRecordListBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : [{"deviceId":9,"brandId":2,"deviceSN":"C78047095","channelId":"1","recordId":"20200608140028-C78047095-1-10002-1-1","recordRegionId":null,"encryptMode":null,"crypt":13,"startTime":"2020-06-08 14:00:22","endTime":"2020-06-08 14:02:03","fileSize":"6132744","coverPic":"https://tencentclouddownhb.ys7.com:8089/api/cloud?method=download&amp;fid=20200608140028-C78047095-1-10002-1-1&amp;fileType=1&amp;startTime=1591596022000&amp;storageVersion=1&amp;session=hik%24shipin7%231%23USK%23ra.b2cbdaj37yweo25l1qkxj5umdmjuull6-4jmcdddqjq-0iqr788-g8tfthy0r","fileIndex":"20200608140028-C78047095-1-10002-1-1","locked":null,"keyChecksum":"","storageType":null,"recordPath":null},{"deviceId":9,"brandId":2,"deviceSN":"C78047095","channelId":"1","recordId":"20200608140238-C78047095-1-10002-1-1","recordRegionId":null,"encryptMode":null,"crypt":13,"startTime":"2020-06-08 14:02:31","endTime":"2020-06-08 14:03:43","fileSize":"4802416","coverPic":"https://tencentclouddownhb.ys7.com:8089/api/cloud?method=download&amp;fid=20200608140238-C78047095-1-10002-2-1&amp;session=hik%24shipin7%231%23USK%23ra.b2cbdaj37yweo25l1qkxj5umdmjuull6-4jmcdddqjq-0iqr788-g8tfthy0r","fileIndex":"20200608140238-C78047095-1-10002-1-1","locked":null,"keyChecksum":"","storageType":null,"recordPath":null},{"deviceId":9,"brandId":2,"deviceSN":"C78047095","channelId":"1","recordId":"20200608140807-C78047095-1-10002-1-1","recordRegionId":null,"encryptMode":null,"crypt":13,"startTime":"2020-06-08 14:08:00","endTime":"2020-06-08 14:08:48","fileSize":"1743936","coverPic":"https://tencentclouddownhb.ys7.com:8089/api/cloud?method=download&amp;fid=20200608140811-C78047095-1-10002-2-1&amp;session=hik%24shipin7%231%23USK%23ra.b2cbdaj37yweo25l1qkxj5umdmjuull6-4jmcdddqjq-0iqr788-g8tfthy0r","fileIndex":"20200608140807-C78047095-1-10002-1-1","locked":null,"keyChecksum":"","storageType":null,"recordPath":null},{"deviceId":9,"brandId":2,"deviceSN":"C78047095","channelId":"1","recordId":"20200608140850-C78047095-1-10002-1-1","recordRegionId":null,"encryptMode":null,"crypt":13,"startTime":"2020-06-08 14:08:50","endTime":"2020-06-08 14:09:15","fileSize":"902652","coverPic":"https://tencentclouddownhb.ys7.com:8089/api/cloud?method=download&amp;fid=20200608140842-C78047095-1-10002-2-1&amp;session=hik%24shipin7%231%23USK%23ra.b2cbdaj37yweo25l1qkxj5umdmjuull6-4jmcdddqjq-0iqr788-g8tfthy0r","fileIndex":"20200608140850-C78047095-1-10002-1-1","locked":null,"keyChecksum":"","storageType":null,"recordPath":null},{"deviceId":9,"brandId":2,"deviceSN":"C78047095","channelId":"1","recordId":"20200608140918-C78047095-1-10002-1-1","recordRegionId":null,"encryptMode":null,"crypt":13,"startTime":"2020-06-08 14:09:17","endTime":"2020-06-08 14:09:44","fileSize":"1561936","coverPic":"https://tencentclouddownhb.ys7.com:8089/api/cloud?method=download&amp;fid=20200608140918-C78047095-1-10002-2-1&amp;session=hik%24shipin7%231%23USK%23ra.b2cbdaj37yweo25l1qkxj5umdmjuull6-4jmcdddqjq-0iqr788-g8tfthy0r","fileIndex":"20200608140918-C78047095-1-10002-1-1","locked":null,"keyChecksum":"","storageType":null,"recordPath":null}]
     * time : 2020-06-08 15:10:02
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
         * recordId : 20200608140028-C78047095-1-10002-1-1
         * recordRegionId : null
         * encryptMode : null
         * crypt : 13
         * startTime : 2020-06-08 14:00:22
         * endTime : 2020-06-08 14:02:03
         * fileSize : 6132744
         * coverPic : https://tencentclouddownhb.ys7.com:8089/api/cloud?method=download&amp;fid=20200608140028-C78047095-1-10002-1-1&amp;fileType=1&amp;startTime=1591596022000&amp;storageVersion=1&amp;session=hik%24shipin7%231%23USK%23ra.b2cbdaj37yweo25l1qkxj5umdmjuull6-4jmcdddqjq-0iqr788-g8tfthy0r
         * fileIndex : 20200608140028-C78047095-1-10002-1-1
         * locked : null
         * keyChecksum :
         * storageType : null
         * recordPath : null
         */

        private int deviceId;
        private int brandId;
        private String deviceSN;
        private String channelId;
        private String recordId;
        private Object recordRegionId;
        private Object encryptMode;
        private int crypt;
        private String startTime;
        private String endTime;
        private String fileSize;
        private String coverPic;
        private String fileIndex;
        private Object locked;
        private String keyChecksum;
        private Object storageType;
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

        public String getCoverPic() {
            return coverPic;
        }

        public void setCoverPic(String coverPic) {
            this.coverPic = coverPic;
        }

        public String getFileIndex() {
            return fileIndex;
        }

        public void setFileIndex(String fileIndex) {
            this.fileIndex = fileIndex;
        }

        public Object getLocked() {
            return locked;
        }

        public void setLocked(Object locked) {
            this.locked = locked;
        }

        public String getKeyChecksum() {
            return keyChecksum;
        }

        public void setKeyChecksum(String keyChecksum) {
            this.keyChecksum = keyChecksum;
        }

        public Object getStorageType() {
            return storageType;
        }

        public void setStorageType(Object storageType) {
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
