package com.neusoft;


import com.neusoft.mapper.CommunityBuildResidentMapper;
import com.neusoft.service.communityBuildResident.CommunityBuildResidentService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// 添加springBoot引导类
@SpringBootTest(classes = MyApplication.class)
// 添加测试依赖注解
@RunWith(SpringRunner.class)
public class BuildResidentTest {

    @Autowired
    private CommunityBuildResidentMapper mapper;

    @Autowired
    private CommunityBuildResidentService service;
}
