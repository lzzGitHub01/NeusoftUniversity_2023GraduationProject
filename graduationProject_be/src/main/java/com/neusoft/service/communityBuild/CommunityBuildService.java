package com.neusoft.service.communityBuild;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.entity.dto.CommunityBuildDTO;
import com.neusoft.entity.po.CommunityBuildPO;

import java.util.List;

/**
 * @author lzz
 */
public interface CommunityBuildService extends IService<CommunityBuildPO> {

    /**
     * 查询所有建筑（支持模糊查询）--最高权限
     * @param dto
     * @return
     */
    public Page<CommunityBuildDTO> selectAllBuildDataLH(CommunityBuildDTO dto);

    /**
     * 查询所有建筑（支持模糊查询）--中级权限
     * @param dto
     * @return
     */
    public Page<CommunityBuildDTO> selectAllBuildDataForLM(CommunityBuildDTO dto);

    /**
     * 查询所有建筑（支持模糊查询）--普通权限
     * @param dto
     * @return
     */
    public Page<CommunityBuildDTO> selectAllBuildDataForLN(CommunityBuildDTO dto);

    /**
     * 新增建筑
     * @param dto
     * @return
     */
    public Integer addNewBuild(CommunityBuildDTO dto);

    /**
     * 根据ID删除建筑
     * @param id
     * @return
     */
    public Integer deleteBuildById(Long id);

    /**
     * 根据ID修改建筑信息
     * @param dto
     * @return
     */
    public Integer updateBuildInfById(CommunityBuildDTO dto);

    /**
     * 根据areaCode，查询建筑的代码和名称，用于前台select选择器
     * @param dto
     * @return
     */
    public List<CommunityBuildPO> selectBuildForSelect(CommunityBuildDTO dto);

}
