package com.all.lin.statrter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * SpringMVC的请求-获得请求参数-自定义类型转换器(应用)
 * SpringMVC 默认已经提供了一些常用的类型转换器，例如客户端提交的字符串转换成int型进行参数设置。
 * 但是不是所有的数据类型都提供了转换器，没有提供的就需要自定义转换器，例如：日期类型的数据就需要自
 * 定义转换器。
 *
 * @author lin
 * @version v1.0
 * @program ssmpro
 * @description SpringMVC的请求-获得请求参数-自定义类型转换器(应用)
 * @date 2021-01-11 18:12
 */
@Configuration
public class DateConverter {
    @Bean
    public Converter<String, LocalDateTime> convert() {
        return new Converter<>() {
            @Override
            public LocalDateTime convert(String dateStr) {
                //将日期字符串转换成日期对象 返回
                LocalDateTime dateTime = null;
                try {
                    dateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return dateTime;
            }
        };

    }
}

