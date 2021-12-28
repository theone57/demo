package com.lin.demo.sign.stu._4factory_interface.lin;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author linpu
 * @dateTime 2021-09-10 13:36
 * @email im.linpu@qq.com
 * @description 工厂
 */
@Component
public class ConvertFactoryComponent {

    private static final Map<PlatformEnum, RequestAndEventConvert> MAP = Maps.newConcurrentMap();

    /**
     * 从工厂获取具体实现类
     *
     * @return
     */
    public static RequestAndEventConvert getConverter(PlatformEnum insuranceInfoEnum) {
        return MAP.get(insuranceInfoEnum);
    }

    /**
     * 构造器注入 到工厂
     *
     * @param c
     */
    private ConvertFactoryComponent(List<RequestAndEventConvert> c) {
        c.forEach(i -> MAP.put(i.setInsuranceType(), i));
    }
}

