package com.all.lin.statrter.validator;

import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author linpu
 * @dateTime 2022-05-06 16:16
 * @email im.linpu@qq.com
 * @description
 */
@RestControllerAdvice
public class ExceptionHandler {
    /**
     * 处理自定义异常
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(RRException.class)
    public R handleRRException(RRException e){
        R r = new R();
        r.put("code", e.getCode());
        r.put("msg", e.getMessage());

        return r;
    }
}
