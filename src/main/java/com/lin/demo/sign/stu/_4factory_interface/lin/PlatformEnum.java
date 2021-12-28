package com.lin.demo.sign.stu._4factory_interface.lin;

import java.util.Arrays;

/**
 * @author linpu
 * @dateTime 2021/12/28 10:15
 * @email im.linpu@qq.com
 * @description
 */
public enum PlatformEnum {

    FIRST_PLATFORM(0),
    SECOND_PLATFORM(0),
    ;

    private Integer code;

    PlatformEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static PlatformEnum getByCode(int code) {
        return Arrays.stream(PlatformEnum.values()).filter(i -> i.getCode() == code).findFirst()
                .orElse(null);
    }
    public static PlatformEnum getByCodeOrThrow(int code) throws NoSuchFieldException {
        return Arrays.stream(PlatformEnum.values()).filter(i -> i.getCode() == code).findFirst()
                .orElseThrow(NoSuchFieldException::new);
    }
}
