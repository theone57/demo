package com.all.lin.codegenerate;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 代码生成器
 * @author      sunkailun
 * @DateTime    2020/12/27  下午4:46
 * @email       376253703@qq.com
 * @phone       13777579028
 */
public class Generate {
    public static void main(String[] args) throws Exception {
        /**
         * 用户输入参数
         */
        String author = "test";
        String moduleName = "test";
        String tableName = "test";
        /**
         * 项目地址:/Users/sunkailun/Desktop/工作/工保网/finance/src/main/java/com/gb
         */
        String path = "/Users/alex/development/company/gongbao/code/utils/";
        //参数
        Map<String, Object> map = Maps.newHashMap();
        map.put("author", author);
        map.put("moduleName", moduleName);
        map.put("include", tableName);
        map.put("controller", "y");
        map.put("entity", "y");
        map.put("entityBO", "y");
        map.put("entityVO", "y");
        map.put("entityQuery", "y");
        map.put("enums", "y");
        map.put("service", "y");
        map.put("serviceResults", "y");
        map.put("serviceQuery", "y");
        map.put("mapper", "y");
//        CodeGenerate.beanQuery(map, tableName, path);
//        CodeGenerate.serviceQuery(map, tableName, path);
        String[] includeS = map.get("include").toString().split(",");
        for (String s : includeS) {
            //重写代码生成器
            CodeGenerate.foundCode(s, String.valueOf(map.get("moduleName")), String.valueOf(map.get("author")), map, path);
        }
    }

}
