package com.neusoft.service.communityArea;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.entity.dto.CommunityAreaDTO;
import com.neusoft.entity.dto.SelectAllAreaDTO;
import com.neusoft.entity.dto.infectionStatus.AreaInfectionSituationDTO;
import com.neusoft.entity.po.CommunityAreaPO;
import com.neusoft.entity.vo.CommunityAreaVO;

import java.util.List;

/**
 * @author lzz
 */
public interface CommunityAreaService extends IService<CommunityAreaPO> {

    /**
     * 查询所有区域数据，用于前端展示
     * @return
     */
    public List<CommunityAreaPO> selectAllData(String managerName);

    /**
     * 根据提供的条件查询所有对应的区域数据（支持模糊查询）
     * @param dto
     * @return
     */
    public Page<SelectAllAreaDTO> selectAreaDataByCondition(SelectAllAreaDTO dto);

    /**
     * 查询所有区域数据（最高权限者：查询展示整合优化版）
     * @return
     */
    public Page<SelectAllAreaDTO> selectAllAreaData(SelectAllAreaDTO dto);

    /**
     * 分页查询所有区域数据（最高权限者）
     * @return
     */
    public Page<SelectAllAreaDTO> selectAllAreaDataPage(Integer pageCurrent);

    /**
     * 新增区域
     * @param dto
     * @return
     */
    public Integer createNewArea(CommunityAreaDTO dto);

    /**
     * 根据区域ID删除区域
     * @param id
     * @return
     */
    public Integer deleteAreaByID(Long id);

    /**
     * 更新指定ID区域的数据
     * @param dto
     * @return
     */
    public Integer updateAreaById(CommunityAreaDTO dto);

    /**
     * 查询区域的代码和名称，用于前台select选择器
     * @return
     */
    public List<CommunityAreaPO> selectAreaForSelect();

    /**
     * 社区感染情况展示
     * @return
     */
    public List<AreaInfectionSituationDTO> selectAreaInfectionSituation();
}
