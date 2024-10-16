package com.neusoft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neusoft.entity.po.CommunityBuildResidentPO;
import org.springframework.stereotype.Repository;

/**
 * @author lzz
 * 4、住户Mapper(dao)
 */
@Repository //mapper接口标识为持久层组件
public interface CommunityBuildResidentMapper extends BaseMapper<CommunityBuildResidentPO> {
}
