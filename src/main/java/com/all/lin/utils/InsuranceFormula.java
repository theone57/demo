package com.all.lin.utils;

import cn.hutool.core.convert.Convert;
import com.google.common.collect.Maps;
import com.googlecode.aviator.AviatorEvaluator;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @ClassName Formula
 * @Description 公式类
 * @Author 孙凯伦
 * @Mobile 13777579028
 * @Email 376253703@qq.com
 * @Time 2021/6/9 10:35 上午
 */
public class InsuranceFormula {
    /**
     * 保费计算
     */
    public static final String INSURANCE_FORMULA = "amount*rate";
    /**
     * 年费率
     */
    public static final String DAY_RATE ="days*rate/365";
    /**
     * 月费率
     */
    public static final String MONTH_RATE = "days/30*rate/12";
    /**
     * 季度费率
     */
    public static final String QUARTER_RATE = "days/120*rate/4";
    /**
     * 费率位数
     */
    public static final Integer NEW_SCALE = 5;
    public static void main(String[] args) {
        BigDecimal quarter = quarter(new BigDecimal("2"), 2);
        System.out.println("quarter = " + quarter);
    }

    public static BigDecimal quarter(BigDecimal rate, Integer days) {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("rate", rate);
        paramMap.put("days", days);
        return Convert.toBigDecimal(AviatorEvaluator.execute(InsuranceFormula.QUARTER_RATE, paramMap)).setScale(NEW_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     *
     * @auther: 孙凯伦
     * @mobile: 13777579028
     * @email: 376253703@qq.com
     * @name: month
     * @description: TODO  月计算
     * @param rate
     * @param days
     * @return: java.math.BigDecimal
     * @date: 2021/6/8 6:49 下午
     *
     */
    public static BigDecimal month(BigDecimal rate, Integer days) {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("rate", rate);
        paramMap.put("days", days);
        return Convert.toBigDecimal(AviatorEvaluator.execute(InsuranceFormula.MONTH_RATE, paramMap)).setScale(NEW_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     *
     * @auther: 孙凯伦
     * @mobile: 13777579028
     * @email: 376253703@qq.com
     * @name: day
     * @description: TODO  日计算
     * @param rate
     * @param days
     * @return: java.math.BigDecimal
     * @date: 2021/6/8 6:49 下午
     *
     */
    public static BigDecimal day(BigDecimal rate, Integer days) {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("rate", rate);
        paramMap.put("days", days);
        return Convert.toBigDecimal(AviatorEvaluator.execute(InsuranceFormula.DAY_RATE, paramMap)).setScale(NEW_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     *
     * @auther: 孙凯伦
     * @mobile: 13777579028
     * @email: 376253703@qq.com
     * @name: payMoney
     * @description: TODO  计算费率
     * @param amount
     * @param rate
     * @return: java.math.BigDecimal
     * @date: 2021/6/9 10:48 上午
     *
     */
    public static BigDecimal payMoney(BigDecimal amount, BigDecimal rate) {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("amount", amount);
        paramMap.put("rate", rate);
        BigDecimal money = Convert.toBigDecimal(AviatorEvaluator.execute(InsuranceFormula.INSURANCE_FORMULA, paramMap));
        return money.setScale(2, BigDecimal.ROUND_HALF_UP);
    }


}
