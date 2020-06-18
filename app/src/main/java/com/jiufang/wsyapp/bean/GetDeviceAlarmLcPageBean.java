package com.jiufang.wsyapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/6/18.
 */

public class GetDeviceAlarmLcPageBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"deviceId":16,"snCode":"5K05D84PAU69A7C","count":2,"alarms":[{"token":"e8567848da6340eca0084a1dfaee7d81","time":"1970-01-19 18:20:36","channelId":"0","name":"5K05D84PAU69A7C","alarmId":1268239872039024,"localDate":"2020-06-18 07:30:39","type":10022,"picurlArray":["https://lechangecloud-paas-sz-private-alarmpic.oss-cn-shenzhen.aliyuncs.com/lechange/5K05D84PAU69A7C_img/Alarm/0/e8567848da6340eca0084a1dfaee7d81_0.jpg?Expires=1592545590&amp;OSSAccessKeyId=LTAIawgVtEFFLN9m&amp;Signature=3hGwEd4Jjzeju8DhLNsdYA9bQZM%3D"],"thumbUrl":"https://lechangecloud-paas-sz-private-alarmpic.oss-cn-shenzhen.aliyuncs.com/lechange/5K05D84PAU69A7C_img/Alarm/0/e8567848da6340eca0084a1dfaee7d81_0_thumb_qcif.dav?Expires=1592545590&amp;OSSAccessKeyId=LTAIawgVtEFFLN9m&amp;Signature=FRPCAIHzTfCKU8w60yvQF5cxSdw%3D"},{"token":"ecdd05e40d3d4be888455077b64881b8","time":"1970-01-19 18:20:36","channelId":"0","name":"5K05D84PAU69A7C","alarmId":1268239738898544,"localDate":"2020-06-18 07:30:06","type":10022,"picurlArray":["https://lechangecloud-paas-sz-private-alarmpic.oss-cn-shenzhen.aliyuncs.com/lechange/5K05D84PAU69A7C_img/Alarm/0/ecdd05e40d3d4be888455077b64881b8_0.jpg?Expires=1592545590&amp;OSSAccessKeyId=LTAIawgVtEFFLN9m&amp;Signature=WtDBNSvLpfBWd3vCU9eANIpQ5QY%3D"],"thumbUrl":"https://lechangecloud-paas-sz-private-alarmpic.oss-cn-shenzhen.aliyuncs.com/lechange/5K05D84PAU69A7C_img/Alarm/0/ecdd05e40d3d4be888455077b64881b8_0_thumb_qcif.dav?Expires=1592545590&amp;OSSAccessKeyId=LTAIawgVtEFFLN9m&amp;Signature=laMEyJjljHBemR8%2BA27qQrAn3iw%3D"}],"nextAlarmId":"1268239738898544"}
     * time : 2020-06-18 13:46:30
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
         * deviceId : 16
         * snCode : 5K05D84PAU69A7C
         * count : 2
         * alarms : [{"token":"e8567848da6340eca0084a1dfaee7d81","time":"1970-01-19 18:20:36","channelId":"0","name":"5K05D84PAU69A7C","alarmId":1268239872039024,"localDate":"2020-06-18 07:30:39","type":10022,"picurlArray":["https://lechangecloud-paas-sz-private-alarmpic.oss-cn-shenzhen.aliyuncs.com/lechange/5K05D84PAU69A7C_img/Alarm/0/e8567848da6340eca0084a1dfaee7d81_0.jpg?Expires=1592545590&amp;OSSAccessKeyId=LTAIawgVtEFFLN9m&amp;Signature=3hGwEd4Jjzeju8DhLNsdYA9bQZM%3D"],"thumbUrl":"https://lechangecloud-paas-sz-private-alarmpic.oss-cn-shenzhen.aliyuncs.com/lechange/5K05D84PAU69A7C_img/Alarm/0/e8567848da6340eca0084a1dfaee7d81_0_thumb_qcif.dav?Expires=1592545590&amp;OSSAccessKeyId=LTAIawgVtEFFLN9m&amp;Signature=FRPCAIHzTfCKU8w60yvQF5cxSdw%3D"},{"token":"ecdd05e40d3d4be888455077b64881b8","time":"1970-01-19 18:20:36","channelId":"0","name":"5K05D84PAU69A7C","alarmId":1268239738898544,"localDate":"2020-06-18 07:30:06","type":10022,"picurlArray":["https://lechangecloud-paas-sz-private-alarmpic.oss-cn-shenzhen.aliyuncs.com/lechange/5K05D84PAU69A7C_img/Alarm/0/ecdd05e40d3d4be888455077b64881b8_0.jpg?Expires=1592545590&amp;OSSAccessKeyId=LTAIawgVtEFFLN9m&amp;Signature=WtDBNSvLpfBWd3vCU9eANIpQ5QY%3D"],"thumbUrl":"https://lechangecloud-paas-sz-private-alarmpic.oss-cn-shenzhen.aliyuncs.com/lechange/5K05D84PAU69A7C_img/Alarm/0/ecdd05e40d3d4be888455077b64881b8_0_thumb_qcif.dav?Expires=1592545590&amp;OSSAccessKeyId=LTAIawgVtEFFLN9m&amp;Signature=laMEyJjljHBemR8%2BA27qQrAn3iw%3D"}]
         * nextAlarmId : 1268239738898544
         */

        private int deviceId;
        private String snCode;
        private int count;
        private String nextAlarmId;
        private List<AlarmsBean> alarms;

        public int getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(int deviceId) {
            this.deviceId = deviceId;
        }

        public String getSnCode() {
            return snCode;
        }

        public void setSnCode(String snCode) {
            this.snCode = snCode;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getNextAlarmId() {
            return nextAlarmId;
        }

        public void setNextAlarmId(String nextAlarmId) {
            this.nextAlarmId = nextAlarmId;
        }

        public List<AlarmsBean> getAlarms() {
            return alarms;
        }

        public void setAlarms(List<AlarmsBean> alarms) {
            this.alarms = alarms;
        }

        public static class AlarmsBean {
            /**
             * token : e8567848da6340eca0084a1dfaee7d81
             * time : 1970-01-19 18:20:36
             * channelId : 0
             * name : 5K05D84PAU69A7C
             * alarmId : 1268239872039024
             * localDate : 2020-06-18 07:30:39
             * type : 10022
             * picurlArray : ["https://lechangecloud-paas-sz-private-alarmpic.oss-cn-shenzhen.aliyuncs.com/lechange/5K05D84PAU69A7C_img/Alarm/0/e8567848da6340eca0084a1dfaee7d81_0.jpg?Expires=1592545590&amp;OSSAccessKeyId=LTAIawgVtEFFLN9m&amp;Signature=3hGwEd4Jjzeju8DhLNsdYA9bQZM%3D"]
             * thumbUrl : https://lechangecloud-paas-sz-private-alarmpic.oss-cn-shenzhen.aliyuncs.com/lechange/5K05D84PAU69A7C_img/Alarm/0/e8567848da6340eca0084a1dfaee7d81_0_thumb_qcif.dav?Expires=1592545590&amp;OSSAccessKeyId=LTAIawgVtEFFLN9m&amp;Signature=FRPCAIHzTfCKU8w60yvQF5cxSdw%3D
             */

            private String token;
            private String time;
            private String channelId;
            private String name;
            private long alarmId;
            private String localDate;
            private int type;
            private String thumbUrl;
            private List<String> picurlArray;

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getChannelId() {
                return channelId;
            }

            public void setChannelId(String channelId) {
                this.channelId = channelId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getAlarmId() {
                return alarmId;
            }

            public void setAlarmId(long alarmId) {
                this.alarmId = alarmId;
            }

            public String getLocalDate() {
                return localDate;
            }

            public void setLocalDate(String localDate) {
                this.localDate = localDate;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getThumbUrl() {
                return thumbUrl;
            }

            public void setThumbUrl(String thumbUrl) {
                this.thumbUrl = thumbUrl;
            }

            public List<String> getPicurlArray() {
                return picurlArray;
            }

            public void setPicurlArray(List<String> picurlArray) {
                this.picurlArray = picurlArray;
            }
        }
    }
}
