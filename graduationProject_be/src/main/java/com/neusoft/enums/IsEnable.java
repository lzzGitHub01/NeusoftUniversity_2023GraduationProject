package com.neusoft.enums;


import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author lzz
 * 枚举（‘是’或‘否’）
 */

public enum IsEnable implements BasicEnum<String,Integer>{

    /**
     *枚举
     */
    IS_ENABLE_YES(120001,"是"),
    IS_ENABLE_NO(120002,"否");


    /**
     * @EnumValue：将注解所标识的属性的值存储到数据库中
     */
    @EnumValue
    private Integer isEnable;

    /**
     * @JsonValue：用于前端展示
     */
    @JsonValue
    private String isEnableName;

    IsEnable(Integer isEnable, String isEnableName) {
        this.isEnable = isEnable;
        this.isEnableName = isEnableName;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public String getIsEnableName() {
        return isEnableName;
    }


    @Override
    public String getName() {
        return this.isEnableName;
    }

    @Override
    public Integer getValue() {
        return this.isEnable;
    }
}
