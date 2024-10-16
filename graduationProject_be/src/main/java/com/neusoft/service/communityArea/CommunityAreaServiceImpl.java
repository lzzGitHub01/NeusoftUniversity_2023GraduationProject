package com.neusoft.service.communityArea;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.entity.dto.CommunityAreaDTO;
import com.neusoft.entity.dto.SelectAllAreaDTO;
import com.neusoft.entity.dto.infectionStatus.AreaInfectionSituationDTO;
import com.neusoft.entity.po.CommunityAreaPO;
import com.neusoft.mapper.CommunityAreaMapper;
import com.neusoft.mapper.MackTimeStampCodeMapper;
import com.neusoft.unit.CurrentTimeUnit;
import com.neusoft.unit.MakeCodeUnit;
import com.neusoft.unit.ServletUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author lzz
 */
@Service
public class CommunityAreaServiceImpl extends ServiceImpl<CommunityAreaMapper, CommunityAreaPO> implements CommunityAreaService {

    @Autowired
    private CommunityAreaMapper mapper;

    @Autowired
    private MackTimeStampCodeMapper codeMapper;

    @Autowired
    HttpServletRequest request;

    /**
     * 查询所有区域数据，用于前端展示
     *
     * @return
     */
    @Override
    public List<CommunityAreaPO> selectAllData(String managerName) {
        //获取当前登陆账户的权限等级
        String managerLevel = mapper.selectCurrentUserLevel(managerName);
        //创建wrapper构造器
        QueryWrapper<CommunityAreaPO> queryWrapper = new QueryWrapper<>();

        List<CommunityAreaPO> areaList = null;
        if ("0".equals(managerLevel)) {
            areaList = mapper.selectList(null);
        } else if ("1".equals(managerLevel)) {
            queryWrapper.like("CREATED_BY", managerName);
            areaList = mapper.selectList(queryWrapper);
        } else {
            System.out.println("当前用户权限等级不足");
        }

        return areaList;
    }

    /**
     * 根据提供的条件查询所有对应的区域数据（支持模糊查询）
     * @param dto
     * @return
     */
    @Override
    public Page<SelectAllAreaDTO> selectAreaDataByCondition(SelectAllAreaDTO dto) {

        //dto传递数据到po
        CommunityAreaPO po = new CommunityAreaPO();
        Page<SelectAllAreaDTO> page;
        if (dto.getId() != null || "".equals(dto.getId())){
            po.setId(dto.getId());
        }
        if (dto.getAreaName() != null || "".equals(dto.getAreaName())){
            po.setAreaName(dto.getAreaName());
        }
        if (dto.getAreaCode() != null || "".equals(dto.getAreaCode())){
            po.setAreaCode(dto.getAreaCode());
        }
        if (dto.getAreaRisk() != null || "".equals(dto.getAreaRisk())){
            po.setAreaRisk(dto.getAreaRisk());
        }

        if (dto.getPageCurrent() != null || "".equals(dto.getPageCurrent())){
             page = new Page<>(dto.getPageCurrent(),10);
        }else {
             page = new Page<>(1,10);
        }

        //创建wrapper构造器
        /*QueryWrapper<CommunityAreaPO> queryWrapper = new QueryWrapper<>();

        if (dto.getId() != null || "".equals(dto.getId())){
            po.setId(dto.getId());
            queryWrapper.like("ID",po.getId());
        }
        if (dto.getAreaCode() != null || "".equals(dto.getAreaCode())){
            po.setAreaCode(dto.getAreaCode());
            queryWrapper.like("AREA_CODE",po.getAreaCode());
        }
        if (dto.getAreaName() != null || "".equals(dto.getAreaName())){
            po.setAreaName(dto.getAreaName());
            queryWrapper.like("AREA_NAME",po.getAreaName());
        }
        if (dto.getAreaRisk() != null || "".equals(dto.getAreaRisk())){
            po.setAreaRisk(dto.getAreaRisk());
            queryWrapper.like("AREA_RISK",po.getAreaRisk());
        }
        queryWrapper.apply("(SELECT CODE_NAME FROM `my_code` WHERE MCODE = "+po.getAreaRisk()+")");*/



        Page<SelectAllAreaDTO> findAreaList = mapper.selectAreaDataByCondition(page,po);

        System.out.println("=================================================================区域查询成功！=================================================================");

        return findAreaList;
    }


    /**
     * 查询所有区域数据（最高权限者）
     * @return
     */
    @Override
    public Page<SelectAllAreaDTO> selectAllAreaData(SelectAllAreaDTO dto) {
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //创建分页
        Page<SelectAllAreaDTO> page;

        CommunityAreaPO po = new CommunityAreaPO();
        if (dto.getId() != null || "".equals(dto.getId())){
            po.setId(dto.getId());
        }
        if (dto.getAreaCode() != null || "".equals(dto.getAreaCode())){
            po.setAreaCode(dto.getAreaCode());
        }
        if (dto.getAreaName() != null || "".equals(dto.getAreaName())){
            po.setAreaName(dto.getAreaName());
        }
        if (dto.getAreaRisk() != null || "".equals(dto.getAreaRisk())){
            po.setAreaRisk(dto.getAreaRisk());
        }
        if (!StringUtils.isEmpty(sessionCommunityCode)){
            po.setCommunityCode(sessionCommunityCode);
        }
        if (dto.getPageCurrent() != null || "".equals(dto.getPageCurrent())){
            page = new Page<>(dto.getPageCurrent(),10);
        }else {
            page = new Page<>(1,10);
        }

        System.out.println("po数据："+po);
        Page<SelectAllAreaDTO> selectAllAreaDTOPage = mapper.selectAllAreaData(page, po);
        return selectAllAreaDTOPage;
    }

    @Override
    public Page<SelectAllAreaDTO> selectAllAreaDataPage(Integer pageCurrent) {
        Page<SelectAllAreaDTO> page = new Page<>(pageCurrent,10);
        Page<SelectAllAreaDTO> selectAllAreaPage = mapper.selectAllAreaDataPage(page);
        return selectAllAreaPage;
    }

    /**
     * 新增区域
     *
     * @param dto
     * @return
     */
    @Override
    public Integer createNewArea(CommunityAreaDTO dto) {
        //获取当前时间
        String currentTime = CurrentTimeUnit.getCurrentTime();
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //接口返回结果
        int result;

        String areaCode = makeAreaCode();

        CommunityAreaPO po = new CommunityAreaPO();
        po.setAreaCode(areaCode);
        po.setAreaName(dto.getAreaName());
        po.setAreaRisk(dto.getAreaRisk());
        po.setCreatedBy(sessionUser);
        po.setCreatedAt(currentTime);
        po.setCommunityCode(sessionCommunityCode);
        result = mapper.insert(po);

        if (result == 1) {
            System.out.println("=================================================================区域新增成功！=================================================================");
        } else {
            System.out.println("=================================================================区域新增失败！=================================================================");
        }
        return result;
    }

    /**
     * 根据区域ID删除区域
     *
     * @param id
     * @return
     */
    @Override
    public Integer deleteAreaByID(Long id) {
        Integer result = null;
        result = mapper.deleteById(id);

        if (result == 1) {
            System.out.println("=================================================================区域删除成功！=================================================================");
        } else {
            System.out.println("=================================================================区域删除失败！=================================================================");
        }
        return result;
    }

    /**
     * 更新指定ID区域的数据
     *
     * @param dto
     * @return
     */
    @Override
    public Integer updateAreaById(CommunityAreaDTO dto) {
        //获取当前时间
        String currentTime = CurrentTimeUnit.getCurrentTime();
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);

        //使用wrapper构造器
        UpdateWrapper<CommunityAreaPO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("ID", dto.getId())
                .like("COMMUNITY_CODE",sessionCommunityCode);

        CommunityAreaPO po = new CommunityAreaPO();
        po.setAreaName(dto.getAreaName());
        po.setAreaRisk(dto.getAreaRisk());
        po.setUpdatedBy(sessionUser);
        po.setUpdatedAt(currentTime);

        int result = mapper.update(po, updateWrapper);
        if (result == 1) {
            System.out.println("=================================================================区域更新成功！=================================================================");
        } else {
            System.out.println("=================================================================区域更新失败！=================================================================");
        }
        return result;
    }

    @Override
    public List<CommunityAreaPO> selectAreaForSelect() {
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //使用wrapper构造器
        QueryWrapper<CommunityAreaPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("COMMUNITY_CODE",sessionCommunityCode);
        queryWrapper.select("AREA_CODE","AREA_NAME");

        List<CommunityAreaPO> areaList = mapper.selectList(queryWrapper);

        return areaList;
    }

    @Override
    public List<AreaInfectionSituationDTO> selectAreaInfectionSituation() {
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //感染比率
        float infectionPercent;

        List<AreaInfectionSituationDTO> situationDTOList = mapper.selectAreaAndPersonalCount(sessionCommunityCode);

        for (AreaInfectionSituationDTO dto : situationDTOList) {
            Integer masculineNumber = mapper.selectMasculineCount(sessionCommunityCode, dto.getAreaCode());
            dto.setMasculineNumber(masculineNumber);
            infectionPercent = (float)masculineNumber / (float)dto.getAreaTotalNumber() * 100;
            dto.setInfectionPercent((float)(Math.round(infectionPercent * 10)) / 10);
        }

        for (AreaInfectionSituationDTO dto : situationDTOList) {
            System.out.println("数据："+dto);
        }

        return situationDTOList;
    }

    /**
     * 生成区域code
     * @return
     */
    public String makeAreaCode(){
        //获取时间戳code
        String mapperCode = codeMapper.getCode();
        //生成区域code
        String areaCode = MakeCodeUnit.makeAreaCode(mapperCode);
        //创建Wrapper构造器
        QueryWrapper<CommunityAreaPO> queryWrapper = new QueryWrapper<>();
        //判断生成的code是否已存在
        queryWrapper.like("AREA_CODE",areaCode);
        List<CommunityAreaPO> codeList = mapper.selectList(queryWrapper);

        if (codeList.size() == 0){
            return areaCode;
        }else {
            //如果code已存在则调用自己
            makeAreaCode();
            return null;
        }
    }
}
