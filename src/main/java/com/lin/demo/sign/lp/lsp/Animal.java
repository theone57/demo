package com.lin.demo.sign.lp.lsp;

/**
 * @author linpu
 * @dateTime 2021-07-05 15:30
 * @email im.linpu@qq.com
 * @phone 17602155862
 * @description
 */
public class Animal {
    double runSpeed;

    public void setSpeed(double speed) {
        runSpeed = speed;
    }

    public double getFlyTime(double distance) {
        return (distance / runSpeed);
    }

}

