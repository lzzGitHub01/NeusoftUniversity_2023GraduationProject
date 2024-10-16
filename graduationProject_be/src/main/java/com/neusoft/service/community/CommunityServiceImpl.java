package com.neusoft.service.community;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.entity.dto.CommunityBuildDTO;
import com.neusoft.entity.dto.CommunityDTO;
import com.neusoft.entity.po.CommunityPO;
import com.neusoft.mapper.CommunityMapper;
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
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper, CommunityPO> implements CommunityService{

    @Autowired
    private CommunityMapper mapper;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private MackTimeStampCodeMapper codeMapper;


    @Override
    public Page<CommunityDTO> selectAllCommunity(CommunityDTO dto) {
        //创建分页插件
        Page<CommunityPO> page;

        //dto传递数据到po
        CommunityPO po = new CommunityPO();
        if (!StringUtils.isEmpty(dto.getId())){
            po.setId(Long.valueOf(dto.getId()));
        }
        if (!StringUtils.isEmpty(dto.getCommunityCode())){
            po.setCommunityCode(dto.getCommunityCode());
        }
        if (!StringUtils.isEmpty(dto.getCommunityName())){
            po.setCommunityName(dto.getCommunityCode());
        }
        if (dto.getPageCurrent() != null || "".equals(dto.getPageCurrent())){
            page = new Page<>(dto.getPageCurrent(),10);
        }else {
            page = new Page<>(1,10);
        }

        Page<CommunityDTO> communityDTOPage = mapper.selectAllCommunity(page, po);
        return communityDTOPage;
    }

    @Override
    public Integer addNewCommunity(CommunityDTO dto) {
        //获取当前时间
        String currentTime = CurrentTimeUnit.getCurrentTime();
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        //创建wrapper构造器
        QueryWrapper<CommunityPO> areaQueryWrapper = new QueryWrapper();
        //获取code
        String CommunityCode = makeCommunityCode();

        //dto传递数据到po
        CommunityPO po = new CommunityPO();
        po.setCommunityCode(CommunityCode);

        if (!StringUtils.isEmpty(dto.getCommunityName())){
            po.setCommunityName(dto.getCommunityName());
        }

        po.setCreatedAt(currentTime);
        po.setCreatedBy(sessionUser);

        int result = mapper.insert(po);

        if (result == 1){
            System.out.println("=================================================================社区新增成功！=================================================================");
        }else {
            System.out.println("=================================================================社区新增失败！=================================================================");
        }

        return result;
    }

    /**
     * 生成社区code
     * @return
     */
    public String makeCommunityCode(){
        //获取时间戳code
        String mapperCode = codeMapper.getCode();
        //生成区域code
        String communityCode = MakeCodeUnit.makeCommunityCode(mapperCode);
        //创建Wrapper构造器
        QueryWrapper<CommunityPO> queryWrapper = new QueryWrapper<>();
        //判断生成的code是否已存在
        queryWrapper.like("COMMUNITY_CODE",communityCode);
        List<CommunityPO> codeList = mapper.selectList(queryWrapper);

        if (codeList.size() == 0){
            return communityCode;
        }else {
            //如果code已存在则调用自己
            makeCommunityCode();
            return null;
        }
    }

    @Override
    public Integer deleteCommunityById(Long id) {

        int result = mapper.deleteById(id);

        if (result == 1) {
            System.out.println("=================================================================社区删除成功！=================================================================");
        } else {
            System.out.println("=================================================================社区删除失败！=================================================================");
        }

        return result;
    }

    @Override
    public Integer updatePersonalInfById(CommunityDTO dto) {
        //获取当前时间
        String currentTime = CurrentTimeUnit.getCurrentTime();
        //获取session中的用户名数据
        String sessionUser = ServletUnit.getSessionUser(request);
        //创建wrapper构造器
        UpdateWrapper<CommunityPO> updateWrapper = new UpdateWrapper<>();
        if (!StringUtils.isEmpty(dto.getId())){
            updateWrapper.like("ID",dto.getId());
        }
        if (!StringUtils.isEmpty(dto.getCommunityCode())){
            updateWrapper.like("COMMUNITY_CODE",dto.getCommunityCode());
        }

        //dto传递数据到po
        CommunityPO po = new CommunityPO();
        if (!StringUtils.isEmpty(dto.getCommunityName())){
            po.setCommunityName(dto.getCommunityName());
        }
        po.setCreatedBy(sessionUser);
        po.setCreatedAt(currentTime);

        int result = mapper.update(po, updateWrapper);

        return result;
    }
}
