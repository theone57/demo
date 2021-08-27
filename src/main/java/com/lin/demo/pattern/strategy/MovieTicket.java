package com.lin.demo.pattern.strategy;

//电影票类
class MovieTicket {
    private double price; //电影票价格
    private String type; //电影票类型

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return this.calculate();
    }

    //计算打折之后的票价
    public double calculate() {
        //学生票折后票价计算
        if ("student".equalsIgnoreCase(this.type)) {
            System.out.println("学生票：");
            return this.price * 0.8;
        }
        //儿童票折后票价计算
        else if ("children".equalsIgnoreCase(this.type) && this.price >= 20) {
            System.out.println("儿童票：");
            return this.price - 10;
        }
        //VIP票折后票价计算
        else if ("vip".equalsIgnoreCase(this.type)) {
            System.out.println("VIP票：");
            System.out.println("增加积分！");
            return this.price * 0.5;
        } else {
            return this.price; //如果不满足任何打折要求，则返回原始票价
        }
    }
}