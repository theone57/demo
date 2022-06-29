package com.all.lin.express;

public class Equals implements IFunction {

    @Override
    public String getName() {
        return "equals";
    }

    @Override
    public String getDesc() {
        return "判断两个表达式在字面上是否相同";
    }

    @Override
    public Object getDefVal() {
        return false;
    }

    @Override
    public Object process(Object... args) throws Exception {
        if (checkArgsIsEmpty(args)) {
            return false;
        }
        Object obj1 = args[0];
        Object obj2 = args[1];
        if (obj1 == null || obj2 == null) {
            return false;
        }
        if (obj1.getClass() == obj2.getClass()) {
            return obj1.equals(obj2);
        }
        return obj1.toString().equals(obj2.toString());
    }

}