package com.neusoft.entity.dto;

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
public class CommunityBuildDTO {
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
     * 建筑风险等级
     */
    private Integer buildRisk;

    /**
     * 分页页数
     */
    private Integer pageCurrent;


    private String codeName;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 建筑规格code
     */
    private String buildFloorCode;

    /**
     * 建筑规格名称
     */
    private String buildFloorName;

}
