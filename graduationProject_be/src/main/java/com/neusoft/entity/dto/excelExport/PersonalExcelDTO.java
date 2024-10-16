package com.neusoft.entity.dto.excelExport;

import com.neusoft.enums.IsEnable;
import com.neusoft.enums.NatResult;
import com.neusoft.enums.ResidentialType;
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
public class PersonalExcelDTO {

    /**
     * 人员编号
     */
    private String personnelCode;

    /**
     * 人员姓名
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
     * 人员所在区域
     */
    private String communityName;

    /**
     * 人员所在区域
     */
    private String areaName;

    /**
     * 人员所在楼组
     */
    private String buildName;

    /**
     * 人员门牌号
     */
    private String houseNumber;

    /**
     * 人员居住类型
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
}
