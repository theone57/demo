package com.lin.demo.utils;

import java.math.BigDecimal;
import java.util.List;

public class PremiumCalculatorUtil {

    /**
     * 根据保证金总额计算保费金额
     * @param marginAmount  保证金总额
     * @param minAmount     最低保费金额
     * @param rateList          费率
     * @return
     */
    public static BigDecimal getPremiumAmount(BigDecimal marginAmount, BigDecimal minAmount, List<BigDecimal> rateList) {
        BigDecimal amount = marginAmount;
        if (minAmount == null) {
            minAmount = BigDecimal.ZERO;
        }
        if (!rateList.isEmpty()) {
            for (BigDecimal rate : rateList) {
                amount = amount.multiply(rate);
                if (amount.compareTo(minAmount) <= 0) {
                    amount = minAmount;
                    break;
                }
            }
        } else {
			if (amount.compareTo(minAmount) <= 0) {
				amount = minAmount;
			}
		}
        return amount.setScale(2, BigDecimal.ROUND_UP);
    }

    /**
     * 根据保证金总额计算保费金额
     * @param rateList          费率
     * @return
     */
    public static BigDecimal getSumBasicRate(BigDecimal areaRate, List<BigDecimal> rateList) {
        BigDecimal basicRate = areaRate;
        if (!rateList.isEmpty()) {
            for (BigDecimal rate : rateList) {
                basicRate = basicRate.multiply(rate);
            }
        }
        return basicRate.setScale(4, BigDecimal.ROUND_UP);
    }
}