package com.neusoft.service.communityBuildFloor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.entity.dto.CommunityBuildFloorDTO;
import com.neusoft.entity.po.CommunityAreaPO;
import com.neusoft.entity.po.CommunityBuildFloorPO;
import com.neusoft.entity.po.CommunityBuildPO;
import com.neusoft.mapper.CommunityAreaMapper;
import com.neusoft.mapper.CommunityBuildFloorMapper;
import com.neusoft.mapper.CommunityBuildMapper;
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
public class CommunityBuildFloorServiceImpl extends ServiceImpl<CommunityBuildFloorMapper, CommunityBuildFloorPO> implements CommunityBuildFloorService {

    @Autowired
    private CommunityBuildFloorMapper mapper;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private MackTimeStampCodeMapper codeMapper;

    @Autowired
    private CommunityAreaMapper areaMapper;

    @Autowired
    private CommunityBuildMapper buildMapper;

    @Override
    public Page<CommunityBuildFloorPO> selectAllBuildFloorData(CommunityBuildFloorDTO dto) {
        //创建分页插件
        Page<CommunityBuildFloorPO> page;
        //创建wrapper构造器
        QueryWrapper<CommunityAreaPO> areaQueryWrapper = new QueryWrapper();
        QueryWrapper<CommunityBuildPO> buildQueryWrapper = new QueryWrapper();
        QueryWrapper<CommunityBuildFloorPO> buildFloorQueryWrapper = new QueryWrapper();

        //dto传递数据到po
        CommunityBuildFloorPO po = new CommunityBuildFloorPO();
        if (!StringUtils.isEmpty(dto.getId())){
            po.setId(Long.valueOf(dto.getId()));
            buildFloorQueryWrapper.like("ID",po.getId());
        }

        if (!StringUtils.isEmpty(dto.getBuildFloorCode())){
            po.setBuildFloorCode(dto.getBuildFloorCode());
            buildFloorQueryWrapper.like("BUILD_FLOOR_CODE",po.getBuildFloorCode());
        }

        if (!StringUtils.isEmpty(dto.getCommunityCode())){
            po.setCommunityCode(dto.getCommunityCode());
            buildFloorQueryWrapper.like("COMMUNITY_CODE",po.getCommunityCode());
        }

        if (!StringUtils.isEmpty(dto.getBuildFloorNum())){
            po.setBuildFloorNum(dto.getBuildFloorNum());
            buildFloorQueryWrapper.like("BUILD_FLOOR_NUM",po.getBuildFloorNum());
        }

        if (!StringUtils.isEmpty(dto.getCommunityOccupant())){
            po.setCommunityOccupant(dto.getCommunityOccupant());
            buildFloorQueryWrapper.like("COMMUNITY_OCCUPANT",po.getCommunityOccupant());
        }

        if (dto.getPageCurrent() != null || "".equals(dto.getPageCurrent())){
            page = new Page<>(dto.getPageCurrent(),10);
        }else {
            page = new Page<>(1,10);
        }

        Page<CommunityBuildFloorPO> selectPage = mapper.selectPage(page, buildFloorQueryWrapper);

        return selectPage;
    }

    @Override
    public Integer addNewBuildFloor(CommunityBuildFloorDTO dto) {
        //获取当前系统时间
        String currentTime = CurrentTimeUnit.getCurrentTime();
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //创建wapper构造器
        QueryWrapper<CommunityBuildFloorPO> queryWrapper = new QueryWrapper<>();
        //生成code
        String buildFloorCode = makeBuildFloorCode();

        //取出对应社区CODE的社区名称
        String communityName = mapper.getCommunityNameByCode(sessionCommunityCode);
        //建筑规则名称
        String buildFloorName = communityName + "：" + dto.getBuildFloorNum() + "-" + dto.getCommunityOccupant();

        //dto传递数据到po
        CommunityBuildFloorPO po = new CommunityBuildFloorPO();
        po.setBuildFloorCode(buildFloorCode);
        po.setBuildFloorName(buildFloorName);
        po.setCommunityCode(sessionCommunityCode);
        po.setBuildFloorNum(dto.getBuildFloorNum());
        po.setCommunityOccupant(dto.getCommunityOccupant());
        po.setCreatedAt(currentTime);
        po.setCreatedBy(sessionUser);

        int result = mapper.insert(po);

        if (result == 1){
            System.out.println("=================================================================规则新增成功！=================================================================");
        }else {
            System.out.println("=================================================================则新增失败！=================================================================");
        }

        return result;
    }

    @Override
    public Integer deleteBuildFloorById(Long id) {
        int result = mapper.deleteById(id);

        if (result == 1) {
            System.out.println("=================================================================规则删除成功！=================================================================");
        } else {
            System.out.println("=================================================================规则删除失败！=================================================================");
        }

        return result;
    }

    @Override
    public Integer updateBuildFloorInfById(CommunityBuildFloorDTO dto) {
        //获取当前系统时间
        String currentTime = CurrentTimeUnit.getCurrentTime();
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);

        //dto传递数据到po
        CommunityBuildFloorPO po = new CommunityBuildFloorPO();
        po.setCommunityCode(dto.getCommunityCode());
        po.setBuildFloorNum(dto.getBuildFloorNum());
        po.setCommunityOccupant(dto.getCommunityOccupant());
        po.setUpdatedAt(currentTime);
        po.setUpdatedBy(sessionUser);

        //使用wrapper构造器
        UpdateWrapper<CommunityBuildFloorPO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("ID",dto.getId())
                .like("COMMUNITY_CODE",sessionCommunityCode);

        int result = mapper.update(po, updateWrapper);

        if (result == 1) {
            System.out.println("=================================================================规则更新成功！=================================================================");
        } else {
            System.out.println("=================================================================规则更新失败！=================================================================");
        }

        return result;
    }

    @Override
    public List<CommunityBuildFloorPO> selectBuildForFloorSelect() {
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //使用wrapper构造器
        QueryWrapper<CommunityBuildFloorPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("COMMUNITY_CODE",sessionCommunityCode);
        queryWrapper.select("BUILD_FLOOR_CODE","BUILD_FLOOR_NAME");

        List<CommunityBuildFloorPO> selectList = mapper.selectList(queryWrapper);
        return selectList;
    }

    /**
     * 生成建筑规格code
     * @return
     */
    public String makeBuildFloorCode(){
        //获取时间戳code
        String mapperCode = codeMapper.getCode();
        //生成区域code
        String buildFloorCode = MakeCodeUnit.makeBuildFloorCode(mapperCode);
        //创建Wrapper构造器
        QueryWrapper<CommunityBuildFloorPO> queryWrapper = new QueryWrapper<>();
        //判断生成的code是否已存在
        queryWrapper.like("BUILD_FLOOR_CODE",buildFloorCode);
        List<CommunityBuildFloorPO> codeList = mapper.selectList(queryWrapper);

        if (codeList.size() == 0){
            return buildFloorCode;
        }else {
            //如果code已存在则调用自己
            makeBuildFloorCode();
            return null;
        }
    }
}