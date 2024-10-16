package com.neusoft.entity.dto.infectionStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author lzz
 * 不同社区感染情况展示
 */
@Getter
@Setter
@ToString
@Component
public class InfectionStatusDTO {

    /**
     * 总人数
     */
    private Integer totalNumber;

    /**
     * 基础病人数
     */
    private Integer isUdNumber;

    /**
     * 阴性人数
     */
    private Integer feminineNumber;

    /**
     * 阳性人数
     */
    private Integer masculineNumber;

    /**
     * 租户人数
     */
    private Integer tenantNumber;

    /**
     * 住户人数
     */
    private Integer holdNumber;

    /**
     * 统计未接种人数
     */
    private Integer noDoseNumber;

    /**
     * 统计接种一针人数
     */
    private Integer oneDoseNumber;

    /**
     * 统计接种两针人数
     */
    private Integer twoDoseNumber;

    /**
     * 统计接种三针人数
     */
    private Integer threeDoseNumber;

    /**
     * 统计接种四针人数
     */
    private Integer foreDoseNumber;
}
