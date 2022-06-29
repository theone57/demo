package com.all.lin.express;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionParser {
    public static final String EXP_FUNCTION = "\\$(?<fun>[a-zA-Z0-9_]+)\\((?<args>.*?)\\)";
    public static final String EXP_VARIABLE = "#\\{(?<var>[a-zA-Z0-9_.]+)(\\:(?<type>[a-z]+))?(\\?(?<def>.*?))?\\}";
    private static final String COMMA = "\\s*,\\s*";
    private static final Integer NOT_SUPPORTED_TYPE = -1;
    private Pattern FUNCTION = Pattern.compile(EXP_FUNCTION);
    private Pattern VARIABLE = Pattern.compile(EXP_VARIABLE);
    private String expression;
    private Stack<ExpressionNode> expNodeStack = new Stack<ExpressionNode>();
    private Map<String, Integer> supportedTypes = new HashMap<>();

    public ExpressionParser(String expression) {
        this.expression = expression;
        supportedTypes.put("string", Constant.STRING);
        supportedTypes.put("boolean", Constant.BOOLEAN);
        supportedTypes.put("integer", Constant.INTEGER);
        supportedTypes.put("object", Constant.OBJECT);
        supportedTypes.put("collection", Constant.COLLECTION);
    }

    public Stack<ExpressionNode> getExpNodeStack() {
        return expNodeStack;
    }

    public void parse() {
        parse(expression);
    }

    /**
     * 解析表达式
     *
     * @param expression
     * @return
     */
    public ExpressionNode parse(String expression) {
        if (expression == null || expression.isEmpty()) {
            return null;
        }
        ExpressionNode expNode = new ExpressionNode();
        expNodeStack.push(expNode);
        if (parseFunction(expNode, expression)) {
            return expNode;
        }
        if (parseVariable(expNode, expression)) {
            return expNode;
        }
        if (parseTemplate(expNode, expression)) {
            return expNode;
        }
        expNode.setNodeType(ExpressionNode.CONSTANT);
        expNode.setExpression(expression);
        return expNode;
    }

    private boolean parseFunction(ExpressionNode expNode, String expression) {
        Matcher matcher = Pattern.compile(EXP_FUNCTION).matcher(expression);
        if (!matcher.find()) {
            return false;
        }
        if (matcher.end() - matcher.start() != expression.length()) {
            return false;
        }
        expNode.setNodeType(ExpressionNode.FUNCTION);
        String fun = matcher.group("fun");
        String args = matcher.group("args");
        expNode.setExpression(fun);
        for (String argExp : args.split(COMMA)) {
            if (!argExp.isEmpty()) {
                expNode.addArgument(parse(argExp));
            }
        }
        return true;
    }

    private boolean parseVariable(ExpressionNode expNode, String expression) {
        Matcher matcher = Pattern.compile(EXP_VARIABLE).matcher(expression);
        if (!matcher.find()) {
            return false;
        }
        if (matcher.end() - matcher.start() != expression.length()) {
            return false;
        }
        expNode.setNodeType(ExpressionNode.VARIABLE);
        String var = matcher.group("var");
        String type = matcher.group("type");
        String defVal = matcher.group("def");
        if (type == null) {
            type = "string"; // default type: string
        }
        Integer varType = getVarType(type);
        expNode.setExpression(var);
        expNode.setVarType(varType);
        expNode.setDefVal(defVal);
        return true;
    }

    /**
     *
     * @param type
     * @return
     */
    private Integer getVarType(String type) {
        if (!supportedTypes.containsKey(type)) {
            return NOT_SUPPORTED_TYPE;
        } else {
            return supportedTypes.get(type);
        }
    }

    /**
     * 解析（模板）表达式
     * 
     * @param expNode
     * @param expression
     * @return
     */
    private boolean parseTemplate(ExpressionNode expNode, String expression) {
        expNode.setNodeType(ExpressionNode.TEMPLATE);
        StringBuffer temp = new StringBuffer();
        int argNodeCount = processSubNode(expNode, 0, FUNCTION.matcher(expression), temp);
        if (argNodeCount > 0) {
            expression = temp.toString();
        }
        temp.setLength(0);
        argNodeCount = processSubNode(expNode, argNodeCount, VARIABLE.matcher(expression), temp);
        if (argNodeCount > 0) {
            expNode.setExpression(temp.toString());
        }
        return argNodeCount > 0;
    }

    /**
     * 处理子节点
     * 
     * @param expNode
     * @param argNodeCount
     * @param matcher
     * @param temp
     * @return
     */
    private int processSubNode(ExpressionNode expNode, int argNodeCount, Matcher matcher, StringBuffer temp) {
        while (matcher.find()) {
            ExpressionNode subNode = parse(matcher.group());
            if (subNode == null) {
                continue;
            }
            expNode.addArgument(subNode);
            matcher.appendReplacement(temp, "{{" + argNodeCount + "}}");
            argNodeCount++;
        }

        matcher.appendTail(temp);
        return argNodeCount;
    }

}