package com.neusoft.entity.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lzz
 */
@Getter
@Setter
@ToString
public class CommunityBuildVO {

    /**
     * 建筑ID
     */
    private String id;

    /**
     * 建筑CODE
     */
    private String buildCode;

    /**
     * 建筑所在区域CODE
     */
    private String areaCode;

    /**
     * 建筑名称
     */
    private String buildName;

    /**
     * 建筑规格
     */
    private String buildFloorCode;

    /**
     * 建筑风险等级
     */
    private Integer buildRisk;

    /**
     * 所属区域名称
     */
    private String areaName;

    /**
     * 分页页数
     */
    private Integer pageCurrent;

}
