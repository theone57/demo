package com.all.lin.sign.lp.lsp.entity;

/**
 * @author linpu
 * @dateTime 2021-07-05 16:57
 * @email im.linpu@qq.com
 * @phone 17602155862
 * @description
 */
public class Customer {
    private String name;
    private String email;

    public Customer() {
    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

