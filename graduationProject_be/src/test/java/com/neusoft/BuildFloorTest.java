package com.neusoft;

import com.neusoft.mapper.CommunityBuildFloorMapper;
import com.neusoft.service.communityBuildFloor.CommunityBuildFloorService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// 添加springBoot引导类
@SpringBootTest(classes = MyApplication.class)
// 添加测试依赖注解
@RunWith(SpringRunner.class)
public class BuildFloorTest {

    @Autowired
    private CommunityBuildFloorMapper mapper;

    @Autowired
    private CommunityBuildFloorService service;

}
