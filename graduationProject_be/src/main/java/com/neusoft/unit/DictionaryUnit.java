package com.neusoft.unit;

import org.springframework.stereotype.Component;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

/**
 * @author lzz
 * 字典工具类
 */
@Component
public class DictionaryUnit {
    /*=============================================常规字典=============================================*/
    /**
     * 初始账户默认密码
     */
    public static final String DEFAULT_PASSWORD = "111";

    /**
     * 最高权限，中级权限，普通权限，系统管理员
     */
    public static final String LEAVE_HIGH = "0";
    public static final String LEAVE_MEDIUM = "1";
    public static final String LEAVE_NORMAL = "2";
    public static final String LEAVE_ADMINISTRATOR = "3";

    /*=============================================文件导出相关字典=============================================*/
    /**
     * 人员导出Excel文件的头部信息
     */
    public static final String[] PERSONAL_HEADER_DATA = {"序号","人员编号","姓名","年龄","手机号","所在社区","所在区域","所在楼组","门牌号","居住类型",
            "72小时核酸结果","是否存在基础疾病","疫苗接种针数","七日内行程","是否处于隔离状态","隔离政策"};



}
