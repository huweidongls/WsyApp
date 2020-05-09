package com.jiufang.wsyapp.bean;

/**
 * Created by Administrator on 2020/5/8.
 */

public class LoginByCaptchaBean {

    /**
     * code : 200
     * success : true
     * message : 操作成功
     * data : {"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhcHAiLCJ1c2VyUGhvbmUiOiIxODY4NjgxNzMxOSIsImlzcyI6Imh2Yy1hcGkiLCJleHAiOjE1ODkyNjk4ODksInVzZXJJZCI6MiwiaWF0IjoxNTg4OTA5ODg5LCJqdGkiOiJhMDMyZjk3MWQ5NTE0MDU3OTYxYWI2NDY1ZTA2NWM5YiJ9.TYsFQBRDM_g-fmn4cAcqntsb7TKh14rBkeoQPIkLO5w","userId":2,"tokenSalt":"666666"}
     * time : 2020-05-08 11:51:29
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
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhcHAiLCJ1c2VyUGhvbmUiOiIxODY4NjgxNzMxOSIsImlzcyI6Imh2Yy1hcGkiLCJleHAiOjE1ODkyNjk4ODksInVzZXJJZCI6MiwiaWF0IjoxNTg4OTA5ODg5LCJqdGkiOiJhMDMyZjk3MWQ5NTE0MDU3OTYxYWI2NDY1ZTA2NWM5YiJ9.TYsFQBRDM_g-fmn4cAcqntsb7TKh14rBkeoQPIkLO5w
         * userId : 2
         * tokenSalt : 666666
         */

        private String token;
        private int userId;
        private String tokenSalt;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getTokenSalt() {
            return tokenSalt;
        }

        public void setTokenSalt(String tokenSalt) {
            this.tokenSalt = tokenSalt;
        }
    }
}
