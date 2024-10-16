package com.neusoft.service.communityBuild;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.entity.dto.CommunityBuildDTO;
import com.neusoft.entity.dto.mangeInf.MangeInfDTO;
import com.neusoft.entity.po.CommunityAreaPO;
import com.neusoft.entity.po.CommunityBuildPO;
import com.neusoft.enums.GmLeave;
import com.neusoft.mapper.CommunityAreaMapper;
import com.neusoft.mapper.CommunityBuildMapper;
import com.neusoft.mapper.MackTimeStampCodeMapper;
import com.neusoft.unit.CurrentTimeUnit;
import com.neusoft.unit.MakeCodeUnit;
import com.neusoft.unit.ServletUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzz
 */
@Service
public class CommunityBuildServiceImpl extends ServiceImpl<CommunityBuildMapper, CommunityBuildPO> implements CommunityBuildService {

    @Autowired
    private CommunityBuildMapper mapper;

    @Autowired
    private CommunityAreaMapper areaMapper;

    @Autowired
    private MackTimeStampCodeMapper codeMapper;

    @Autowired
    HttpServletRequest request;

    @Override
    public Page<CommunityBuildDTO> selectAllBuildDataLH(CommunityBuildDTO dto) {
        //创建分页插件
        Page<CommunityBuildDTO> page;
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //获取session中的用户的等级
        String sessionLevel = ServletUnit.getSessionLevel(request);

        //dto传递数据到po
        CommunityBuildPO po = new CommunityBuildPO();
        if (!StringUtils.isEmpty(dto.getId())){
            po.setId(Long.valueOf(dto.getId()));
        }
        if (!StringUtils.isEmpty(dto.getBuildCode())){
            po.setBuildCode(dto.getBuildCode());
        }
        if (!StringUtils.isEmpty(dto.getAreaCode())){
            po.setAreaCode(dto.getAreaCode());
        }
        if (!StringUtils.isEmpty(dto.getBuildName())){
            po.setBuildName(dto.getBuildName());
        }
        if (dto.getBuildRisk() != null || "".equals(dto.getBuildRisk())){
            po.setBuildRisk(dto.getBuildRisk());
        }
        if (!StringUtils.isEmpty(sessionCommunityCode)){
            po.setCommunityCode(sessionCommunityCode);
        }

        if (dto.getPageCurrent() != null || "".equals(dto.getPageCurrent())){
            page = new Page<>(dto.getPageCurrent(),10);
        }else {
            page = new Page<>(1,10);
        }

        Page<CommunityBuildDTO> buildData = mapper.selectAllBuildData(page, po);

        return buildData;
    }

    @Override
    public Page<CommunityBuildDTO> selectAllBuildDataForLM(CommunityBuildDTO dto) {
        //创建分页插件
        Page<CommunityBuildDTO> page;
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //获取session中的所属区域code
        String sessionAreaCode = ServletUnit.getSessionAreaCode(request);
        //获取session中的用户的等级
        String sessionLevel = ServletUnit.getSessionLevel(request);

        //存放当前登陆账户的信息
        MangeInfDTO mangeInfDTO = new MangeInfDTO();
        mangeInfDTO.setManageName(sessionUser);
        mangeInfDTO.setManageLevel(GmLeave.valueOf(sessionLevel));
        mangeInfDTO.setAreaCode(sessionAreaCode);

        //dto传递数据到po
        CommunityBuildPO po = new CommunityBuildPO();
        if (!StringUtils.isEmpty(dto.getId())){
            po.setId(Long.valueOf(dto.getId()));
        }
        if (!StringUtils.isEmpty(dto.getBuildCode())){
            po.setBuildCode(dto.getBuildCode());
        }
        if (!StringUtils.isEmpty(dto.getAreaCode())){
            po.setAreaCode(dto.getAreaCode());
        }
        if (!StringUtils.isEmpty(dto.getBuildName())){
            po.setBuildName(dto.getBuildName());
        }
        if (dto.getBuildRisk() != null || "".equals(dto.getBuildRisk())){
            po.setBuildRisk(dto.getBuildRisk());
        }
        if (!StringUtils.isEmpty(sessionCommunityCode)){
            po.setCommunityCode(sessionCommunityCode);
        }

        if (dto.getPageCurrent() != null || "".equals(dto.getPageCurrent())){
            page = new Page<>(dto.getPageCurrent(),10);
        }else {
            page = new Page<>(1,10);
        }

        Page<CommunityBuildDTO> buildData = mapper.selectAllBuildDataForLM(page, po, mangeInfDTO);

        return buildData;
    }

    @Override
    public Page<CommunityBuildDTO> selectAllBuildDataForLN(CommunityBuildDTO dto) {
        //创建分页插件
        Page<CommunityBuildDTO> page;
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //获取session中的所属区域code
        String sessionAreaCode = ServletUnit.getSessionAreaCode(request);
        //获取session中的用户的等级
        String sessionLevel = ServletUnit.getSessionLevel(request);

        //存放当前登陆账户的信息
        MangeInfDTO mangeInfDTO = new MangeInfDTO();
        mangeInfDTO.setManageName(sessionUser);
        mangeInfDTO.setManageLevel(GmLeave.valueOf(sessionLevel));
        mangeInfDTO.setAreaCode(sessionAreaCode);

        //存放楼组code的集合
        List<String> buildInf = new ArrayList<>();
        //查询，当前登陆的普通权限账户下，的所有楼组code
        String build = mapper.selectMangerBuild(sessionUser);
        //将查出的楼组code数据存入集合中
        String[] split = build.split(",");
        for (int i = 0; i < split.length; i++) {
            buildInf.add(split[i]);
        }

        //dto传递数据到po
        CommunityBuildPO po = new CommunityBuildPO();
        if (!StringUtils.isEmpty(dto.getId())){
            po.setId(Long.valueOf(dto.getId()));
        }
        if (!StringUtils.isEmpty(dto.getBuildCode())){
            po.setBuildCode(dto.getBuildCode());
        }
        if (!StringUtils.isEmpty(dto.getAreaCode())){
            po.setAreaCode(dto.getAreaCode());
        }
        if (!StringUtils.isEmpty(dto.getBuildName())){
            po.setBuildName(dto.getBuildName());
        }
        if (dto.getBuildRisk() != null || "".equals(dto.getBuildRisk())){
            po.setBuildRisk(dto.getBuildRisk());
        }
        if (!StringUtils.isEmpty(sessionCommunityCode)){
            po.setCommunityCode(sessionCommunityCode);
        }

        if (dto.getPageCurrent() != null || "".equals(dto.getPageCurrent())){
            page = new Page<>(dto.getPageCurrent(),10);
        }else {
            page = new Page<>(1,10);
        }

        Page<CommunityBuildDTO> buildData = mapper.selectAllBuildDataForLN(page, po, buildInf, mangeInfDTO);

        return buildData;
    }

    @Override
    public Integer addNewBuild(CommunityBuildDTO dto) {
        //获取当前时间
        String currentTime = CurrentTimeUnit.getCurrentTime();
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //创建wrapper构造器
        QueryWrapper<CommunityAreaPO> queryWrapper = new QueryWrapper<>();
        //取出对应区域名称的区域code
        /*String areaCode = null;
        queryWrapper.like("AREA_NAME",dto.getAreaName());
        List<CommunityAreaPO> areaList = areaMapper.selectList(queryWrapper);
        for (CommunityAreaPO communityAreaPO : areaList) {
            areaCode = communityAreaPO.getAreaCode();
        }*/

        String buildCode = makeBuildCode();

        //dto传递数据到po
        CommunityBuildPO po = new CommunityBuildPO();
        po.setBuildCode(buildCode);
        po.setAreaCode(dto.getAreaCode());
        po.setBuildName(dto.getBuildName());
        po.setBuildRisk(dto.getBuildRisk());
        po.setCreatedBy(sessionUser);
        po.setCreatedAt(currentTime);
        po.setCommunityCode(sessionCommunityCode);
        po.setBuildFloorCode(dto.getBuildFloorCode());

        int result = mapper.insert(po);

        if (result == 1){
            System.out.println("=================================================================建筑新增成功！=================================================================");
        }else {
            System.out.println("=================================================================建筑新增失败！=================================================================");
        }

        return result;
    }

    @Override
    public Integer deleteBuildById(Long id) {
        int result = mapper.deleteById(id);

        if (result == 1) {
            System.out.println("=================================================================建筑删除成功！=================================================================");
        } else {
            System.out.println("=================================================================建筑删除失败！=================================================================");
        }

        return result;
    }

    @Override
    public Integer updateBuildInfById(CommunityBuildDTO dto) {
        //获取当前时间
        String currentTime = CurrentTimeUnit.getCurrentTime();
        //获取session中的用户名数据
        String sessionUser = ServletUnit.getSessionUser(request);
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);

        //dto传递数据到po
        CommunityBuildPO po = new CommunityBuildPO();
        po.setBuildName(dto.getBuildName());
        po.setBuildFloorCode(dto.getBuildFloorCode());
        po.setBuildRisk(dto.getBuildRisk());
        po.setAreaCode(dto.getAreaCode());
        po.setUpdatedBy(sessionUser);
        po.setUpdatedAt(currentTime);

        //使用wrapper构造器
        UpdateWrapper<CommunityBuildPO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("ID", dto.getId())
                .eq("COMMUNITY_CODE",sessionCommunityCode);

        int result = mapper.update(po,updateWrapper);

        if (result == 1) {
            System.out.println("=================================================================建筑更新成功！=================================================================");
        } else {
            System.out.println("=================================================================建筑更新失败！=================================================================");
        }

        return result;
    }

    @Override
    public List<CommunityBuildPO> selectBuildForSelect(CommunityBuildDTO dto) {
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //创建wrapper构造器
        QueryWrapper<CommunityBuildPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("COMMUNITY_CODE",sessionCommunityCode);

        //dto传递数据到po
        CommunityBuildPO po = new CommunityBuildPO();
        if (!StringUtils.isEmpty(dto.getAreaCode())){
            po.setAreaCode(dto.getAreaCode());
            queryWrapper.like("AREA_CODE",po.getAreaCode());
        }

        //查询
        queryWrapper.select("BUILD_CODE","BUILD_NAME");
        List<CommunityBuildPO> buildList = mapper.selectList(queryWrapper);

        return buildList;
    }

    /**
     * 生成建筑code
     * @return
     */
    public String makeBuildCode(){
        //获取时间戳code
        String mapperCode = codeMapper.getCode();
        System.out.println("时间戳："+mapperCode);
        //生成区域code
        String buildCode = MakeCodeUnit.makeBuildCode(mapperCode);
        System.out.println("生成："+buildCode);
        //创建Wrapper构造器
        QueryWrapper<CommunityBuildPO> queryWrapper = new QueryWrapper<>();
        //判断生成的code是否已存在
        queryWrapper.like("BUILD_CODE",buildCode);
        List<CommunityBuildPO> codeList = mapper.selectList(queryWrapper);

        if (codeList.size() == 0){
            return buildCode;
        }else {
            //如果code已存在则调用自己
            makeBuildCode();
            return null;
        }
    }

}
