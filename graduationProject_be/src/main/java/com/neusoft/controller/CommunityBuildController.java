package com.neusoft.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.entity.dto.CommunityBuildDTO;
import com.neusoft.entity.po.CommunityBuildPO;
import com.neusoft.entity.vo.CommunityBuildVO;
import com.neusoft.service.communityBuild.CommunityBuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lzz
 * 楼（建筑）
 */
@RestController
@RequestMapping(value = "/build")
public class CommunityBuildController {

    @Autowired
    private CommunityBuildService service;

    @Autowired
    private CommunityBuildDTO dto;

    /**
     * 查询所有建筑（支持模糊查询）--最高权限
     * @param vo
     * @return
     */
    @RequestMapping(value = "/selectAllBuildData", method = RequestMethod.POST)
    public Page<CommunityBuildDTO> selectAllBuildDataLH(@RequestBody CommunityBuildVO vo) {
        //vo传递数据到dto
        dto.setId(vo.getId());
        dto.setBuildCode(vo.getBuildCode());
        dto.setAreaCode(vo.getAreaCode());
        dto.setBuildName(vo.getBuildName());
        dto.setBuildRisk(vo.getBuildRisk());
        dto.setPageCurrent(vo.getPageCurrent());

        Page<CommunityBuildDTO> buildData = service.selectAllBuildDataLH(dto);

        return buildData;
    }

    /**
     * 查询所有建筑（支持模糊查询）--中级权限
     * @param vo
     * @return
     */
    @RequestMapping(value = "/selectAllBuildDataForLM", method = RequestMethod.POST)
    public Page<CommunityBuildDTO> selectAllBuildDataForLM(@RequestBody CommunityBuildVO vo) {
        //vo传递数据到dto
        dto.setId(vo.getId());
        dto.setBuildCode(vo.getBuildCode());
        dto.setAreaCode(vo.getAreaCode());
        dto.setBuildName(vo.getBuildName());
        dto.setBuildRisk(vo.getBuildRisk());
        dto.setPageCurrent(vo.getPageCurrent());

        Page<CommunityBuildDTO> buildData = service.selectAllBuildDataForLM(dto);

        return buildData;
    }

    /**
     * 查询所有建筑（支持模糊查询）--普通权限
     * @param vo
     * @return
     */
    @RequestMapping(value = "/selectAllBuildDataForLN", method = RequestMethod.POST)
    public Page<CommunityBuildDTO> selectAllBuildDataForLN(@RequestBody CommunityBuildVO vo) {
        //vo传递数据到dto
        dto.setId(vo.getId());
        dto.setBuildCode(vo.getBuildCode());
        dto.setAreaCode(vo.getAreaCode());
        dto.setBuildName(vo.getBuildName());
        dto.setBuildRisk(vo.getBuildRisk());
        dto.setPageCurrent(vo.getPageCurrent());

        Page<CommunityBuildDTO> buildData = service.selectAllBuildDataForLN(dto);

        return buildData;
    }

    /**
     * 新增建筑
     * @param vo
     * @return
     */
    @RequestMapping(value = "/addNewBuild", method = RequestMethod.POST)
    public Integer addNewBuild(@RequestBody CommunityBuildVO vo){
        System.out.println("vo数据："+vo);
        //vo传递数据到dto
        dto.setBuildName(vo.getBuildName());
        dto.setBuildRisk(vo.getBuildRisk());
        dto.setAreaCode(vo.getAreaCode());
        dto.setBuildFloorCode(vo.getBuildFloorCode());

        Integer result = service.addNewBuild(dto);

        return result;
    }

    /**
     * 根据ID删除建筑
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteBuildById", method = RequestMethod.GET)
    public Integer deleteBuildById(Long id){
        Integer result = service.deleteBuildById(id);
        return result;
    }

    /**
     * 根据ID修改建筑信息
     * @param vo
     * @return
     */
    @RequestMapping(value = "/updateBuildInfById", method = RequestMethod.POST)
    public Integer updateBuildInfById(@RequestBody CommunityBuildVO vo){
        //vo传递数据到dto
        dto.setId(vo.getId());
        dto.setBuildName(vo.getBuildName());
        dto.setBuildRisk(vo.getBuildRisk());
        dto.setBuildFloorCode(vo.getBuildFloorCode());
        dto.setAreaCode(vo.getAreaCode());

        Integer result = service.updateBuildInfById(dto);

        return result;
    }

    /**
     * 根据areaCode，查询建筑的代码和名称，用于前台select选择器
     * @param vo
     * @return
     */
    @RequestMapping(value = "/selectBuildForSelect", method = RequestMethod.POST)
    public List<CommunityBuildPO> selectBuildForSelect(@RequestBody CommunityBuildVO vo){
        //vo传递数据到dto
        dto.setAreaCode(vo.getAreaCode());
        System.out.println("vo数据："+vo);
        List<CommunityBuildPO> buildList = service.selectBuildForSelect(dto);
        return buildList;
    }



}
