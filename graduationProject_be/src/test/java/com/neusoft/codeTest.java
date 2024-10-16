package com.neusoft;

import com.neusoft.mapper.MackTimeStampCodeMapper;
import com.neusoft.unit.DictionaryUnit;
import com.neusoft.unit.MakeCodeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

// 添加springBoot引导类
@SpringBootTest(classes = MyApplication.class)
// 添加测试依赖注解
@RunWith(SpringRunner.class)
public class codeTest {

    @Autowired
    private MackTimeStampCodeMapper mapper;

    @Test
    public void getCode(){

        String code = mapper.getCode();
        System.out.println("数据："+code);
        String substring = code.substring(code.length() - 5);

        /*System.out.println("生成Code："+code);
        System.out.println("截取："+substring);*/

        String communityCode = MakeCodeUnit.makeCommunityCode(code);
        String areaCode = MakeCodeUnit.makeAreaCode(code);
        String buildCode = MakeCodeUnit.makeBuildCode(code);
        String personnelCode = MakeCodeUnit.makePersonnelCode(code);
        String floorCode = MakeCodeUnit.makeBuildFloorCode(code);

        System.out.println(communityCode);
        System.out.println(areaCode);
        System.out.println(buildCode);
        System.out.println(personnelCode);
        System.out.println(floorCode);
    }

    @Test
    public void getPath(){

        File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
        // s为当前用户桌面所在路径
        String s = desktopDir.getAbsolutePath() + "\\";
        System.out.println("路径："+s);
    }
}
