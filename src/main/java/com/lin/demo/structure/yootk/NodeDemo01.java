package com.lin.demo.structure.yootk;

class Node <T> { // 定义描述节点的结构类
    private T data ; // 保存所有数据
    private Node next ; // 描述下一个节点
    public Node(T data) {	// 所有的节点一定要包裹数据
        this.data = data ; // 保存数据信息
    }
    public T getData() {
        return this.data ;
    }
    public void setNext(Node next) { // 设置下一个节点
        this.next = next ;
    }
    public Node getNext() {
        return this.next ; // 返回下一个节点
    }
}
public class NodeDemo01 { // 李兴华编程训练营：edu.yootk.com
    public static void main(String args[]) {
        Node<String> nodeA = new Node<>("edu.yootk.com") ;
        Node<String> nodeB = new Node<>("www.yootk.com") ;
        Node<String> nodeC = new Node<>("yootk.ke.qq.com") ;
        nodeA.setNext(nodeB) ; // 设置节点关联
        nodeB.setNext(nodeC) ; // 设置节点关联
        print(nodeA) ; // 节点输出
    }
    public static void print(Node node) {	// 获取数据
        if (node == null) {	//  结束条件
            return ; // 结束方法调用
        }
        System.out.println(node.getData()) ;
        print(node.getNext()) ; // 继续输出下一个节点
    }
}