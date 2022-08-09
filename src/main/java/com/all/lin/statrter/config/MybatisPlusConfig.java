package com.all.lin.statrter.config;

import com.all.lin.statrter.mybatis.core.handler.AutoFillMetaObjectHandler;
import com.all.lin.statrter.mybatis.customtenant.CustomTenantLineHandler;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 向振华
 * @date 2021/04/26 14:45
 * 2.定义MybatisPlusConfig配置类将多租户插件生效：
 */
@Configuration
public class MybatisPlusConfig {


@Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 多租户插件（注意：这个一定要放在最上面）
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new CustomTenantLineHandler()));
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }

    @Bean
    public AutoFillMetaObjectHandler fillMetaObjectHandler() {
        return new AutoFillMetaObjectHandler();
    }
}