package com.all.lin.structure.yootk;

interface ILink02<T> {  // 链表的实现标准
    public void add(T data) ; // 实现数据增加
}
class LinkImpl02<T> implements ILink02<T> {
    // 使用内部类的结构表示该类的所属范围，同时使用private标注其只能够被内部使用
    private class Node <T> { // 定义描述节点的结构类
        private T data ; // 保存所有数据
        private Node next ; // 描述下一个节点
        public Node(T data) {	// 所有的节点一定要包裹数据
            this.data = data ; // 保存数据信息
        }
        // 当调用此方法的时候实际上已经在LinkImpl类里面判断完成了根节点是否存在
        // 【第1次调用】是由LinkImpl.root实例发出的调用（this = LinkImpl.root）
        // 【第2次调用】是由Node类发出的（this = LinkImpl.root.next）
        // 【第3次调用】是由Node类发出的（this = LinkImpl.root.next.next）
        public void addNode(Node newNode) {	// 进行节点的追加
            if (this.next == null) {	// 当前节点的后续节点为空
                this.next = newNode ; // 存储节点
            } else {	// 现在已经有了后续节点
                this.next.addNode(newNode) ; // 继续向后进行判断与添加
            }
        }
    }
    // ========================= 链表实现的相关的处理操作 =============
    private Node root ; // 根节点
    @Override
    public void add(T data) {	// 接口中进行方法覆写
        if (data == null) {
            return ; // 结束方法调用
        }
        // 1、传进来的是一个数据，数据本身是无法进行所谓的顺序定义的，所以需要将其封装
        Node<T> newNode = new Node<>(data) ; // 创建新的数据节点
        // 2、需要去确认保存的节点位置，第一个节点应该是根节点
        if (this.root == null) {	// 没有根节点
            this.root = newNode ; // 保存节点引用
        } else {	// 如果不是根节点
            this.root.addNode(newNode) ; // 追加节点
        }
    }
}

public class LinkDemo02 { // 李兴华编程训练营：edu.yootk.com
    public static void main(String args[]) {
        ILink02<String> link = new LinkImpl02<String>() ;
        link.add("edu.yootk.com") ;
        link.add("www.yootk.com") ;
        link.add("yootk.ke.qq.com") ;
    }
}