package com.neusoft.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author Lzz
 */
@Getter
@Setter
@ToString
@Component
public class CommunityAreaDTO {

    /**
     * 区域ID
     */
    private Long id;

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
     * 修改人
     */
    private String updatedBy;
}
