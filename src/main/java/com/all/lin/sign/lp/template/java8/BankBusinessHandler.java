package com.all.lin.sign.lp.template.java8;

import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
import java.util.function.Consumer;

/**

 * @author Hollis
 * https://mp.weixin.qq.com/s/Dwl9XVAFM5XhtoWPWT7IQw

 */

public class BankBusinessHandler {

    private void execute(Consumer<BigDecimal> consumer) {

        getNumber();

        consumer.accept(null);

        judge();

    }

    private void getNumber() {

        System.out.println("number-00" + RandomUtils.nextInt());

    }

    private void judge() {

        System.out.println("give a praised");

    }

}