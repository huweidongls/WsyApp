package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/7/2.
 */

public class GetLcDeviceMoreCapabilityBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"infraredLightStatus":1,"breathingLampStatus":1,"soundsStatus":0,"reverseStatus":0}
     * time : 2020-07-02 09:32:13
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
         * infraredLightStatus : 1
         * breathingLampStatus : 1
         * soundsStatus : 0
         * reverseStatus : 0
         */

        private int infraredLightStatus;
        private int breathingLampStatus;
        private int soundsStatus;
        private int reverseStatus;

        public int getInfraredLightStatus() {
            return infraredLightStatus;
        }

        public void setInfraredLightStatus(int infraredLightStatus) {
            this.infraredLightStatus = infraredLightStatus;
        }

        public int getBreathingLampStatus() {
            return breathingLampStatus;
        }

        public void setBreathingLampStatus(int breathingLampStatus) {
            this.breathingLampStatus = breathingLampStatus;
        }

        public int getSoundsStatus() {
            return soundsStatus;
        }

        public void setSoundsStatus(int soundsStatus) {
            this.soundsStatus = soundsStatus;
        }

        public int getReverseStatus() {
            return reverseStatus;
        }

        public void setReverseStatus(int reverseStatus) {
            this.reverseStatus = reverseStatus;
        }
    }
}
