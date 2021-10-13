package com.lin.demo.sign.lp.pipeline.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * PipeLineContext 实现管道上下文的概念，内部记录一个所处阀门在管道中的索引；使用HashMap存储业务数据，用户流程进行时的数据传递。
 */
public class PipeLineContext {

    private PipeLineContext() {
    }

    @Getter
    private int index;

    @Getter
    private Map<String, Object> context;

    public PipeLineContext(int size) {
        this.index = 0;
        this.context = new HashMap<>(size);
    }

    public void put(String key, Object value) {
        context.put(key, value);
    }

    public void get(String key) {
        context.get(key);
    }

    @JSONField(serialize = false)
    public int getAndIncrement() {
        this.index++;
        return index;
    }

    @Override
    public String toString() {
        return "{\"index\":\"" + index + "\", \"context\":\"" + JSON.toJSONString(context) + "\"}";
    }

}