package com.neusoft.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.entity.dto.CommunityBuildFloorDTO;
import com.neusoft.entity.po.CommunityBuildFloorPO;
import com.neusoft.entity.vo.CommunityBuildFloorVO;
import com.neusoft.service.communityBuildFloor.CommunityBuildFloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lzz
 * 楼层
 */
@RestController
@RequestMapping(value = "/buildFloor")
public class CommunityBuildFloorController {

    @Autowired
    private CommunityBuildFloorService service;


    /**
     * 查询所有楼层规则信息（支持模糊查询）
     * @param vo
     * @return
     */
    @RequestMapping(value = "/selectAllBuildFloorData", method = RequestMethod.POST)
    public Page<CommunityBuildFloorPO> selectAllBuildFloorData(@RequestBody CommunityBuildFloorVO vo){
        //vo传递数据到dto
        CommunityBuildFloorDTO dto = new CommunityBuildFloorDTO();
        dto.setId(vo.getId());
        dto.setBuildFloorCode(vo.getBuildFloorCode());
        dto.setBuildFloorNum(vo.getBuildFloorNum());
        dto.setCommunityCode(vo.getCommunityCode());
        dto.setCommunityOccupant(vo.getCommunityOccupant());

        dto.setPageCurrent(vo.getPageCurrent());

        Page<CommunityBuildFloorPO> buildFloorData = service.selectAllBuildFloorData(dto);

        return buildFloorData;
    }

    /**
     * 新增楼层规则信息
     * @param vo
     * @return
     */
    @RequestMapping(value = "/addNewBuildFloor", method = RequestMethod.POST)
    public Integer addNewBuildFloor(@RequestBody CommunityBuildFloorVO vo){
        //vo传递数据到dto
        CommunityBuildFloorDTO dto = new CommunityBuildFloorDTO();
        dto.setBuildFloorNum(vo.getBuildFloorNum());
        dto.setCommunityOccupant(vo.getCommunityOccupant());

        Integer result = service.addNewBuildFloor(dto);

        return result;
    }

    /**
     * 根据ID删除楼层规则信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteBuildFloorById", method = RequestMethod.GET)
    public Integer deleteBuildFloorById(Long id){
        Integer result = service.deleteBuildFloorById(id);
        return result;
    }

    /**
     * 根据ID修改楼层规则信息
     * @param vo
     * @return
     */
    @RequestMapping(value = "/updateBuildFloorInfById", method = RequestMethod.POST)
    public Integer updateBuildFloorInfById(@RequestBody CommunityBuildFloorVO vo){
        //vo传递数据到dto
        CommunityBuildFloorDTO dto = new CommunityBuildFloorDTO();
        dto.setId(vo.getId());
        dto.setBuildFloorNum(vo.getBuildFloorNum());
        dto.setCommunityOccupant(vo.getCommunityOccupant());

        Integer result = service.updateBuildFloorInfById(dto);

        return result;
    }

    /**
     * 根据areaCode，查询建筑规则的代码和楼层数信息，用于前台select选择器
     * @return
     */
    @RequestMapping(value = "/selectBuildForFloorSelect", method = RequestMethod.GET)
    public List<CommunityBuildFloorPO> selectBuildForFloorSelect(){
        List<CommunityBuildFloorPO> communityBuildFloorList = service.selectBuildForFloorSelect();
        return communityBuildFloorList;
    }

}
