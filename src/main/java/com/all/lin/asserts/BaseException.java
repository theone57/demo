package com.all.lin.asserts;

/**
 * @author linpu
 * @dateTime 2022-04-08 11:26
 * @email im.linpu@qq.com
 * @description * <p>业务异常</p>
 * * <p>业务处理时，出现异常，可以抛出该异常</p>
 */

public class BaseException extends RuntimeException {
    public BaseException(IResponseEnum responseEnum, Object[] args, String message) {
    }
    public BaseException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
    }

    public static void main(String[] args) {
        ResponseEnum.BAD_LICENCE_TYPE.newException("a");
    }
}
