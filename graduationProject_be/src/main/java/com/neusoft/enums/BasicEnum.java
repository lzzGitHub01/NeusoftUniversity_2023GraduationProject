package com.neusoft.enums;

/**
 * @author lzz
 */
public interface BasicEnum<N,V> {
    /**
     * 获取名称
     * @return
     */
    N getName();

    /**
     * 获取值
     * @return
     */
    V getValue();
}
