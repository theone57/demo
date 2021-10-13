package com.lin.demo.spring.listener.constant;

import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author linpu
 * @dateTime 2021-06-03 17:01
 * @email im.linpu@qq.com
 * @phone 17602155862
 * @description  EnumUserOperate 枚举类
 */
@Getter
public enum EnumUserOperate implements Serializable {
    /**
     * 新增操作
     */
    ADD("add", 0, "新增"),
    /**
     * 更新操作
     */
    UPDATE("update", 1, "更新"),
    /**
     * 删除操作
     */
    DELETE("delete", 3, "删除");


    private String name;

    private Integer value;

    private String desc;

    EnumUserOperate(String name, Integer value, String desc) {
        this.name = name;
        this.value = value;
        this.desc = desc;
    }

    /**
     * 根据value获取 {@link EnumUserOperate}
     *
     * @param value
     * @return {@link EnumUserOperate}
     */
    public static EnumUserOperate getByValue(Integer value) {
        return Arrays.stream(values()).filter((e) -> e.getValue().equals(value)).findFirst().orElse(null);
    }
}

