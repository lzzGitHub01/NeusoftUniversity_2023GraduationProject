package com.neusoft.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Myself
 * 4、住户表PO实体类
 */
@TableName("my_build_floor")
public class CommunityBuildResidentPO {

    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "楼ID", name = "Id")
    private Long Id;

    @TableField("RESIDENT_CODE")
    @ApiModelProperty(value = "住户编号", name = "residentCode")
    private String residentCode;

    @TableField("RESIDENT_NAME")
    @ApiModelProperty(value = "住户房号", name = "residentName")
    private String residentName;

    @TableField("RESIDENT_COUNT")
    @ApiModelProperty(value = "住户人口数", name = "residentCount")
    private String residentCount;

    @TableField("RESIDENT_RISK")
    @ApiModelProperty(value = "住户风险状况", name = "residentRisk")
    private Integer residentRisk;

    @TableField("RESIDENT_STATE")
    @ApiModelProperty(value = "住户类型", name = "residentState")
    private Integer residentState;

    @TableField("COMMUNITY_CODE")
    @ApiModelProperty(value = "所属社区编号", name = "communityCode")
    private String communityCode;

    @TableField("AREA_CODE")
    @ApiModelProperty(value = "区域编号", name = "areaCode")
    private String areaCode;

    @TableField("BUILD_CODE")
    @ApiModelProperty(value = "楼编号", name = "buildCode")
    private String buildCode;

    @TableField("BUILD_FLOOR_CODE")
    @ApiModelProperty(value = "楼层编号", name = "buildFloorCode")
    private String buildFloorCode;

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

    public CommunityBuildResidentPO(Long id, String residentCode, String residentName, String residentCount, Integer residentRisk, Integer residentState, String communityCode, String areaCode, String buildCode, String buildFloorCode, String createdBy, String createdAt, String updatedBy, String updatedAt, Integer version) {
        Id = id;
        this.residentCode = residentCode;
        this.residentName = residentName;
        this.residentCount = residentCount;
        this.residentRisk = residentRisk;
        this.residentState = residentState;
        this.communityCode = communityCode;
        this.areaCode = areaCode;
        this.buildCode = buildCode;
        this.buildFloorCode = buildFloorCode;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.version = version;
    }

    public CommunityBuildResidentPO() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getResidentCode() {
        return residentCode;
    }

    public void setResidentCode(String residentCode) {
        this.residentCode = residentCode;
    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public String getResidentCount() {
        return residentCount;
    }

    public void setResidentCount(String residentCount) {
        this.residentCount = residentCount;
    }

    public Integer getResidentRisk() {
        return residentRisk;
    }

    public void setResidentRisk(Integer residentRisk) {
        this.residentRisk = residentRisk;
    }

    public Integer getResidentState() {
        return residentState;
    }

    public void setResidentState(Integer residentState) {
        this.residentState = residentState;
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

    public String getBuildFloorCode() {
        return buildFloorCode;
    }

    public void setBuildFloorCode(String buildFloorCode) {
        this.buildFloorCode = buildFloorCode;
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
        return "CommunityBuildResidentPO{" +
                "Id=" + Id +
                ", residentCode='" + residentCode + '\'' +
                ", residentName='" + residentName + '\'' +
                ", residentCount='" + residentCount + '\'' +
                ", residentRisk=" + residentRisk +
                ", residentState=" + residentState +
                ", communityCode='" + communityCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", buildCode='" + buildCode + '\'' +
                ", buildFloorCode='" + buildFloorCode + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", version=" + version +
                '}';
    }
}
