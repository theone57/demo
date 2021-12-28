package com.lin.demo.sign.stu._23strategy.lin;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.lin.demo.sign.stu._4factory_interface.lin.PlatformEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 投保策略包装类
 * @author alex
 */

@Service
@Slf4j
public class InsuredActivity {

    /**
     * 缓存接口
     */
    public static final Map<PlatformEnum, ServiceStrategy> STRATEGY_MAP = Maps.newConcurrentMap();

    /**
     * 构造器注入 代替工厂模式
     */
    public InsuredActivity(List<ServiceStrategy> insuredStrategyList) {
        insuredStrategyList.forEach(insuredStrategy -> STRATEGY_MAP.put(insuredStrategy.getType(), insuredStrategy));
    }

    /**
     * 投保
     */
    public void toPay(JSONObject insuredRequest, PlatformEnum platformEnum) {
        // 获取具体实现类
        ServiceStrategy insuredStrategy = STRATEGY_MAP.get(platformEnum);
        insuredStrategy.toPay(insuredRequest);
    }

}
