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
public class CommunityBuildFloorDTO {

    /**
     * 楼组规则ID
     */
    private String Id;

    /**
     * 楼组规则code
     */
    private String buildFloorCode;

    /**
     * 规则所属社区
     */
    private String communityCode;

    /**
     * 最大楼层数
     */
    private String buildFloorNum;

    /**
     * 每层最大可容纳住户数量
     */
    private String communityOccupant;

    /**
     * 分页页数
     */
    private Integer pageCurrent;

}
