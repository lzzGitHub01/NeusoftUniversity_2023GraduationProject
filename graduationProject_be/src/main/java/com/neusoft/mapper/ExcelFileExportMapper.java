package com.neusoft.mapper;

import com.neusoft.entity.dto.excelExport.PersonalExcelDTO;
import com.neusoft.entity.dto.mangeInf.MangeInfDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lzz
 */
@Repository
public interface ExcelFileExportMapper {


    /**
     * Excel文件数据--最高权限
     * @param sessionCommunityCode
     * @return
     */
    public List<PersonalExcelDTO> selectExcelDataForLH(String sessionCommunityCode);

    /**
     * Excel文件数据--中级权限
     * @param mangeInfDTO
     * @return
     */
    public List<PersonalExcelDTO> selectExcelDataForLM(@Param("dto")MangeInfDTO mangeInfDTO);

    /**
     * Excel文件数据--普通权限
     * @param mangeInfDTO
     * @return
     */
    public List<PersonalExcelDTO> selectExcelDataForLN(@Param("list") List<String> list, @Param("dto")MangeInfDTO mangeInfDTO);

}
