package com.lin.demo.structure.yootk;

interface ILink05<T> {  // 链表的实现标准
    public void add(T data) ; // 实现数据增加
    public int size() ; // 获取元素个数
    public boolean isEmpty() ; // 判断是否为空链表
    public Object[] toArray() ; // 将链表数据转为对象数组
    public T get(int index) ; // 根据索引获取数据
}
class LinkImpl05<T> implements ILink05<T> {
    // 使用内部类的结构表示该类的所属范围，同时使用private标注其只能够被内部使用
    private class Node <T> { // 定义描述节点的结构类
        private T data ; // 保存所有数据
        private Node next ; // 描述下一个节点
        public Node(T data) {	// 所有的节点一定要包裹数据
            this.data = data ; // 保存数据信息
        }
    }
    // ========================= 链表实现的相关的处理操作 =============
    private Node root ; // 根节点
    private int count ; // 统计计数
    // 在LinkImpl类里面追加有一个新的属性，作为性能的提升
    private Node lastNode ; // 保存最后一个节点
    public void add(T data) {	// 接口中进行方法覆写
        if (data == null) {
            return ; // 结束方法调用
        }
        // 1、传进来的是一个数据，数据本身是无法进行所谓的顺序定义的，所以需要将其封装
        Node<T> newNode = new Node<>(data) ; // 创建新的数据节点
        // 2、需要去确认保存的节点位置，第一个节点应该是根节点
        if (this.root == null) {	// 没有根节点
            this.root = newNode ; // 保存节点引用
            this.lastNode = newNode ; // 保存最后一个节点
        } else {	// 如果不是根节点
            this.lastNode.next = newNode ; // 保存新节点
            this.lastNode = newNode ; // 改变节点
        }
        this.count ++ ;
    }
    public int size() {
        return this.count ; // 返回元素的个数
    }
    public boolean isEmpty() {
        return this.count == 0 ;
    }
    public Object[] toArray() {
        if (this.count == 0) {
            return null ;
        }
        Object[] returnData = new Object[this.count] ; // 返回数组信息
        int foot = 0 ; // 操作角标
        Node node = this.root ; // 通过root获取当前节点
        while (node != null) {	// 当前存在有节点对象
            returnData[foot ++] = node.data ; // 获取对象的数据
            node = node.next ; // 获取下一个节点
        }
        return returnData ; // 返回当前的数组内容
    }
    @Override
    public T get(int index) {	// 操作的覆写
        if (index >= this.count) { // 当前的索引无效
            return null ; // 没有对应的数据内容
        }
        int foot = 0 ; // 进行节点的脚标操作
        Node node = this.root ; // 获取当前节点
        while (node != null) {	// 当前存在有节点
            if (index == foot ++) {	// 索引号相同
                return (T) node.data ; // 返回索引数据
            }
            node = node.next ; // 修改当前节点
        }
        return null ;
    }
}
public class SDemo05 { // 李兴华编程训练营：edu.yootk.com
    public static void main(String args[]) {
        ILink05<String> link = new LinkImpl05<String>() ;
        link.add("edu.yootk.com") ;
        link.add("www.yootk.com") ;
        link.add("yootk.ke.qq.com") ;
        System.out.println(link.get(1)) ;
        System.out.println(link.get(0)) ;
        System.out.println(link.get(3)) ;
    }
}
