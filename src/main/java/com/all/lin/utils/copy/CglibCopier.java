package com.all.lin.utils.copy;

import org.springframework.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;

/**
 * 利用JAVA 动态代理CGLIB 来复制对象
 * 比之前用 APACHE 的利用反射的效率好很多，内容占用降低很多。
 * <p>
 * Created by wangjun on 2016/11/22.
 */

public class CglibCopier {

    //直接先初始化静态的容器
    public static Map<String, BeanCopier> copierMap = new HashMap<>();

    //cglib 动态代理速度高于现在我们的浅复制的工具类
    public static void copyProperties(Object source, Object target) {
        String beanKey = generateKey(source.getClass(), target.getClass());
        BeanCopier copier;
        if (!copierMap.containsKey(beanKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            copierMap.put(beanKey, copier);
        } else {
            copier = copierMap.get(beanKey);
        }
        copier.copy(source, target, null);
    }

    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.toString() + class2.toString();
    }
}