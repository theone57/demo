package com.all.lin.statrter.mybatis.core.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author linpu
 * @dateTime 2022-07-28 09:29
 * @email im.linpu@qq.com
 * @description 配置自定义多数据源 需要手动设置config里
 */
public class AutoFillMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //第一个对应实体属性名, 第二个参数需要填充的值
        setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        //第一个对应实体属性名, 第二个参数需要填充的值
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //第一个对应实体属性名, 第二个参数需要填充的值
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}
