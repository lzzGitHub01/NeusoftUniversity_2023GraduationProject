package com.neusoft.entity.vo;

import com.neusoft.enums.IsEnable;
import com.neusoft.enums.NatResult;
import com.neusoft.enums.ResidentialType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lzz
 */
@Getter
@Setter
@ToString
public class PersonnelVO {

    /**
     * 人员ID
     */
    private String Id;

    /**
     * 人员编号
     */
    private String personnelCode;

    /**
     * 人员名称
     */
    private String personnelName;

    /**
     * 人员年龄
     */
    private String personnelAge;

    /**
     * 人员联系电话
     */
    private String phone;

    /**
     * 建筑所在区域CODE
     */
    private String areaCode;

    /**
     * 建筑CODE
     */
    private String buildCode;

    /**
     * 人员类型
     */
    private ResidentialType personnelState;

    /**
     * 三天内病毒检测状态
     */
    private NatResult natResult;

    /**
     * 是否存在基础病
     */
    private IsEnable isUd;

    /**
     * 疫苗接种情况
     */
    private String inoculate;

    /**
     * 七日内行程
     */
    private String sevenDayTrip;

    /**
     * 是否处于隔离状态
     */
    private IsEnable isQuarantine;

    /**
     * 隔离政策
     */
    private String quarantinePolicy;

    /**
     * 分页页数
     */
    private Integer pageCurrent;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 楼名称
     */
    private String buildName;

    /**
     * 住户编号
     */
    private String residentCode;
}
