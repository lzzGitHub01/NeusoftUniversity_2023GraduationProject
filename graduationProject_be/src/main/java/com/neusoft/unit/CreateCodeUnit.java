package com.neusoft.unit;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Map;

/**
 * @author lm
 */
public class CreateCodeUnit {

    /**
     * 文件生成
     * mybatis-plus-boot-starter 3.1.0
     * mybatis-plus-generator 3.1.0
     * velocity-engine-core 2.3.0
     * @param mySqlInf
     * @param fileInf
     * @param packageInf
     */
    public static void convertTableToPO(Map<String, String> mySqlInf, Map<String, Object> fileInf, Map<String, String> packageInf) {


        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl(mySqlInf.get("requestHeader")+mySqlInf.get("libraryName")+"?serverTimezone=UTC&characterEncoding=utf-8&useSSL=false")
                .setUsername(mySqlInf.get("Username"))
                .setPassword(mySqlInf.get("Password"))
                .setDbType(DbType.MYSQL);

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        //文件生成目录
        globalConfig.setOutputDir(System.getProperty("user.dir") + fileInf.get("outputDir").toString())
                //文件作者
                .setAuthor(fileInf.get("author").toString())
                //设置生成文件后是否打开文件所在目录
                .setOpen(false)
                //设置主键生成策略
                .setIdType((IdType)fileInf.get("idType"))
                //设置日期属性
                .setDateType((DateType)fileInf.get("dateType"))
                //自定义文件名称
                .setEntityName(makePO(fileInf.get("fileName").toString()))
                .setControllerName(makeController(fileInf.get("fileName").toString()))
                .setServiceName(makeService(fileInf.get("fileName").toString()))
                .setServiceImplName(makeServiceImpl(fileInf.get("fileName").toString()))
                .setMapperName(makeMapper(fileInf.get("fileName").toString()))
                .setXmlName(makeMapper(fileInf.get("fileName").toString()));

        // 包名配置
        PackageConfig packageConfig = new PackageConfig();
        //配置路径
        packageConfig.setParent(packageInf.get("parentPath"))
                //配置存放文件的目标文件夹
                .setController(packageInf.get("controllerPath"))
                .setEntity(packageInf.get("entityPath"))
                .setService(packageInf.get("servicePath")+"."+toLowerCaseFirstChar(fileInf.get("fileName").toString()))
                .setServiceImpl(packageInf.get("servicePath")+"."+toLowerCaseFirstChar(fileInf.get("fileName").toString()))
                .setMapper(packageInf.get("mapperPath"))
                .setXml(packageInf.get("mapperXmlPath"));


        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        //根据表名生成实体类的名称
        strategyConfig
                //.setNaming(NamingStrategy.underline_to_camel)
                //根据列名生成实体类的属性名
                .setColumnNaming(NamingStrategy.underline_to_camel)
                //开启lombok
                .setEntityLombokModel(true)
                //开启@TableField注解
                .setEntityTableFieldAnnotationEnable(true)
                .setEntityBooleanColumnRemoveIsPrefix(true)
                //需要生成代码的数据库表名
                .setInclude(mySqlInf.get("tableName"));

        // 代码生成器对象
        AutoGenerator generator = new AutoGenerator();
        generator.setDataSource(dataSourceConfig)
                .setGlobalConfig(globalConfig)
                .setPackageInfo(packageConfig)
                .setStrategy(strategyConfig);

        // 执行生成
        generator.execute();
    }

    /**
     * 表名转换
     * @param tableName
     */
    private static String nameResolution(String tableName){
        StringBuilder result = new StringBuilder();
        boolean upperCase = false;

        for (char c : tableName.toCharArray()) {
            if (c == '_') {
                upperCase = true;
            } else {
                if (upperCase) {
                    result.append(Character.toUpperCase(c));
                    upperCase = false;
                } else {
                    result.append(c);
                }
            }
        }

        // 确保首字母大写
        if (result.length() > 0) {
            result.setCharAt(0, Character.toUpperCase(result.charAt(0)));
        }
        makeController(result.toString());
        makePO(result.toString());
        makeService(result.toString());
        makeServiceImpl(result.toString());
        makeMapper(result.toString());

        return result.toString();
    }

    /**
     * Controller类
     * @param fileName
     * @return
     */
    private static String makeController(String fileName){
        return fileName+"Controller";
    }

    /**
     * PO类
     * @param fileName
     * @return
     */
    private static String makePO(String fileName){
        return fileName+"PO";
    }

    /**
     * service类
     * @param fileName
     * @return
     */
    private static String makeService(String fileName){
        return fileName+"Service";
    }

    /**
     * serviceImpl类
     * @param fileName
     * @return
     */
    private static String makeServiceImpl(String fileName){
        return fileName+"ServiceImpl";
    }

    /**
     * mapper类
     * @param fileName
     * @return
     */
    private static String makeMapper(String fileName){
        return fileName+"Mapper";
    }

    /**
     * 生成文件夹名称 使首字母小写
     * @param str
     * @return
     */
    public static String toLowerCaseFirstChar(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        char firstChar = str.charAt(0);
        if (Character.isLowerCase(firstChar)) {
            return str;
        }
        return Character.toLowerCase(firstChar) + str.substring(1);
    }

}
