package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/6/30.
 */

public class GetYsDeviceCapabilityBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"monitoringStatus":1,"trackingStatus":-1,"intelligentCheckStatus":0,"sensitivity":"3","detectPeriod":"0,1,2,3,4,5,6","scheduleStartTime":null,"scheduleEndTime":null,"alarmSoundMode":"0"}
     * time : 2020-06-30 16:10:06
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
         * monitoringStatus : 1
         * trackingStatus : -1
         * intelligentCheckStatus : 0
         * sensitivity : 3
         * detectPeriod : 0,1,2,3,4,5,6
         * scheduleStartTime : null
         * scheduleEndTime : null
         * alarmSoundMode : 0
         */

        private int monitoringStatus;
        private int trackingStatus;
        private int intelligentCheckStatus;
        private String sensitivity;
        private String detectPeriod;
        private String scheduleStartTime;
        private String scheduleEndTime;
        private String alarmSoundMode;

        public int getMonitoringStatus() {
            return monitoringStatus;
        }

        public void setMonitoringStatus(int monitoringStatus) {
            this.monitoringStatus = monitoringStatus;
        }

        public int getTrackingStatus() {
            return trackingStatus;
        }

        public void setTrackingStatus(int trackingStatus) {
            this.trackingStatus = trackingStatus;
        }

        public int getIntelligentCheckStatus() {
            return intelligentCheckStatus;
        }

        public void setIntelligentCheckStatus(int intelligentCheckStatus) {
            this.intelligentCheckStatus = intelligentCheckStatus;
        }

        public String getSensitivity() {
            return sensitivity;
        }

        public void setSensitivity(String sensitivity) {
            this.sensitivity = sensitivity;
        }

        public String getDetectPeriod() {
            return detectPeriod;
        }

        public void setDetectPeriod(String detectPeriod) {
            this.detectPeriod = detectPeriod;
        }

        public String getScheduleStartTime() {
            return scheduleStartTime;
        }

        public void setScheduleStartTime(String scheduleStartTime) {
            this.scheduleStartTime = scheduleStartTime;
        }

        public String getScheduleEndTime() {
            return scheduleEndTime;
        }

        public void setScheduleEndTime(String scheduleEndTime) {
            this.scheduleEndTime = scheduleEndTime;
        }

        public String getAlarmSoundMode() {
            return alarmSoundMode;
        }

        public void setAlarmSoundMode(String alarmSoundMode) {
            this.alarmSoundMode = alarmSoundMode;
        }
    }
}
