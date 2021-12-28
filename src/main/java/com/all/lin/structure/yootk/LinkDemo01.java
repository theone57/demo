package com.all.lin.structure.yootk;

interface ILink01<T> {  // 链表的实现标准
}
class LinkImpl01<T> implements ILink01<T> {
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
}

public class LinkDemo01 { // 李兴华编程训练营：edu.yootk.com
    public static void main(String args[]) {
        ILink01<String> link = new LinkImpl01<String>() ;
    }
}