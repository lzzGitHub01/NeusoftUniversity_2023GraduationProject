package com.neusoft.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author lzz
 * 病毒检测结果枚举类
 */

public enum NatResult implements BasicEnum<String,Integer>{

    /**
     *枚举
     */
    FEMININE_GENDER(120031,"阴性"),
    MASCULINE_GENDER(120032,"阳性");

    /**
     * @EnumValue：将注解所标识的属性的值存储到数据库中
     */
    @EnumValue
    private Integer natResultValue;

    /**
     * @JsonValue：用于前端展示
     */
    @JsonValue
    private String natResultName;

    NatResult(Integer natResultValue, String natResultName) {
        this.natResultValue = natResultValue;
        this.natResultName = natResultName;
    }

    public Integer getNatResultValue() {
        return natResultValue;
    }

    public String getNatResultName() {
        return natResultName;
    }

    @Override
    public String getName() {
        return this.natResultName;
    }

    @Override
    public Integer getValue() {
        return this.natResultValue;
    }
}
