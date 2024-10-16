package com.neusoft.service.SystemManager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.entity.dto.SystemManagerDTO;
import com.neusoft.entity.dto.forLogin.LoginDTO;
import com.neusoft.entity.po.SystemManagerPO;
import io.swagger.models.auth.In;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author lzz
 */
public interface SystemManagerService extends IService<SystemManagerPO> {

    /**
     * 用户登陆接口
     * @param dto
     * @return
     */
    public LoginDTO userLogin(SystemManagerDTO dto);

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
     * 新增用户
     * @param dto
     * @return
     */
    public Integer addNewManager(SystemManagerDTO dto);

    /**
     * 查询所有用户信息--最高权限
     * @param dto
     * @return
     */
    public Page<SystemManagerPO> selectAllUser(SystemManagerDTO dto);

    /**
     * 查询所有用户信息--中级权限
     * @param dto
     * @return
     */
    public Page<SystemManagerPO> selectAllUserForLM(SystemManagerDTO dto);

    /**
     * 修改当前账户密码密码
     * @param dto
     * @return
     */
    public Integer changePassword(SystemManagerDTO dto);

    /**
     * 修改用户信息
     * @param dto
     * @return
     */
    public Integer changeInf(SystemManagerDTO dto);

    /**
     * 删除用户
     * @param dto
     * @return
     */
    public Integer deleteUser(SystemManagerDTO dto);

    /**
     * 获取当前登陆账户的信息
     * @return
     */
    public List<SystemManagerPO> selectCurrentAccount();

    /**
     * 当前登陆账户修改自己的基础数据
     * @return
     */
    public Integer updateCurrentAccount(SystemManagerDTO dto);

    /**
     * 修改其他账户的名称和密码
     * @param dto
     * @return
     */
    public Integer updateOtherManagerName(SystemManagerDTO dto);

    /**
     * 修改其他账户的密码
     * @param dto
     * @return
     */
    public Integer updateOtherManagerPassword(SystemManagerDTO dto);

    /**
     * 用户登出清除session会话
     * @return
     */
    public void cleanSession(HttpServletRequest request);
}
