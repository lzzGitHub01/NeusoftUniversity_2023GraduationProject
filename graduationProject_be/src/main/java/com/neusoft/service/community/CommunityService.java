package com.neusoft.service.community;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.entity.dto.CommunityDTO;
import com.neusoft.entity.po.CommunityPO;

/**
 * @author lzz
 */
public interface CommunityService extends IService<CommunityPO> {

    /**
     * 查询所有社区（支持模糊查询）
     * @param dto
     * @return
     */
    public Page<CommunityDTO> selectAllCommunity(CommunityDTO dto);

    /**
     * 新增社区
     * @param dto
     * @return
     */
    public Integer addNewCommunity(CommunityDTO dto);

    /**
     * 根据ID删除社区
     * @param id
     * @return
     */
    public Integer deleteCommunityById(Long id);

    /**
     * 根据ID修改社区名称
     * @param dto
     * @return
     */
    public Integer updatePersonalInfById(CommunityDTO dto);
}
