package com.lin.demo.utils;

import org.springframework.util.StringUtils;

import java.text.NumberFormat;

/**
 * 字符串格式化方法
 */
public class StringFormatterUtil {

    /**
     * 整形自动转为字符串，并在前面自动加补齐位数
     * 如果 value 长度大于length值，则会从后往前截取
     * 例如：value = 123456789; length = 6; 则返回值为456789
     *
     * @param value     原整型数字
     * @param minLength 最小显示位数
     * @param maxLength 最大显示位数
     * @return
     */
    public static String intConvertString(int value, int minLength, int maxLength) {
        NumberFormat nf = NumberFormat.getInstance();
        //设置是否使用分组
        nf.setGroupingUsed(false);
        //设置最大整数位数
        nf.setMaximumIntegerDigits(maxLength);
        //设置最小整数位数
        nf.setMinimumIntegerDigits(minLength);
        return nf.format(value);
    }

    public static String getFileExtension(String fileUrl) {
    	if (StringUtils.hasText(fileUrl) && fileUrl.indexOf(".") > 0) {
    		return fileUrl.trim().substring(fileUrl.trim().lastIndexOf(".")+1).toLowerCase().trim();
		}
		return null;
	}
}