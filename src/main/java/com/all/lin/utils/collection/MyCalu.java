package com.all.lin.utils.collection;

/**
 * @author linpu
 * @dateTime 2022-04-19 16:29
 * @email im.linpu@qq.com
 * @description
 */
public class MyCalu {
    public static void main(String[] args) {
        String hello = CalculateCheckDigit("hello");
        System.out.println("hello = " + hello);
    }

    private static String CalculateCheckDigit(String strBarcode) {
        int intBarcodeLen;
        int intWeight;
        int intCheckVal;
        int i;
        // 字符串长度 -1   4
        intBarcodeLen = strBarcode.length() - 1;
        intWeight = 3;
        intCheckVal = 0;
        i = intBarcodeLen;
        while (i >= 0) {
            char ch1 = strBarcode.charAt(i);
            intCheckVal = intCheckVal + Character.getNumericValue(ch1) * intWeight;
            if (intWeight == 3) {
                intWeight = 1;
                i += -1;
            } else {
                intWeight = 3;
            }

        }
        /*for (i = intBarcodeLen; i >= 0; i += -1)
        {
            char ch1 = strBarcode.charAt(i);
            intCheckVal = intCheckVal + Character.getNumericValue(ch1) * intWeight;
            if (intWeight == 3)
                intWeight = 1;
            else
                intWeight = 3;
        }*/
        intCheckVal = intCheckVal % 10;
        if (intCheckVal != 0) {
            intCheckVal = 10 - intCheckVal;
        }
        String result = String.valueOf(intCheckVal);
        return String.valueOf(result.charAt(0));
    }

}
