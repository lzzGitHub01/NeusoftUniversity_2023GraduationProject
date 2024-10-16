package com.neusoft.mapper;

import org.springframework.stereotype.Repository;


/**
 * @author lzz
 */
@Repository
public interface MackTimeStampCodeMapper {

    /**
     * 获取时间戳（精确到后五位）用于code生成
     * @return
     */
    public String getCode();
}
