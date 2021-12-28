package com.all.lin.juc.sut01.blockqueue;

import java.util.concurrent.*;

/**
 * BlockingQueue接口主要有以下7个实现类：
 *
 * 1. <font color="red">ArrayBlockingQueue：由数组结构组成的有界阻塞队列。</font>
 * 2. <font color="red">LinkedBlockingQueue：由链表结构组成的有界（但大小默认值为integer.MAX_VALUE）阻塞队列。</font>
 * 3. PriorityBlockingQueue：支持优先级排序的无界阻塞队列。
 * 4. DelayQueue：使用优先级队列实现的延迟无界阻塞队列。
 * 5. <font color="red">SynchronousQueue：不存储元素的阻塞队列，也即单个元素的队列。</font>
 * 6. LinkedTransferQueue：由链表组成的无界阻塞队列。
 * 7. LinkedBlockingDeque：由链表组成的双向阻塞队列。
 *
 * |          | 抛出异常  | 特殊值   | 阻塞   | 超时                 |
 * | -------- | --------- | -------- | ------ | -------------------- |
 * | **插入** | add(e)    | offer(e) | put(e) | offer(e, time, unit) |
 * | **移除** | remove()  | poll()   | take() | poll(time, unit)     |
 * | **检查** | element() | peek()   | 不可用 | 不可用               |
 * ----------------------------
 * 阻塞队列主要用在生产者/消费者的场景
 * @author lin
 * @version v1.0
 * @program juc
 * @description
 * @date 2021-03-31 18:21
 */
public class BlockQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        //ArrayBlockingQueue:自定义边界的数组阻塞队列
        BlockingQueue<String> queue =  new ArrayBlockingQueue<>(3);
//        BlockingQueue<String> queue2 = new LinkedBlockingQueue<>();//由链表结构组成的有界（但大小默认值为integer.MAX_VALUE）阻塞队列
        BlockingQueue<String> queue3 = new SynchronousQueue<>();//不存储元素的阻塞队列，也即单个元素的队列。</font>
        System.out.println(queue3.add("4"));//IllegalStateException: Queue full
//        System.out.println(queue3.add("4"));
        // 第一组方法：add remove element ->执行时如果阻塞,抛出异常
//        System.out.println(queue.element());
//        queue.add("6");
//        queue.add("2");
//        queue.add("3");
//        System.out.println(queue.remove("3"));
//        System.out.println(queue.remove());
//        queue.add("1");
//        System.out.println(queue);
////        System.out.println(queue.element());

        // 第二组：offer poll peek 执行时如果阻塞,则返回特定数据
//        System.out.println(queue.poll());//null
//        System.out.println(queue.peek());//null
//        System.out.println(queue.offer("l"));
//        queue.offer("i");
//        queue.offer("n");
//        System.out.println(queue.poll());
//        System.out.println(queue.offer("p"));//false

        // 第三组：put take
//        new Thread(()->{
//            try {
//                TimeUnit.SECONDS.sleep(3);
//                queue.put("a");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        System.out.println(queue.take());//阻塞
//        TimeUnit.SECONDS.sleep(3);
//        queue.put("a");


        //4. 指定阻塞超时时间:offer(e, time, unit)   poll(time, unit)
//        System.out.println(queue.poll(3000, TimeUnit.MILLISECONDS));
//        queue.offer("6", 1, TimeUnit.SECONDS);
//        queue.offer("66", 1, TimeUnit.SECONDS);
//        queue.offer("666", 1, TimeUnit.SECONDS);
//        System.out.println(queue.offer("666", 1, TimeUnit.SECONDS));

        System.out.println(queue);
    }
}

