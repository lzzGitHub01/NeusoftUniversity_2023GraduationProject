package com.neusoft.entity.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.neusoft.enums.GmLeave;
import com.neusoft.enums.IsEnable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author lzz
 */
@Getter
@Setter
@ToString
@Component
public class SystemManagerDTO {
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
     * 用户等级
     */
    private GmLeave manageLevel;

    /**
     * 所属社区编号
     */
    private String communityCode;

    /**
     * 所属社区名称
     */
    private String communityName;

    /**
     * 所管理的区域code
     */
    private String areaCode;

    /**
     * 所管理的楼组code集
     */
    private String buildCode;

    /**
     * 是否启用
     */
    private IsEnable isEnable;

    /**
     * 修改时间
     */
    private String updatedAt;

    /**
     * 最近一次登陆时间
     */
    private String loginAt;

    /**
     * 分页页数
     */
    private Integer pageCurrent;

}
