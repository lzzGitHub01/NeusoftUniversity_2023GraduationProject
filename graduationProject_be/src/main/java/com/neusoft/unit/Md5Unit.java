package com.neusoft.unit;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @author lzz
 * MD5加密工具类
 */
@Component
public class Md5Unit {

    /**
     * 获取UUID生成的盐值
     * @return
     */
    public static String getSaltValue(){
        //使用UUID生成盐值(String类型)
        return UUID.randomUUID().toString().toUpperCase();
    }

    /**
     * 密码加密
     * @param password
     */
    public static String passwordEncryption(String password, String salt){
        //使用盐值拼接密码生成新密码
        String newPassword = salt+password;

        //使用MD5进行加密，并转换成大写
        return DigestUtils.md5DigestAsHex(newPassword.getBytes()).toUpperCase();
    }

}
