package com.neusoft.service.personnel;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.entity.dto.PersonnelDTO;
import com.neusoft.entity.dto.infectionStatus.InfectionStatusDTO;
import com.neusoft.entity.dto.mangeInf.MangeInfDTO;
import com.neusoft.entity.po.CommunityAreaPO;
import com.neusoft.entity.po.CommunityBuildPO;
import com.neusoft.entity.po.PersonnelPO;
import com.neusoft.enums.GmLeave;
import com.neusoft.enums.IsEnable;
import com.neusoft.enums.NatResult;
import com.neusoft.enums.ResidentialType;
import com.neusoft.mapper.CommunityAreaMapper;
import com.neusoft.mapper.CommunityBuildMapper;
import com.neusoft.mapper.MackTimeStampCodeMapper;
import com.neusoft.mapper.PersonnelMapper;
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
public class PersonnelServiceImpl extends ServiceImpl<PersonnelMapper, PersonnelPO> implements PersonnelService{

    @Autowired
    private PersonnelMapper mapper;

    @Autowired
    private MackTimeStampCodeMapper codeMapper;

    @Autowired
    private CommunityAreaMapper areaMapper;

    @Autowired
    private CommunityBuildMapper buildMapper;

    @Autowired
    HttpServletRequest request;

    @Override
    public Page<PersonnelDTO> selectAllPersonnelData(PersonnelDTO dto) {
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //创建分页插件
        Page<PersonnelPO> page;

        System.out.println("dto数据："+dto);

        //dto传递数据到po
        PersonnelPO po = new PersonnelPO();
        if (!StringUtils.isEmpty(dto.getId())){
            po.setId(Long.valueOf(dto.getId()));
        }
        if (!StringUtils.isEmpty(dto.getPersonnelCode())){
            po.setPersonnelCode(dto.getPersonnelCode());
        }
        if (!StringUtils.isEmpty(dto.getPersonnelName())){
            po.setPersonnelName(dto.getPersonnelName());
        }
        if (!StringUtils.isEmpty(dto.getPersonnelAge())){
            po.setPersonnelAge(dto.getPersonnelAge());
        }
        if (!StringUtils.isEmpty(dto.getBuildCode())){
            po.setBuildCode(dto.getBuildCode());
        }
        if (!StringUtils.isEmpty(dto.getAreaCode())){
            po.setAreaCode(dto.getAreaCode());
        }
        if (dto.getPersonnelState() != null || "".equals(dto.getPersonnelState())){
            String personnelState = dto.getPersonnelState().toString();
            Integer stateValue = ResidentialType.valueOf(personnelState).getValue();
            po.setPersonnelState(stateValue);
        }
        if (dto.getNatResult() != null || "".equals(dto.getNatResult())){
            String natResult = dto.getNatResult().toString();
            Integer natResultValue = NatResult.valueOf(natResult).getValue();
            po.setNatResult(natResultValue);
        }
        if (dto.getIsUd() != null || "".equals(dto.getIsUd())){
            String isUd = dto.getIsUd().toString();
            Integer isUdValue = IsEnable.valueOf(isUd).getValue();
            po.setIsUd(isUdValue);
        }
        if (!StringUtils.isEmpty(dto.getInoculate())){
            po.setInoculate(dto.getInoculate());
        }
        if (!StringUtils.isEmpty(dto.getSevenDayTrip())){
            po.setSevenDayTrip(dto.getSevenDayTrip());
        }
        if (dto.getIsQuarantine() != null || "".equals(dto.getIsQuarantine())){
            String isQuarantine = dto.getIsQuarantine().toString();
            Integer isQuarantineValue = IsEnable.valueOf(isQuarantine).getValue();
            po.setIsQuarantine(isQuarantineValue);
        }
        if (!StringUtils.isEmpty(dto.getQuarantinePolicy())){
            po.setQuarantinePolicy(dto.getQuarantinePolicy());
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

        Page<PersonnelDTO> allPersonnelData = mapper.selectAllPersonnelData(page, po);

        return allPersonnelData;
    }

    @Override
    public Page<PersonnelDTO> selectAllPersonnelDataForLM(PersonnelDTO dto) {
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //获取session中的所属区域code
        String sessionAreaCode = ServletUnit.getSessionAreaCode(request);
        //获取session中的用户的等级
        String sessionLevel = ServletUnit.getSessionLevel(request);
        //创建分页插件
        Page<PersonnelPO> page;

        //存放当前登陆账户的信息
        MangeInfDTO mangeInfDTO = new MangeInfDTO();
        mangeInfDTO.setManageName(sessionUser);
        mangeInfDTO.setManageLevel(GmLeave.valueOf(sessionLevel));
        mangeInfDTO.setAreaCode(sessionAreaCode);

        //dto传递数据到po
        PersonnelPO po = new PersonnelPO();
        if (!StringUtils.isEmpty(dto.getId())){
            po.setId(Long.valueOf(dto.getId()));
        }
        if (!StringUtils.isEmpty(dto.getPersonnelCode())){
            po.setPersonnelCode(dto.getPersonnelCode());
        }
        if (!StringUtils.isEmpty(dto.getPersonnelName())){
            po.setPersonnelName(dto.getPersonnelName());
        }
        if (!StringUtils.isEmpty(dto.getPersonnelAge())){
            po.setPersonnelAge(dto.getPersonnelAge());
        }
        if (!StringUtils.isEmpty(dto.getBuildCode())){
            po.setBuildCode(dto.getBuildCode());
        }
        if (!StringUtils.isEmpty(dto.getAreaCode())){
            po.setAreaCode(dto.getAreaCode());
        }
        if (dto.getPersonnelState() != null || "".equals(dto.getPersonnelState())){
            String personnelState = dto.getPersonnelState().toString();
            Integer stateValue = ResidentialType.valueOf(personnelState).getValue();
            po.setPersonnelState(stateValue);
        }
        if (dto.getNatResult() != null || "".equals(dto.getNatResult())){
            String natResult = dto.getNatResult().toString();
            Integer natResultValue = NatResult.valueOf(natResult).getValue();
            po.setNatResult(natResultValue);
        }
        if (dto.getIsUd() != null || "".equals(dto.getIsUd())){
            String isUd = dto.getIsUd().toString();
            Integer isUdValue = IsEnable.valueOf(isUd).getValue();
            po.setIsUd(isUdValue);
        }
        if (!StringUtils.isEmpty(dto.getInoculate())){
            po.setInoculate(dto.getInoculate());
        }
        if (!StringUtils.isEmpty(dto.getSevenDayTrip())){
            po.setSevenDayTrip(dto.getSevenDayTrip());
        }
        if (dto.getIsQuarantine() != null || "".equals(dto.getIsQuarantine())){
            String isQuarantine = dto.getIsQuarantine().toString();
            Integer isQuarantineValue = IsEnable.valueOf(isQuarantine).getValue();
            po.setIsQuarantine(isQuarantineValue);
        }
        if (!StringUtils.isEmpty(dto.getQuarantinePolicy())){
            po.setQuarantinePolicy(dto.getQuarantinePolicy());
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

        Page<PersonnelDTO> allPersonnelData = mapper.selectAllPersonnelDataForLM(page, po, mangeInfDTO);

        return allPersonnelData;
    }

    @Override
    public Page<PersonnelDTO> selectAllPersonnelDataForLN(PersonnelDTO dto) {
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //获取session中的所属区域code
        String sessionAreaCode = ServletUnit.getSessionAreaCode(request);
        //获取session中的用户的等级
        String sessionLevel = ServletUnit.getSessionLevel(request);
        //创建分页插件
        Page<PersonnelPO> page;

        //存放楼组code的集合
        List<String> buildInf = new ArrayList<>();
        //查询，当前登陆的普通权限账户下，的所有楼组code
        String build = buildMapper.selectMangerBuild(sessionUser);
        //将查出的楼组code数据存入集合中
        String[] split = build.split(",");
        for (int i = 0; i < split.length; i++) {
            buildInf.add(split[i]);
        }

        //存放当前登陆账户的信息
        MangeInfDTO mangeInfDTO = new MangeInfDTO();
        mangeInfDTO.setManageName(sessionUser);
        mangeInfDTO.setManageLevel(GmLeave.valueOf(sessionLevel));
        mangeInfDTO.setAreaCode(sessionAreaCode);

        //dto传递数据到po
        PersonnelPO po = new PersonnelPO();
        if (!StringUtils.isEmpty(dto.getId())){
            po.setId(Long.valueOf(dto.getId()));
        }
        if (!StringUtils.isEmpty(dto.getPersonnelCode())){
            po.setPersonnelCode(dto.getPersonnelCode());
        }
        if (!StringUtils.isEmpty(dto.getPersonnelName())){
            po.setPersonnelName(dto.getPersonnelName());
        }
        if (!StringUtils.isEmpty(dto.getPersonnelAge())){
            po.setPersonnelAge(dto.getPersonnelAge());
        }
        if (!StringUtils.isEmpty(dto.getBuildCode())){
            po.setBuildCode(dto.getBuildCode());
        }
        if (!StringUtils.isEmpty(dto.getAreaCode())){
            po.setAreaCode(dto.getAreaCode());
        }
        if (dto.getPersonnelState() != null || "".equals(dto.getPersonnelState())){
            String personnelState = dto.getPersonnelState().toString();
            Integer stateValue = ResidentialType.valueOf(personnelState).getValue();
            po.setPersonnelState(stateValue);
        }
        if (dto.getNatResult() != null || "".equals(dto.getNatResult())){
            String natResult = dto.getNatResult().toString();
            Integer natResultValue = NatResult.valueOf(natResult).getValue();
            po.setNatResult(natResultValue);
        }
        if (dto.getIsUd() != null || "".equals(dto.getIsUd())){
            String isUd = dto.getIsUd().toString();
            Integer isUdValue = IsEnable.valueOf(isUd).getValue();
            po.setIsUd(isUdValue);
        }
        if (!StringUtils.isEmpty(dto.getInoculate())){
            po.setInoculate(dto.getInoculate());
        }
        if (!StringUtils.isEmpty(dto.getSevenDayTrip())){
            po.setSevenDayTrip(dto.getSevenDayTrip());
        }
        if (dto.getIsQuarantine() != null || "".equals(dto.getIsQuarantine())){
            String isQuarantine = dto.getIsQuarantine().toString();
            Integer isQuarantineValue = IsEnable.valueOf(isQuarantine).getValue();
            po.setIsQuarantine(isQuarantineValue);
        }
        if (!StringUtils.isEmpty(dto.getQuarantinePolicy())){
            po.setQuarantinePolicy(dto.getQuarantinePolicy());
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

        Page<PersonnelDTO> allPersonnelData = mapper.selectAllPersonnelDataForLN(page, po, buildInf, mangeInfDTO);

        return allPersonnelData;
    }

    @Override
    public Integer addNewPersonal(PersonnelDTO dto) {
        //获取当前时间
        String currentTime = CurrentTimeUnit.getCurrentTime();
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //创建wrapper构造器
        QueryWrapper<CommunityAreaPO> areaQueryWrapper = new QueryWrapper();
        QueryWrapper<CommunityBuildPO> buildQueryWrapper = new QueryWrapper();
        //获取code
        String personnelCode = makePersonnelCode();

        //dto传递数据到po
        PersonnelPO po = new PersonnelPO();
        po.setPersonnelCode(personnelCode);
        if (!StringUtils.isEmpty(dto.getPersonnelName())){
            po.setPersonnelName(dto.getPersonnelName());
        }
        if (!StringUtils.isEmpty(dto.getPersonnelAge())){
            po.setPersonnelAge(dto.getPersonnelAge());
        }
        if (!StringUtils.isEmpty(dto.getPhone())){
            po.setPhone(dto.getPhone());
        }
        if (!StringUtils.isEmpty(dto.getAreaCode())){
            po.setAreaCode(dto.getAreaCode());
        }
        if (!StringUtils.isEmpty(dto.getBuildCode())){
            po.setBuildCode(dto.getBuildCode());
        }
        if (dto.getPersonnelState() != null || "".equals(dto.getPersonnelState())){
            Integer residentialTypeValue = ResidentialType.valueOf(dto.getPersonnelState().toString()).getValue();
            po.setPersonnelState(residentialTypeValue);
        }
        if (dto.getNatResult() != null || "".equals(dto.getNatResult())){
            Integer natResultValue = NatResult.valueOf(dto.getNatResult().toString()).getValue();
            po.setNatResult(natResultValue);
        }
        if (dto.getIsUd() != null || "".equals(dto.getIsUd())){
            Integer isEnableValue = IsEnable.valueOf(dto.getIsUd().toString()).getValue();
            po.setIsUd(isEnableValue);
        }
        if (!StringUtils.isEmpty(dto.getInoculate())){
            po.setInoculate(dto.getInoculate());
        }
        if (!StringUtils.isEmpty(dto.getSevenDayTrip())){
            po.setSevenDayTrip(dto.getSevenDayTrip());
        }
        if (!StringUtils.isEmpty(dto.getQuarantinePolicy())){
            po.setQuarantinePolicy(dto.getQuarantinePolicy());
        }
        if (dto.getIsQuarantine() != null || "".equals(dto.getIsQuarantine())){
            Integer isEnableValue = IsEnable.valueOf(dto.getIsQuarantine().toString()).getValue();
            po.setIsQuarantine(isEnableValue);
        }
        if (!StringUtils.isEmpty(sessionCommunityCode)){
            po.setCommunityCode(sessionCommunityCode);
        }

        po.setCreatedAt(currentTime);
        po.setCreatedBy(sessionUser);

        int result = mapper.insert(po);

        if (result == 1){
            System.out.println("=================================================================人员新增成功！=================================================================");
        }else {
            System.out.println("=================================================================人员新增失败！=================================================================");
        }

        return result;
    }

    @Override
    public Integer deletePersonalById(Long id) {
        int result = mapper.deleteById(id);

        if (result == 1) {
            System.out.println("=================================================================人员删除成功！=================================================================");
        } else {
            System.out.println("=================================================================人员删除失败！=================================================================");
        }

        return result;
    }

    @Override
    public Integer updatePersonalInfById(PersonnelDTO dto) {
        //获取当前时间
        String currentTime = CurrentTimeUnit.getCurrentTime();
        //获取session中的用户名数据
        String sessionUser = ServletUnit.getSessionUser(request);
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //创建wrapper构造器
        UpdateWrapper<PersonnelPO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("COMMUNITY_CODE",sessionCommunityCode);

        //dto传递数据到po
        PersonnelPO po = new PersonnelPO();

        if (!StringUtils.isEmpty(dto.getPersonnelName())){
            po.setPersonnelName(dto.getPersonnelName());
        }
        if (!StringUtils.isEmpty(dto.getPersonnelAge())){
            po.setPersonnelAge(dto.getPersonnelAge());
        }
        if (!StringUtils.isEmpty(dto.getPhone())){
            po.setPhone(dto.getPhone());
        }
        if (!StringUtils.isEmpty(dto.getAreaCode())){
            po.setAreaCode(dto.getAreaCode());
        }
        if (!StringUtils.isEmpty(dto.getBuildCode())){
            po.setBuildCode(dto.getBuildCode());
        }
        if (dto.getPersonnelState() != null || "".equals(dto.getPersonnelState())){
            Integer residentialTypeValue = ResidentialType.valueOf(dto.getPersonnelState().toString()).getValue();
            po.setPersonnelState(residentialTypeValue);
        }
        if (dto.getNatResult() != null || "".equals(dto.getNatResult())){
            Integer natResultValue = NatResult.valueOf(dto.getNatResult().toString()).getValue();
            po.setNatResult(natResultValue);
        }
        if (dto.getIsUd() != null || "".equals(dto.getIsUd())){
            Integer isEnableValue = IsEnable.valueOf(dto.getIsUd().toString()).getValue();
            po.setIsUd(isEnableValue);
        }
        if (!StringUtils.isEmpty(dto.getInoculate())){
            po.setInoculate(dto.getInoculate());
        }
        if (!StringUtils.isEmpty(dto.getSevenDayTrip())){
            po.setSevenDayTrip(dto.getSevenDayTrip());
        }
        if (!StringUtils.isEmpty(dto.getQuarantinePolicy())){
            po.setQuarantinePolicy(dto.getQuarantinePolicy());
        }
        if (dto.getIsQuarantine() != null || "".equals(dto.getIsQuarantine())){
            Integer isEnableValue = IsEnable.valueOf(dto.getIsQuarantine().toString()).getValue();
            po.setIsQuarantine(isEnableValue);
        }

        po.setUpdatedAt(currentTime);
        po.setUpdatedBy(sessionUser);

        updateWrapper.like("ID",dto.getId());

        int result = mapper.update(po,updateWrapper);

        if (result == 1) {
            System.out.println("=================================================================人员更新成功！=================================================================");
        } else {
            System.out.println("=================================================================人员更新失败！=================================================================");
        }

        return result;
    }

    @Override
    public InfectionStatusDTO infectionStatusForLH() {
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //创建wrapper构造器
        QueryWrapper<PersonnelPO> queryWrapper = new QueryWrapper<>();

        System.out.println(sessionCommunityCode);

        InfectionStatusDTO infectionStatusDTO = new InfectionStatusDTO();
        //统计总人数
        Integer countNumber = mapper.selectCountNumber(sessionCommunityCode);
        //统计存在基础病人数
        Integer selectIsUdNumber = mapper.selectIsUdNumber(sessionCommunityCode);
        //统计阴性人数
        Integer selectFeminineNumber = mapper.selectFeminineNumber(sessionCommunityCode);
        //统计阳性人数
        Integer selectMasculineNumber = mapper.selectMasculineNumber(sessionCommunityCode);
        //统计租户人数
        Integer selectTenantNumber = mapper.selectTenantNumber(sessionCommunityCode);
        //统计住户人数
        Integer selectHoldNumber = mapper.selectHoldNumber(sessionCommunityCode);
        //统计未接种人数
        Integer noDoseNumber = mapper.selectNoDoseNumber(sessionCommunityCode);
        //统计接种一针人数
        Integer oneDoseNumber = mapper.selectOneDoseNumber(sessionCommunityCode);
        //统计接种两针人数
        Integer twoDoseNumber = mapper.selectTwoDoseNumber(sessionCommunityCode);
        //统计接种三针人数
        Integer threeDoseNumber = mapper.selectThreeDoseNumber(sessionCommunityCode);
        //统计接种四针人数
        Integer foreDoseNumber = mapper.selectForeDoseNumber(sessionCommunityCode);

        infectionStatusDTO.setTotalNumber(countNumber);
        infectionStatusDTO.setIsUdNumber(selectIsUdNumber);
        infectionStatusDTO.setFeminineNumber(selectFeminineNumber);
        infectionStatusDTO.setMasculineNumber(selectMasculineNumber);
        infectionStatusDTO.setTenantNumber(selectTenantNumber);
        infectionStatusDTO.setHoldNumber(selectHoldNumber);
        infectionStatusDTO.setNoDoseNumber(noDoseNumber);
        infectionStatusDTO.setOneDoseNumber(oneDoseNumber);
        infectionStatusDTO.setTwoDoseNumber(twoDoseNumber);
        infectionStatusDTO.setThreeDoseNumber(threeDoseNumber);
        infectionStatusDTO.setForeDoseNumber(foreDoseNumber);

        return infectionStatusDTO;
    }

    @Override
    public InfectionStatusDTO infectionStatusForLM() {
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //获取session中的所属区域code
        String sessionAreaCode = ServletUnit.getSessionAreaCode(request);
        //获取session中的用户的等级
        String sessionLevel = ServletUnit.getSessionLevel(request);

        InfectionStatusDTO infectionStatusDTO = new InfectionStatusDTO();
        //统计总人数
        Integer countNumber = mapper.selectCountNumberForLM(sessionCommunityCode, sessionAreaCode);
        //统计存在基础病人数
        Integer selectIsUdNumber = mapper.selectIsUdNumberForLM(sessionCommunityCode, sessionAreaCode);
        //统计阴性人数
        Integer selectFeminineNumber = mapper.selectFeminineNumberForLM(sessionCommunityCode, sessionAreaCode);
        //统计阳性人数
        Integer selectMasculineNumber = mapper.selectMasculineNumberForLM(sessionCommunityCode, sessionAreaCode);
        //统计租户人数
        Integer selectTenantNumber = mapper.selectTenantNumberForLM(sessionCommunityCode, sessionAreaCode);
        //统计住户人数
        Integer selectHoldNumber = mapper.selectHoldNumberForLM(sessionCommunityCode, sessionAreaCode);
        //统计未接种人数
        Integer noDoseNumber = mapper.selectNoDoseNumberForLM(sessionCommunityCode, sessionAreaCode);
        //统计接种一针人数
        Integer oneDoseNumber = mapper.selectOneDoseNumberForLM(sessionCommunityCode, sessionAreaCode);
        //统计接种两针人数
        Integer twoDoseNumber = mapper.selectTwoDoseNumberForLM(sessionCommunityCode, sessionAreaCode);
        //统计接种三针人数
        Integer threeDoseNumber = mapper.selectThreeDoseNumberForLM(sessionCommunityCode, sessionAreaCode);
        //统计接种四针人数
        Integer foreDoseNumber = mapper.selectForeDoseNumberForLM(sessionCommunityCode, sessionAreaCode);

        infectionStatusDTO.setTotalNumber(countNumber);
        infectionStatusDTO.setIsUdNumber(selectIsUdNumber);
        infectionStatusDTO.setFeminineNumber(selectFeminineNumber);
        infectionStatusDTO.setMasculineNumber(selectMasculineNumber);
        infectionStatusDTO.setTenantNumber(selectTenantNumber);
        infectionStatusDTO.setHoldNumber(selectHoldNumber);
        infectionStatusDTO.setNoDoseNumber(noDoseNumber);
        infectionStatusDTO.setOneDoseNumber(oneDoseNumber);
        infectionStatusDTO.setTwoDoseNumber(twoDoseNumber);
        infectionStatusDTO.setThreeDoseNumber(threeDoseNumber);
        infectionStatusDTO.setForeDoseNumber(foreDoseNumber);

        return infectionStatusDTO;
    }

    @Override
    public InfectionStatusDTO infectionStatusForLN() {
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //获取session中的所属区域code
        String sessionAreaCode = ServletUnit.getSessionAreaCode(request);
        //获取session中的用户的等级
        String sessionLevel = ServletUnit.getSessionLevel(request);

        //存放楼组code的集合
        List<String> buildInf = new ArrayList<>();
        //查询，当前登陆的普通权限账户下，的所有楼组code
        String build = buildMapper.selectMangerBuild(sessionUser);
        //将查出的楼组code数据存入集合中
        String[] split = build.split(",");
        for (int i = 0; i < split.length; i++) {
            buildInf.add(split[i]);
        }

        InfectionStatusDTO infectionStatusDTO = new InfectionStatusDTO();
        //统计总人数
        Integer countNumber = mapper.selectCountNumberForLN(sessionCommunityCode, sessionAreaCode, buildInf);
        //统计存在基础病人数
        Integer selectIsUdNumber = mapper.selectIsUdNumberForLN(sessionCommunityCode, sessionAreaCode, buildInf);
        //统计阴性人数
        Integer selectFeminineNumber = mapper.selectFeminineNumberForLN(sessionCommunityCode, sessionAreaCode, buildInf);
        //统计阳性人数
        Integer selectMasculineNumber = mapper.selectMasculineNumberForLN(sessionCommunityCode, sessionAreaCode, buildInf);
        //统计租户人数
        Integer selectTenantNumber = mapper.selectTenantNumberForLN(sessionCommunityCode, sessionAreaCode, buildInf);
        //统计住户人数
        Integer selectHoldNumber = mapper.selectHoldNumberForLN(sessionCommunityCode, sessionAreaCode, buildInf);
        //统计未接种人数
        Integer noDoseNumber = mapper.selectNoDoseNumberForLN(sessionCommunityCode, sessionAreaCode, buildInf);
        //统计接种一针人数
        Integer oneDoseNumber = mapper.selectOneDoseNumberForLN(sessionCommunityCode, sessionAreaCode, buildInf);
        //统计接种两针人数
        Integer twoDoseNumber = mapper.selectTwoDoseNumberForLN(sessionCommunityCode, sessionAreaCode, buildInf);
        //统计接种三针人数
        Integer threeDoseNumber = mapper.selectThreeDoseNumberForLN(sessionCommunityCode, sessionAreaCode, buildInf);
        //统计接种四针人数
        Integer foreDoseNumber = mapper.selectForeDoseNumberForLN(sessionCommunityCode, sessionAreaCode, buildInf);

        infectionStatusDTO.setTotalNumber(countNumber);
        infectionStatusDTO.setIsUdNumber(selectIsUdNumber);
        infectionStatusDTO.setFeminineNumber(selectFeminineNumber);
        infectionStatusDTO.setMasculineNumber(selectMasculineNumber);
        infectionStatusDTO.setTenantNumber(selectTenantNumber);
        infectionStatusDTO.setHoldNumber(selectHoldNumber);
        infectionStatusDTO.setNoDoseNumber(noDoseNumber);
        infectionStatusDTO.setOneDoseNumber(oneDoseNumber);
        infectionStatusDTO.setTwoDoseNumber(twoDoseNumber);
        infectionStatusDTO.setThreeDoseNumber(threeDoseNumber);
        infectionStatusDTO.setForeDoseNumber(foreDoseNumber);

        return infectionStatusDTO;
    }

    /**
     * 生成建筑code
     * @return
     */
    public String makePersonnelCode(){
        //获取时间戳code
        String mapperCode = codeMapper.getCode();
        //生成区域code
        String personnelCode = MakeCodeUnit.makePersonnelCode(mapperCode);
        //创建Wrapper构造器
        QueryWrapper<PersonnelPO> queryWrapper = new QueryWrapper<>();
        //判断生成的code是否已存在
        queryWrapper.like("PERSONNEL_CODE",personnelCode);
        List<PersonnelPO> codeList = mapper.selectList(queryWrapper);

        if (codeList.size() == 0){
            return personnelCode;
        }else {
            //如果code已存在则调用自己
            makePersonnelCode();
            return null;
        }
    }
}
