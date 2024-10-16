package com.neusoft.service.excelFileExport;

import com.neusoft.entity.dto.excelExport.PersonalExcelDTO;
import com.neusoft.entity.dto.mangeInf.MangeInfDTO;
import com.neusoft.mapper.CommunityBuildMapper;
import com.neusoft.mapper.ExcelFileExportMapper;
import com.neusoft.unit.DictionaryUnit;
import com.neusoft.unit.ExcelFileExportUnit;
import com.neusoft.unit.ServletUnit;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzz
 */
@Service
public class ExcelFileExportServiceImpl implements ExcelFileExportService {

    @Autowired
    private ExcelFileExportMapper excelFileExportMapper;

    @Autowired
    private CommunityBuildMapper communityBuildMapper;

    @Autowired
    HttpServletRequest request;

    @Override
    public void excelFileDownloadForLH(HttpServletResponse response) throws IOException {
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);

        //设置导出文件的保存地址（当前地址为桌面）
        String PATH = ExcelFileExportUnit.getPathAddress();
        //给导出的文件名加上导出时间
        String fileName = PATH+"社区人员信息_"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".xlsx";

        // 1、创建一个webBook（创建一个Excel工作簿）
        Workbook workbook = new XSSFWorkbook();

        // 2、创建Sheet页（创建一个Excel工作表）
        Sheet sheet = workbook.createSheet("社区人员信息");
        // 2.1、设置默认列宽为20
        sheet.setDefaultColumnWidth((short) 12);
        sheet.setDefaultRowHeightInPoints(16);

        // 设置字体
        Font font = workbook.createFont();
        //设置字号
        font.setFontHeightInPoints((short)12);
        //设置是否加粗
        font.setBold(true);

        // 设置表头样式
        CellStyle headerStyle = workbook.createCellStyle();
        // 水平居中
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直集中
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //下边框
        headerStyle.setBorderBottom(BorderStyle.THIN);
        //左边框
        headerStyle.setBorderLeft(BorderStyle.THIN);
        //右边框
        headerStyle.setBorderRight(BorderStyle.THIN);
        //上边框
        headerStyle.setBorderTop(BorderStyle.THIN);
        //使用字体样式
        headerStyle.setFont(font);

        // 设置表内数据样式
        CellStyle bodyStyle = workbook.createCellStyle();
        // 水平居中
        bodyStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直集中
        bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 3、创建行，0表示第一行（Excel表头信息行）
        Row rowHead = sheet.createRow(0);


        // 3.1、Excel表头信息
        String[] headerData = DictionaryUnit.PERSONAL_HEADER_DATA;
        for (int i = 0; i < headerData.length; i++) {
            Cell cell = rowHead.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(headerData[i]);
        }

        //获取到数据库种的数据
        List<PersonalExcelDTO> personalExcelDTOList = excelFileExportMapper.selectExcelDataForLH(sessionCommunityCode);
        int rowNum = 1;
        for (PersonalExcelDTO personalExcel : personalExcelDTOList) {
            Row rowBody = sheet.createRow(rowNum);
            Cell cell = rowBody.createCell(0);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(rowNum);

            rowBody.createCell(1).setCellValue(personalExcel.getPersonnelCode());
            rowBody.createCell(2).setCellValue(personalExcel.getPersonnelName());
            rowBody.createCell(3).setCellValue(personalExcel.getPersonnelAge());
            rowBody.createCell(4).setCellValue(personalExcel.getPhone());
            rowBody.createCell(5).setCellValue(personalExcel.getCommunityName());
            rowBody.createCell(6).setCellValue(personalExcel.getAreaName());
            rowBody.createCell(7).setCellValue(personalExcel.getBuildName());
            rowBody.createCell(8).setCellValue(personalExcel.getHouseNumber());
            rowBody.createCell(9).setCellValue(personalExcel.getPersonnelState().getName());
            rowBody.createCell(10).setCellValue(personalExcel.getNatResult().getName());
            rowBody.createCell(11).setCellValue(personalExcel.getIsUd().getName());
            rowBody.createCell(12).setCellValue(personalExcel.getInoculate());
            rowBody.createCell(13).setCellValue(personalExcel.getSevenDayTrip());
            rowBody.createCell(14).setCellValue(personalExcel.getIsQuarantine().getName());
            rowBody.createCell(15).setCellValue(personalExcel.getQuarantinePolicy());
            rowNum++;
        }

        //4、创建输出流，将workbook对象（生成的Excel文件）下载到指定地址
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        //5.输出
        workbook.write(fileOutputStream);
        //6.关闭
        workbook.close();
    }

    @Override
    public void excelFileDownloadForLM(HttpServletResponse response) throws IOException {
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //获取session中的所属区域code
        String sessionAreaCode = ServletUnit.getSessionAreaCode(request);

        //存放当前登陆账户的信息
        MangeInfDTO mangeInfDTO = new MangeInfDTO();
        mangeInfDTO.setAreaCode(sessionAreaCode);
        mangeInfDTO.setCommunityCode(sessionCommunityCode);

        //设置导出文件的保存地址（当前地址为桌面）
        String PATH = ExcelFileExportUnit.getPathAddress();
        //给导出的文件名加上导出时间
        String fileName = PATH+"社区人员信息_"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".xlsx";

        // 1、创建一个webBook（创建一个Excel工作簿）
        Workbook workbook = new XSSFWorkbook();

        // 2、创建Sheet页（创建一个Excel工作表）
        Sheet sheet = workbook.createSheet("社区人员信息");
        // 2.1、设置默认列宽为20
        sheet.setDefaultColumnWidth((short) 12);
        sheet.setDefaultRowHeightInPoints(16);

        // 设置字体
        Font font = workbook.createFont();
        //设置字号
        font.setFontHeightInPoints((short)12);
        //设置是否加粗
        font.setBold(true);

        // 设置表头样式
        CellStyle headerStyle = workbook.createCellStyle();
        // 水平居中
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直集中
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //下边框
        headerStyle.setBorderBottom(BorderStyle.THIN);
        //左边框
        headerStyle.setBorderLeft(BorderStyle.THIN);
        //右边框
        headerStyle.setBorderRight(BorderStyle.THIN);
        //上边框
        headerStyle.setBorderTop(BorderStyle.THIN);
        //使用字体样式
        headerStyle.setFont(font);

        // 设置表内数据样式
        CellStyle bodyStyle = workbook.createCellStyle();
        // 水平居中
        bodyStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直集中
        bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 3、创建行，0表示第一行（Excel表头信息行）
        Row rowHead = sheet.createRow(0);


        // 3.1、Excel表头信息
        String[] headerData = DictionaryUnit.PERSONAL_HEADER_DATA;
        for (int i = 0; i < headerData.length; i++) {
            Cell cell = rowHead.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(headerData[i]);
        }

        //获取到数据库种的数据
        List<PersonalExcelDTO> personalExcelDTOList = excelFileExportMapper.selectExcelDataForLM(mangeInfDTO);
        int rowNum = 1;
        for (PersonalExcelDTO personalExcel : personalExcelDTOList) {
            Row rowBody = sheet.createRow(rowNum);
            Cell cell = rowBody.createCell(0);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(rowNum);

            rowBody.createCell(1).setCellValue(personalExcel.getPersonnelCode());
            rowBody.createCell(2).setCellValue(personalExcel.getPersonnelName());
            rowBody.createCell(3).setCellValue(personalExcel.getPersonnelAge());
            rowBody.createCell(4).setCellValue(personalExcel.getPhone());
            rowBody.createCell(5).setCellValue(personalExcel.getCommunityName());
            rowBody.createCell(6).setCellValue(personalExcel.getAreaName());
            rowBody.createCell(7).setCellValue(personalExcel.getBuildName());
            rowBody.createCell(8).setCellValue(personalExcel.getHouseNumber());
            rowBody.createCell(9).setCellValue(personalExcel.getPersonnelState().getName());
            rowBody.createCell(10).setCellValue(personalExcel.getNatResult().getName());
            rowBody.createCell(11).setCellValue(personalExcel.getIsUd().getName());
            rowBody.createCell(12).setCellValue(personalExcel.getInoculate());
            rowBody.createCell(13).setCellValue(personalExcel.getSevenDayTrip());
            rowBody.createCell(14).setCellValue(personalExcel.getIsQuarantine().getName());
            rowBody.createCell(15).setCellValue(personalExcel.getQuarantinePolicy());
            rowNum++;
        }

        //4、创建输出流，将workbook对象（生成的Excel文件）下载到指定地址
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        //5.输出
        workbook.write(fileOutputStream);
        //6.关闭
        workbook.close();
    }

    @Override
    public void excelFileDownloadForLN(HttpServletResponse response) throws IOException {
        //获取session中的用户名称
        String sessionUser = ServletUnit.getSessionUser(request);
        //获取session中的所属社区code
        String sessionCommunityCode = ServletUnit.getSessionCommunityCode(request);
        //获取session中的所属区域code
        String sessionAreaCode = ServletUnit.getSessionAreaCode(request);

        //存放当前登陆账户的信息
        MangeInfDTO mangeInfDTO = new MangeInfDTO();
        mangeInfDTO.setAreaCode(sessionAreaCode);
        mangeInfDTO.setCommunityCode(sessionCommunityCode);

        //存放楼组code的集合
        List<String> buildInf = new ArrayList<>();
        //查询，当前登陆的普通权限账户下，的所有楼组code
        String build = communityBuildMapper.selectMangerBuild(sessionUser);
        //将查出的楼组code数据存入集合中
        String[] split = build.split(",");
        for (int i = 0; i < split.length; i++) {
            buildInf.add(split[i]);
        }

        //设置导出文件的保存地址（当前地址为桌面）
        String PATH = ExcelFileExportUnit.getPathAddress();
        //给导出的文件名加上导出时间
        String fileName = PATH+"社区人员信息_"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".xlsx";

        // 1、创建一个webBook（创建一个Excel工作簿）
        Workbook workbook = new XSSFWorkbook();

        // 2、创建Sheet页（创建一个Excel工作表）
        Sheet sheet = workbook.createSheet("社区人员信息");
        // 2.1、设置默认列宽为20
        sheet.setDefaultColumnWidth((short) 12);
        sheet.setDefaultRowHeightInPoints(16);

        // 设置字体
        Font font = workbook.createFont();
        //设置字号
        font.setFontHeightInPoints((short)12);
        //设置是否加粗
        font.setBold(true);

        // 设置表头样式
        CellStyle headerStyle = workbook.createCellStyle();
        // 水平居中
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直集中
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //下边框
        headerStyle.setBorderBottom(BorderStyle.THIN);
        //左边框
        headerStyle.setBorderLeft(BorderStyle.THIN);
        //右边框
        headerStyle.setBorderRight(BorderStyle.THIN);
        //上边框
        headerStyle.setBorderTop(BorderStyle.THIN);
        //使用字体样式
        headerStyle.setFont(font);

        // 设置表内数据样式
        CellStyle bodyStyle = workbook.createCellStyle();
        // 水平居中
        bodyStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直集中
        bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 3、创建行，0表示第一行（Excel表头信息行）
        Row rowHead = sheet.createRow(0);


        // 3.1、Excel表头信息
        String[] headerData = DictionaryUnit.PERSONAL_HEADER_DATA;
        for (int i = 0; i < headerData.length; i++) {
            Cell cell = rowHead.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(headerData[i]);
        }

        //获取到数据库种的数据
        List<PersonalExcelDTO> personalExcelDTOList = excelFileExportMapper.selectExcelDataForLN(buildInf,mangeInfDTO);
        int rowNum = 1;
        for (PersonalExcelDTO personalExcel : personalExcelDTOList) {
            Row rowBody = sheet.createRow(rowNum);
            Cell cell = rowBody.createCell(0);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(rowNum);

            rowBody.createCell(1).setCellValue(personalExcel.getPersonnelCode());
            rowBody.createCell(2).setCellValue(personalExcel.getPersonnelName());
            rowBody.createCell(3).setCellValue(personalExcel.getPersonnelAge());
            rowBody.createCell(4).setCellValue(personalExcel.getPhone());
            rowBody.createCell(5).setCellValue(personalExcel.getCommunityName());
            rowBody.createCell(6).setCellValue(personalExcel.getAreaName());
            rowBody.createCell(7).setCellValue(personalExcel.getBuildName());
            rowBody.createCell(8).setCellValue(personalExcel.getHouseNumber());
            rowBody.createCell(9).setCellValue(personalExcel.getPersonnelState().getName());
            rowBody.createCell(10).setCellValue(personalExcel.getNatResult().getName());
            rowBody.createCell(11).setCellValue(personalExcel.getIsUd().getName());
            rowBody.createCell(12).setCellValue(personalExcel.getInoculate());
            rowBody.createCell(13).setCellValue(personalExcel.getSevenDayTrip());
            rowBody.createCell(14).setCellValue(personalExcel.getIsQuarantine().getName());
            rowBody.createCell(15).setCellValue(personalExcel.getQuarantinePolicy());
            rowNum++;
        }

        //4、创建输出流，将workbook对象（生成的Excel文件）下载到指定地址
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        //5.输出
        workbook.write(fileOutputStream);
        //6.关闭
        workbook.close();
    }
}
