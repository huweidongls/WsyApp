package com.jiufang.wsyapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2020/6/10.
 */

public class GetLcCloudStorageRecordListBean implements Serializable {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : [{"deviceId":7,"brandId":1,"deviceSN":"5K05D84PAU69A7C","channelId":"0","recordId":"64090237575","recordRegionId":"64090237575|5K05D84PAU69A7C|0|22|lechange/5K05D84PAU69A7C_record/0_20200610085843357_628105b5703a4d9a80ac672b8a9cd822.m3u","encryptMode":0,"crypt":null,"startTime":"2020-06-10 08:58:35","endTime":"2020-06-10 09:00:38","fileSize":"15530303","coverPic":"https://lechangecloud-paas-sz-private-alarmrecord.oss-cn-shenzhen.aliyuncs.com/lechange/5K05D84PAU69A7C_record/0_20200610085843357_628105b5703a4d9a80ac672b8a9cd822_thumb_qcif.dav?Expires=1591846302&amp;OSSAccessKeyId=LTAIawgVtEFFLN9m&amp;Signature=esFyUo19YX1Rcasipss5%2FzN1GpI%3D","fileIndex":null,"locked":null,"keyChecksum":null,"storageType":null,"recordPath":null},{"deviceId":7,"brandId":1,"deviceSN":"5K05D84PAU69A7C","channelId":"0","recordId":"64090137223","recordRegionId":"64090137223|5K05D84PAU69A7C|0|22|lechange/5K05D84PAU69A7C_record/0_20200610085703751_35017598c32346bcb953d905699235f8.m3u","encryptMode":0,"crypt":null,"startTime":"2020-06-10 08:56:57","endTime":"2020-06-10 08:57:35","fileSize":"5197749","coverPic":"https://lechangecloud-paas-sz-private-alarmrecord.oss-cn-shenzhen.aliyuncs.com/lechange/5K05D84PAU69A7C_record/0_20200610085703751_35017598c32346bcb953d905699235f8_thumb_qcif.dav?Expires=1591846302&amp;OSSAccessKeyId=LTAIawgVtEFFLN9m&amp;Signature=mfOB4zlj2w3UXclcVCX4KNJ8Tws%3D","fileIndex":null,"locked":null,"keyChecksum":null,"storageType":null,"recordPath":null}]
     * time : 2020-06-10 11:31:42
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
        @Override
        public String toString() {
            return "DataBean{" +
                    "deviceId=" + deviceId +
                    ", brandId=" + brandId +
                    ", deviceSN='" + deviceSN + '\'' +
                    ", channelId='" + channelId + '\'' +
                    ", recordId='" + recordId + '\'' +
                    ", recordRegionId='" + recordRegionId + '\'' +
                    ", encryptMode=" + encryptMode +
                    ", crypt=" + crypt +
                    ", startTime='" + startTime + '\'' +
                    ", endTime='" + endTime + '\'' +
                    ", fileSize='" + fileSize + '\'' +
                    ", coverPic='" + coverPic + '\'' +
                    ", fileIndex=" + fileIndex +
                    ", locked=" + locked +
                    ", keyChecksum=" + keyChecksum +
                    ", storageType=" + storageType +
                    ", recordPath=" + recordPath +
                    '}';
        }

        /**
         * deviceId : 7
         * brandId : 1
         * deviceSN : 5K05D84PAU69A7C
         * channelId : 0
         * recordId : 64090237575
         * recordRegionId : 64090237575|5K05D84PAU69A7C|0|22|lechange/5K05D84PAU69A7C_record/0_20200610085843357_628105b5703a4d9a80ac672b8a9cd822.m3u
         * encryptMode : 0
         * crypt : null
         * startTime : 2020-06-10 08:58:35
         * endTime : 2020-06-10 09:00:38
         * fileSize : 15530303
         * coverPic : https://lechangecloud-paas-sz-private-alarmrecord.oss-cn-shenzhen.aliyuncs.com/lechange/5K05D84PAU69A7C_record/0_20200610085843357_628105b5703a4d9a80ac672b8a9cd822_thumb_qcif.dav?Expires=1591846302&amp;OSSAccessKeyId=LTAIawgVtEFFLN9m&amp;Signature=esFyUo19YX1Rcasipss5%2FzN1GpI%3D
         * fileIndex : null
         * locked : null
         * keyChecksum : null
         * storageType : null
         * recordPath : null
         */

        private int deviceId;
        private int brandId;
        private String deviceSN;
        private String channelId;
        private String recordId;
        private String recordRegionId;
        private int encryptMode;
        private Object crypt;
        private String startTime;
        private String endTime;
        private String fileSize;
        private String coverPic;
        private Object fileIndex;
        private Object locked;
        private Object keyChecksum;
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

        public String getRecordRegionId() {
            return recordRegionId;
        }

        public void setRecordRegionId(String recordRegionId) {
            this.recordRegionId = recordRegionId;
        }

        public int getEncryptMode() {
            return encryptMode;
        }

        public void setEncryptMode(int encryptMode) {
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

        public String getCoverPic() {
            return coverPic;
        }

        public void setCoverPic(String coverPic) {
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
