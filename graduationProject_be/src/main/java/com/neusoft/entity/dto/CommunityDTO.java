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
public class CommunityDTO {

    /**
     * 社区ID
     */
    private String Id;

    /**
     * 社区CODE
     */
    private String communityCode;

    /**
     * 社区名称
     */
    private String communityName;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private String createdAt;

    /**
     * 分页页数
     */
    private Integer pageCurrent;

}
