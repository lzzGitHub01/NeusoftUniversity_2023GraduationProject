package com.neusoft.service.personnel;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.entity.dto.PersonnelDTO;
import com.neusoft.entity.dto.infectionStatus.InfectionStatusDTO;
import com.neusoft.entity.po.PersonnelPO;

import java.util.List;

/**
 * @author lzz
 */
public interface PersonnelService extends IService<PersonnelPO> {

    /**
     * 查询所有人员（支持模糊查询）--最高权限
     * @param dto
     * @return
     */
    public Page<PersonnelDTO> selectAllPersonnelData(PersonnelDTO dto);

    /**
     * 查询所有人员（支持模糊查询）--中级权限
     * @param dto
     * @return
     */
    public Page<PersonnelDTO> selectAllPersonnelDataForLM(PersonnelDTO dto);

    /**
     * 查询所有人员（支持模糊查询）--普通权限
     * @param dto
     * @return
     */
    public Page<PersonnelDTO> selectAllPersonnelDataForLN(PersonnelDTO dto);

    /**
     * 新增人员
     * @param dto
     * @return
     */
    public Integer addNewPersonal(PersonnelDTO dto);

    /**
     * 根据ID删除人员
     * @param id
     * @return
     */
    public Integer deletePersonalById(Long id);

    /**
     * 更新人员信息
     * @param dto
     * @return
     */
    public Integer updatePersonalInfById(PersonnelDTO dto);

    /**
     * 社区人员统计--最高权限
     * @return
     */
    public InfectionStatusDTO infectionStatusForLH();

    /**
     * 社区人员统计--最高权限
     * @return
     */
    public InfectionStatusDTO infectionStatusForLM();

    /**
     * 社区人员统计--最高权限
     * @return
     */
    public InfectionStatusDTO infectionStatusForLN();
}
