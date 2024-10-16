package com.neusoft.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lzz
 * 2、楼表PO实体类
 */
@TableName("my_community_build")
public class CommunityBuildPO {

    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "楼ID", name = "Id")
    private Long Id;

    @TableField("BUILD_CODE")
    @ApiModelProperty(value = "楼编号", name = "buildCode")
    private String buildCode;

    @TableField("BUILD_FLOOR_CODE")
    @ApiModelProperty(value = "楼层信息编号", name = "buildFloorCode")
    private String buildFloorCode;

    @TableField("AREA_CODE")
    @ApiModelProperty(value = "区域编号", name = "areaCode")
    private String areaCode;

    @TableField("COMMUNITY_CODE")
    @ApiModelProperty(value = "所属社区编号", name = "communityCode")
    private String communityCode;

    @TableField("BUILD_NAME")
    @ApiModelProperty(value = "楼名称", name = "buildName")
    private String buildName;

    @TableField("BUILD_RISK")
    @ApiModelProperty(value = "楼风险状况", name = "buildRisk")
    private Integer buildRisk;

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

    public CommunityBuildPO(Long id, String buildCode, String buildFloorCode, String areaCode, String communityCode, String buildName, Integer buildRisk, String createdBy, String createdAt, String updatedBy, String updatedAt, Integer version) {
        Id = id;
        this.buildCode = buildCode;
        this.buildFloorCode = buildFloorCode;
        this.areaCode = areaCode;
        this.communityCode = communityCode;
        this.buildName = buildName;
        this.buildRisk = buildRisk;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.version = version;
    }

    public CommunityBuildPO() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getBuildCode() {
        return buildCode;
    }

    public void setBuildCode(String buildCode) {
        this.buildCode = buildCode;
    }

    public String getBuildFloorCode() {
        return buildFloorCode;
    }

    public void setBuildFloorCode(String buildFloorCode) {
        this.buildFloorCode = buildFloorCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public Integer getBuildRisk() {
        return buildRisk;
    }

    public void setBuildRisk(Integer buildRisk) {
        this.buildRisk = buildRisk;
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
        return "CommunityBuildPO{" +
                "Id=" + Id +
                ", buildCode='" + buildCode + '\'' +
                ", buildFloorCode='" + buildFloorCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", communityCode='" + communityCode + '\'' +
                ", buildName='" + buildName + '\'' +
                ", buildRisk=" + buildRisk +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", version=" + version +
                '}';
    }
}
