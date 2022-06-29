package com.all.lin.spring.transaction;

import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author linpu
 * @dateTime 2022-05-27 10:25
 * @email im.linpu@qq.com
 * @description
 */
public class TransactionGet {
    public static void main(String[] args) {

        String txName = TransactionSynchronizationManager.getCurrentTransactionName();
        Integer txId = TransactionSynchronizationManager.getCurrentTransactionIsolationLevel();
    }

}
