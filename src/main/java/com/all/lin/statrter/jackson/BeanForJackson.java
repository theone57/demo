package com.all.lin.statrter.jackson;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.math.BigDecimal;

/**
 * @author linpu
 * @dateTime 2022-08-01 15:31
 * @email im.linpu@qq.com
 * @description
 */
public class BeanForJackson {
    /**
     * 多线程生命周期状态值
     */
    @JsonSerialize(using = MySerializerUtils.class)
    private int status;

    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal amount;
}
