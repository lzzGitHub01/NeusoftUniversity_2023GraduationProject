package com.neusoft.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lzz
 * 1、区域表PO实体类
 */
@TableName("my_community_area")
public class CommunityAreaPO {

    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "区域ID", name = "Id")
    private Long Id;

    @TableField("AREA_CODE")
    @ApiModelProperty(value = "区域编号", name = "areaCode")
    private String areaCode;

    @TableField("AREA_NAME")
    @ApiModelProperty(value = "区域名称", name = "areaName")
    private String areaName;

    @TableField("AREA_RISK")
    @ApiModelProperty(value = "区域风险状况", name = "areaRisk")
    private Integer areaRisk;

    @TableField("COMMUNITY_CODE")
    @ApiModelProperty(value = "所属社区编号", name = "communityCode")
    private String communityCode;

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

    public CommunityAreaPO(Long id, String areaCode, String areaName, Integer areaRisk, String communityCode, String createdBy, String createdAt, String updatedBy, String updatedAt, Integer version) {
        Id = id;
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.areaRisk = areaRisk;
        this.communityCode = communityCode;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.version = version;
    }

    public CommunityAreaPO() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    public Integer getAreaRisk() {
        return areaRisk;
    }

    public void setAreaRisk(Integer areaRisk) {
        this.areaRisk = areaRisk;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "CommunityAreaPO{" +
                "Id=" + Id +
                ", areaCode='" + areaCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", areaRisk=" + areaRisk +
                ", communityCode='" + communityCode + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", version=" + version +
                '}';
    }
}
