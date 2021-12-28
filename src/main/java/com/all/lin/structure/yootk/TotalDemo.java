package com.all.lin.structure.yootk;

import java.util.Arrays;

interface ILink<T> {  // 链表的实现标准
    public void add(T data); // 实现数据增加

    public int size(); // 获取元素个数

    public boolean isEmpty(); // 判断是否为空链表

    public Object[] toArray(); // 将链表数据转为对象数组

    public T get(int index); // 根据索引获取数据

    public T set(int index, T data); // 根据索引修改数据，并返回原始数据

    public boolean contains(T data); // 数据查找判断

    public T remove(T data); // 删除数据，并返回删除的数据内容

    public void clear(); // 清空链表
}

class LinkImpl<T> implements ILink<T> {
    // 使用内部类的结构表示该类的所属范围，同时使用private标注其只能够被内部使用
    private static class Node<T> { // 定义描述节点的结构类
        private T data; // 保存所有数据
        private Node<T> next; // 描述下一个节点

        public Node(T data) {    // 所有的节点一定要包裹数据
            this.data = data; // 保存数据信息
        }
    }

    // ========================= 链表实现的相关的处理操作 =============
    private Node<T> root; // 根节点
    private int count; // 统计计数
    // 在LinkImpl类里面追加有一个新的属性，作为性能的提升
    private Node<T> lastNode; // 保存最后一个节点

    @Override
    public void add(T data) {    // 接口中进行方法覆写
        if (data == null) {
            return; // 结束方法调用
        }
        // 1、传进来的是一个数据，数据本身是无法进行所谓的顺序定义的，所以需要将其封装
        Node<T> newNode = new Node<>(data); // 创建新的数据节点
        // 2、需要去确认保存的节点位置，第一个节点应该是根节点
        if (this.root == null) {    // 没有根节点
            this.root = newNode; // 保存节点引用
            this.lastNode = newNode; // 保存最后一个节点
        } else {    // 如果不是根节点
            this.lastNode.next = newNode; // 保存新节点
            this.lastNode = newNode; // 改变节点
        }
        this.count++;
    }

    @Override
    public int size() {
        return this.count; // 返回元素的个数
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    @Override
    public Object[] toArray() {
        if (this.count == 0) {
            return null;
        }
        Object[] returnData = new Object[this.count]; // 返回数组信息
        int foot = 0; // 操作角标
        Node<T> node = this.root; // 通过root获取当前节点
        while (node != null) {    // 当前存在有节点对象
            returnData[foot++] = node.data; // 获取对象的数据
            node = node.next; // 获取下一个节点
        }
        return returnData; // 返回当前的数组内容
    }

    @Override
    public T get(int index) {    // 操作的覆写
        if (index >= this.count) { // 当前的索引无效
            return null; // 没有对应的数据内容
        }
        int foot = 0; // 进行节点的脚标操作
        Node<T> node = this.root; // 获取当前节点
        while (node != null) {    // 当前存在有节点
            if (index == foot++) {    // 索引号相同
                return (T) node.data; // 返回索引数据
            }
            node = node.next; // 修改当前节点
        }
        return null;
    }

    @Override
    public T set(int index, T data) {
        if (index >= this.count) { // 当前的索引无效
            return null; // 没有对应的数据内容
        }
        T returnData = null; // 要返回的数据
        int foot = 0; // 进行节点的脚标操作
        Node<T> node = this.root; // 获取当前节点
        while (node != null) {    // 当前存在有节点
            if (index == foot++) {    // 索引号相同
                returnData = (T) node.data; // 返回索引数据
                node.data = data; // 替换新数据
            }
            node = node.next; // 修改当前节点
        }
        return returnData; // 返回原始数据内容
    }

    @Override
    public boolean contains(T data) {
        if (this.root == null || data == null) {    // 此时没有根节点
            return false; // 没有要查询的数据内容
        }
        Node<T> node = this.root; // 获取当前节点
        while (node != null) {
            if (node.data.equals(data)) {    // 利用equals()方法进行判断
                return true;
            }
            node = node.next; // 继续访问下一个节点
        }
        return false; // 没有数据满足查询要求
    }

    @Override
    public T remove(T data) { // 删除数据，并返回删除的数据内容
        if (!this.contains(data)) { // 数据不存在
            return null; // 没有要删除的数据
        }
        T returnData = null;
        if (this.root.data.equals(data)) {    // 进行根节点的比较
            returnData = (T) this.root.data; // 保存原始节点数据
            this.root = this.root.next; // 第二个节点作为根节点
        } else {// 如果现在不是根节点，此时已经判断过了根节点是否存在
            Node<T> parentNode = this.root; // 第2个节点的父节点为root
            Node<T> node = this.root.next; // 从第2个节点开始判断
            while (node != null) {    // 不断循环
                if (node.data.equals(data)) {    // 当前节点数据满足
                    returnData = (T) node.data; // 获取要删除的数据
                    parentNode.next = node.next; // 空出当前节点
                    this.count--; // 保存元素的个数减少
                }
                parentNode = node; // 修改当前节点父节点
                node = node.next; // 改变当前节点
            }
        }
        return returnData; // 返回处理结果
    }

    @Override
    public void clear() {
        this.root = null; // 断开链表所有节点
        this.count = 0; // 保存的元素个数为0
    }
}

interface IBook { // 只要编写接口都要加一个字母I
    public String getTitle(); // 获取图书的名称

    public double getPrice(); // 获取图书价格

    public String getAuthor(); // 获取图书作者
}

class MathBook implements IBook { // 数学书
    private String title;
    private double price;
    private String author;

    public MathBook() {
    }

    public MathBook(String title, double price, String author) {
        this.title = title;
        this.price = price;
        this.author = author;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public String toString() {
        return "【数学】图书名称：" + this.title + "、图书价格：" + this.price + "、图书作者：" + this.author;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {    // 地址相同
            return true;
        }
        if (obj == null) { // 回避null
            return false;
        }
        if (!(obj instanceof MathBook)) { // 不是本类对象实例
            return false;
        }
        MathBook book = (MathBook) obj;
        return book.title.equals(this.title) && book.price == this.price && book.author.equals(this.author);
    }
}

class ProgramBook implements IBook { // 数学书
    private String title;
    private double price;
    private String author;

    public ProgramBook() {
    }

    public ProgramBook(String title, double price, String author) {
        this.title = title;
        this.price = price;
        this.author = author;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public String toString() {
        return "【程序开发】图书名称：" + this.title + "、图书价格：" + this.price + "、图书作者：" + this.author;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {    // 地址相同
            return true;
        }
        if (obj == null) { // 回避null
            return false;
        }
        if (!(obj instanceof ProgramBook)) { // 不是本类对象实例
            return false;
        }
        ProgramBook book = (ProgramBook) obj;
        return book.title.equals(this.title) && book.price == this.price && book.author.equals(this.author);
    }
}

class BigDataBook implements IBook { // 数学书
    private String title;
    private double price;
    private String author;

    public BigDataBook() {
    }

    public BigDataBook(String title, double price, String author) {
        this.title = title;
        this.price = price;
        this.author = author;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public String toString() {
        return "【大数据】图书名称：" + this.title + "、图书价格：" + this.price + "、图书作者：" + this.author;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {    // 地址相同
            return true;
        }
        if (obj == null) { // 回避null
            return false;
        }
        if (!(obj instanceof BigDataBook)) { // 不是本类对象实例
            return false;
        }
        BigDataBook book = (BigDataBook) obj;
        return book.title.equals(this.title) && book.price == this.price && book.author.equals(this.author);
    }
}

class Student {
    private ILink<IBook> books = new LinkImpl<>(); // 图书信息保存链表

    public void buy(IBook book) {    // 图书购买
        this.books.add(book); // 将图书信息向链表保存
    }

    public void give(IBook book) {    // 图书赠送
        this.books.remove(book); // 从链表删除
    }

    public ILink search(String keyword) { // 检索
        ILink<IBook> result = new LinkImpl<>();
        Object obj[] = this.books.toArray(); // 获取全部的链表数据
        for (Object temp : obj) {
            IBook book = (IBook) temp; // 强制转换找到具体的接口方法
            if (book.getTitle().contains(keyword) || book.getAuthor().contains(keyword)) {
                result.add(book); // 结果链表
            }
        }
        return result;
    }

    public ILink getBooks() {
        return this.books;
    }
}

public class TotalDemo { // 李兴华编程训练营：edu.yootk.com
    public static void main(String[] args) {
        Student student = new Student();
        student.buy(new MathBook("布尔传奇", 67.8, "乔治·布尔"));
        student.buy(new MathBook("线性代数", 37.8, "国内数学家"));
        student.buy(new ProgramBook("Java从入门到项目实战", 99.8, "李兴华"));
        student.buy(new ProgramBook("Python从入门到项目实战", 99.8, "李兴华"));
        student.buy(new ProgramBook("GO从入门到项目实战", 99.8, "李兴华"));
        student.buy(new BigDataBook("Flink实时分析", 129.8, "小李老师"));
        student.buy(new BigDataBook("Spark实时分析", 1569.8, "小李老师"));
        student.give(new ProgramBook("Java从入门到项目实战", 99.8, "李兴华")); // 转送
        Object[] books = student.search("李").toArray(); // 获取全部的图书信息

        Arrays.stream(books).forEach(System.out::println);
    }
}