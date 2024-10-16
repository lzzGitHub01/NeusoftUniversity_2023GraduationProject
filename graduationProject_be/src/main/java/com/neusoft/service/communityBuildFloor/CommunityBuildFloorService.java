package com.neusoft.service.communityBuildFloor;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.entity.dto.CommunityBuildFloorDTO;
import com.neusoft.entity.po.CommunityBuildFloorPO;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @author lzz
 */
public interface CommunityBuildFloorService extends IService<CommunityBuildFloorPO> {

    /**
     * 查询所有楼层（支持模糊查询）
     * @param dto
     * @return
     */
    public Page<CommunityBuildFloorPO> selectAllBuildFloorData(CommunityBuildFloorDTO dto);

    /**
     * 新增楼层规则信息
     * @param dto
     * @return
     */
    public Integer addNewBuildFloor(CommunityBuildFloorDTO dto);

    /**
     * 根据ID删除楼层规则信息
     * @param id
     * @return
     */
    public Integer deleteBuildFloorById(Long id);

    /**
     * 根据ID修改楼层规则信息
     * @param dto
     * @return
     */
    public Integer updateBuildFloorInfById(CommunityBuildFloorDTO dto);

    /**
     * 根据areaCode，查询建筑规则的代码和楼层数信息，用于前台select选择器
     * @return
     */
    public List<CommunityBuildFloorPO> selectBuildForFloorSelect();

}
