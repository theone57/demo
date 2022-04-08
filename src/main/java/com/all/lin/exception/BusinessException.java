package com.all.lin.exception;


public class BusinessException extends RuntimeException {

    public BusinessException() {
        super();
    }


    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }


    public BusinessException(String message) {
        super(message);
    }


    public BusinessException(Throwable cause) {
        super(cause);
    }

}
