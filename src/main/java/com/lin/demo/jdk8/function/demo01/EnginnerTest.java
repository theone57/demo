package com.lin.demo.jdk8.function.demo01;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnginnerTest {
    /*
     * 为啥需要lambda
     * 举个例子哈： 有个项目，老板说需要组建一个研发团队，巴拉巴拉~
     * 老板说 来俩Java程序猿
     *
     */
    public List getJavaEngineers(List<Enginner> enginnerList) {
        List javaEnginnerList = new ArrayList();
        for (Enginner enginner : enginnerList) {
            if ("Java".equals(enginner.getJob())) {
                javaEnginnerList.add(enginner);
            }
        }
        return javaEnginnerList;
    }

    public List getEngineersByJob(List<Enginner> enginnerList, String job) {
        List targetEngineerList = new ArrayList();
        for (Enginner enginner : enginnerList) {
            if (job.equals(enginner.getJob())) {
                targetEngineerList.add(enginner);
            }
        }
        return targetEngineerList;
    }

    public List findEnginner(List<Enginner> enginnerList, EnginnerFilter filter) {
        List targetEngineerList = new ArrayList();
        for (Enginner enginner : enginnerList) {
            if (filter.getMatchedEnginner(enginner)) { // 谓词对象封装了测试Enginner 的条件
                targetEngineerList.add(enginner);
            }
        }
        return targetEngineerList;
    }

    public static <T> List filter(List<T> list, Filter<T> p) {
        List<T> targetList = new ArrayList();
        for (T t : list) {
            if (p.filter(t)) {
                targetList.add(t);
            }
        }
        return targetList;
    }


    public static void main(String[] args) {
        List<Enginner> enginnerList = Arrays.asList(new Enginner("Java", 18), new Enginner("GO", 20), new Enginner("Python", 15), new Enginner("DBA", 15), new Enginner("Java", 25));
        EnginnerTest enginnerTest = new EnginnerTest();
        List javaEngineers = enginnerTest.getJavaEngineers(enginnerList);
        System.out.println(javaEngineers);


        List targetEngineerList4 = enginnerTest.findEnginner(enginnerList, new AgeGte30JavaEnginnerFilter());
        System.out.println(targetEngineerList4);

        List targetEngineerList5 = enginnerTest.findEnginner(enginnerList, new JavaEnginnerFilter());
        System.out.println(targetEngineerList5);

        List targetEngineerList6 = enginnerTest.findEnginner(enginnerList, new EnginnerFilter() {
            @Override
            public boolean getMatchedEnginner(Enginner enginner) {
                return "Python".equals(enginner.getJob());
            }
        });
        /**
         * 在通往抽象的路上，我们还可以更进一步。目前， getMatchedEnginner方法还只适用于 Enginner 。 你还可以将 List 类型抽象化，从而兼容更多类型
         */
        List targetEngineerList7 = filter(enginnerList, enginner -> "Java".equals(enginner.getJob()));

    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Enginner {
    private String job;
    private Integer age;
}

