package com.neusoft;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.entity.dto.CommunityBuildDTO;
import com.neusoft.entity.po.CommunityBuildPO;
import com.neusoft.mapper.CommunityBuildMapper;
import com.neusoft.service.communityBuild.CommunityBuildService;
import com.neusoft.unit.ServletUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

// 添加springBoot引导类
@SpringBootTest(classes = MyApplication.class)
// 添加测试依赖注解
@RunWith(SpringRunner.class)
public class BuildTest {

    @Autowired
    private CommunityBuildMapper mapper;

    @Autowired
    private CommunityBuildService service;

    @Autowired
    HttpServletRequest request;

    @Test
    public void selectAllBuildData(){
        Page<CommunityBuildDTO> page = new Page<>(1,8);
        CommunityBuildPO po = new CommunityBuildPO();

        Page<CommunityBuildDTO> allBuildData = mapper.selectAllBuildData(page, po);
    }

    @Test //直接进行接口测试
    public void getSessionUser(){
        String sessionUser = ServletUnit.getSessionUser(request);
        System.out.println("数据："+sessionUser);
    }

    @Test
    public void getBuildInf(){
        List<String> buildInf = new ArrayList<>();

        String build = mapper.selectMangerBuild("test");

        String[] split = build.split(",");
        for (int i = 0; i < split.length; i++) {
            buildInf.add(split[i]);
        }


        /*List<CommunityBuildDTO> communityBuildDTOS = mapper.selectData(buildInf);
        System.out.println("数据1："+communityBuildDTOS);*/

    }

}
