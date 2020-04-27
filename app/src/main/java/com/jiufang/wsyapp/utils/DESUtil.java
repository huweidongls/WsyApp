package com.jiufang.wsyapp.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author zhaochunhui
 * @version v1.0
 * Copyright (c) 2020-2021, hvc project team
 * @pakeage com.hvc.framework
 * @date 2020/4/27
 */
public class DESUtil {
    private static final String KEY_ALGORITHM = "DES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";// 默认的加密算法
    private static byte[] iv = {1,2,3,4,5,6,7,8};

    public static String encryptDES(String encryptString, String encryptKey)  {
        try {
            IvParameterSpec zeroIv = new IvParameterSpec(iv);
            SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
            byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
            return Base64.encode(encryptedData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String decryptDES(String decryptString, String decryptKey){
        try {
            byte[] byteMi = new Base64().decode(decryptString);
            IvParameterSpec zeroIv = new IvParameterSpec(iv);
            SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
            byte decryptedData[] = cipher.doFinal(byteMi);
            return new String(decryptedData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
//    public static void main(String[] args){
//        String content = "18686817319";
//        String key = "Hvc.2020";
//        System.out.println("content:" + content);
//        String s1 = DESUtil.encryptDES(content, key);
//        System.out.println("加密:" + s1);
//        System.out.println("解密:" + DESUtil.decryptDES(s1, key));
//    }
}
