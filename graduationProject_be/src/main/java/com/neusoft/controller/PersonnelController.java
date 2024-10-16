package com.neusoft.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.entity.dto.PersonnelDTO;
import com.neusoft.entity.dto.infectionStatus.InfectionStatusDTO;
import com.neusoft.entity.po.PersonnelPO;
import com.neusoft.entity.vo.PersonnelVO;
import com.neusoft.service.personnel.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lzz
 * 社区人员
 */
@RestController
@RequestMapping(value = "/personnel")
public class PersonnelController {

    @Autowired
    private PersonnelService service;

    @Autowired
    private PersonnelDTO dto;

    /**
     * 查询所有人员（支持模糊查询）--最高权限
     * @param vo
     * @return
     */
    @RequestMapping(value = "/selectAllPersonnelData", method = RequestMethod.POST)
    public Page<PersonnelDTO> selectAllPersonnelData(@RequestBody PersonnelVO vo) {
        //vo传递数据到dto
        dto.setId(vo.getId());
        dto.setPersonnelCode(vo.getPersonnelCode()); //人员编码
        dto.setPersonnelName(vo.getPersonnelName()); //人员姓名
        dto.setPersonnelAge(vo.getPersonnelAge()); //人员年龄
        dto.setAreaCode(vo.getAreaCode()); //区域代码
        dto.setAreaName(vo.getAreaName()); //区域名称
        dto.setBuildCode(vo.getBuildCode()); //建筑代码
        dto.setBuildName(vo.getBuildName()); //建筑名称
        dto.setPersonnelState(vo.getPersonnelState()); //居住状态
        dto.setNatResult(vo.getNatResult()); //核酸结果
        dto.setIsUd(vo.getIsUd()); //基础病
        dto.setInoculate(vo.getInoculate()); //接种次数
        dto.setSevenDayTrip(vo.getSevenDayTrip()); //七天行程
        dto.setIsQuarantine(vo.getIsQuarantine()); //是否隔离
        dto.setQuarantinePolicy(vo.getQuarantinePolicy()); //隔离政策
        dto.setPageCurrent(vo.getPageCurrent()); //分页

        Page<PersonnelDTO> allPersonnelData = service.selectAllPersonnelData(dto);

        return allPersonnelData;
    }

    /**
     * 查询所有人员（支持模糊查询）--中级权限
     * @param vo
     * @return
     */
    @RequestMapping(value = "/selectAllPersonnelDataForLM", method = RequestMethod.POST)
    public Page<PersonnelDTO> selectAllPersonnelDataForLM(@RequestBody PersonnelVO vo) {
        //vo传递数据到dto
        dto.setId(vo.getId());
        dto.setPersonnelCode(vo.getPersonnelCode()); //人员编码
        dto.setPersonnelName(vo.getPersonnelName()); //人员姓名
        dto.setPersonnelAge(vo.getPersonnelAge()); //人员年龄
        dto.setAreaCode(vo.getAreaCode()); //区域代码
        dto.setAreaName(vo.getAreaName()); //区域名称
        dto.setBuildCode(vo.getBuildCode()); //建筑代码
        dto.setBuildName(vo.getBuildName()); //建筑名称
        dto.setPersonnelState(vo.getPersonnelState()); //居住状态
        dto.setNatResult(vo.getNatResult()); //核酸结果
        dto.setIsUd(vo.getIsUd()); //基础病
        dto.setInoculate(vo.getInoculate()); //接种次数
        dto.setSevenDayTrip(vo.getSevenDayTrip()); //七天行程
        dto.setIsQuarantine(vo.getIsQuarantine()); //是否隔离
        dto.setQuarantinePolicy(vo.getQuarantinePolicy()); //隔离政策
        dto.setPageCurrent(vo.getPageCurrent()); //分页

        Page<PersonnelDTO> allPersonnelData = service.selectAllPersonnelDataForLM(dto);

        return allPersonnelData;
    }

    /**
     * 查询所有人员（支持模糊查询）--普通权限
     * @param vo
     * @return
     */
    @RequestMapping(value = "/selectAllPersonnelDataForLN", method = RequestMethod.POST)
    public Page<PersonnelDTO> selectAllPersonnelDataForLN(@RequestBody PersonnelVO vo) {
        //vo传递数据到dto
        dto.setId(vo.getId());
        dto.setPersonnelCode(vo.getPersonnelCode()); //人员编码
        dto.setPersonnelName(vo.getPersonnelName()); //人员姓名
        dto.setPersonnelAge(vo.getPersonnelAge()); //人员年龄
        dto.setAreaCode(vo.getAreaCode()); //区域代码
        dto.setAreaName(vo.getAreaName()); //区域名称
        dto.setBuildCode(vo.getBuildCode()); //建筑代码
        dto.setBuildName(vo.getBuildName()); //建筑名称
        dto.setPersonnelState(vo.getPersonnelState()); //居住状态
        dto.setNatResult(vo.getNatResult()); //核酸结果
        dto.setIsUd(vo.getIsUd()); //基础病
        dto.setInoculate(vo.getInoculate()); //接种次数
        dto.setSevenDayTrip(vo.getSevenDayTrip()); //七天行程
        dto.setIsQuarantine(vo.getIsQuarantine()); //是否隔离
        dto.setQuarantinePolicy(vo.getQuarantinePolicy()); //隔离政策
        dto.setPageCurrent(vo.getPageCurrent()); //分页

        Page<PersonnelDTO> allPersonnelData = service.selectAllPersonnelDataForLN(dto);

        return allPersonnelData;
    }

    /**
     * 新增人员
     * @param vo
     * @return
     */
    @RequestMapping(value = "/addNewPersonal", method = RequestMethod.POST)
    public Integer addNewPersonal(@RequestBody PersonnelVO vo){
        //vo传递数据到dto
        dto.setPersonnelName(vo.getPersonnelName());
        dto.setPersonnelAge(vo.getPersonnelAge());
        dto.setPhone(vo.getPhone());
        dto.setAreaCode(vo.getAreaCode());
        dto.setBuildCode(vo.getBuildCode());
        dto.setPersonnelState(vo.getPersonnelState());
        dto.setNatResult(vo.getNatResult());
        dto.setIsUd(vo.getIsUd());
        dto.setInoculate(vo.getInoculate());
        dto.setSevenDayTrip(vo.getSevenDayTrip());
        dto.setIsQuarantine(vo.getIsQuarantine());
        dto.setQuarantinePolicy(vo.getQuarantinePolicy());

        Integer result = service.addNewPersonal(dto);

        return result;
    }

    /**
     * 根据ID删除人员
     * @param id
     * @return
     */
    @RequestMapping(value = "/deletePersonalById", method = RequestMethod.GET)
    public Integer deletePersonalById(Long id){
        Integer result = service.deletePersonalById(id);
        return result;
    }

    /**
     * 更新人员信息数据
     * @param vo
     * @return
     */
    @RequestMapping(value = "/updatePersonalInfById", method = RequestMethod.POST)
    public Integer updatePersonalInfById(@RequestBody PersonnelVO vo){
        //vo传dto
        dto.setId(vo.getId());
        dto.setPersonnelName(vo.getPersonnelName());
        dto.setPersonnelAge(vo.getPersonnelAge());
        dto.setPhone(vo.getPhone());
        dto.setAreaCode(vo.getAreaCode());
        dto.setBuildCode(vo.getBuildCode());
        dto.setAreaName(vo.getAreaName());
        dto.setBuildName(vo.getBuildName());
        dto.setPersonnelState(vo.getPersonnelState());
        dto.setNatResult(vo.getNatResult());
        dto.setIsUd(vo.getIsUd());
        dto.setInoculate(vo.getInoculate());
        dto.setSevenDayTrip(vo.getSevenDayTrip());
        dto.setIsQuarantine(vo.getIsQuarantine());
        dto.setQuarantinePolicy(vo.getQuarantinePolicy());

        Integer result = service.updatePersonalInfById(dto);

        return result;
    }

    /**
     * 社区人员统计（包括感染数据在内）--最高权限
     * @return
     */
    @RequestMapping(value = "/infectionStatus", method = RequestMethod.POST)
    public InfectionStatusDTO infectionStatus(){
        InfectionStatusDTO infectionStatusDTO = service.infectionStatusForLH();
        return infectionStatusDTO;
    }

    /**
     * 社区人员统计（包括感染数据在内）--中级权限
     * @return
     */
    @RequestMapping(value = "/infectionStatusForLM", method = RequestMethod.POST)
    public InfectionStatusDTO infectionStatusForLM(){
        InfectionStatusDTO infectionStatusDTO = service.infectionStatusForLM();
        return infectionStatusDTO;
    }

    /**
     * 社区人员统计（包括感染数据在内）--普通权限
     * @return
     */
    @RequestMapping(value = "/infectionStatusForLN", method = RequestMethod.POST)
    public InfectionStatusDTO infectionStatusForLN(){
        InfectionStatusDTO infectionStatusDTO = service.infectionStatusForLN();
        return infectionStatusDTO;
    }
}
