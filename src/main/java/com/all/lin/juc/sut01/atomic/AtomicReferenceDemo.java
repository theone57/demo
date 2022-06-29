package com.all.lin.juc.sut01.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Copyright © 2018 五月工作室. All rights reserved.
 *
 * @Project: tools
 * @ClassName: AtomicReferenceDemo
 * @Package: com.amos.tools.common.bean
 * @author: zhuqb
 * @Description: 主要用来展示AtomicReference使用方法
 * @date: 2019/9/11 0011 上午 9:46
 * @Version: V1.0
 */
public class AtomicReferenceDemo {

    private Reference reference;

    private AtomicReference<Reference> atomicReference;

    /**
     * 构建器中初始化AtomicReference
     *
     * @param reference
     */
    public AtomicReferenceDemo(Reference reference) {
        this.reference = reference;
        this.atomicReference = new AtomicReference<>(reference);
    }

    public void atomic(Reference reference) {
        Reference referenceOld;
        Reference referenceNew;

        long sequence;
        long timestamp;

        while (true) {
            referenceOld = this.atomicReference.get();
            sequence = referenceOld.getSequence();
            sequence++;
            timestamp = System.currentTimeMillis();

            referenceNew = new Reference(sequence, timestamp);
            /**
             * 比较交换
             */
            if (this.atomicReference.compareAndSet(referenceOld, referenceNew)) {
                reference.setSequence(sequence);
                reference.setTimestamp(timestamp);
                break;
            }
        }
    }
}

/**
 * 业务场景模拟
 * 序列需要自增并且时间需要更新成最新的时间戳
 */
@Data
@AllArgsConstructor
class Reference {
    /**
     * 序列
     */
    private long sequence;
    /**
     * 时间戳
     */
    private long timestamp;
}
