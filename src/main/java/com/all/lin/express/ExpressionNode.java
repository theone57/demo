package com.all.lin.express;

import java.util.ArrayList;
import java.util.List;

public class ExpressionNode {
    /** 模板 **/
    public static final Integer TEMPLATE = 0;
    /** 函数 **/
    public static final Integer FUNCTION = 1;
    /** 变量 **/
    public static final Integer VARIABLE = 2;
    /** 常量 **/
    public static final Integer CONSTANT = 3;
    /** 类型 **/
    private Integer nodeType;
    /** 表达式 **/
    private String expression;
    /** 值 **/
    private Object value;
    /** 参数 **/
    private List<ExpressionNode> arguments;
    /** 变量值类型 **/
    private Integer varType;
    /** 默认值 **/
    private String defVal;

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public List<ExpressionNode> getArguments() {
        return arguments;
    }

    public void setArguments(List<ExpressionNode> arguments) {
        this.arguments = arguments;
    }

    public Integer getVarType() {
        return varType;
    }

    public void setVarType(Integer varType) {
        this.varType = varType;
    }

    public String getDefVal() {
        return defVal;
    }

    public void setDefVal(String defVal) {
        this.defVal = defVal;
    }

    public void addArgument(ExpressionNode argument) {
        if (argument == null) {
            return;
        }
        if (arguments == null) {
            arguments = new ArrayList<ExpressionNode>();
        }
        arguments.add(argument);
    }

    public List<Object> getArgumentValues() {
        List<Object> values = new ArrayList<Object>();
        if (arguments != null) {
            arguments.forEach(arg -> {
                values.add(arg.getValue());
            });
        }
        return values;
    }

    @Override
    public String toString() {
        return "ExpressionNode [nodeType=" + nodeType + ", expression=" + expression + ", value=" + value
                + ", arguments=" + arguments + ", varType=" + varType + ", defVal=" + defVal + "]";
    }

}