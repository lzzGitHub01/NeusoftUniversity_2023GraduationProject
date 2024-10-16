package com.neusoft.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Lzz
 */
@Getter
@Setter
@ToString
public class CommunityAreaVO {

    /**
     * 区域ID
     */
    private String id;
    /**
     * 区域编号
     */
    private String areaCode;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 区域风险状况
     */
    private Integer areaRisk;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 分页页数
     */
    private Integer pageCurrent;

}
