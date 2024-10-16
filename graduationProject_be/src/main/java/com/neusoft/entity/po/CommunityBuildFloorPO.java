package com.neusoft.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lzz
 * 3、楼层表PO实体类
 */
@TableName("my_build_floor")
public class CommunityBuildFloorPO {

    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "楼ID", name = "Id")
    private Long Id;

    @TableField("BUILD_FLOOR_CODE")
    @ApiModelProperty(value = "建筑规格编号", name = "buildFloorCode")
    private String buildFloorCode;

    @TableField("BUILD_FLOOR_NAME")
    @ApiModelProperty(value = "建筑规格名称", name = "buildFloorName")
    private String buildFloorName;

    @TableField("COMMUNITY_CODE")
    @ApiModelProperty(value = "所属社区编号", name = "communityCode")
    private String communityCode;

    @TableField("BUILD_FLOOR_NUM")
    @ApiModelProperty(value = "楼层数", name = "buildFloorNum")
    private String buildFloorNum;

    @TableField("COMMUNITY_OCCUPANT")
    @ApiModelProperty(value = "可容纳住户数量", name = "communityOccupant")
    private String communityOccupant;

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

    public CommunityBuildFloorPO(Long id, String buildFloorCode, String buildFloorName, String communityCode, String buildFloorNum, String communityOccupant, String createdBy, String createdAt, String updatedBy, String updatedAt, Integer version) {
        Id = id;
        this.buildFloorCode = buildFloorCode;
        this.buildFloorName = buildFloorName;
        this.communityCode = communityCode;
        this.buildFloorNum = buildFloorNum;
        this.communityOccupant = communityOccupant;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.version = version;
    }

    public CommunityBuildFloorPO() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getBuildFloorCode() {
        return buildFloorCode;
    }

    public void setBuildFloorCode(String buildFloorCode) {
        this.buildFloorCode = buildFloorCode;
    }

    public String getBuildFloorName() {
        return buildFloorName;
    }

    public void setBuildFloorName(String buildFloorName) {
        this.buildFloorName = buildFloorName;
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    public String getBuildFloorNum() {
        return buildFloorNum;
    }

    public void setBuildFloorNum(String buildFloorNum) {
        this.buildFloorNum = buildFloorNum;
    }

    public String getCommunityOccupant() {
        return communityOccupant;
    }

    public void setCommunityOccupant(String communityOccupant) {
        this.communityOccupant = communityOccupant;
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
        return "CommunityBuildFloorPO{" +
                "Id=" + Id +
                ", buildFloorCode='" + buildFloorCode + '\'' +
                ", buildFloorName='" + buildFloorName + '\'' +
                ", communityCode='" + communityCode + '\'' +
                ", buildFloorNum='" + buildFloorNum + '\'' +
                ", communityOccupant='" + communityOccupant + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", version=" + version +
                '}';
    }
}
