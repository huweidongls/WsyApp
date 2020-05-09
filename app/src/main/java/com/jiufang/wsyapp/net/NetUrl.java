package com.jiufang.wsyapp.net;

/**
 * Created by Administrator on 2020/4/27.
 */

public class NetUrl {

//    public static final String BASE_URL = "http://hvc.jiufangkeji.com";
    public static final String BASE_URL = "http://hvc.jiufangkeji.com";
    public static final String H5BASE_URL = "http://xfsysh5.5ijiaoyu.cn/";
    //用户获取验证码
    public static final String sendCaptchaCodeWithSMS = "/v1.0.0/sendCaptchaCodeWithSMS";
    //手机验证码登录
    public static final String loginByCaptcha = "/v1.0.0/loginByCaptcha";
    //设置密码
    public static final String setPassword = "/user/v1.0.0/setPassword";
    //密码登录
    public static final String loginByPassword = "/v1.0.0/loginByPassword";

}
