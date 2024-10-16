package com.neusoft.entity.dto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author lzz
 * 1、区域表PO实体类
 */

@Getter
@Setter
@ToString
@Component
public class SelectAllAreaDTO {

    private Long Id;

    private String areaCode;

    private String areaName;

    private Integer areaRisk;

    private String createdBy;

    private String createdAt;

    private String updatedBy;

    private String updatedAt;

    private Integer version;

    private String codeName;

    private Integer pageCurrent;

    private String communityCode;
}
