package com.all.lin.jdk8.stream8.groupingby;

import java.util.Comparator;

/**
 * @author lin's
 * @program se
 * @description 学生类比较器工具 : StudentComparator
 * 降序排列,比较顺序:score age name
 * @date 2020-11-17 15:26
 * 可以创建一个比较器,传入TreeSet集合
 */
public class StudentComparator implements Comparator<Student> {

    Student student = new Student();

    @Override
    public int compare(Student o1, Student o2) {
        int result = (int) (o2.getScore() - o1.getScore());
        if (result > 0) {
            student.setScore(o2.getScore());
        }
        int ageR = o2.getAge() - o1.getAge();

        if (ageR > 0) {
            student.setAge(o2.getAge());
        }
        return result;
    }
}
