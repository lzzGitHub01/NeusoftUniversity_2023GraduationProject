package com.neusoft.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.neusoft.enums.GmLeave;
import com.neusoft.enums.IsEnable;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lzz
 */
@TableName("my_manage")
public class SystemManagerPO {

    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "用户ID", name = "Id")
    private Long Id;

    @TableField("MANAGE_CODE")
    @ApiModelProperty(value = "用户代码", name = "manageCode")
    private String manageCode;

    @TableField("MANAGE_NAME")
    @ApiModelProperty(value = "用户姓名", name = "manageName")
    private String manageName;

    @TableField("PASSWORD")
    @ApiModelProperty(value = "用户密码", name = "password")
    private String password;

    @TableField("PHONE")
    @ApiModelProperty(value = "用户电话", name = "phone")
    private String phone;

    @ApiModelProperty(value = "用户等级")
    private GmLeave manageLevel;

    @TableField("COMMUNITY_CODE")
    @ApiModelProperty(value = "所属社区编号", name = "communityCode")
    private String communityCode;

    @TableField("AREA_CODE")
    @ApiModelProperty(value = "所属区域编号", name = "areaCode")
    private String areaCode;

    @TableField("BUILD_CODE")
    @ApiModelProperty(value = "所属楼编号", name = "buildCode")
    private String buildCode;

    @ApiModelProperty(value = "是否启用")
    private IsEnable isEnable;

    @TableField("UUID")
    @ApiModelProperty(value = "加密盐值", name = "uuid")
    private String uuid;


    @TableField("LAST_LOGIN_AT")
    @ApiModelProperty(value = "最近一次登陆时间", name = "lastLoginAt")
    private String lastLoginAt;

    @TableField("LOGIN_AT")
    @ApiModelProperty(value = "当前登陆时间", name = "loginAt")
    private String loginAt;

    @TableField("CREATED_BY")
    @ApiModelProperty(value = "创建人", name = "createdBy")
    private String createdBy;

    @TableField("CREATED_AT")
    @ApiModelProperty(value = "创建时间", name = "createdAt")
    private String createdAt;

    @TableField("UPDATED_BY")
    @ApiModelProperty(value = "修改人", name = "updatedBy")
    private String updatedBy;

    @TableField("UPDATED_AT")
    @ApiModelProperty(value = "修改时间", name = "updatedAt")
    private String updatedAt;

    @Version
    @TableField("VERSION")
    @ApiModelProperty(value = "乐观锁版本号", name = "updatedBy")
    private Integer version;

    public SystemManagerPO() {
    }

    public SystemManagerPO(Long id, String manageCode, String manageName, String password, String phone, GmLeave manageLevel, String communityCode, String areaCode, String buildCode, IsEnable isEnable, String uuid, String lastLoginAt, String loginAt, String createdBy, String createdAt, String updatedBy, String updatedAt, Integer version) {
        Id = id;
        this.manageCode = manageCode;
        this.manageName = manageName;
        this.password = password;
        this.phone = phone;
        this.manageLevel = manageLevel;
        this.communityCode = communityCode;
        this.areaCode = areaCode;
        this.buildCode = buildCode;
        this.isEnable = isEnable;
        this.uuid = uuid;
        this.lastLoginAt = lastLoginAt;
        this.loginAt = loginAt;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.version = version;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getManageCode() {
        return manageCode;
    }

    public void setManageCode(String manageCode) {
        this.manageCode = manageCode;
    }

    public String getManageName() {
        return manageName;
    }

    public void setManageName(String manageName) {
        this.manageName = manageName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public GmLeave getManageLevel() {
        return manageLevel;
    }

    public void setManageLevel(GmLeave manageLevel) {
        this.manageLevel = manageLevel;
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getBuildCode() {
        return buildCode;
    }

    public void setBuildCode(String buildCode) {
        this.buildCode = buildCode;
    }

    public IsEnable getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(IsEnable isEnable) {
        this.isEnable = isEnable;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(String lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public String getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(String loginAt) {
        this.loginAt = loginAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "SystemManagerPO{" +
                "Id=" + Id +
                ", manageCode='" + manageCode + '\'' +
                ", manageName='" + manageName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", manageLevel=" + manageLevel +
                ", communityCode='" + communityCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", buildCode='" + buildCode + '\'' +
                ", isEnable=" + isEnable +
                ", uuid='" + uuid + '\'' +
                ", lastLoginAt='" + lastLoginAt + '\'' +
                ", loginAt='" + loginAt + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", version=" + version +
                '}';
    }
}
