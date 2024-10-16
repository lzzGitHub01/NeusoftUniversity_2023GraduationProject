package com.neusoft.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author lzz
 * 获取session数据工具类
 */
@Component
public class ServletUnit {

    /**
     * 获取当前账户用户名
     * @param request
     * @return
     */
    public static String getSessionUser(HttpServletRequest request){
        //获取session中的数据
        HttpSession session = request.getSession();
        return session.getAttribute("manageName").toString();
    }

    /**
     * 获取当前账户等级
     * @param request
     * @return
     */
    public static String getSessionLevel(HttpServletRequest request){
        //获取session中的数据
        HttpSession session = request.getSession();
        return session.getAttribute("manageLevel").toString();
    }

    /**
     * 获取当前账户所属社区code
     * @param request
     * @return
     */
    public static String getSessionCommunityCode(HttpServletRequest request){
        //获取session中的数据
        HttpSession session = request.getSession();
        return session.getAttribute("communityCode").toString();
    }

    /**
     * 获取当前账户所属区域code
     * @param request
     * @return
     */
    public static String getSessionAreaCode(HttpServletRequest request){
        //获取session中的数据
        HttpSession session = request.getSession();
        return session.getAttribute("areaCode").toString();
    }

    /**
     * 获取当前账户所属建筑code
     * @param request
     * @return
     */
    public static String getSessionBuildCode(HttpServletRequest request){
        //获取session中的数据
        HttpSession session = request.getSession();
        return session.getAttribute("buildCode").toString();
    }

}
