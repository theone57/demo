package com.all.lin.express;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

public class ExpressionCalculator {
    private static Logger logger = LogManager.getLogger(ExpressionCalculator.class);
    private static ConcurrentHashMap<String, Stack> expressions = new ConcurrentHashMap<>();
    private static final ExpressionCalculator calculator = new ExpressionCalculator();

    public static void main(String[] args) {
        Object user = new Object() {
            private String name = "lichmama";

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        };
        String expression = "{'name': '#{user.name}', 'result': $equals(#{user.name}, lichmama})}";
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        params.put("result", null);
        Object result = ExpressionCalculator.calculate(expression, params);
        System.out.println("模板 => " + expression);
        System.out.println("入参 => " + JSON.toJSONString(params));
        System.out.println("结果 => " + result);
    }

    private ExpressionCalculator() {
    }

    public static Object calculate(String expression, Map<String, Object> params) {
        if (expression == null || expression.length() == 0) {
            logger.info("expression is null!");
            return null;
        }
        try {
            Stack<ExpressionNode> expNodeStack = calculator.parse(expression);
            return calculator.calculate(expNodeStack, params);
        } catch (Exception e) {
            logger.error("计算表达式异常", e);
            return null;
        }
    }

    public Stack<ExpressionNode> parse(String expression) {
        Stack<ExpressionNode> expNodeStack = expressions.get(expression);
        if (expNodeStack == null) {
            synchronized (ExpressionCalculator.class) {
                if (expNodeStack == null) {
                    ExpressionParser parser = new ExpressionParser(expression);
                    parser.parse();
                    expNodeStack = parser.getExpNodeStack();
                    expressions.put(expression, expNodeStack);
                }
            }
        }
        Stack<ExpressionNode> stackCopy = new Stack<>();
        stackCopy.addAll(expNodeStack);
        return stackCopy;
    }

    public Object calculate(Stack<ExpressionNode> expNodeStack, Map<String, Object> params) {
        Object result = null;
        while (!expNodeStack.isEmpty()) {
            ExpressionNode expNode = expNodeStack.pop();
            if (ExpressionNode.CONSTANT.equals(expNode.getNodeType())) {
                expNode.setValue(expNode.getExpression());
            } else if (ExpressionNode.VARIABLE.equals(expNode.getNodeType())) {
                Object value = getVariable(expNode, params);
                expNode.setValue(value);
            } else if (ExpressionNode.FUNCTION.equals(expNode.getNodeType())) {
                // FunctionUtil
                Object value = FunctionUtil.call(expNode.getExpression(), expNode.getArgumentValues());
                expNode.setValue(value);
            } else if (ExpressionNode.TEMPLATE.equals(expNode.getNodeType())) {
                String value = TemplateFormat.format(expNode.getExpression(), expNode.getArgumentValues());
                expNode.setValue(value);
            }
            result = expNode.getValue();
        }
        return result;
    }

    /**
     * 仅支持boolean/string/integer类型
     **/
    private Object convert(Integer varType, String value) {
        if (value == null) {
            return null;
        }
        try {
            if (Constant.BOOLEAN.equals(varType)) {
                return Boolean.parseBoolean(value);
            } else if (Constant.STRING.equals(varType)) {
                return value;
            } else if (Constant.INTEGER.equals(varType)) {
                return Integer.parseInt(value);
            }
        } catch (Exception e) {
            logger.error("获取默认值异常", e);
        }
        return null;
    }

    private Object getVariable(ExpressionNode varExpNode, Map<String, Object> params) {
        Object value = JSONPath.eval(params, "$." + varExpNode.getExpression());
        if (value == null) {
            value = convert(varExpNode.getVarType(), varExpNode.getDefVal());
        }
        return value;
    }

    private static class TemplateFormat {

        public static String format(String pattern, Collection<?> collection) {
            return format(pattern, collection.toArray());
        }

        public static String format(String pattern, Object... objects) {
            if (objects == null || objects.length == 0) {
                return pattern;
            }
            StringBuilder temp = new StringBuilder(pattern);
            for (int i = 0; i < objects.length; i++) {
                String token = "{{" + i + "}}";
                int start = temp.indexOf(token);
                if (start == -1) {
                    break;
                }
                temp.replace(start, start + token.length(), String.valueOf(objects[i]));
            }
            return temp.toString();
        }
    }

}