package com.neusoft.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.entity.dto.CommunityDTO;
import com.neusoft.entity.vo.CommunityVO;
import com.neusoft.service.community.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzz
 * 社区
 */
@RestController
@RequestMapping(value = "/community")
public class CommunityController {

    @Autowired
    private CommunityService service;

    /**
     * 查询所有社区（支持模糊查询）
     * @param vo
     * @return
     */
    @RequestMapping(value = "/selectAllCommunity", method = RequestMethod.POST)
    public Page<CommunityDTO> selectAllCommunity(@RequestBody CommunityVO vo) {
        //创建dto对象
        CommunityDTO dto = new CommunityDTO();
        //vo传递数据到dto
        dto.setId(vo.getId());
        dto.setCommunityCode(vo.getCommunityCode());
        dto.setCommunityName(vo.getCommunityName());

        Page<CommunityDTO> allCommunity = service.selectAllCommunity(dto);
        return allCommunity;
    }

    /**
     * 新增社区
     * @param vo
     * @return
     */
    @RequestMapping(value = "/addNewCommunity", method = RequestMethod.POST)
    public Integer addNewCommunity(@RequestBody CommunityVO vo){
        //创建dto对象
        CommunityDTO dto = new CommunityDTO();
        //vo传递数据到dto
        dto.setCommunityName(vo.getCommunityName());

        Integer result = service.addNewCommunity(dto);

        return result;
    }

    /**
     * 根据ID删除社区
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteCommunityById", method = RequestMethod.GET)
    public Integer deleteCommunityById(Long id){
        Integer result = service.deleteCommunityById(id);
        return result;
    }

    @RequestMapping(value = "/updateCommunityById", method = RequestMethod.POST)
    public Integer updateCommunityById(@RequestBody CommunityVO vo){
        //创建dto对象
        CommunityDTO dto = new CommunityDTO();
        //vo传递数据到dto
        dto.setId(vo.getId());
        dto.setCommunityCode(vo.getCommunityCode());
        dto.setCommunityName(vo.getCommunityName());

        Integer result = service.updatePersonalInfById(dto);

        return result;
    }
}
