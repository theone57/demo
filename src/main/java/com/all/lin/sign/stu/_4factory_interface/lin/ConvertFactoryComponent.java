package com.all.lin.sign.stu._4factory_interface.lin;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author linpu
 * @dateTime 2021-09-10 13:36
 * @email im.linpu@qq.com
 * @description 工厂
 * <p>
 * 测试类
 * @link MyFactoryTest
 */
@Component
public class ConvertFactoryComponent {

    private static final Map<PlatformEnum, Handler> MAP = Maps.newConcurrentMap();

    /**
     * 从工厂获取具体实现类
     *
     * @return
     */
    public static Handler getConverter(PlatformEnum insuranceInfoEnum) {
        return MAP.get(insuranceInfoEnum);
    }

    /**
     * 构造器注入 到工厂
     *
     * @param c
     */
    private ConvertFactoryComponent(List<Handler> c) {
        c.forEach(i -> MAP.put(i.setPlatformType(), i));
    }
}

