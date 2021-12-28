package com.all.lin.sign.stu._24responsibilitychain.lin;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSONObject;
import freemarker.template.utility.NullArgumentException;
import lombok.Data;

import java.util.Map;

/**
 * @author linpu
 * @dateTime 2021-09-16 10:01
 * @email im.linpu@qq.com
 * @description
 */
@SuppressWarnings("ALL")
@Data
public abstract class AbstractHandlerChain {
    protected AbstractHandlerChain nextHandler;

    public AbstractHandlerChain() {
    }

    protected AbstractHandlerChain addNextHandler(AbstractHandlerChain... handlers) {
        Assert.notEmpty(handlers, () -> new NullArgumentException("handlers must not empty"));
        this.nextHandler = handlers[0];
        for (int i = 0; i < handlers.length - 1; i++) {
            handlers[i].setNextHandler(handlers[i + 1]);
        }
        return this;
    }

    /**
     * 当前责任链是否可用
     *
     * @return true:可用;false:不可用
     */
    protected abstract boolean isEnable(ChainContext tc);

    /**
     * 申请
     */
    protected abstract <T> void apply(JSONObject request, JSONObject config, ChainContext tc);

    /**
     * 回调
     */
    protected abstract String callback(ChainContext tc, Map<String, Object> param);

    /**
     * 调用->执行申请
     */
    protected void executeApply(JSONObject request, JSONObject config, ChainContext tc) {
        boolean b = isEnable(tc);
        if (b) {
            apply(request, config, tc);
            return;
        }
        executeNextApply(request, config, tc);
    }

    /**
     * 调用->执行回调
     */
    protected String executeCallback(ChainContext tc, Map<String, Object> param) {
        boolean b = isEnable(tc);
        if (b) {
            return callback(tc, param);
        }
        return executeNextCallback(tc, param);
    }

    /**
     * 执行下一个 申请
     */
    protected void executeNextApply(JSONObject request, JSONObject config, ChainContext tc) {
        assertNextHandler().executeApply(request, config, tc);
    }

    /**
     * 执行下一个 回调
     */
    protected String executeNextCallback(ChainContext tc, Map<String, Object> param) {
        return assertNextHandler().executeCallback(tc, param);
    }

    /**
     * 断言 没有找到适配的 handler
     */
    private AbstractHandlerChain assertNextHandler() {
        Assert.notNull(nextHandler, () -> new NullArgumentException("not find next handler"));
        return nextHandler;
    }

    public static class ChainContext {
    }

}
