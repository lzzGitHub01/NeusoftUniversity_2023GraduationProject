package com.neusoft.entity.dto.infectionStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author lzz
 * 区域风险状况
 */
@Getter
@Setter
@ToString
@Component
public class AreaInfectionSituationDTO {

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 区域名称
     */
    private String areaCode;

    /**
     * 区域总人数
     */
    private Integer areaTotalNumber;

    /**
     * 区域感染人数
     */
    private Integer masculineNumber;

    /**
     * 感染比率
     */
    private Float infectionPercent;

}
