package com.jiufang.wsyapp.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/5/20.
 */

public class GetBindDeviceDetailBean implements Serializable {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"deviceId":1,"userId":2,"brandId":1,"productCategoryId":1,"productCategoryName":"摄像机","deviceName":"萤石带云台","deviceModal":null,"deviceAccessToken":"Ut_0000c76b0e76d8334a18a5849f815641","haveCloudConsole":1,"productImage":"202005022100276117296.png","snapImage":null,"snCode":"5K05D84PAU69A7C","securityCode":"L25C2542","channelId":"0","bateMode":1,"isOpt":true,"deviceStatus":1,"cloudStorageStatus":null,"audioTalk":1,"deviceAbilitySet":"WLAN,MT,HSEncrypt,CloudStorage,LocalStorage,PlaybackByFilename,BreathingLight,RD,CK,LocalRecord,XUpgrade,Auth,ModifyPassword,LocalStorageEnable,RTSV1,PBSV1,ESV1,PlaySound,Reboot,InfraredLight,LinkDevAlarm,AbAlarmSound,SCCode,AlarmMD,PT,AudioEncodeControlV2,FrameReverse,MDW,MDS,HeaderDetect,SmartTrack,CloseCamera,CheckAbDecible,PT1,AudioTalk","channelAbilitySet":""}
     * time : 2020-06-04 14:08:52
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

    public static class DataBean implements Serializable {
        /**
         * deviceId : 1
         * userId : 2
         * brandId : 1
         * productCategoryId : 1
         * productCategoryName : 摄像机
         * deviceName : 萤石带云台
         * deviceModal : null
         * deviceAccessToken : Ut_0000c76b0e76d8334a18a5849f815641
         * haveCloudConsole : 1
         * productImage : 202005022100276117296.png
         * snapImage : null
         * snCode : 5K05D84PAU69A7C
         * securityCode : L25C2542
         * channelId : 0
         * bateMode : 1
         * isOpt : true
         * deviceStatus : 1
         * cloudStorageStatus : null
         * audioTalk : 1
         * deviceAbilitySet : WLAN,MT,HSEncrypt,CloudStorage,LocalStorage,PlaybackByFilename,BreathingLight,RD,CK,LocalRecord,XUpgrade,Auth,ModifyPassword,LocalStorageEnable,RTSV1,PBSV1,ESV1,PlaySound,Reboot,InfraredLight,LinkDevAlarm,AbAlarmSound,SCCode,AlarmMD,PT,AudioEncodeControlV2,FrameReverse,MDW,MDS,HeaderDetect,SmartTrack,CloseCamera,CheckAbDecible,PT1,AudioTalk
         * channelAbilitySet :
         */

        private int deviceId;
        private int userId;
        private int brandId;
        private int productCategoryId;
        private String productCategoryName;
        private String deviceName;
        private String deviceModal;
        private String deviceAccessToken;
        private int haveCloudConsole;
        private String productImage;
        private Object snapImage;
        private String snCode;
        private String securityCode;
        private String channelId;
        private int bateMode;
        private boolean isOpt;
        private int deviceStatus;
        private Object cloudStorageStatus;
        private int audioTalk;
        private String deviceAbilitySet;
        private String channelAbilitySet;

        public int getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(int deviceId) {
            this.deviceId = deviceId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public int getProductCategoryId() {
            return productCategoryId;
        }

        public void setProductCategoryId(int productCategoryId) {
            this.productCategoryId = productCategoryId;
        }

        public String getProductCategoryName() {
            return productCategoryName;
        }

        public void setProductCategoryName(String productCategoryName) {
            this.productCategoryName = productCategoryName;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceModal() {
            return deviceModal;
        }

        public void setDeviceModal(String deviceModal) {
            this.deviceModal = deviceModal;
        }

        public String getDeviceAccessToken() {
            return deviceAccessToken;
        }

        public void setDeviceAccessToken(String deviceAccessToken) {
            this.deviceAccessToken = deviceAccessToken;
        }

        public int getHaveCloudConsole() {
            return haveCloudConsole;
        }

        public void setHaveCloudConsole(int haveCloudConsole) {
            this.haveCloudConsole = haveCloudConsole;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public Object getSnapImage() {
            return snapImage;
        }

        public void setSnapImage(Object snapImage) {
            this.snapImage = snapImage;
        }

        public String getSnCode() {
            return snCode;
        }

        public void setSnCode(String snCode) {
            this.snCode = snCode;
        }

        public String getSecurityCode() {
            return securityCode;
        }

        public void setSecurityCode(String securityCode) {
            this.securityCode = securityCode;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public int getBateMode() {
            return bateMode;
        }

        public void setBateMode(int bateMode) {
            this.bateMode = bateMode;
        }

        public boolean isIsOpt() {
            return isOpt;
        }

        public void setIsOpt(boolean isOpt) {
            this.isOpt = isOpt;
        }

        public int getDeviceStatus() {
            return deviceStatus;
        }

        public void setDeviceStatus(int deviceStatus) {
            this.deviceStatus = deviceStatus;
        }

        public Object getCloudStorageStatus() {
            return cloudStorageStatus;
        }

        public void setCloudStorageStatus(Object cloudStorageStatus) {
            this.cloudStorageStatus = cloudStorageStatus;
        }

        public int getAudioTalk() {
            return audioTalk;
        }

        public void setAudioTalk(int audioTalk) {
            this.audioTalk = audioTalk;
        }

        public String getDeviceAbilitySet() {
            return deviceAbilitySet;
        }

        public void setDeviceAbilitySet(String deviceAbilitySet) {
            this.deviceAbilitySet = deviceAbilitySet;
        }

        public String getChannelAbilitySet() {
            return channelAbilitySet;
        }

        public void setChannelAbilitySet(String channelAbilitySet) {
            this.channelAbilitySet = channelAbilitySet;
        }
    }
}
