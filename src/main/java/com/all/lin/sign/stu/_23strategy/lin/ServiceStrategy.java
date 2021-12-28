package com.all.lin.sign.stu._23strategy.lin;

import com.alibaba.fastjson.JSONObject;
import com.all.lin.sign.stu._4factory_interface.lin.PlatformEnum;

import java.util.Map;

/**
 * 策略相关
 * @author alex
 */
public interface ServiceStrategy {

    /**
     * 获取保险公司
     *
     * @return 保险公司
     */
    PlatformEnum getType();

    /**
     * 支付申请
     *
     * @param request 请求参数
     */
    void toPay(JSONObject request);

    /**
     * 核保通知
     *
     * @param param 请求参数
     * @return 响应信息
     */
    String checkNotice(Map<String, Object> param);

}
