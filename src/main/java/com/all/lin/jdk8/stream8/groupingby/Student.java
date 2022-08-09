package com.all.lin.jdk8.stream8.groupingby;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class Student {
    public Student() {
    }

    public Student(String grade, int age) {
        this.grade = grade;
        this.age = age;
    }

    public Student(String name, String grade, int age, Integer score) {
        this.name = name;
        this.grade = grade;
        this.age = age;
        this.score = score;
    }

    private String name;
    /**
     * 班级
     */
    private String grade;
    /**
     * 年龄
     */
    private int age;

    private Integer score;

    public static void main(String[] args) {
        Student t1 = new Student("a","六年级", 23,11);
        Student t2 = new Student("b","六年级", 14,12);
        Student t3 = new Student("c","六年级", 56,13);
        Student t4 = new Student("d","九年级", 56,14);
        Student t5 = new Student("e","九年级", 16,15);
        Student t6 = new Student("f","九年级", 36,16);
        Student t7 = new Student("g","七年级", 56,16);
        Student t8 = new Student("h","七年级", 16,17);
        Student t9 = new Student("i","七年级", 36,20);
        List<Student> list = Lists.newArrayList(t1, t2, t3, t4, t5, t6, t7, t8, t9);

        //根据班级分组
        list.stream().collect(Collectors.groupingBy(Student::getGrade));
        StudentComparator studentComparator = new StudentComparator();
        Student student = list.stream().collect(Collectors.maxBy(studentComparator)).get();
        System.out.println("student = " + student);
        Student student1 = studentComparator.student;
        System.out.println("student1 = " + student1);

//        Map<String, List<Student>> collect1 = list.stream().sorted(Comparator.comparingInt(Student::getAge))
//                .collect(Collectors.groupingBy(Student::getGrade));
//        System.out.println(collect1);
//输出
//        {九年级=[Student{grade='九年级', age=16}, Student{grade='九年级', age=36}, Student{grade='九年级', age=56}],
//            七年级=[Student{grade='七年级', age=16}, Student{grade='七年级', age=36}, Student{grade='七年级', age=56}],
//            六年级=[Student{grade='六年级', age=14}, Student{grade='六年级', age=23}, Student{grade='六年级', age=56}]}
    }
}