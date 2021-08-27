package com.lin.demo.juc.sut01.thread;

/**
 * @author lin
 * @version v1.0
 * @program juc
 * @description
 * @date 2021-03-30 19:07
 */
interface Foo {
    public int add(int x, int y);
}

/**
 *NOTE_ 为了正确使用Lambda表达式，需要给接口加个注解：@FunctionalInterface。如有两个方法，立刻报错。
 *
 * --
 * 其实，函数式接口必须只有一个方法，这个描述并不准确，它还允许有default方法和静态方法。
 */
@FunctionalInterface
//声明当前接口时函数式接口,只能有一个抽象方法
interface Animal {
    void eat(String food);

    //    void sleep(String address);

    //函数接口中还可以有 默认实现方法
    default void run(String address) {
        System.out.println("address =>>>>> " + address);
    }

    //静态方法
    public static void drink(String name) {
        System.out.println("drink =>>> " + name);
    }

}

public class LambdaDemo {
    public static void main(String[] args) {
        Foo foo = (x, y) -> {
            return x + y;
        };
        System.out.println(foo.add(1, 2));


        Animal a1 = (String food) -> {
            System.out.println("eat->>" + food);
        };
        a1.eat("me");
        a1.run("gym");
        Animal.drink("WINK");
    }


}

