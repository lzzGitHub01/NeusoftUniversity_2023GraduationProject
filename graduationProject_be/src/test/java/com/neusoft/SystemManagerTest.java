package com.neusoft;

import com.neusoft.entity.dto.SystemManagerDTO;
import com.neusoft.entity.dto.forLogin.LoginDTO;
import com.neusoft.entity.po.SystemManagerPO;
import com.neusoft.enums.GmLeave;
import com.neusoft.enums.IsEnable;
import com.neusoft.mapper.SystemManagerMapper;
import com.neusoft.service.SystemManager.SystemManagerService;
import com.neusoft.unit.MakeCodeUnit;
import com.neusoft.unit.Md5Unit;
import com.neusoft.unit.ServletUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// 添加springBoot引导类
@SpringBootTest(classes = MyApplication.class)
// 添加测试依赖注解
@RunWith(SpringRunner.class)
public class SystemManagerTest {

    @Autowired
    private SystemManagerMapper mapper;

    @Autowired
    private SystemManagerService service;

    @Autowired
    HttpServletRequest request;

    /**
     * 新增用户
     */
    @Test
    public void addNewManager(){
        SystemManagerPO po = new SystemManagerPO();

        po.setManageCode("MAN00001");
        po.setManageName("lzz");
        String saltValue = Md5Unit.getSaltValue();
        po.setUuid(saltValue);
        String s = Md5Unit.passwordEncryption("111", saltValue);
        po.setPassword(s);
        po.setPhone("15008614749");
        /*po.setManageLevel("最高权限");
        po.setIsEnable(120001);*/

        int insert = mapper.insert(po);
        System.out.println("数据："+insert);
    }

    /**
     * MD5工具类测试
     */
    @Test
    public void MD5(){
        //根据用户姓名查询
        SystemManagerPO po = new SystemManagerPO();
        po.setManageName("lzz");
        po.setPassword("11");
        SystemManagerPO systemManagerPO = mapper.userLogin(po);

        System.out.println(systemManagerPO);
        if(systemManagerPO != null){
            String password = po.getPassword();
            String pass = systemManagerPO.getPassword();
            String uuid = systemManagerPO.getUuid();
            String s = Md5Unit.passwordEncryption(password, uuid);
            if (s.equals(pass)){
                System.out.println("登陆成功");
            }else {
                System.out.println("密码错误登陆失败");
            }
        }
    }

    /**
     * 用户登陆测试
     */
    @Test
    public void loginTest(){
        SystemManagerDTO dto = new SystemManagerDTO();
        dto.setManageName("lzz");
        dto.setPassword("111");
        LoginDTO loginDTO = service.userLogin(dto);
        System.out.println("返回结果："+loginDTO.getResult());
    }

    /**
     * 管理员code生成测试
     */
    @Test
    public void getCodeTest(){
        String s = MakeCodeUnit.makeUserCode("0317061E-BE21-42C3-97B3-17B76CA6AF2F");
        System.out.println("数据："+s);
    }

    @Test
    public void EnumTest(){
        System.out.println("枚举："+GmLeave.valueOf("LEAVE_HIGH").getValue());
        System.out.println("枚举："+GmLeave.valueOf("LEAVE_HIGH").getName());

        System.out.println("枚举："+IsEnable.valueOf("IS_ENABLE_YES").getValue());
        System.out.println("枚举："+IsEnable.valueOf("IS_ENABLE_YES").getName());
    }
}
