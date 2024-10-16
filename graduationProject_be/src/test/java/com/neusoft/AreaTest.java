package com.neusoft;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.entity.dto.CommunityAreaDTO;
import com.neusoft.entity.dto.PersonnelDTO;
import com.neusoft.entity.dto.SelectAllAreaDTO;
import com.neusoft.entity.po.CommunityAreaPO;
import com.neusoft.mapper.CommunityAreaMapper;
import com.neusoft.service.communityArea.CommunityAreaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

// 添加springBoot引导类
@SpringBootTest(classes = MyApplication.class)
// 添加测试依赖注解
@RunWith(SpringRunner.class)
public class AreaTest {

    @Autowired
    private CommunityAreaMapper mapper;

    @Autowired
    private CommunityAreaService service;

    /**
     * 获取当前账户等级
     */
    @Test
    public void getUserLevel(){
        String level = mapper.selectCurrentUserLevel("lzz");
        int i = Integer.parseInt(level);
        System.out.println("等级："+level);
        System.out.println("等级："+i);
    }

    /**
     * 查询所有数据
     */
    @Test
    public void selectAllDate(){
        List<CommunityAreaPO> areaPOList = service.selectAllData("admin");
        System.out.println("数据："+areaPOList);
    }

    @Test
    public void sellectAllAreaData(){
        CommunityAreaPO po = new CommunityAreaPO();
        po.setId(1614441799376134145L);
        Page<SelectAllAreaDTO> page = new Page<>(1,8);
        Page<SelectAllAreaDTO> communityAreaPOPage = mapper.selectAllAreaData(page, po);
        System.out.println(communityAreaPOPage);
    }

    @Test
    public void selectTest(){
        CommunityAreaPO po = new CommunityAreaPO();
        Page<SelectAllAreaDTO> page = new Page<>(1,8);
        //po.setAreaName("04");
        //po.setAreaRisk(120021);
        Long aLong = Long.valueOf("34145");
        po.setId(aLong);
        Page<SelectAllAreaDTO> allAreaDTOList = mapper.selectAreaDataByCondition(page,po);

    }

    @Test
    public void addNewPersonal(){
        //创建wrapper构造器
        QueryWrapper<CommunityAreaPO> areaQueryWrapper = new QueryWrapper();
        PersonnelDTO dto = new PersonnelDTO();
        dto.setAreaName("测试区域111");
        areaQueryWrapper.inSql("AREA_CODE","SELECT AREA_CODE FROM `my_community_area` WHERE AREA_NAME = '"+dto.getAreaName()+"'");
        List<CommunityAreaPO> areaList = mapper.selectList(areaQueryWrapper);
        for (CommunityAreaPO areaPO : areaList) {
            System.out.println("数据："+areaPO.getAreaCode());
        }
    }

    @Test
    public void selectAreaForSelect(){
        List<CommunityAreaPO> communityAreaPOS = service.selectAreaForSelect();
        System.out.println("数据："+communityAreaPOS);
    }
}
