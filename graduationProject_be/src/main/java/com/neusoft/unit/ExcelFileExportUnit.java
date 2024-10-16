package com.neusoft.unit;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

/**
 * @author lzz
 * 文件导出工具类
 */
public class ExcelFileExportUnit {

    /**
     * 导出Excel文件的路径地址
     * @return
     */
    public static String getPathAddress(){
        //获取当前系统的桌面地址
        File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
        //生成完整地址
        String s = desktopDir.getAbsolutePath() + "\\";
        return s;
    }

}
