package com.neusoft;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.entity.dto.PersonnelDTO;
import com.neusoft.entity.dto.excelExport.PersonalExcelDTO;
import com.neusoft.entity.dto.infectionStatus.InfectionStatusDTO;
import com.neusoft.entity.po.PersonnelPO;
import com.neusoft.enums.IsEnable;
import com.neusoft.mapper.ExcelFileExportMapper;
import com.neusoft.mapper.PersonnelMapper;
import com.neusoft.service.personnel.PersonnelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

// 添加springBoot引导类
@SpringBootTest(classes = MyApplication.class)
// 添加测试依赖注解
@RunWith(SpringRunner.class)
public class PersonnelTest {

    @Autowired
    private PersonnelMapper mapper;

    @Autowired
    private ExcelFileExportMapper excelFileExportMapper;

    @Autowired
    private PersonnelService service;

    /**
     * 查询测试
     */
    @Test
    public void selectAllPersonal(){
        PersonnelDTO dto = new PersonnelDTO();
        //dto.setBuildCode("BID00002");

        service.selectAllPersonnelData(dto);
    }

    /**
     * 新增测试
     */
    @Test
    public void addNewPersonal(){
        PersonnelDTO dto = new PersonnelDTO();
        dto.setAreaName("测试人01");
        dto.setPersonnelAge("22");
        dto.setAreaName("测试区域02");
        dto.setBuildName("测试建筑07");

        Integer integer = service.addNewPersonal(dto);
        System.out.println("结果："+integer);
    }

    @Test
    public void deletePersonalById(){
        Long id = 1623251940624015362L;
        Integer integer = service.deletePersonalById(id);
        System.out.println("结果："+integer);
    }

    @Test //直接进行接口测试
    public void total(){
        InfectionStatusDTO infectionStatusDTO = service.infectionStatusForLH();
        System.out.println(infectionStatusDTO);
    }

    @Test
    public void excel(){
        List<PersonalExcelDTO> list = excelFileExportMapper.selectExcelDataForLH("CMY00001");
        for (PersonalExcelDTO dto : list) {
            System.out.println("数据："+dto);
        }
    }
}
