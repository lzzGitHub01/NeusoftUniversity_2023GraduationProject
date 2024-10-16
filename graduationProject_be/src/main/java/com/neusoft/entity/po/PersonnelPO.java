package com.neusoft.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lzz
 * 5、人员表PO实体类
 */
@TableName("my_personnel")
public class PersonnelPO {

    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "人员ID", name = "Id")
    private Long Id;

    @TableField("PERSONNEL_CODE")
    @ApiModelProperty(value = "人员编号", name = "personnelCode")
    private String personnelCode;

    @TableField("PERSONNEL_NAME")
    @ApiModelProperty(value = "人员姓名", name = "personnelName")
    private String personnelName;

    @TableField("PERSONNEL_AGE")
    @ApiModelProperty(value = "人员年龄", name = "personnelAge")
    private String personnelAge;

    @TableField("PHONE")
    @ApiModelProperty(value = "用户电话", name = "phone")
    private String phone;

    @TableField("COMMUNITY_CODE")
    @ApiModelProperty(value = "所属社区编号", name = "communityCode")
    private String communityCode;

    @TableField("AREA_CODE")
    @ApiModelProperty(value = "区域编号", name = "areaCode")
    private String areaCode;

    @TableField("BUILD_CODE")
    @ApiModelProperty(value = "楼编号", name = "buildCode")
    private String buildCode;

    @TableField("HOUSE_NUMBER")
    @ApiModelProperty(value = "门牌号", name = "houseNumber")
    private String houseNumber;

    @TableField("PERSONNEL_STATE")
    @ApiModelProperty(value = "人员类型", name = "personnelState")
    private Integer personnelState;

    @TableField("NAT_RESULT")
    @ApiModelProperty(value = "三天内核酸状态", name = "NatResult")
    private Integer natResult;

    @TableField("IS_UD")
    @ApiModelProperty(value = "是否存在基础疾病", name = "isUd")
    private Integer isUd;

    @TableField("INOCULATE")
    @ApiModelProperty(value = "疫苗接种状况", name = "inoculate")
    private String inoculate;

    @TableField("SEVEN_DAY_TRIP")
    @ApiModelProperty(value = "七天内行程", name = "sevenDayTrip")
    private String sevenDayTrip;

    @TableField("IS_QUARANTINE")
    @ApiModelProperty(value = "是否处于隔离状态", name = "isQuarantine")
    private Integer isQuarantine;

    @TableField("QUARANTINE_POLICY")
    @ApiModelProperty(value = "隔离政策", name = "quarantinePolicy")
    private String quarantinePolicy;

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

    public PersonnelPO(Long id, String personnelCode, String personnelName, String personnelAge, String phone, String communityCode, String areaCode, String buildCode, String houseNumber, Integer personnelState, Integer natResult, Integer isUd, String inoculate, String sevenDayTrip, Integer isQuarantine, String quarantinePolicy, String createdBy, String createdAt, String updatedBy, String updatedAt, Integer version) {
        Id = id;
        this.personnelCode = personnelCode;
        this.personnelName = personnelName;
        this.personnelAge = personnelAge;
        this.phone = phone;
        this.communityCode = communityCode;
        this.areaCode = areaCode;
        this.buildCode = buildCode;
        this.houseNumber = houseNumber;
        this.personnelState = personnelState;
        this.natResult = natResult;
        this.isUd = isUd;
        this.inoculate = inoculate;
        this.sevenDayTrip = sevenDayTrip;
        this.isQuarantine = isQuarantine;
        this.quarantinePolicy = quarantinePolicy;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.version = version;
    }

    public PersonnelPO() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getPersonnelCode() {
        return personnelCode;
    }

    public void setPersonnelCode(String personnelCode) {
        this.personnelCode = personnelCode;
    }

    public String getPersonnelName() {
        return personnelName;
    }

    public void setPersonnelName(String personnelName) {
        this.personnelName = personnelName;
    }

    public String getPersonnelAge() {
        return personnelAge;
    }

    public void setPersonnelAge(String personnelAge) {
        this.personnelAge = personnelAge;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getPersonnelState() {
        return personnelState;
    }

    public void setPersonnelState(Integer personnelState) {
        this.personnelState = personnelState;
    }

    public Integer getNatResult() {
        return natResult;
    }

    public void setNatResult(Integer natResult) {
        this.natResult = natResult;
    }

    public Integer getIsUd() {
        return isUd;
    }

    public void setIsUd(Integer isUd) {
        this.isUd = isUd;
    }

    public String getInoculate() {
        return inoculate;
    }

    public void setInoculate(String inoculate) {
        this.inoculate = inoculate;
    }

    public String getSevenDayTrip() {
        return sevenDayTrip;
    }

    public void setSevenDayTrip(String sevenDayTrip) {
        this.sevenDayTrip = sevenDayTrip;
    }

    public Integer getIsQuarantine() {
        return isQuarantine;
    }

    public void setIsQuarantine(Integer isQuarantine) {
        this.isQuarantine = isQuarantine;
    }

    public String getQuarantinePolicy() {
        return quarantinePolicy;
    }

    public void setQuarantinePolicy(String quarantinePolicy) {
        this.quarantinePolicy = quarantinePolicy;
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
        return "PersonnelPO{" +
                "Id=" + Id +
                ", personnelCode='" + personnelCode + '\'' +
                ", personnelName='" + personnelName + '\'' +
                ", personnelAge='" + personnelAge + '\'' +
                ", phone='" + phone + '\'' +
                ", communityCode='" + communityCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", buildCode='" + buildCode + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", personnelState=" + personnelState +
                ", natResult=" + natResult +
                ", isUd=" + isUd +
                ", inoculate='" + inoculate + '\'' +
                ", sevenDayTrip='" + sevenDayTrip + '\'' +
                ", isQuarantine=" + isQuarantine +
                ", quarantinePolicy='" + quarantinePolicy + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", version=" + version +
                '}';
    }
}
