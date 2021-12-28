package com.all.lin.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MoneyConvert {

    private static final String UNIT = "万仟佰拾亿仟佰拾万仟佰拾元角分";
    private static final String DIGIT = "零壹贰叁肆伍陆柒捌玖";
    private static final BigDecimal MAX_VALUE = new BigDecimal("9999999999999.999999");

    public static String convert(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0 || amount.compareTo(MAX_VALUE) > 0) {
            return "";
        }
        long l = Math.round(amount.multiply(new BigDecimal("100")).doubleValue());
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            return "零元整";
        }
        String strValue = l + "";
        // i用来控制数
        int i = 0;
        // j用来控制单位
        int j = UNIT.length() - strValue.length();
        String rs = "";
        boolean isZero = false;
        for (; i < strValue.length(); i++, j++) {
            char ch = strValue.charAt(i);
            if (ch == '0') {
                isZero = true;
                if (UNIT.charAt(j) == '亿' || UNIT.charAt(j) == '万' || UNIT.charAt(j) == '元') {
                    rs = rs + UNIT.charAt(j);
                    isZero = false;
                }
            } else {
                if (isZero) {
                    rs = rs + "零";
                    isZero = false;
                }
                rs = rs + DIGIT.charAt(ch - '0') + UNIT.charAt(j);
            }
        }
        if (!rs.endsWith("分")) {
            rs = rs + "整";
        }
        rs = rs.replaceAll("亿万", "亿");
        return rs;
    }

    /**
     * 转换成货币格式 ##,####
     *
     * @param amount
     * @return
     */
    public static String toDecimalFormat(BigDecimal amount) {
        NumberFormat nf = new DecimalFormat("###,###");
        if (amount == null) {
            return "";
        }
        return nf.format(amount);
    }

    /**
     * 转换成货币格式 ##,####
     *
     * @param amount
     * @return
     */
    public static String toDecFormatByInt(Integer amount) {
        NumberFormat nf = new DecimalFormat("###,###");
        if (amount == null) {
            return "";
        }
        return nf.format(amount);
    }

    /**
     * 转换成货币格式 ##,####.##
     *
     * @param amount
     * @return
     */
    public static String toDecFormatByDouble(Double amount) {
        NumberFormat nf = new DecimalFormat("###,###.##");
        return amount == null ? "" : nf.format(amount);
    }
}