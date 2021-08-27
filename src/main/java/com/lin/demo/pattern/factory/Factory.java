package com.lin.demo.pattern.factory;

/**
 * @author linpu
 * @dateTime 2021-07-14 15:38
 * @email im.linpu@qq.com
 * @phone 17602155862
 * @description
 */
public class Factory {
    public static Product manufacture(String productName) {
        switch (productName) {
            case "A":
                return new ProductA();

            case "B":
                return new ProductB();

            case "C":
                return new ProductC();
            default:
                return null;
        }
    }
}

