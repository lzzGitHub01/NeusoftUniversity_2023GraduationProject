package com.neusoft.unit;

import com.neusoft.mapper.MackTimeStampCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author lzz
 * code生成
 */
@Component
public class MakeCodeUnit {

    /**
     * 获取时间戳，截取后五位
     * @param code
     * @return
     */
    public static String codeDispose(String code){
        String substring = code.substring(code.length() - 5);
        return substring;
    }

    /**
     * 生成管理员code
     * @param str
     * @return
     */
    public static String makeUserCode(String str){
        String Start = "SMC";
        String substring1 = str.substring(0,2);
        String substring2 = str.substring(15,16);
        String substring3 = str.substring(str.length() - 3);

        String userCode = Start + substring1 + substring2 + substring3;
        return userCode;
    }

    /**
     * 生成社区Code
     * @param code
     * @return
     */
    public static String makeCommunityCode(String code){
        String Start = "CMY";
        String codeDispose = codeDispose(code);
        String finallyCode = Start + codeDispose;
        return finallyCode;
    }

    /**
     * 生成区域Code
     * @param code
     * @return
     */
    public static String makeAreaCode(String code){
        String Start = "DTO";
        String codeDispose = codeDispose(code);
        String finallyCode = Start + codeDispose;
        return finallyCode;
    }

    /**
     * 生成建筑Code
     * @param code
     * @return
     */
    public static String makeBuildCode(String code){
        String Start = "BID";
        String codeDispose = codeDispose(code);
        String finallyCode = Start + codeDispose;
        return finallyCode;
    }

    /**
     * 生成人员Code
     * @param code
     * @return
     */
    public static String makePersonnelCode(String code){
        String Start = "PSN";
        String codeDispose = codeDispose(code);
        String finallyCode = Start + codeDispose;
        return finallyCode;
    }

    /**
     * 生成建筑规则Code
     * @param code
     * @return
     */
    public static String makeBuildFloorCode(String code){
        String Start = "BFR";
        String codeDispose = codeDispose(code);
        String finallyCode = Start + codeDispose;
        return finallyCode;
    }

}
