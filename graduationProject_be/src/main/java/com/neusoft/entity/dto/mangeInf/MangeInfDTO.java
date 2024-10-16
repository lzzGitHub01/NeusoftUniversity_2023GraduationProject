package com.neusoft.entity.dto.mangeInf;

import com.neusoft.enums.GmLeave;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author lzz
 * 登陆信息DTO
 */
@Getter
@Setter
@ToString
@Component
public class MangeInfDTO {

    /**
     * 账户名称
     */
    private String manageName;

    /**
     * 账户电话
     */
    private String phone;

    /**
     * 账户等级
     */
    private GmLeave manageLevel;

    /**
     * 社区代码
     */
    private String communityCode;

    /**
     * 区域代码
     */
    private String areaCode;

    /**
     * 建筑代码
     */
    private String buildCode;
}
