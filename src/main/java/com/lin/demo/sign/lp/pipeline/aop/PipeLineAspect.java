package com.lin.demo.sign.lp.pipeline.aop;

import com.lin.demo.sign.lp.pipeline.model.PipeLineContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 使用AOP织入阀门，跟踪执行流
 */
@Slf4j
@Aspect
@Component
public class PipeLineAspect {
    /**
     * 定义阀门invoke切点
     */
    @Pointcut(value = "this(com.lin.demo.sign.lp.pipeline.Valve) " +
            "&& execution(* invoke(com.lin.demo.sign.lp.pipeline.model.PipeLineContext)) && args((pipeLineContext))",
            argNames = "pipeLineContext")
    public void valveInvokeCutOffPoint(PipeLineContext pipeLineContext) {
    }

    @Before(value = "valveInvokeCutOffPoint(pipeLineContext)", argNames = "point,pipeLineContext")
    public void doBefore(JoinPoint point, PipeLineContext pipeLineContext) {
        int currentIndex = pipeLineContext.getAndIncrement();
        String className = point.getTarget().getClass().getName();
        log.info("管道前置通知-{}号阀门({})进入执行, pipeLineContext={}", currentIndex, className, pipeLineContext.toString());
    }
}