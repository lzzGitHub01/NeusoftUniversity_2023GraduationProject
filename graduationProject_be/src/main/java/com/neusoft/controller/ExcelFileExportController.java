package com.neusoft.controller;

import com.neusoft.enums.GmLeave;
import com.neusoft.service.excelFileExport.ExcelFileExportService;
import com.neusoft.unit.DictionaryUnit;
import com.neusoft.unit.ServletUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lzz
 * 文件导出
 */

@RestController
@RequestMapping(value = "/fileExport")
public class ExcelFileExportController {

    @Autowired
    private ExcelFileExportService fileExportService;

    /**
     * excel文件导出（人员信息）
     * @param response
     * @param request
     */
    @RequestMapping(value = "/excelFileDownload", method = RequestMethod.GET)
    public void excelFileDownload(HttpServletResponse response, HttpServletRequest request){
        //获取session中的用户的等级
        String sessionLevel = ServletUnit.getSessionLevel(request);
        //获取等级数值
        GmLeave gmLeave = GmLeave.valueOf(sessionLevel);
        String leaveValue = gmLeave.getValue();

        if (DictionaryUnit.LEAVE_HIGH.equals(leaveValue)){
            try{
                fileExportService.excelFileDownloadForLH(response);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if (DictionaryUnit.LEAVE_MEDIUM.equals(leaveValue)){
            try{
                fileExportService.excelFileDownloadForLM(response);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if (DictionaryUnit.LEAVE_NORMAL.equals(leaveValue)){
            try{
                fileExportService.excelFileDownloadForLN(response);
            }catch (Exception e){
                e.printStackTrace();

            }
        }
    }


}
