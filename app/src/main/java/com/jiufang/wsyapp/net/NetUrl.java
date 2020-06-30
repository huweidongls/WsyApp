package com.jiufang.wsyapp.net;

/**
 * Created by Administrator on 2020/4/27.
 */

public class NetUrl {

    public static final String YS_APP_KEY = "7ce02f98c4614264882a4fec56a32d5d";

//    public static final String BASE_URL = "http://hvc.jiufangkeji.com";
    public static final String BASE_URL = "http://hvc.jiufangkeji.com";
//    public static final String BASE_URL = "http://192.168.1.125:8888";
    public static final String BASE_IMG_URL = "http://hvc.jiufangkeji.com/resource/";
    public static final String H5BASE_URL = "http://xfsysh5.5ijiaoyu.cn/";
    //用户获取验证码
    public static final String sendCaptchaCodeWithSMS = "/v1.0.0/sendCaptchaCodeWithSMS";
    //手机验证码登录
    public static final String loginByCaptcha = "/v1.0.0/loginByCaptcha";
    //设置密码
    public static final String setPassword = "/user/v1.0.0/setPassword";
    //密码登录
    public static final String loginByPassword = "/v1.0.0/loginByPassword";
    //获取个人消息分页列表
    public static final String getPersonalMessagePageList = "/message/v1.0.0/getPersonalMessagePageList";
    //获取系统消息分页列表
    public static final String getSysMessagePageList = "/message/v1.0.0/getSysMessagePageList";
    //上传单个文件
    public static final String upload = "/user/upload";
    //获取个人消息详情
    public static final String getPersonalMessageById = "/message/v1.0.0/getPersonalMessageById";
    //获取系统消息详情
    public static final String getSysMessageById = "/message/v1.0.0/getSysMessageById";
    //获取绑定设备分页列表
    public static final String getBindDeviceList = "/home/v1.0.0/getBindDeviceList";
    //获取套餐类别列表
    public static final String getComboCategoryList = "/combo/v1.0.0/getComboCategoryList";
    //获取套餐详情
    public static final String getComboById = "/combo/v1.0.0/getComboById";
    //获取绑定设备列表
    public static final String getComboDeviceChooseList = "/combo/v1.0.0/getComboDeviceChooseList";
    //绑定设备
    public static final String bindDevice = "/device/v1.0.0/bindDevice";
    //解绑设备
    public static final String unBindDevice = "/device/v1.0.0/unBindDevice";
    //检测设备是否被绑定
    public static final String checkDeviceBindStatus = "/device/v1.0.0/checkDeviceBindStatus";
    //检测设备是否在线
    public static final String checkDeviceOnlineStatus = "/device/v1.0.0/checkDeviceOnlineStatus";
    //创建乐橙直播地址
    public static final String bindLcDeviceLive = "/device/v1.0.0/bindLcDeviceLive";
    //获取乐橙设备详情
    public static final String getBindDeviceDetail = "/device/v1.0.0/getBindDeviceDetail";
    //更新设备名称
    public static final String updateBindDeviceName = "/device/v1.0.0/updateBindDeviceName";
    //根据名称查询地区
    public static final String getAreaByName = "/area/v1.0.0/getAreaByName";
    //更新设备使用人信息
    public static final String updateBindDeviceUsePerson = "/device/v1.0.0/updateBindDeviceUsePerson";
    //退出登录
    public static final String logout = "/v1.0.0/logout";
    //获取设备型号
    public static final String getDeviceModal = "/device/v1.0.0/getDeviceModal";
    //获取用户信息
    public static final String getUserInfo = "/user/v1.0.0/getUserInfo";
    //更新用户实名信息
    public static final String updateUserRealInfo = "/user/v1.0.0/updateUserRealInfo";
    //更新昵称
    public static final String updateNickname = "/user/v1.0.0/updateNickname";
    //获取设备使用者信息
    public static final String getBindDeviceUserInfo = "/device/v1.0.0/getBindDeviceUserInfo";
    //获取设备状态
    public static final String getBindDeviceStatusInfo = "/device/v1.0.0/getBindDeviceStatusInfo";
    //萤石云存储
    public static final String getYSCloudStorageRecordList = "/device/storage/v1.0.0/getYSCloudStorageRecordList";
    //萤石本地存储
    public static final String getYSLocalStorageRecordList = "/device/storage/v1.0.0/getYSLocalStorageRecordList";
    //乐橙云存储
    public static final String getLcCloudStorageRecordList = "/device/storage/v1.0.0/getLcCloudStorageRecordList";
    //乐橙本地存储
    public static final String getLcLocalStorageRecordList = "/device/storage/v1.0.0/getLcLocalStorageRecordList";
    //乐橙云台控制
    public static final String controlLcDeviceMovePTZ = "/device/console/v1.0.0/controlLcDeviceMovePTZ";
    //获取萤石设备报警信息
    public static final String getDeviceAlarmYsPage = "/device/alarm/v1.0.0/getDeviceAlarmYsPage";
    //获取乐橙设备报警信息
    public static final String getDeviceAlarmLcPage = "/device/alarm/v1.0.0/getDeviceAlarmLcPage";
    //一键报警-乐橙
    public static final String createLcAlarm = "/alarm/v1.0.0/createLcAlarm";
    //一键报警-萤石
    public static final String createYsAlarm = "/alarm/v1.0.0/createYsAlarm";
    //读取云存储和本地存储信息获取数量、剩余天数等
    public static final String getStorageDetailInfo = "/device/storage/v1.0.0/getStorageDetailInfo";
    //测试阿里云推送通知
    public static final String testAliyunPushNotice = "/callback/testAliyunPushNotice";
    //测试阿里云推送消息
    public static final String testAliyunPushMessage = "/callback/testAliyunPushMessage";
    //获取设备详情信息
    public static final String getDeviceDetailInfo = "/device/v1.0.0/getDeviceDetailInfo";
    //设置设备报警消息提醒
    public static final String setDeviceNoticeStatus = "/device/v1.0.0/setDeviceNoticeStatus";
    //获取乐橙设备动检参数信息
    public static final String getLcDeviceCapability = "/device/capability/v1.0.0/getLcDeviceCapability";
    //获取萤石设备动检参数信息
    public static final String getYsDeviceCapability = "/device/capability/v1.0.0/getYsDeviceCapability";

}
