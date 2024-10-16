package com.neusoft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseMapper;
import com.neusoft.entity.dto.PersonnelDTO;
import com.neusoft.entity.dto.excelExport.PersonalExcelDTO;
import com.neusoft.entity.dto.mangeInf.MangeInfDTO;
import com.neusoft.entity.po.PersonnelPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzz
 * 5、人员Mapper(dao)
 */
@Repository //mapper接口标识为持久层组件
public interface PersonnelMapper extends BaseMapper<PersonnelPO> {

    /**
     * 查询所有人员（支持模糊查询）--最高权限
     * @param page
     * @param po
     * @return
     */
    public Page<PersonnelDTO> selectAllPersonnelData(@Param("page") Page<PersonnelPO> page, @Param("po")PersonnelPO po);

    /**
     * 查询所有人员（支持模糊查询）--中级权限
     * @param page
     * @param po
     * @return
     */
    public Page<PersonnelDTO> selectAllPersonnelDataForLM(@Param("page") Page<PersonnelPO> page, @Param("po")PersonnelPO po, @Param("mangeInf") MangeInfDTO mangeInfDTO);

    /**
     * 查询所有人员（支持模糊查询）--普通权限
     * @param page
     * @param po
     * @return
     */
    public Page<PersonnelDTO> selectAllPersonnelDataForLN(@Param("page") Page<PersonnelPO> page, @Param("po")PersonnelPO po, @Param("list") List<String> list, @Param("mangeInf") MangeInfDTO mangeInfDTO);

    /**
     * 查询当前社区总人口--最高权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectCountNumber(String sessionCommunityCode);

    /**
     * 查询存在基础病人口--最高权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectIsUdNumber(String sessionCommunityCode);

    /**
     * 查询阴性人口--最高权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectFeminineNumber(String sessionCommunityCode);

    /**
     * 查询阳性人口--最高权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectMasculineNumber(String sessionCommunityCode);

    /**
     * 查询租户人口--最高权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectTenantNumber(String sessionCommunityCode);

    /**
     * 查询住户人口--最高权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectHoldNumber(String sessionCommunityCode);

    /**
     * 统计未接种人数--最高权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectNoDoseNumber(String sessionCommunityCode);

    /**
     * 统计接种一针人数--最高权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectOneDoseNumber(String sessionCommunityCode);

    /**
     * 统计接种两针人数--最高权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectTwoDoseNumber(String sessionCommunityCode);

    /**
     * 统计接种三针人数--最高权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectThreeDoseNumber(String sessionCommunityCode);

    /**
     * 统计接种四针人数--最高权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectForeDoseNumber(String sessionCommunityCode);

    /*public List<PersonalExcelDTO> selectExcelData(String sessionCommunityCode);*/


    /**
     * 查询当前社区总人口--中级权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectCountNumberForLM(String sessionCommunityCode, String sessionAreaCode);

    /**
     * 查询存在基础病人口--中级权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectIsUdNumberForLM(String sessionCommunityCode, String sessionAreaCode);

    /**
     * 查询阴性人口--中级权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectFeminineNumberForLM(String sessionCommunityCode, String sessionAreaCode);

    /**
     * 查询阳性人口--中级权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectMasculineNumberForLM(String sessionCommunityCode, String sessionAreaCode);

    /**
     * 查询租户人口--中级权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectTenantNumberForLM(String sessionCommunityCode, String sessionAreaCode);

    /**
     * 查询住户人口--中级权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectHoldNumberForLM(String sessionCommunityCode, String sessionAreaCode);

    /**
     * 统计未接种人数--中级权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectNoDoseNumberForLM(String sessionCommunityCode, String sessionAreaCode);

    /**
     * 统计接种一针人数--中级权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectOneDoseNumberForLM(String sessionCommunityCode, String sessionAreaCode);

    /**
     * 统计接种两针人数--中级权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectTwoDoseNumberForLM(String sessionCommunityCode, String sessionAreaCode);

    /**
     * 统计接种三针人数--中级权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectThreeDoseNumberForLM(String sessionCommunityCode, String sessionAreaCode);

    /**
     * 统计接种四针人数--中级权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectForeDoseNumberForLM(String sessionCommunityCode, String sessionAreaCode);

    /**
     * 查询当前社区总人口--普通权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectCountNumberForLN(String sessionCommunityCode, String sessionAreaCode, @Param("list") List<String> list);

    /**
     * 查询存在基础病人口--普通权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectIsUdNumberForLN(String sessionCommunityCode, String sessionAreaCode, @Param("list") List<String> list);

    /**
     * 查询阴性人口--普通权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectFeminineNumberForLN(String sessionCommunityCode, String sessionAreaCode, @Param("list") List<String> list);

    /**
     * 查询阳性人口--普通权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectMasculineNumberForLN(String sessionCommunityCode, String sessionAreaCode, @Param("list") List<String> list);

    /**
     * 查询租户人口--普通权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectTenantNumberForLN(String sessionCommunityCode, String sessionAreaCode, @Param("list") List<String> list);

    /**
     * 查询住户人口--普通权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectHoldNumberForLN(String sessionCommunityCode, String sessionAreaCode, @Param("list") List<String> list);

    /**
     * 统计未接种人数--普通权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectNoDoseNumberForLN(String sessionCommunityCode, String sessionAreaCode, @Param("list") List<String> list);

    /**
     * 统计接种一针人数--普通权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectOneDoseNumberForLN(String sessionCommunityCode, String sessionAreaCode, @Param("list") List<String> list);

    /**
     * 统计接种两针人数--普通权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectTwoDoseNumberForLN(String sessionCommunityCode, String sessionAreaCode, @Param("list") List<String> list);

    /**
     * 统计接种三针人数--普通权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectThreeDoseNumberForLN(String sessionCommunityCode, String sessionAreaCode, @Param("list") List<String> list);

    /**
     * 统计接种四针人数--普通权限
     * @param sessionCommunityCode
     * @return
     */
    public Integer selectForeDoseNumberForLN(String sessionCommunityCode, String sessionAreaCode, @Param("list") List<String> list);

}
