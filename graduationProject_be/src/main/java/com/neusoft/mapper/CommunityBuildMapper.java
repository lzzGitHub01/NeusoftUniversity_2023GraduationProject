package com.neusoft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.entity.dto.CommunityBuildDTO;
import com.neusoft.entity.dto.mangeInf.MangeInfDTO;
import com.neusoft.entity.po.CommunityBuildPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Myself
 * 2、楼Mapper(dao)
 */
@Repository //mapper接口标识为持久层组件
public interface CommunityBuildMapper extends BaseMapper<CommunityBuildPO> {

    /**
     * 查询所有建筑（支持模糊查询）--最高权限
     * @param page
     * @param po
     * @return
     */
    public Page<CommunityBuildDTO> selectAllBuildData(@Param("page") Page<CommunityBuildDTO> page, @Param("po")CommunityBuildPO po);

    /**
     * 查询所有建筑（支持模糊查询）--中级权限
     * @param page
     * @param po
     * @return
     */
    public Page<CommunityBuildDTO> selectAllBuildDataForLM(@Param("page") Page<CommunityBuildDTO> page, @Param("po")CommunityBuildPO po, @Param("mangeInf") MangeInfDTO mangeInfDTO);


    /**
     * 查询普通账户名下管理的楼组
     * @param manageName
     * @return
     */
    public String selectMangerBuild(String manageName);

    /**
     * 查询所有建筑（支持模糊查询）--普通权限
     * @param page
     * @param po
     * @param list
     * @param mangeInfDTO
     * @return
     */
    public Page<CommunityBuildDTO> selectAllBuildDataForLN(@Param("page") Page<CommunityBuildDTO> page, @Param("po")CommunityBuildPO po, @Param("list") List<String> list, @Param("mangeInf") MangeInfDTO mangeInfDTO);

}
