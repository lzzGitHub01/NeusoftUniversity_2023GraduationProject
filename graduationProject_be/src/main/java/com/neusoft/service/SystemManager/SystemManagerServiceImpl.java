package com.neusoft.service.SystemManager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.entity.dto.SystemManagerDTO;
import com.neusoft.entity.dto.forLogin.LoginDTO;
import com.neusoft.entity.po.SystemManagerPO;
import com.neusoft.enums.GmLeave;
import com.neusoft.enums.IsEnable;
import com.neusoft.mapper.SystemManagerMapper;
import com.neusoft.unit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzz
 */
@Service
public class SystemManagerServiceImpl extends ServiceImpl<SystemManagerMapper, SystemManagerPO> implements SystemManagerService {

    @Autowired
    private SystemManagerMapper mapper;

    @Autowired
    HttpServletRequest request;


    /**
     * 用户登陆接口
     *
     * @param dto
     * @return Integer
     * @author lzz
     */
    @Override
    public LoginDTO userLogin(SystemManagerDTO dto) {
        //获取当前时间
        String currentTime = CurrentTimeUnit.getCurrentTime();
        //创建wrapper构造器
        UpdateWrapper<SystemManagerPO> updateWrapper = new UpdateWrapper<>();
        QueryWrapper<SystemManagerPO> queryWrapper = new QueryWrapper<>();

        //DTO传递数据到PO（用于登陆）
        SystemManagerPO po = new SystemManagerPO();
        po.setManageName(dto.getManageName());
        po.setPassword(dto.getPassword());

        //DTO传递数据到PO（用于记录登陆时间）
        SystemManagerPO systemManagerPO = new SystemManagerPO();

        //创建LoginDTO对象
        LoginDTO loginDTO = new LoginDTO();

        //执行用户登陆方法
        SystemManagerPO systemManager = mapper.userLogin(po);

        //接口返回结果
        Integer result = null;

        //判断密码是否正确
        if (systemManager != null) {
            //获取前台传来的密码
            String password = po.getPassword();
            //获取到登陆用户的加密密码和盐值
            String pass = systemManager.getPassword();
            String uuid = systemManager.getUuid();
            //根据指定用户的盐值和前台传来的password生成加密密码，并对比数据库中的加密密码
            String s = Md5Unit.passwordEncryption(password, uuid);
            if (s.equals(pass)) {
                result = 1;

                //查询上一次登陆时间
                queryWrapper.eq("MANAGE_NAME",dto.getManageName());
                List<SystemManagerPO> selectList = mapper.selectList(queryWrapper);
                for (SystemManagerPO managerPo : selectList) {
                    systemManagerPO.setLastLoginAt(managerPo.getLoginAt());
                }

                //更新当前登陆时间
                updateWrapper.eq("MANAGE_NAME",dto.getManageName());
                systemManagerPO.setLoginAt(currentTime);
                mapper.update(systemManagerPO,updateWrapper);

                System.out.println("==================================================================登陆成功！==================================================================");
            } else {
                result = 0;
                System.out.println("==================================================================登陆失败，密码错误==================================================================");
            }
        } else {
            result = 2;
            System.out.println("==================================================================登陆失败，用户不存在或未启用==================================================================");
        }

        loginDTO.setResult(result);
        loginDTO.setSystemManagerPO(systemManager);

        return loginDTO;
    }

    /**
     * 根据用户名称查询当前账户等级
     * @param manageName
     * @return
     */
    @Override
    public String selectUserLevel(String manageName) {
        String userLevel = mapper.selectUserLevel(manageName);
        return userLevel;
    }

    @Override
    public String selectUserCommunity(String manageName) {
        String communityName = mapper.selectUserCommunity(manageName);
        return communityName;
    }

    @Override
    public String selectUserArea(String manageName) {
        String areaName = mapper.selectUserArea(manageName);
        return areaName;
    }

    @Override
    public String selectUserBuild(String manageName) {
        String buildName = mapper.selectUserBuild(manageName);
        return buildName;
    }

    /**
     * 新增用户
     *
     * @param dto
     * @return
     */
    @Override
    public Integer addNewManager(SystemManagerDTO dto) {
        //生成UUID盐值
        String saltValue = Md5Unit.getSaltValue();
        //对密码进行加密处理
        String encryptionPassword = Md5Unit.passwordEncryption(DictionaryUnit.DEFAULT_PASSWORD, saltValue);
        //生成管理员code
        String code = MakeCodeUnit.makeUserCode(saltValue);
        //获取当前时间
        String currentTime = CurrentTimeUnit.getCurrentTime();
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //接口返回结果
        Integer result = null;

        //DTO传递数据到PO
        SystemManagerPO po = new SystemManagerPO();

        //查询数据库中用户名称是否重复
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("MANAGE_NAME", dto.getManageName());
        List<SystemManagerPO> selectList = mapper.selectByMap(userMap);

        if (selectList != null && selectList.size() != 0) {
            result = 0;
        } else {
            po.setManageCode(code);
            po.setManageName(dto.getManageName());
            po.setPassword(encryptionPassword);
            po.setPhone(dto.getPhone());
            po.setManageLevel(dto.getManageLevel());
            po.setCommunityCode(sessionCommunityCode);
            if (!StringUtils.isEmpty(dto.getAreaCode())){
                po.setAreaCode(dto.getAreaCode());
            }
            if (!StringUtils.isEmpty(dto.getBuildCode())){
                po.setBuildCode(dto.getBuildCode());
            }
            po.setIsEnable(dto.getIsEnable());
            po.setUuid(saltValue);
            po.setCreatedAt(currentTime);
            po.setCreatedBy(sessionUser);
            //执行新增操作
            result = mapper.insert(po);
        }

        if (result == 1) {
            System.out.println("==================================================================注册成功！==================================================================");
        } else {
            System.out.println("==================================================================注册失败！用户名已存在==================================================================");
        }

        return result;
    }

    @Override
    public Page<SystemManagerPO> selectAllUser(SystemManagerDTO dto) {
        SystemManagerPO po = new SystemManagerPO();
        //创建wrapper构造器
        QueryWrapper<SystemManagerPO> queryWrapper = new QueryWrapper<>();
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //创建分页插件
        Page<SystemManagerPO> page;

        if (dto.getId() != null || "".equals(dto.getId())){
            po.setId(dto.getId());
            queryWrapper.like("ID",po.getId());
        }
        if (dto.getManageCode() != null || "".equals(dto.getManageCode())){
            po.setManageCode(dto.getManageCode());
            queryWrapper.like("MANAGE_CODE",po.getManageCode());
        }
        if (dto.getManageName() != null || "".equals(dto.getManageName())){
            po.setManageName(dto.getManageName());
            queryWrapper.like("MANAGE_NAME",po.getManageName());
        }
        if (dto.getManageLevel() != null || "".equals(dto.getManageLevel())){
            po.setManageLevel(dto.getManageLevel());
            String manageLevelName = po.getManageLevel().toString();
            String manageLevelValue = GmLeave.valueOf(manageLevelName).getValue();
            queryWrapper.like("MANAGE_LEVEL",manageLevelValue);
        }
        if (dto.getIsEnable() != null || "".equals(dto.getIsEnable())){
            po.setIsEnable(dto.getIsEnable());
            String isEnableName = po.getIsEnable().toString();
            Integer isEnableValue = IsEnable.valueOf(isEnableName).getValue();
            queryWrapper.like("IS_ENABLE",isEnableValue);
        }
        queryWrapper.eq("COMMUNITY_CODE",sessionCommunityCode);
        po.setCommunityCode(sessionCommunityCode);
        if (dto.getPageCurrent() != null || "".equals(dto.getPageCurrent())){
            page = new Page<>(dto.getPageCurrent(),10);
        }else {
            page = new Page<>(1,10);
        }

        Page<SystemManagerPO> systemManagerPO = mapper.selectPage(page,queryWrapper);

        /*Page<SystemManagerPO> selectAllUser = mapper.selectAllUser(page, po);
        System.out.println("数据集："+selectAllUser);*/

        return systemManagerPO;
    }

    @Override
    public Page<SystemManagerPO> selectAllUserForLM(SystemManagerDTO dto) {
        //获取session中的用户名称
        //String sessionUser = ServletUnit.getSessionUser(request);
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //获取session中的所属区域code
        String sessionAreaCode = ServletUnit.getSessionAreaCode(request);
        //获取session中的用户的等级
        //String sessionLevel = ServletUnit.getSessionLevel(request);
        //创建wrapper构造器
        SystemManagerPO po = new SystemManagerPO();
        QueryWrapper<SystemManagerPO> queryWrapper = new QueryWrapper<>();

        //创建分页插件
        Page<SystemManagerPO> page;

        if (dto.getId() != null || "".equals(dto.getId())){
            po.setId(dto.getId());
            queryWrapper.like("ID",po.getId());
        }
        if (dto.getManageCode() != null || "".equals(dto.getManageCode())){
            po.setManageCode(dto.getManageCode());
            queryWrapper.like("MANAGE_CODE",po.getManageCode());
        }
        if (dto.getManageName() != null || "".equals(dto.getManageName())){
            po.setManageName(dto.getManageName());
            queryWrapper.like("MANAGE_NAME",po.getManageName());
        }
        if (dto.getManageLevel() != null || "".equals(dto.getManageLevel())){
            po.setManageLevel(dto.getManageLevel());
            String manageLevelName = po.getManageLevel().toString();
            String manageLevelValue = GmLeave.valueOf(manageLevelName).getValue();
            queryWrapper.like("MANAGE_LEVEL",manageLevelValue);
        }
        if (dto.getIsEnable() != null || "".equals(dto.getIsEnable())){
            po.setIsEnable(dto.getIsEnable());
            String isEnableName = po.getIsEnable().toString();
            Integer isEnableValue = IsEnable.valueOf(isEnableName).getValue();
            queryWrapper.like("IS_ENABLE",isEnableValue);
        }
        queryWrapper.eq("COMMUNITY_CODE",sessionCommunityCode);
        queryWrapper.eq("AREA_CODE",sessionAreaCode);

        if (dto.getPageCurrent() != null || "".equals(dto.getPageCurrent())){
            page = new Page<>(dto.getPageCurrent(),10);
        }else {
            page = new Page<>(1,10);
        }
        Page<SystemManagerPO> systemManagerPO = mapper.selectPage(page,queryWrapper);

        return systemManagerPO;
    }

    @Override
    public Integer changePassword(SystemManagerDTO dto) {
        //获取当前时间
        String currentTime = CurrentTimeUnit.getCurrentTime();
        //生成UUID盐值
        String saltValue = Md5Unit.getSaltValue();
        //对密码进行加密处理
        String encryptionPassword = Md5Unit.passwordEncryption(dto.getPassword(), saltValue);
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        //接口返回结果
        Integer result = null;

        //使用wrapper构造器
        UpdateWrapper<SystemManagerPO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("MANAGE_NAME", sessionUser);

        //DTO传递数据到PO
        SystemManagerPO po = new SystemManagerPO();
        po.setUpdatedAt(currentTime);
        po.setUpdatedBy(sessionUser);
        po.setPassword(encryptionPassword);
        po.setUuid(saltValue);

        //修改密码
        result = mapper.update(po, updateWrapper);

        if (result == 1) {
            System.out.println("==================================================================密码修改成功！==================================================================");
        } else {
            System.out.println("==================================================================密码修改失败！==================================================================");
        }

        return result;
    }

    @Override
    public Integer changeInf(SystemManagerDTO dto) {
        //获取当前时间
        String currentTime = CurrentTimeUnit.getCurrentTime();
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //使用wrapper构造器
        UpdateWrapper<SystemManagerPO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("ID", dto.getId())
                .eq("COMMUNITY_CODE",sessionCommunityCode);
        //接口返回结果
        Integer result = null;

        //DTO传递数据到PO
        SystemManagerPO po = new SystemManagerPO();
        po.setPhone(dto.getPhone());
        po.setManageLevel(dto.getManageLevel());
        po.setIsEnable(dto.getIsEnable());
        po.setUpdatedAt(currentTime);
        po.setUpdatedBy(sessionUser);

        //修改密码
        result = mapper.update(po, updateWrapper);

        if (result == 1) {
            System.out.println("==================================================================信息修改成功！==================================================================");
        } else {
            System.out.println("==================================================================信息修改失败！==================================================================");
        }

        return result;
    }

    @Override
    public Integer deleteUser(SystemManagerDTO dto) {
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //创建Wrapper构造器
        QueryWrapper<SystemManagerPO> queryWrapper = new QueryWrapper();
        queryWrapper.like("COMMUNITY_CODE",sessionCommunityCode);
        //返回结果
        Integer result = 100;

        queryWrapper.like("ID",dto.getId());
        List<SystemManagerPO> managerList = mapper.selectList(queryWrapper);
        for (SystemManagerPO po : managerList) {
            boolean equals = sessionUser.equals(po.getManageName());
            System.out.println("数据："+po.getManageName()+equals);
            if (sessionUser.equals(po.getManageName())){
                result = 2;
            }else {
                if (result == 2){
                    System.out.println("=============================================================不能删除当前登陆账户！=============================================================");
                }else {
                    result = mapper.deleteById(dto.getId());
                    if (result == 1) {
                        System.out.println("==================================================================删除成功！==================================================================");
                    }else {
                        System.out.println("==================================================================删除失败！==================================================================");
                    }
                }
            }
        }

        return result;
    }

    @Override
    public List<SystemManagerPO> selectCurrentAccount() {
        //创建Wrapper构造器
        QueryWrapper<SystemManagerPO> queryWrapper = new QueryWrapper();
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);

        //查询出当前账户的信息
        queryWrapper.eq("MANAGE_NAME",sessionUser);
        List<SystemManagerPO> managerPOList = mapper.selectList(queryWrapper);

        return managerPOList;
    }

    @Override
    public Integer updateCurrentAccount(SystemManagerDTO dto) {
        //获取当前时间
        String currentTime = CurrentTimeUnit.getCurrentTime();
        //获取session
        HttpSession session = request.getSession();
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //生成UUID盐值
        String saltValue = Md5Unit.getSaltValue();
        //返回结果
        Integer result = null;
        //创建Wrapper构造器
        UpdateWrapper<SystemManagerPO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("COMMUNITY_CODE",sessionCommunityCode)
                .eq("MANAGE_NAME",sessionUser);

        //dto传递数据到po
        SystemManagerPO po = new SystemManagerPO();
        po.setUpdatedAt(currentTime);
        po.setUpdatedBy(sessionUser);
        if (!StringUtils.isEmpty(dto.getPassword())){
            //对密码进行加密处理
            String encryptionPassword = Md5Unit.passwordEncryption(dto.getPassword(), saltValue);
            po.setPassword(encryptionPassword);
        }
        if (!StringUtils.isEmpty(dto.getManageName())){
            //判断是否存在重复名称
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("MANAGE_NAME", dto.getManageName());
            List<SystemManagerPO> selectList = mapper.selectByMap(userMap);
            if (selectList.size() > 0){
                result = 3;
                System.out.println("==================================================================修改失败！用户名已存在==================================================================");
            }else {
                po.setManageName(dto.getManageName());
                result = mapper.update(po, updateWrapper);
                if (result == 1){
                    //更新为session中的数据
                    session.setAttribute("manageName", po.getManageName());
                    System.out.println("==================================================================修改成功！==================================================================");
                }else {
                    System.out.println("==================================================================修改失败！==================================================================");
                }
            }
        }

        return result;
    }

    @Override
    public Integer updateOtherManagerName(SystemManagerDTO dto) {
        //获取当前时间
        String currentTime = CurrentTimeUnit.getCurrentTime();
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //创建Wrapper构造器
        UpdateWrapper<SystemManagerPO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("COMMUNITY_CODE",sessionCommunityCode);
        //返回结果
        Integer result = null;

        //dto传递数据到po
        SystemManagerPO po = new SystemManagerPO();
        po.setUpdatedAt(currentTime);
        po.setUpdatedBy(sessionUser);
        if (!StringUtils.isEmpty(dto.getManageCode())){
            updateWrapper.like("MANAGE_CODE",dto.getManageCode());
        }
        if (!StringUtils.isEmpty(dto.getManageName())){
            //判断是否存在重复名称
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("MANAGE_NAME", dto.getManageName());
            List<SystemManagerPO> selectList = mapper.selectByMap(userMap);
            if (selectList.size() > 0){
                result = 3;
                System.out.println("==================================================================修改失败！用户名已存在==================================================================");
            }else {

                po.setManageName(dto.getManageName());
                result = mapper.update(po, updateWrapper);
                if (result == 1){
                    System.out.println("==================================================================修改成功！==================================================================");
                }else {
                    System.out.println("==================================================================修改失败！==================================================================");
                }
            }
        }
        return result;
    }

    @Override
    public Integer updateOtherManagerPassword(SystemManagerDTO dto) {
        //获取当前时间
        String currentTime = CurrentTimeUnit.getCurrentTime();
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        //生成UUID盐值
        String saltValue = Md5Unit.getSaltValue();
        //对密码进行加密处理
        String encryptionPassword = Md5Unit.passwordEncryption(dto.getPassword(), saltValue);
        //接口返回结果
        Integer result = null;

        //使用wrapper构造器
        UpdateWrapper<SystemManagerPO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("MANAGE_NAME", dto.getManageName());

        //dto传递数据到po
        SystemManagerPO po = new SystemManagerPO();
        po.setManageName(dto.getManageName());
        po.setPassword(encryptionPassword);
        po.setUpdatedAt(currentTime);
        po.setUpdatedBy(sessionUser);
        po.setUuid(saltValue);

        //修改密码
        result = mapper.update(po, updateWrapper);
        System.out.println("返回结果："+result);

        if (result == 1) {
            System.out.println("==================================================================密码修改成功！==================================================================");
        } else {
            System.out.println("==================================================================密码修改失败！==================================================================");
        }

        return result;
    }

    @Override
    public void cleanSession(HttpServletRequest request) {

        //获取session中的数据
        HttpSession session = request.getSession();

        System.out.println("================================================================准备清除会话！================================================================");
        // 清除Session
        session.invalidate();

        //捕获异常
        try{
            session.getAttribute("manageName");
        }catch (IllegalStateException e){
            System.out.println("==========================================="+e.getLocalizedMessage()+"（会话已失效）============================================");
        }

        System.out.println("================================================================会话清除成功！================================================================");
    }
}
