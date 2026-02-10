package com.neusoft;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.neusoft.unit.CreateCodeUnit;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@SpringBootTest(classes = MyApplication.class)
@RunWith(SpringRunner.class)
class ApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("hello,world");
    }



    /**
     * 文件生成
     */
    @Test
    void javaCode() {
        //数据库信息
        Map<String, String> mySqlInf = new HashMap<>();
        //数据库厂商&主机名&端口
        mySqlInf.put("requestHeader", "jdbc:mysql://localhost:3306/");
        //库名
        mySqlInf.put("libraryName", "lm2024");
        //表名
        mySqlInf.put("tableName","my_class");
        //用户名
        mySqlInf.put("Username", "root");
        //密码
        mySqlInf.put("Password", "111111");

        //文件信息
        Map<String, Object> fileInf = new HashMap<>();
        //文件生成位置
        fileInf.put("outputDir", "/src/main/java");
        //文件作者
        fileInf.put("author", "lm");
        //PO文件设置--主键生成策略
        fileInf.put("idType", IdType.AUTO);
        //PO文件设置--日期属性
        fileInf.put("dateType", DateType.SQL_PACK);
        //自定义文件夹名称--使用大驼峰，无需带PO、serve等名称后缀
        fileInf.put("fileName","MyClass");

        //包信息
        Map<String, String> packageInf = new HashMap<>();
        //java文件下全路径包名
        packageInf.put("parentPath", "com.neusoft");
        //存放controller文件的文件夹名称
        packageInf.put("controllerPath", "controller");
        //存放PO文件的文件夹名称
        packageInf.put("entityPath", "entity.po");
        //存放service文件的文件夹名称--service和impl都会生成
        packageInf.put("servicePath", "service");
        //存放mapper文件的文件夹名称
        packageInf.put("mapperPath", "mapper");
        //存放xml文件的文件夹名称
        packageInf.put("mapperXmlPath", "mapper.dao");

        CreateCodeUnit.convertTableToPO(mySqlInf,fileInf,packageInf);
    }

}
