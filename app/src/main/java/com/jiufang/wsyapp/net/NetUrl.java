package com.jiufang.wsyapp.net;

/**
 * Created by Administrator on 2020/4/27.
 */

public class NetUrl {

    public static final String YS_APP_KEY = "7ce02f98c4614264882a4fec56a32d5d";

//    public static final String BASE_URL = "http://hvc.jiufangkeji.com";
    public static final String BASE_URL = "http://hvc.jiufangkeji.com";
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

}
