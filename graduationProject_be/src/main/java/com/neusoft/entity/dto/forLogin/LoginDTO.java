package com.neusoft.entity.dto.forLogin;

import com.neusoft.entity.po.SystemManagerPO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lzz
 */
@Getter
@Setter
@ToString
public class LoginDTO {

    private Integer result;

    private SystemManagerPO systemManagerPO;
}
