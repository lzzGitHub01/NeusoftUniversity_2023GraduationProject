package com.neusoft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.entity.dto.CommunityDTO;
import com.neusoft.entity.po.CommunityPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author lzz
 */
@Repository
public interface CommunityMapper extends BaseMapper<CommunityPO> {

    /**
     * 查询所有社区数据
     * @param page
     * @param po
     * @return
     */
    public Page<CommunityDTO> selectAllCommunity(@Param("page") Page<CommunityPO> page, @Param("po")CommunityPO po);
}
