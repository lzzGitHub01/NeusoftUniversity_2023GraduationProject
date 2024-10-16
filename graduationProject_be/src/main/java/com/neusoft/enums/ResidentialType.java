package com.neusoft.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author lzz
 */

public enum ResidentialType implements BasicEnum<String,Integer>{

    /**
     *枚举
     */
    HOUSE_TENANT(120011,"租户"),
    HOUSE_HOLD(120012,"住户");

    /**
     * @EnumValue：将注解所标识的属性的值存储到数据库中
     */
    @EnumValue
    private Integer residentialTypeValue;

    /**
     * @JsonValue：用于前端展示
     */
    @JsonValue
    private String residentialTypeName;

    ResidentialType(Integer residentialTypeValue, String residentialTypeName) {
        this.residentialTypeValue = residentialTypeValue;
        this.residentialTypeName = residentialTypeName;
    }

    public Integer getResidentialTypeValue() {
        return residentialTypeValue;
    }

    public String getResidentialTypeName() {
        return residentialTypeName;
    }

    @Override
    public String getName() {
        return  this.residentialTypeName;
    }

    @Override
    public Integer getValue() {
        return this.residentialTypeValue;
    }
}
