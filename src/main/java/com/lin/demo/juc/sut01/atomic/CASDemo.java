//package com.lin.demo.juc.sut01.atomic;
//
//import sun.misc.Unsafe;
//
//import java.lang.reflect.Field;
//
///**
// * 模拟AtomicInteger的底层,使用CAS实现原子性
// * 1. AtomicInteger的底层实现原理:
// * 调用了unsafe.compareAndSwapInt(对象, 要操作的对象的属性的偏移量, 期望值, 结果)
// * 借助了unsafe使用硬件底层的case方法乐观锁机制保证了更新时的数据的原子性
// *
// * @author lin
// * @version v1.0
// * @program juc
// * @description
// * @date 2021-04-01 15:57
// */
//public class CASDemo {
//    //必须时引用类型>>要获取偏移量
//    private int num = 1;
//
//    public static void main(String[] args) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
//        CASDemo casDemo = new CASDemo();
//        //更新对象的属性值的是脏使用cas实现
//        /*
//        * 获取unsafe对象 Unsafe.getUnsafe(); ->> 我么自己的代码不能使用该方式获取
//        * Unsafe 类 中有一个theUnsafe类型,只要能获取就可以得到Unsafe
//        *public final class Unsafe {
//        * private static final Unsafe theUnsafe;
//        * }
//        * */
////        Unsafe unsafe = Unsafe.getUnsafe();
////        //Exception in thread "main" java.lang.SecurityException: Unsafe
////        System.out.println(unsafe);
//
//        Class<Unsafe> aClass = (Class<Unsafe>) Class.forName("sun.misc.Unsafe");
//        Field field = aClass.getDeclaredField("theUnsafe");
//        field.setAccessible(true);//设置可见
//        Unsafe unsafe = (Unsafe) field.get(null);
//        System.out.println("unsafe = " + unsafe);
//        //p1:要修改的对象
//        //p2:要修改对象的属性的偏移量
//        //p3:要修改对象属性的旧值
//        //p4:要修改对象属性的新值
//        long valueOffset = unsafe.objectFieldOffset(CASDemo.class.getDeclaredField("num"));
//
//
//        boolean b = unsafe.compareAndSwapInt(casDemo, valueOffset, 1, 2);
//        System.out.println(b);
//    }
//}
//
//
