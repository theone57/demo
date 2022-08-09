package com.all.lin.sign.stu._9decorator.juejin;

//抽象饰品
public abstract class Accessory {
    public abstract String name();//饰品名称

    public abstract int cost();//饰品价格
}

//耳环
class Ring extends Accessory {
    @Override
    public String name() {
        return "Ring";
    }

    @Override
    public int cost() {
        return 20;
    }
}

//钻石
class Diamond extends Accessory {
    @Override
    public String name() {
        return "Diamond";
    }

    @Override
    public int cost() {
        return 1000;
    }
}

//黄金
class Gold extends Accessory {
    @Override
    public String name() {
        return "Gold";
    }

    @Override
    public int cost() {
        return 300;
    }
}

//羽毛
class Feather extends Accessory {
    @Override
    public String name() {
        return "Feather";
    }

    @Override
    public int cost() {
        return 90;
    }

}

/**
 * 如果继续推出更多的新品，比如羽毛耳环，钻石耳环，羽毛钻石耳环。。。每个新产品都用一个新的类表示，这样就会遇到子类膨胀的问题。
 * 除此之外，继承还有一个更致命的缺点：对单个类型的饰品没有统一的控制力。如果黄金涨价了，我们需要分别修改GoldRing和FeatherGoldRing的价格，如果和黄金相关的饰品有好几十个，那简直是一场噩梦。
 * 在计算GoldRing价格的时候，我们并没有复用现有代码，即没有复用Gold和Ring已经定义的cost()行为，而只是通过继承复用了类型（GoldRing是一个Accessory）。只复用类型而没有复用行为的后果是：当Gold涨价时，GoldRing无感知。
 * <p>
 * 作者：唐子玄
 * 链接：https://juejin.cn/post/6844903854262190088
 * 来源：稀土掘金
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class GoldRing extends Accessory {
    @Override
    public String name() {
        return "GoldRing";
    }

    @Override
    public int cost() {
        return 320;
    }
}

class FeatherGoldRing extends Accessory {
    @Override
    public String name() {
        return "FeatherGoldRing";
    }

    @Override
    public int cost() {
        return 1110;
    }
}