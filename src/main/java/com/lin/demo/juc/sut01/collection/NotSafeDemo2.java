package com.lin.demo.juc.sut01.collection;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ## 4.2. CopyOnWrite容器
 * <p>
 * 什么是CopyOnWrite容器
 * <p>
 * ​**CopyOnWrite容器**（简称COW容器）即**写时复制**的容器。
 * 通俗的理解是当我们往一个容器添加元素的时候，不直接往当前容器添加，而是先将当前容器进行Copy，
 * 复制出一个新的容器，然后新的容器里添加元素，添加完元素之后，再将原容器的引用指向新的容器。
 * 这样做的好处是我们可以对CopyOnWrite容器进行并发的读，而不需要加锁，
 * 因为当前容器不会添加任何元素。所以**CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器**。
 * <p>
 * 从JDK1.5开始Java并发包里提供了两个使用CopyOnWrite机制实现的并发容器,
 * 它们是CopyOnWriteArrayList和CopyOnWriteArraySet。
 * 先看看CopyOnWriteArrayList类：发现它的本质就是数组
 *
 * -----------------------------------------------------
 *
 ***CopyOnWrite并发容器用于读多写少的并发场景**。比如：白名单，黑名单。
 * 假如我们有一个搜索网站，用户在这个网站的搜索框中，输入关键字搜索内容，但是某些关键字不允许被搜索。
 * 这些不能被搜索的关键字会被放在一个黑名单当中，黑名单一定周期才会更新一次。
 *
 *
 *
 * 缺点：
 *
 * 1. **内存占用问题。**写的时候会创建新对象添加到新容器里，而旧容器的对象还在使用，所以有两份对象内存。
 * 通过压缩容器中的元素的方法来减少大对象的内存消耗，比如，如果元素全是10进制的数字，可以考虑把它压缩成36进制或64进制。
 * 或者不使用CopyOnWrite容器，而使用其他的并发容器，如ConcurrentHashMap。
 * 2. **数据一致性问题。**CopyOnWrite容器只能保证数据的最终一致性，不能保证数据的实时一致性。
 * 所以如果你希望写入的的数据，马上能读到，请不要使用CopyOnWrite容器。
 *
 * @author lin
 * @version v1.0
 * @program juc
 * @description
 * @date 2021-03-31 01:08
 */
public class NotSafeDemo2 {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

    }
}

