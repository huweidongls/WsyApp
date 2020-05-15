package com.jiufang.wsyapp.net;

/**
 * Created by Administrator on 2020/4/27.
 */

public class NetUrl {

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

}
