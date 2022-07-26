package com.all.lin.spring.sensitive.method2;

@FunctionalInterface
public interface DataMaskingOperation {

    String MASK_CHAR = "*";

    String mask(String content, String maskChar);

}

