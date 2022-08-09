package com.all.lin.statrter.sensitive.method2;

@FunctionalInterface
public interface DataMaskingOperation {

    String MASK_CHAR = "*";

    String mask(String content, String maskChar);

}

