package com.neusoft.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.entity.dto.SystemManagerDTO;
import com.neusoft.entity.dto.forLogin.LoginDTO;
import com.neusoft.entity.po.SystemManagerPO;
import com.neusoft.entity.vo.SystemManagerVO;
import com.neusoft.enums.IsEnable;
import com.neusoft.service.SystemManager.SystemManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author lzz
 * 管理员
 */
@RestController
@RequestMapping("/systemManager")
public class SystemManagerController {

    @Autowired
    private SystemManagerService service;

    @Autowired
    private SystemManagerDTO dto;

    @Autowired
    HttpServletRequest request;

    /**
     * 用户登陆
     *
     * @return
     */
    @RequestMapping(value = "/userLogin1", method = RequestMethod.POST)
    public Integer userLogin1(@RequestBody SystemManagerVO vo) {
        //获取session
        HttpSession session = request.getSession();

        //VO传递数据到DTO
        dto.setManageName(vo.getManageName());
        dto.setPassword(vo.getPassword());

        return null;
    }

    /**
     * 用户登陆
     *
     * @return
     */
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public Integer userLogin(@RequestBody SystemManagerVO vo) {
        //获取session
        HttpSession session = request.getSession();

        //VO传递数据到DTO
        dto.setManageName(vo.getManageName());
        dto.setPassword(vo.getPassword());

        LoginDTO loginDTO = service.userLogin(dto);
        Integer result = loginDTO.getResult();

        //登陆成功存放用户信息到session中
        if (result == 1) {
            SystemManagerPO systemManagerPO = loginDTO.getSystemManagerPO();
            session.setAttribute("manageName", systemManagerPO.getManageName());
            session.setAttribute("phone", systemManagerPO.getPhone());
            session.setAttribute("manageLevel", systemManagerPO.getManageLevel());
            session.setAttribute("communityCode", systemManagerPO.getCommunityCode());
            if(!StringUtils.isEmpty(systemManagerPO.getAreaCode())){
                session.setAttribute("areaCode", systemManagerPO.getAreaCode());
            }
            if(!StringUtils.isEmpty(systemManagerPO.getBuildCode())){
                session.setAttribute("buildCode", systemManagerPO.getBuildCode());
            }
            System.out.println("============================================================用户信息已存放在session============================================================");
        }
        System.out.println("返回值："+result);
        return result;
    }

    /**
     * 查询当前账户等级
     *
     * @return
     */
    @RequestMapping(value = "/selectUserLevel", method = RequestMethod.GET)
    public String selectUserLevel(String manageName) {
        String userLevel = service.selectUserLevel(manageName);
        return userLevel;
    }

    /**
     * 查询当前账户所属社区
     *
     * @return
     */
    @RequestMapping(value = "/selectUserCommunity", method = RequestMethod.GET)
    public String selectUserCommunity(String manageName) {
        String communityName = service.selectUserCommunity(manageName);
        return communityName;
    }

    /**
     * 查询当前账户所属区域
     *
     * @return
     */
    @RequestMapping(value = "/selectUserArea", method = RequestMethod.GET)
    public String selectUserArea(String manageName) {
        String areaName = service.selectUserArea(manageName);
        return areaName;
    }

    /**
     * 查询当前账户所属楼组
     *
     * @return
     */
    @RequestMapping(value = "/selectUserBuild", method = RequestMethod.GET)
    public String selectUserBuild(String manageName) {
        String buildName = service.selectUserBuild(manageName);
        return buildName;
    }

    /**
     * 查询所有用户，用于展示（优化整合体）--最高权限
     *
     * @return
     */
    @RequestMapping(value = "/selectAllUser", method = RequestMethod.POST)
    public Page<SystemManagerPO> selectAllUser(@RequestBody SystemManagerVO vo) {
        System.out.println("执行！！！");
        //VO数据传递DTO
        dto.setId(vo.getId());
        dto.setManageCode(vo.getManageCode());
        dto.setManageName(vo.getManageName());
        dto.setManageLevel(vo.getManageLevel());
        dto.setIsEnable(vo.getIsEnable());
        dto.setPageCurrent(vo.getPageCurrent());

        Page<SystemManagerPO> systemManagerPOList = service.selectAllUser(dto);

        return systemManagerPOList;
    }

    /**
     * 查询所有用户，用于展示（优化整合体）--中级权限
     *
     * @return
     */
    @RequestMapping(value = "/selectAllUserForLM", method = RequestMethod.POST)
    public Page<SystemManagerPO> selectAllUserForLM(@RequestBody SystemManagerVO vo) {
        //VO数据传递DTO
        dto.setId(vo.getId());
        dto.setManageCode(vo.getManageCode());
        dto.setManageName(vo.getManageName());
        dto.setManageLevel(vo.getManageLevel());
        dto.setIsEnable(vo.getIsEnable());
        dto.setPageCurrent(vo.getPageCurrent());

        Page<SystemManagerPO> systemManagerPOList = service.selectAllUserForLM(dto);

        return systemManagerPOList;
    }

    /**
     * 添加新用户
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
    public Integer addNewUser(@RequestBody SystemManagerVO vo) {
        //VO传递数据到DTO对象
        dto.setManageName(vo.getManageName());
        dto.setPhone(vo.getPhone());
        dto.setManageLevel(vo.getManageLevel());
        if (!StringUtils.isEmpty(vo.getAreaCode())){
            dto.setAreaCode(vo.getAreaCode());
        }
        if (!StringUtils.isEmpty(vo.getBuildCode())){
            dto.setBuildCode(vo.getBuildCode());
        }
        dto.setIsEnable(vo.getIsEnable());

        Integer result = service.addNewManager(dto);

        return result;
    }

    /**
     * 修改当前账户密码密码
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @Transactional
    public Integer changePassword(@RequestBody SystemManagerVO vo) {

        //VO传递数据到DTO对象
        dto.setPassword(vo.getPassword());

        Integer result = service.changePassword(dto);

        return result;
    }

    /**
     * 修改用户基本信息
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/changeInf", method = RequestMethod.POST)
    public Integer changeInf(@RequestBody SystemManagerVO vo) {
        //VO传递数据到DTO对象
        dto.setId(vo.getId());
        dto.setPhone(vo.getPhone());
        dto.setManageLevel(vo.getManageLevel());
        dto.setIsEnable(vo.getIsEnable());

        Integer result = service.changeInf(dto);

        return result;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    @Transactional
    public Integer deleteUser(Long id) {
        System.out.println("ID数据："+id);
        //VO传递数据到DTO对象
        dto.setId(id);
        Integer result = service.deleteUser(dto);

        return result;
    }

    /**
     * 获取当前登陆账户的信息
     * @return
     */
    @RequestMapping(value = "/selectCurrentAccount", method = RequestMethod.GET)
    public List<SystemManagerPO> selectCurrentAccount(){
        List<SystemManagerPO> managerPOList = service.selectCurrentAccount();
        return managerPOList;
    }

    /**
     * 当前登陆账户修改自己的基础数据
     * @param vo
     * @return
     */
    @RequestMapping(value = "/updateCurrentAccount", method = RequestMethod.POST)
    public Integer updateCurrentAccount(@RequestBody SystemManagerVO vo){
        //vo传递数据到dto
        SystemManagerDTO systemManagerDTO = new SystemManagerDTO();
        systemManagerDTO.setManageName(vo.getManageName());
        systemManagerDTO.setPassword(vo.getPassword());

        Integer result = service.updateCurrentAccount(systemManagerDTO);
        return result;
    }

    /**
     * 修改其他账户的名称
     * @param vo
     * @return
     */
    @RequestMapping(value = "/updateOtherManagerName", method = RequestMethod.POST)
    public Integer updateOtherManagerName(@RequestBody SystemManagerVO vo){
        //vo传递数据到dto
        SystemManagerDTO systemManagerDTO = new SystemManagerDTO();
        systemManagerDTO.setManageName(vo.getManageName());
        systemManagerDTO.setManageCode(vo.getManageCode());
        Integer result = service.updateOtherManagerName(systemManagerDTO);
        return result;
    }

    /**
     * 修改其他账户的密码
     * @param vo
     * @return
     */
    @RequestMapping(value = "/updateOtherManagerPassword", method = RequestMethod.POST)
    public Integer updateOtherManagerPassword(@RequestBody SystemManagerVO vo){
        //vo传递数据到dto
        SystemManagerDTO systemManagerDTO = new SystemManagerDTO();
        systemManagerDTO.setPassword(vo.getPassword());
        systemManagerDTO.setManageName(vo.getManageName());
        Integer result = service.updateOtherManagerPassword(systemManagerDTO);
        return result;
    }

    /**
     * 用户登出
     */
    @RequestMapping(value = "/userExit", method = RequestMethod.GET)
    public void userExit() {
        service.cleanSession(request);
    }

}
