package com.jiufang.wsyapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/6/18.
 */

public class GetDeviceAlarmYsPageBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"deviceId":27,"snCode":"C78047095","totals":12,"pageIndex":1,"pageSize":10,"alrams":[{"alarmId":"KBKQLDTB7PU_0N9DKZK01_C78047095_1","alarmName":"C1HC(C78047095)","alarmType":10002,"alarmTime":"2020-06-18 20:04:38","channelNo":1,"isEncrypt":0,"isChecked":0,"recState":5,"preTime":5,"delayTime":25,"alarmPicUrl":"https://alipicdeco.ys7.com:8089/ezviz/pic/down?url=aHR0cHM6Ly9jb3MuYXAtYmVpamluZy5teXFjbG91ZC5jb20vY2xvdWQtaGJiai1iaWctMTI1NjY4MzA0MS83L0M3ODA0NzA5NV8xXzIwMjAwNjE4MjAwNDM5LUM3ODA0NzA5NS0xLTEwMDAyLTItMT9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1EYXRlPTIwMjAwNjE4VDEyNDUyN1omWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JlgtQW16LUV4cGlyZXM9NjA0Nzk5JlgtQW16LUNyZWRlbnRpYWw9QUtJRHhFSW80MjFBWGFHbTZCTlhqR1lTTHh3dGM1UEFEejhCJTJGMjAyMDA2MTglMkZjb3MuYXAtYmVpamluZyUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LVNpZ25hdHVyZT05M2E0Nzk5ZTAwZTY4MDNlNmFiMzg4MGM5ODFmZTZmOWQ3NTU2YTJmMjg4MjUyNGJhZWZkNzllOGNhOGVlOGQ4&amp;crypt=2&amp;time=2020-06-18T20:04:38&amp;key=cde837bd509a88fec10a7ed208509cb2","relationAlarms":[]},{"alarmId":"KBKQK3107PU_0N9DKZK00_C78047095_1","alarmName":"C1HC(C78047095)","alarmType":10002,"alarmTime":"2020-06-18 20:03:38","channelNo":1,"isEncrypt":0,"isChecked":0,"recState":5,"preTime":5,"delayTime":25,"alarmPicUrl":"https://alipicdeco.ys7.com:8089/ezviz/pic/down?url=aHR0cHM6Ly9jb3MuYXAtYmVpamluZy5teXFjbG91ZC5jb20vY2xvdWQtaGJiai1iaWctMTI1NjY4MzA0MS83L0M3ODA0NzA5NV8xXzIwMjAwNjE4MjAwMzM4LUM3ODA0NzA5NS0xLTEwMDAyLTItMT9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1EYXRlPTIwMjAwNjE4VDEyNDUyN1omWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JlgtQW16LUV4cGlyZXM9NjA0ODAwJlgtQW16LUNyZWRlbnRpYWw9QUtJRHhFSW80MjFBWGFHbTZCTlhqR1lTTHh3dGM1UEFEejhCJTJGMjAyMDA2MTglMkZjb3MuYXAtYmVpamluZyUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LVNpZ25hdHVyZT02YTJkZTIyODkyYzYyNGMxNmIyNDg2YjUyOTVmMWFhMDVkNTE3NTM0ZTEwMzA0M2Q5YjU1YjkwYWY0NTlmNjMw&amp;crypt=2&amp;time=2020-06-18T20:03:38&amp;key=cde837bd509a88fec10a7ed208509cb2","relationAlarms":[]}]}
     * time : 2020-06-18 20:45:27
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
         * deviceId : 27
         * snCode : C78047095
         * totals : 12
         * pageIndex : 1
         * pageSize : 10
         * alrams : [{"alarmId":"KBKQLDTB7PU_0N9DKZK01_C78047095_1","alarmName":"C1HC(C78047095)","alarmType":10002,"alarmTime":"2020-06-18 20:04:38","channelNo":1,"isEncrypt":0,"isChecked":0,"recState":5,"preTime":5,"delayTime":25,"alarmPicUrl":"https://alipicdeco.ys7.com:8089/ezviz/pic/down?url=aHR0cHM6Ly9jb3MuYXAtYmVpamluZy5teXFjbG91ZC5jb20vY2xvdWQtaGJiai1iaWctMTI1NjY4MzA0MS83L0M3ODA0NzA5NV8xXzIwMjAwNjE4MjAwNDM5LUM3ODA0NzA5NS0xLTEwMDAyLTItMT9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1EYXRlPTIwMjAwNjE4VDEyNDUyN1omWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JlgtQW16LUV4cGlyZXM9NjA0Nzk5JlgtQW16LUNyZWRlbnRpYWw9QUtJRHhFSW80MjFBWGFHbTZCTlhqR1lTTHh3dGM1UEFEejhCJTJGMjAyMDA2MTglMkZjb3MuYXAtYmVpamluZyUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LVNpZ25hdHVyZT05M2E0Nzk5ZTAwZTY4MDNlNmFiMzg4MGM5ODFmZTZmOWQ3NTU2YTJmMjg4MjUyNGJhZWZkNzllOGNhOGVlOGQ4&amp;crypt=2&amp;time=2020-06-18T20:04:38&amp;key=cde837bd509a88fec10a7ed208509cb2","relationAlarms":[]},{"alarmId":"KBKQK3107PU_0N9DKZK00_C78047095_1","alarmName":"C1HC(C78047095)","alarmType":10002,"alarmTime":"2020-06-18 20:03:38","channelNo":1,"isEncrypt":0,"isChecked":0,"recState":5,"preTime":5,"delayTime":25,"alarmPicUrl":"https://alipicdeco.ys7.com:8089/ezviz/pic/down?url=aHR0cHM6Ly9jb3MuYXAtYmVpamluZy5teXFjbG91ZC5jb20vY2xvdWQtaGJiai1iaWctMTI1NjY4MzA0MS83L0M3ODA0NzA5NV8xXzIwMjAwNjE4MjAwMzM4LUM3ODA0NzA5NS0xLTEwMDAyLTItMT9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1EYXRlPTIwMjAwNjE4VDEyNDUyN1omWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JlgtQW16LUV4cGlyZXM9NjA0ODAwJlgtQW16LUNyZWRlbnRpYWw9QUtJRHhFSW80MjFBWGFHbTZCTlhqR1lTTHh3dGM1UEFEejhCJTJGMjAyMDA2MTglMkZjb3MuYXAtYmVpamluZyUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LVNpZ25hdHVyZT02YTJkZTIyODkyYzYyNGMxNmIyNDg2YjUyOTVmMWFhMDVkNTE3NTM0ZTEwMzA0M2Q5YjU1YjkwYWY0NTlmNjMw&amp;crypt=2&amp;time=2020-06-18T20:03:38&amp;key=cde837bd509a88fec10a7ed208509cb2","relationAlarms":[]}]
         */

        private int deviceId;
        private String snCode;
        private int totals;
        private int pageIndex;
        private int pageSize;
        private List<AlramsBean> alrams;

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

        public int getTotals() {
            return totals;
        }

        public void setTotals(int totals) {
            this.totals = totals;
        }

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public List<AlramsBean> getAlrams() {
            return alrams;
        }

        public void setAlrams(List<AlramsBean> alrams) {
            this.alrams = alrams;
        }

        public static class AlramsBean {
            /**
             * alarmId : KBKQLDTB7PU_0N9DKZK01_C78047095_1
             * alarmName : C1HC(C78047095)
             * alarmType : 10002
             * alarmTime : 2020-06-18 20:04:38
             * channelNo : 1
             * isEncrypt : 0
             * isChecked : 0
             * recState : 5
             * preTime : 5
             * delayTime : 25
             * alarmPicUrl : https://alipicdeco.ys7.com:8089/ezviz/pic/down?url=aHR0cHM6Ly9jb3MuYXAtYmVpamluZy5teXFjbG91ZC5jb20vY2xvdWQtaGJiai1iaWctMTI1NjY4MzA0MS83L0M3ODA0NzA5NV8xXzIwMjAwNjE4MjAwNDM5LUM3ODA0NzA5NS0xLTEwMDAyLTItMT9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1EYXRlPTIwMjAwNjE4VDEyNDUyN1omWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JlgtQW16LUV4cGlyZXM9NjA0Nzk5JlgtQW16LUNyZWRlbnRpYWw9QUtJRHhFSW80MjFBWGFHbTZCTlhqR1lTTHh3dGM1UEFEejhCJTJGMjAyMDA2MTglMkZjb3MuYXAtYmVpamluZyUyRnMzJTJGYXdzNF9yZXF1ZXN0JlgtQW16LVNpZ25hdHVyZT05M2E0Nzk5ZTAwZTY4MDNlNmFiMzg4MGM5ODFmZTZmOWQ3NTU2YTJmMjg4MjUyNGJhZWZkNzllOGNhOGVlOGQ4&amp;crypt=2&amp;time=2020-06-18T20:04:38&amp;key=cde837bd509a88fec10a7ed208509cb2
             * relationAlarms : []
             */

            private String alarmId;
            private String alarmName;
            private int alarmType;
            private String alarmTime;
            private int channelNo;
            private int isEncrypt;
            private int isChecked;
            private int recState;
            private int preTime;
            private int delayTime;
            private String alarmPicUrl;
            private List<?> relationAlarms;

            public String getAlarmId() {
                return alarmId;
            }

            public void setAlarmId(String alarmId) {
                this.alarmId = alarmId;
            }

            public String getAlarmName() {
                return alarmName;
            }

            public void setAlarmName(String alarmName) {
                this.alarmName = alarmName;
            }

            public int getAlarmType() {
                return alarmType;
            }

            public void setAlarmType(int alarmType) {
                this.alarmType = alarmType;
            }

            public String getAlarmTime() {
                return alarmTime;
            }

            public void setAlarmTime(String alarmTime) {
                this.alarmTime = alarmTime;
            }

            public int getChannelNo() {
                return channelNo;
            }

            public void setChannelNo(int channelNo) {
                this.channelNo = channelNo;
            }

            public int getIsEncrypt() {
                return isEncrypt;
            }

            public void setIsEncrypt(int isEncrypt) {
                this.isEncrypt = isEncrypt;
            }

            public int getIsChecked() {
                return isChecked;
            }

            public void setIsChecked(int isChecked) {
                this.isChecked = isChecked;
            }

            public int getRecState() {
                return recState;
            }

            public void setRecState(int recState) {
                this.recState = recState;
            }

            public int getPreTime() {
                return preTime;
            }

            public void setPreTime(int preTime) {
                this.preTime = preTime;
            }

            public int getDelayTime() {
                return delayTime;
            }

            public void setDelayTime(int delayTime) {
                this.delayTime = delayTime;
            }

            public String getAlarmPicUrl() {
                return alarmPicUrl;
            }

            public void setAlarmPicUrl(String alarmPicUrl) {
                this.alarmPicUrl = alarmPicUrl;
            }

            public List<?> getRelationAlarms() {
                return relationAlarms;
            }

            public void setRelationAlarms(List<?> relationAlarms) {
                this.relationAlarms = relationAlarms;
            }
        }
    }
}
