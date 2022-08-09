package com.all.lin.utils;

import cn.hutool.core.util.ReflectUtil;
import com.all.lin.statrter.listener.pojo.User;

import java.lang.reflect.Field;

/**
 * @author linpu
 * @dateTime 2022-07-18 16:32
 * @email im.linpu@qq.com
 * @description
 */
public class CompareFiledUtils {
    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setAge(18);
        user.setSex(21);

        User user2 = new User();
        user2.setAge(20);
        user2.setSex(11);
        User o = (User) compareObject(user, user2);
        System.out.println("o = " + o);


    }

    public static Object compareObject(Object obj1, Object obj2) throws Exception {
        Object newInstance = ReflectUtil.newInstance(obj1.getClass());
        //确保是同一对象
        if (obj1.getClass() == obj2.getClass()) {
            // 获取所有属性
            Field[] fs = obj1.getClass().getDeclaredFields();
            //获取各个属性的值
            for (Field field : fs) {
                //true代表暴力反射，可以访问private修饰的东西，否则无法访问
                field.setAccessible(true);
                Object v1 = field.get(obj1);
                Object v2 = field.get(obj2);
                //值的类型
                if (v1 instanceof Integer && v1 instanceof Integer) {
                    field.set(newInstance, (int) v1  > (int) v2 ? v1 : v2);

                }
            }
        }
        return newInstance;
    }

    public static void ifOverThreshold(String fieldName, Object obj1, Object obj2) throws Exception {
        String message = "";
        if ((double) obj1 > (double) obj2) {
            message = fieldName + "数据超阈值";
            System.out.println(message);

            //超出阀值插入另一张表的操作

        } else {
            //符合标准值以下的操作

        }
    }

}
