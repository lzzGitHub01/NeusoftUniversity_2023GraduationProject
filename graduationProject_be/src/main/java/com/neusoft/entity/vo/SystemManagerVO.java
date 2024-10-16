package com.neusoft.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.neusoft.enums.GmLeave;
import com.neusoft.enums.IsEnable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lzz
 */
@Getter
@Setter
@ToString
public class SystemManagerVO {
    /**
     * 用户ID
     */
    private Long Id;

    /**
     * 用户CODE
     */
    private String manageCode;

    /**
     * 用户名
     */
    private String manageName;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 账户等级
     */
    private GmLeave manageLevel;

    /**
     * 是否启用
     */
    private IsEnable isEnable;

    /**
     * 所管理的区域code
     */
    private String areaCode;

    /**
     * 所管理的楼组code集
     */
    private String buildCode;

    /**
     * 分页页数
     */
    private Integer pageCurrent;

}
