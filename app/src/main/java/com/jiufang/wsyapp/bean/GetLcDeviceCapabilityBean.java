package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/7/1.
 */

public class GetLcDeviceCapabilityBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"monitoringStatus":1,"faceDetect":-1,"isHaveSmartTrack":1,"smartTrackStatus":1,"sensitivity":3,"detectPeriod":"Sunday","scheduleStartTime":"00:00:00","scheduleEndTime":"23:59:59","alarmSoundStatus":1,"alarmSound":"70"}
     * time : 2020-07-01 11:03:38
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
         * faceDetect : -1
         * isHaveSmartTrack : 1
         * smartTrackStatus : 1
         * sensitivity : 3
         * detectPeriod : Sunday
         * scheduleStartTime : 00:00:00
         * scheduleEndTime : 23:59:59
         * alarmSoundStatus : 1
         * alarmSound : 70
         */

        private int monitoringStatus;
        private int faceDetect;
        private int isHaveSmartTrack;
        private int smartTrackStatus;
        private int sensitivity;
        private String detectPeriod;
        private String scheduleStartTime;
        private String scheduleEndTime;
        private int alarmSoundStatus;
        private String alarmSound;

        public int getMonitoringStatus() {
            return monitoringStatus;
        }

        public void setMonitoringStatus(int monitoringStatus) {
            this.monitoringStatus = monitoringStatus;
        }

        public int getFaceDetect() {
            return faceDetect;
        }

        public void setFaceDetect(int faceDetect) {
            this.faceDetect = faceDetect;
        }

        public int getIsHaveSmartTrack() {
            return isHaveSmartTrack;
        }

        public void setIsHaveSmartTrack(int isHaveSmartTrack) {
            this.isHaveSmartTrack = isHaveSmartTrack;
        }

        public int getSmartTrackStatus() {
            return smartTrackStatus;
        }

        public void setSmartTrackStatus(int smartTrackStatus) {
            this.smartTrackStatus = smartTrackStatus;
        }

        public int getSensitivity() {
            return sensitivity;
        }

        public void setSensitivity(int sensitivity) {
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

        public int getAlarmSoundStatus() {
            return alarmSoundStatus;
        }

        public void setAlarmSoundStatus(int alarmSoundStatus) {
            this.alarmSoundStatus = alarmSoundStatus;
        }

        public String getAlarmSound() {
            return alarmSound;
        }

        public void setAlarmSound(String alarmSound) {
            this.alarmSound = alarmSound;
        }
    }
}
