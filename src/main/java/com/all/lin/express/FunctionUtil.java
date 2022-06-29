package com.all.lin.express;

import java.util.Map;

public class FunctionUtil {
    private static Map<String, IFunction> FUNCTIONS = null;

    protected FunctionUtil() {
    }

    private static Map<String, IFunction> getFunctions() {
        return FUNCTIONS;
    }

    /** call by CronJob.updateFunction() **/
    protected static synchronized void setFunctions(Map<String, IFunction> functions) {
        FUNCTIONS = functions;
    }

    /** load functions from jar file **/


    private static IFunction getFunction(String name) {
        Map<String, IFunction> functions = getFunctions();
        if (functions == null || functions.size() == 0) {
            return null;
        }
        return functions.get(name);
    }

    /** call the function **/
    @SuppressWarnings("unchecked")
    public static <T> T call(String name, Object... args) {
        IFunction function = getFunction(name);
        if (function == null) {
            System.err.println("function \"" + name + "\" not exist!");
            return null;
        }
        try {
            return (T) function.process(args);
        } catch (Exception e) {
            e.printStackTrace();
            return (T) function.getDefVal();
        }
    }

}