package com.neusoft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.entity.dto.SelectAllAreaDTO;
import com.neusoft.entity.dto.infectionStatus.AreaInfectionSituationDTO;
import com.neusoft.entity.po.CommunityAreaPO;
import com.neusoft.entity.vo.CommunityAreaVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzz
 * 1、区域Mapper(dao)
 */
@Repository //mapper接口标识为持久层组件
public interface CommunityAreaMapper extends BaseMapper<CommunityAreaPO> {

    /**
     * 查询当前登陆账户的账户等级
     * @param managerName
     * @return
     */
    public String selectCurrentUserLevel(String managerName);

    /**
     * 查询所有区域数据（最高权限者-整合优化版本）
     * @return
     */
    public Page<SelectAllAreaDTO> selectAllAreaData(@Param("page") Page<SelectAllAreaDTO> page,@Param("po")CommunityAreaPO po);

    /**
     * 分页查询所有区域数据（最高权限者）--停用
     * @param page
     * @return
     */
    public Page<SelectAllAreaDTO> selectAllAreaDataPage(@Param("page") Page<SelectAllAreaDTO> page);

    /**
     * 根据提供的条件查询所有对应的区域数据（支持模糊查询）--停用
     * @param po
     * @return
     */
    public Page<SelectAllAreaDTO> selectAreaDataByCondition(@Param("page") Page<SelectAllAreaDTO> page,@Param("po")CommunityAreaPO po);

    /**
     * 社区感染情况展示(区域，及其总人口数)
     * @param sessionCommunityCode
     * @return
     */
    public List<AreaInfectionSituationDTO> selectAreaAndPersonalCount(String sessionCommunityCode);

    /**
     * 社区感染情况展示(感染人数)
     * @param sessionCommunityCode
     * @param areaCode
     * @return
     */
        public Integer selectMasculineCount(String sessionCommunityCode,String areaCode);
}
