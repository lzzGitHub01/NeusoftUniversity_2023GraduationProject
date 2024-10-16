package com.neusoft.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.entity.dto.SystemManagerDTO;
import com.neusoft.entity.po.SystemManagerPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author lzz
 * 管理员Mapper(dao)
 */
@Repository //mapper接口标识为持久层组件
public interface SystemManagerMapper extends BaseMapper<SystemManagerPO> {

    /**
     * 用户登陆接口
     * @param po
     * @return
     */
    public SystemManagerPO userLogin(SystemManagerPO po);

    /**
     * 根据用户名称查询当前账户等级
     * @param manageName
     * @return
     */
    public String selectUserLevel(String manageName);

    /**
     * 根据用户名称查询当前账户所属社区
     * @param manageName
     * @return
     */
    public String selectUserCommunity(String manageName);

    /**
     * 根据用户名称查询当前账户所属区域
     * @param manageName
     * @return
     */
    public String selectUserArea(String manageName);

    /**
     * 根据用户名称查询当前账户所属楼组
     * @param manageName
     * @return
     */
    public String selectUserBuild(String manageName);

    /**
     * 查询所有账户信息--最高权限
     * @param page
     * @param po
     * @return
     */
    public Page<SystemManagerPO> selectAllUser(@Param("page") Page<SystemManagerPO> page, @Param("po") SystemManagerPO po);

}
