package com.neusoft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neusoft.entity.po.CommunityBuildFloorPO;
import org.springframework.stereotype.Repository;

/**
 * @author lzz
 * 3、楼层Mapper(dao)
 */
@Repository //mapper接口标识为持久层组件
public interface CommunityBuildFloorMapper extends BaseMapper<CommunityBuildFloorPO> {

    /**
     * 根据社区code获取社区名称
     * @param communityCode
     * @return
     */
    public String getCommunityNameByCode(String communityCode);
}
