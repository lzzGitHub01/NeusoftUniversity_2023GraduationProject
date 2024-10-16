package com.neusoft.service.excelFileExport;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lzz
 */
public interface ExcelFileExportService {

    /**
     * Excel文件导出(人员信息)--最高权限
     * @param response
     * @throws IOException
     */
    public void excelFileDownloadForLH(HttpServletResponse response) throws IOException;

    /**
     * Excel文件导出(人员信息)---中级权限
     * @param response
     * @throws IOException
     */
    public void excelFileDownloadForLM(HttpServletResponse response) throws IOException;

    /**
     * Excel文件导出(人员信息)---普通权限
     * @param response
     * @throws IOException
     */
    public void excelFileDownloadForLN(HttpServletResponse response) throws IOException;
}
