package com.all.lin.statrter.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.math.BigDecimal;

/**
 * @author linpu
 * @dateTime 2022-08-01 15:17
 * @email im.linpu@qq.com
 * @description 参考：
 * 1. https://blog.csdn.net/z69183787/article/details/111039717
 * 2.https://github.com/FasterXML/jackson-databind/wiki/Deserialization-Features
 * 3.https://github.com/FasterXML/jackson-databind/wiki/Serialization-features
 * <p>
 * https://blog.csdn.net/m0_47645268/article/details/123407675?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_title~default-0-123407675-blog-100979414.pc_relevant_multi_platform_whitelistv3&spm=1001.2101.3001.4242.1&utm_relevant_index=3
 * https://blog.csdn.net/chuojuezhi4749/article/details/100979414?spm=1001.2101.3001.6650.3&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-3-100979414-blog-103072752.pc_relevant_aa&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-3-100979414-blog-103072752.pc_relevant_aa&utm_relevant_index=6
 * # bigdecimal精度丢失与科学计数法
 * spring:
 * jackson:
 * deserialization:
 * use-big-decimal-for-floats: true
 * serialization:
 * write-bigdecimal-as-plain: true
 */
public class JacksonConfig {

    /**
     * Jackson全局转化BigDecimal类型为String，解决jackson序列化时BigDecimal类型缺失精度问题
     *
     * @return Jackson2ObjectMapperBuilderCustomizer 注入的对象
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        Jackson2ObjectMapperBuilderCustomizer cunstomizer = new Jackson2ObjectMapperBuilderCustomizer() {

            @Override
            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
                jacksonObjectMapperBuilder.serializerByType(BigDecimal.class, ToStringSerializer.instance);
            }
        };

        return cunstomizer;
    }

}
