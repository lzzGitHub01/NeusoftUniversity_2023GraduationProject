package com.neusoft.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lzz
 * 配置类
 */
@Configuration  // 标注当前类是一个配置类
@MapperScan("com.neusoft.mapper")  // 设置扫描mapper接口所在的包(有配置类建议添加到配置类)
public class MyBatisPlusConfig {

    /**
     * 配置MybatisPlus中的插件
     * @return
     * @Bean：将方法交给spring容器来管理
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        // 创建插件对象
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 添加分页插件，并设置数据库类型
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 添加乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        // 返回对象
        return interceptor;
    }

}
