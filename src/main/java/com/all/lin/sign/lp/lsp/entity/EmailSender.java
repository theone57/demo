package com.all.lin.sign.lp.lsp.entity;

/**
 * @author linpu
 * @dateTime 2021-07-05 17:00
 * @email im.linpu@qq.com
 * @phone 17602155862
 * @description
 */
public class EmailSender {
    public void send(Customer customer) {
        System.out.println("发送email, name:" + customer.getName() + "email:" + customer.getEmail());
    }
}

