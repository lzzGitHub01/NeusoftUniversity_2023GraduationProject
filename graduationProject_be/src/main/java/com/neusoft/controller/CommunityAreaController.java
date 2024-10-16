package com.neusoft.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.entity.dto.CommunityAreaDTO;
import com.neusoft.entity.dto.SelectAllAreaDTO;
import com.neusoft.entity.dto.infectionStatus.AreaInfectionSituationDTO;
import com.neusoft.entity.po.CommunityAreaPO;
import com.neusoft.entity.vo.CommunityAreaVO;
import com.neusoft.service.communityArea.CommunityAreaService;
import com.neusoft.unit.ServletUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author lzz
 * 区域
 */
@RestController
@RequestMapping(value = "/area")
public class CommunityAreaController {

    @Autowired
    private CommunityAreaService service;

    @Autowired
    private CommunityAreaDTO dto;

    @Autowired
    HttpServletRequest request;

    /**
     * 查询所有区域（根据当前登陆账户的等级会发生变化）
     * 最高权限：查询所有
     * 中等权限：查看自己所在
     *
     * @return
     */
    @RequestMapping(value = "/selectAllData", method = RequestMethod.GET)
    public List<CommunityAreaPO> selectAllData() {
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);

        List<CommunityAreaPO> areaPOList = service.selectAllData(sessionUser);
        return areaPOList;
    }

    /**
     * 查询所有区域数据（最高权限者）
     * @return
     */
    @RequestMapping(value = "/selectAllAreaData", method = RequestMethod.POST)
    public Page<SelectAllAreaDTO> selectAllAreaData(@RequestBody CommunityAreaVO vo) {
        System.out.println("vo数据："+vo);

        //vo传递数据到dto
        SelectAllAreaDTO dto = new SelectAllAreaDTO();
        if (!StringUtils.isEmpty(vo.getId())){
            dto.setId(Long.valueOf(vo.getId()));
        }
        dto.setAreaCode(vo.getAreaCode());
        dto.setAreaName(vo.getAreaName());
        dto.setAreaRisk(vo.getAreaRisk());
        dto.setPageCurrent(vo.getPageCurrent());

        Page<SelectAllAreaDTO> communityAreaPOPage = service.selectAllAreaData(dto);
        return communityAreaPOPage;
    }

    /**
     * 分页询所有区域数据（最高权限者）---已停用
     * @return
     */
    @RequestMapping(value = "/selectAllAreaDataPage", method = RequestMethod.GET)
    public Page<SelectAllAreaDTO> selectAllAreaDataPage(Integer pageCurrent) {
        Page<SelectAllAreaDTO> selectAllAreaDTOPage = service.selectAllAreaDataPage(pageCurrent);
        return selectAllAreaDTOPage;
    }

    /**
     * 根据提供的条件查询所有对应的区域数据（支持模糊查询）---已停用
     * @return
     */
    @RequestMapping(value = "/selectAreaDataByCondition", method = RequestMethod.POST)
    public Page<SelectAllAreaDTO> selectAreaDataByCondition(@RequestBody CommunityAreaVO vo) {
        System.out.println("数据："+vo);
        SelectAllAreaDTO dto = new SelectAllAreaDTO();

        //vo传递数据到dto
        if (!StringUtils.isEmpty(vo.getId())){
            dto.setId(Long.valueOf(vo.getId()));
        }
        if (vo.getAreaCode() != null || "".equals(vo.getAreaCode())){
            dto.setAreaCode(vo.getAreaCode());
        }
        if (vo.getAreaName() != null || "".equals(vo.getAreaName())){
            dto.setAreaName(vo.getAreaName());
        }
        if (vo.getAreaRisk() != null || "".equals(vo.getAreaRisk())){
            dto.setAreaRisk(vo.getAreaRisk());
        }
        if (vo.getPageCurrent() != null || "".equals(vo.getPageCurrent())){
            dto.setPageCurrent(vo.getPageCurrent());
        }else {
            dto.setPageCurrent(1);
        }

        Page<SelectAllAreaDTO> allAreaList = service.selectAreaDataByCondition(dto);

        return allAreaList;
    }

    /**
     * 新增区域
     *
     * @return
     */
    @RequestMapping(value = "/createNewArea", method = RequestMethod.POST)
    public Integer createNewArea(@RequestBody CommunityAreaVO vo) {
        //vo传递数据到dto
        dto.setAreaName(vo.getAreaName());
        dto.setAreaRisk(vo.getAreaRisk());

        Integer result = service.createNewArea(dto);
        return result;
    }

    /**
     * 根据区域ID删除区域
     *
     * @return
     */
    @RequestMapping(value = "/deleteAreaById", method = RequestMethod.GET)
    public Integer deleteAreaByID(Long id) {
        //获取当前账户等级
        String userLevel = ServletUnit.getSessionLevel(request);
        Integer result = null;

        if ("LEAVE_HIGH".equals(userLevel)) {
            result = service.deleteAreaByID(id);
        }

        return result;
    }

    /**
     * 更新指定ID区域的数据
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/changeAreaInf", method = RequestMethod.POST)
    public Integer changeAreaInf(@RequestBody CommunityAreaVO vo) {
        Integer result = null;

        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        //获取当前账户等级
        String userLevel = ServletUnit.getSessionLevel(request);

        //vo传递数据到dto
        dto.setId(Long.valueOf(vo.getId()));
        dto.setAreaName(vo.getAreaName());
        dto.setAreaRisk(vo.getAreaRisk());
        dto.setUpdatedBy(sessionUser);

        if ("LEAVE_HIGH".equals(userLevel)) {
            result = service.updateAreaById(dto);
        }

        return result;
    }

    /**
     * 查询区域的代码和名称，用于前台select选择器
     * @return
     */
    @RequestMapping(value = "/selectAreaForSelect", method = RequestMethod.GET)
    public List<CommunityAreaPO> selectAreaForSelect(){
        List<CommunityAreaPO> selectAreaForSelect = service.selectAreaForSelect();
        return selectAreaForSelect;
    }

    /**
     * 社区感染情况展示
     * @return
     */
    @RequestMapping(value = "/selectAreaInfectionSituation", method = RequestMethod.GET)
    public List<AreaInfectionSituationDTO> selectAreaInfectionSituation(){
        List<AreaInfectionSituationDTO> situationDTOList = service.selectAreaInfectionSituation();
        return situationDTOList;
    }


}
