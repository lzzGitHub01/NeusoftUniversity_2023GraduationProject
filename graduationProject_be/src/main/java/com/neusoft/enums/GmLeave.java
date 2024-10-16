package com.neusoft.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author lzz
 */

public enum GmLeave implements BasicEnum<String,String>{

    /**
     *枚举
     */

    LEAVE_HIGH("0","最高权限"),
    LEAVE_MEDIUM("1","中级权限"),
    LEAVE_NORMAL("2","普通权限"),
    LEAVE_ADMINISTRATOR("3","系统管理员");

    /**
     * @EnumValue：将注解所标识的属性的值存储到数据库中
     */
    @EnumValue
    private String manageLevel;

    /**
     * @JsonValue：用于前端展示
     */
    @JsonValue
    private String manageLevelName;

    GmLeave(String manageLevel, String manageLevelName) {
        this.manageLevel = manageLevel;
        this.manageLevelName = manageLevelName;
    }

    public String getManageLevel() {
        return manageLevel;
    }

    public String getManageLevelName() {
        return manageLevelName;
    }

    @Override
    public String getName() {
        return this.manageLevelName;
    }

    @Override
    public String getValue() {
        return this.manageLevel;
    }
}
