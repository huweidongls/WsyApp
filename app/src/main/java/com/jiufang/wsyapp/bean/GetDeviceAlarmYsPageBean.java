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
     * data : {"total":7,"totalPages":1,"records":[{"id":1,"brandId":2,"bindDeviceId":59,"deviceSn":"D97176623","recState":null,"alarmType":"intelligentDetection","alarmTime":"2021-01-29 11:27:01","alarmId":"ea928764-6224-11eb-8000-ca9aff9a779e","alarmPicId":"20210129112702-D97176623-1-10079-2-1","alarmPicUrl":"https://alipicdeco.ys7.com:8089/ezviz/pic/down?url=aHR0cHM6Ly9zMy1jbi1iai51ZmlsZW9zLmNvbS91Y2xvdWQtYmovNy9EOTcxNzY2MjNfMV8yMDIxMDEyOTExMjcwMi1EOTcxNzY2MjMtMS0xMDA3OS0yLTE/WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotRGF0ZT0yMDIxMDEyOVQwMzI3MDZaJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZYLUFtei1FeHBpcmVzPTYwNDc5OSZYLUFtei1DcmVkZW50aWFsPVRPS0VOXzdhYTFkNTUwLWI1MTgtNGRjOC1hNDgxLWEzMDhjY2U5YjQwMCUyRjIwMjEwMTI5JTJGY24tYmolMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1TaWduYXR1cmU9OTc3ZDI1YzY1ZTUxMGU0MTI0OWZmN2I0YjAxMTgzOWRjNGI1YjcyYTc4ZWJmMGE5MDM3N2YyMzVmMjY5ODhjNg==&amp;crypt=2&amp;time=2021-01-29T11:27:01&amp;key=95f5b6722e03628ecc6257e7b4fc95f1","isRead":0},{"id":2,"brandId":2,"bindDeviceId":59,"deviceSn":"D97176623","recState":null,"alarmType":"intelligentDetection","alarmTime":"2021-01-29 11:28:11","alarmId":"13e32c54-6225-11eb-8000-ca9aff9a779e","alarmPicId":"20210129112812-D97176623-1-10079-2-1","alarmPicUrl":"https://alipicdeco.ys7.com:8089/ezviz/pic/down?url=aHR0cHM6Ly9zMy1jbi1iai51ZmlsZW9zLmNvbS91Y2xvdWQtYmovNy9EOTcxNzY2MjNfMV8yMDIxMDEyOTExMjgxMi1EOTcxNzY2MjMtMS0xMDA3OS0yLTE/WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotRGF0ZT0yMDIxMDEyOVQwMzI4MTZaJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZYLUFtei1FeHBpcmVzPTYwNDgwMCZYLUFtei1DcmVkZW50aWFsPVRPS0VOXzdhYTFkNTUwLWI1MTgtNGRjOC1hNDgxLWEzMDhjY2U5YjQwMCUyRjIwMjEwMTI5JTJGY24tYmolMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1TaWduYXR1cmU9NmZmMGI5MmIzNTkzNWM3NjNiZWVhN2Y2MGEzY2VkOGM3NDRkNGEzZTY4ZmEwOTNlM2U5YjMyMDIxM2JkNTIxZQ==&amp;crypt=2&amp;time=2021-01-29T11:28:11&amp;key=95f5b6722e03628ecc6257e7b4fc95f1","isRead":0},{"id":3,"brandId":2,"bindDeviceId":59,"deviceSn":"D97176623","recState":null,"alarmType":"intelligentDetection","alarmTime":"2021-01-29 11:28:56","alarmId":"30677934-6225-11eb-8000-ca9aff9a779e","alarmPicId":"20210129112857-D97176623-1-10079-2-1","alarmPicUrl":"https://alipicdeco.ys7.com:8089/ezviz/pic/down?url=aHR0cHM6Ly9zMy1jbi1iai51ZmlsZW9zLmNvbS91Y2xvdWQtYmovNy9EOTcxNzY2MjNfMV8yMDIxMDEyOTExMjg1Ny1EOTcxNzY2MjMtMS0xMDA3OS0yLTE/WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotRGF0ZT0yMDIxMDEyOVQwMzI5MDNaJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZYLUFtei1FeHBpcmVzPTYwNDgwMCZYLUFtei1DcmVkZW50aWFsPVRPS0VOXzdhYTFkNTUwLWI1MTgtNGRjOC1hNDgxLWEzMDhjY2U5YjQwMCUyRjIwMjEwMTI5JTJGY24tYmolMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1TaWduYXR1cmU9ZGQzMTMwZWM0MzkwN2Q2NjhjZDJlZjVkODllOTY3MzU5NTBlYzM4ZWNmYmZjZWJjNjAxM2JmMWYwMzA3MTQyZA==&amp;crypt=2&amp;time=2021-01-29T11:28:56&amp;key=95f5b6722e03628ecc6257e7b4fc95f1","isRead":0},{"id":4,"brandId":2,"bindDeviceId":59,"deviceSn":"D97176623","recState":null,"alarmType":"intelligentDetection","alarmTime":"2021-01-29 11:29:26","alarmId":"410afe00-6225-11eb-8000-ca9aff9a779e","alarmPicId":"20210129112926-D97176623-1-10079-2-1","alarmPicUrl":"https://alipicdeco.ys7.com:8089/ezviz/pic/down?url=aHR0cHM6Ly9zMy1jbi1iai51ZmlsZW9zLmNvbS91Y2xvdWQtYmovNy9EOTcxNzY2MjNfMV8yMDIxMDEyOTExMjkyNi1EOTcxNzY2MjMtMS0xMDA3OS0yLTE/WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotRGF0ZT0yMDIxMDEyOVQwMzI5MzFaJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZYLUFtei1FeHBpcmVzPTYwNDgwMCZYLUFtei1DcmVkZW50aWFsPVRPS0VOXzdhYTFkNTUwLWI1MTgtNGRjOC1hNDgxLWEzMDhjY2U5YjQwMCUyRjIwMjEwMTI5JTJGY24tYmolMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1TaWduYXR1cmU9MDk5NDdiMjY1YjhjOTIxN2Y3MzM2OGI4Yjc3MWQ0ZmU1N2ExMjc3MWY4MWQ3MTE3MWQyY2RmMmNkZGE4ZDhjMw==&amp;crypt=2&amp;time=2021-01-29T11:29:26&amp;key=95f5b6722e03628ecc6257e7b4fc95f1","isRead":0}]}
     */

    private int code;
    private boolean success;
    private String message;
    private DataBean data;

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

    public static class DataBean {
        /**
         * total : 7
         * totalPages : 1
         * records : [{"id":1,"brandId":2,"bindDeviceId":59,"deviceSn":"D97176623","recState":null,"alarmType":"intelligentDetection","alarmTime":"2021-01-29 11:27:01","alarmId":"ea928764-6224-11eb-8000-ca9aff9a779e","alarmPicId":"20210129112702-D97176623-1-10079-2-1","alarmPicUrl":"https://alipicdeco.ys7.com:8089/ezviz/pic/down?url=aHR0cHM6Ly9zMy1jbi1iai51ZmlsZW9zLmNvbS91Y2xvdWQtYmovNy9EOTcxNzY2MjNfMV8yMDIxMDEyOTExMjcwMi1EOTcxNzY2MjMtMS0xMDA3OS0yLTE/WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotRGF0ZT0yMDIxMDEyOVQwMzI3MDZaJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZYLUFtei1FeHBpcmVzPTYwNDc5OSZYLUFtei1DcmVkZW50aWFsPVRPS0VOXzdhYTFkNTUwLWI1MTgtNGRjOC1hNDgxLWEzMDhjY2U5YjQwMCUyRjIwMjEwMTI5JTJGY24tYmolMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1TaWduYXR1cmU9OTc3ZDI1YzY1ZTUxMGU0MTI0OWZmN2I0YjAxMTgzOWRjNGI1YjcyYTc4ZWJmMGE5MDM3N2YyMzVmMjY5ODhjNg==&amp;crypt=2&amp;time=2021-01-29T11:27:01&amp;key=95f5b6722e03628ecc6257e7b4fc95f1","isRead":0},{"id":2,"brandId":2,"bindDeviceId":59,"deviceSn":"D97176623","recState":null,"alarmType":"intelligentDetection","alarmTime":"2021-01-29 11:28:11","alarmId":"13e32c54-6225-11eb-8000-ca9aff9a779e","alarmPicId":"20210129112812-D97176623-1-10079-2-1","alarmPicUrl":"https://alipicdeco.ys7.com:8089/ezviz/pic/down?url=aHR0cHM6Ly9zMy1jbi1iai51ZmlsZW9zLmNvbS91Y2xvdWQtYmovNy9EOTcxNzY2MjNfMV8yMDIxMDEyOTExMjgxMi1EOTcxNzY2MjMtMS0xMDA3OS0yLTE/WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotRGF0ZT0yMDIxMDEyOVQwMzI4MTZaJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZYLUFtei1FeHBpcmVzPTYwNDgwMCZYLUFtei1DcmVkZW50aWFsPVRPS0VOXzdhYTFkNTUwLWI1MTgtNGRjOC1hNDgxLWEzMDhjY2U5YjQwMCUyRjIwMjEwMTI5JTJGY24tYmolMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1TaWduYXR1cmU9NmZmMGI5MmIzNTkzNWM3NjNiZWVhN2Y2MGEzY2VkOGM3NDRkNGEzZTY4ZmEwOTNlM2U5YjMyMDIxM2JkNTIxZQ==&amp;crypt=2&amp;time=2021-01-29T11:28:11&amp;key=95f5b6722e03628ecc6257e7b4fc95f1","isRead":0},{"id":3,"brandId":2,"bindDeviceId":59,"deviceSn":"D97176623","recState":null,"alarmType":"intelligentDetection","alarmTime":"2021-01-29 11:28:56","alarmId":"30677934-6225-11eb-8000-ca9aff9a779e","alarmPicId":"20210129112857-D97176623-1-10079-2-1","alarmPicUrl":"https://alipicdeco.ys7.com:8089/ezviz/pic/down?url=aHR0cHM6Ly9zMy1jbi1iai51ZmlsZW9zLmNvbS91Y2xvdWQtYmovNy9EOTcxNzY2MjNfMV8yMDIxMDEyOTExMjg1Ny1EOTcxNzY2MjMtMS0xMDA3OS0yLTE/WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotRGF0ZT0yMDIxMDEyOVQwMzI5MDNaJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZYLUFtei1FeHBpcmVzPTYwNDgwMCZYLUFtei1DcmVkZW50aWFsPVRPS0VOXzdhYTFkNTUwLWI1MTgtNGRjOC1hNDgxLWEzMDhjY2U5YjQwMCUyRjIwMjEwMTI5JTJGY24tYmolMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1TaWduYXR1cmU9ZGQzMTMwZWM0MzkwN2Q2NjhjZDJlZjVkODllOTY3MzU5NTBlYzM4ZWNmYmZjZWJjNjAxM2JmMWYwMzA3MTQyZA==&amp;crypt=2&amp;time=2021-01-29T11:28:56&amp;key=95f5b6722e03628ecc6257e7b4fc95f1","isRead":0},{"id":4,"brandId":2,"bindDeviceId":59,"deviceSn":"D97176623","recState":null,"alarmType":"intelligentDetection","alarmTime":"2021-01-29 11:29:26","alarmId":"410afe00-6225-11eb-8000-ca9aff9a779e","alarmPicId":"20210129112926-D97176623-1-10079-2-1","alarmPicUrl":"https://alipicdeco.ys7.com:8089/ezviz/pic/down?url=aHR0cHM6Ly9zMy1jbi1iai51ZmlsZW9zLmNvbS91Y2xvdWQtYmovNy9EOTcxNzY2MjNfMV8yMDIxMDEyOTExMjkyNi1EOTcxNzY2MjMtMS0xMDA3OS0yLTE/WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotRGF0ZT0yMDIxMDEyOVQwMzI5MzFaJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZYLUFtei1FeHBpcmVzPTYwNDgwMCZYLUFtei1DcmVkZW50aWFsPVRPS0VOXzdhYTFkNTUwLWI1MTgtNGRjOC1hNDgxLWEzMDhjY2U5YjQwMCUyRjIwMjEwMTI5JTJGY24tYmolMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1TaWduYXR1cmU9MDk5NDdiMjY1YjhjOTIxN2Y3MzM2OGI4Yjc3MWQ0ZmU1N2ExMjc3MWY4MWQ3MTE3MWQyY2RmMmNkZGE4ZDhjMw==&amp;crypt=2&amp;time=2021-01-29T11:29:26&amp;key=95f5b6722e03628ecc6257e7b4fc95f1","isRead":0}]
         */

        private int total;
        private int totalPages;
        private List<RecordsBean> records;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean {
            /**
             * id : 1
             * brandId : 2
             * bindDeviceId : 59
             * deviceSn : D97176623
             * recState : null
             * alarmType : intelligentDetection
             * alarmTime : 2021-01-29 11:27:01
             * alarmId : ea928764-6224-11eb-8000-ca9aff9a779e
             * alarmPicId : 20210129112702-D97176623-1-10079-2-1
             * alarmPicUrl : https://alipicdeco.ys7.com:8089/ezviz/pic/down?url=aHR0cHM6Ly9zMy1jbi1iai51ZmlsZW9zLmNvbS91Y2xvdWQtYmovNy9EOTcxNzY2MjNfMV8yMDIxMDEyOTExMjcwMi1EOTcxNzY2MjMtMS0xMDA3OS0yLTE/WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotRGF0ZT0yMDIxMDEyOVQwMzI3MDZaJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZYLUFtei1FeHBpcmVzPTYwNDc5OSZYLUFtei1DcmVkZW50aWFsPVRPS0VOXzdhYTFkNTUwLWI1MTgtNGRjOC1hNDgxLWEzMDhjY2U5YjQwMCUyRjIwMjEwMTI5JTJGY24tYmolMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1TaWduYXR1cmU9OTc3ZDI1YzY1ZTUxMGU0MTI0OWZmN2I0YjAxMTgzOWRjNGI1YjcyYTc4ZWJmMGE5MDM3N2YyMzVmMjY5ODhjNg==&amp;crypt=2&amp;time=2021-01-29T11:27:01&amp;key=95f5b6722e03628ecc6257e7b4fc95f1
             * isRead : 0
             */

            private int id;
            private int brandId;
            private int bindDeviceId;
            private String deviceSn;
            private Object recState;
            private String alarmType;
            private String alarmTime;
            private String alarmId;
            private String alarmPicId;
            private String alarmPicUrl;
            private int isRead;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getBrandId() {
                return brandId;
            }

            public void setBrandId(int brandId) {
                this.brandId = brandId;
            }

            public int getBindDeviceId() {
                return bindDeviceId;
            }

            public void setBindDeviceId(int bindDeviceId) {
                this.bindDeviceId = bindDeviceId;
            }

            public String getDeviceSn() {
                return deviceSn;
            }

            public void setDeviceSn(String deviceSn) {
                this.deviceSn = deviceSn;
            }

            public Object getRecState() {
                return recState;
            }

            public void setRecState(Object recState) {
                this.recState = recState;
            }

            public String getAlarmType() {
                return alarmType;
            }

            public void setAlarmType(String alarmType) {
                this.alarmType = alarmType;
            }

            public String getAlarmTime() {
                return alarmTime;
            }

            public void setAlarmTime(String alarmTime) {
                this.alarmTime = alarmTime;
            }

            public String getAlarmId() {
                return alarmId;
            }

            public void setAlarmId(String alarmId) {
                this.alarmId = alarmId;
            }

            public String getAlarmPicId() {
                return alarmPicId;
            }

            public void setAlarmPicId(String alarmPicId) {
                this.alarmPicId = alarmPicId;
            }

            public String getAlarmPicUrl() {
                return alarmPicUrl;
            }

            public void setAlarmPicUrl(String alarmPicUrl) {
                this.alarmPicUrl = alarmPicUrl;
            }

            public int getIsRead() {
                return isRead;
            }

            public void setIsRead(int isRead) {
                this.isRead = isRead;
            }
        }
    }
}
