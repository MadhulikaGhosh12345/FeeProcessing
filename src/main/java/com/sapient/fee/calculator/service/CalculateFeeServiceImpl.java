package com.sapient.fee.calculator.service;

import com.sapient.fee.calculator.models.Transaction;

import java.util.*;

public class CalculateFeeServiceImpl{


    public List<Transaction> calculateFee(List<Transaction> txns) {

        Set<Transaction> intraTxn = intraTxnFee(txns);
        Set<Transaction> finalTxn = normalTxnFee(intraTxn);
        return new ArrayList<>(finalTxn);
    }
    public static  Set<Transaction> intraTxnFee(List<Transaction> txns)
    {
        String key;

        Map<String,Transaction> uniqueTxn = new HashMap<String,Transaction>();
        Set<Transaction> finalTxn = new HashSet<Transaction>();
        for(Transaction txn : txns)
        {
            key = txn.getClientId()+txn.getSecurityId()+txn.getTxnDate();


            if(uniqueTxn.containsKey(key))
            {
                Transaction Oldtxn = uniqueTxn.get(key);
                if(!(Oldtxn.getTxnType().equals(txn.getTxnType())))
                {
                    Oldtxn.setTxnCategory(Transaction.TxnCategory.INTRA);
                    txn.setTxnCategory(Transaction.TxnCategory.INTRA);
                    finalTxn.add(txn);
                }
            }
            else if(txn.getTxnType() == Transaction.TxnType.BUY || txn.getTxnType() == Transaction.TxnType.SELL)
            {
                uniqueTxn.put(key,txn);
                finalTxn.add(txn);
            }
            else
            {
                finalTxn.add(txn);
            }


        }
        return finalTxn;
    }

    public static  Set<Transaction> normalTxnFee(Set<Transaction> txns)
    {
        for(Transaction txn : txns)
        {
            if(txn.getPriority() == Transaction.Priority.Y)
            {
                txn.setProcessingFees(500);
            }
            else if(txn.getTxnType() == Transaction.TxnType.SELL || txn.getTxnType() == Transaction.TxnType.WITHDRAW)
            {
                txn.setProcessingFees(100);
            }
            else
                txn.setProcessingFees(50);

            if (txn.getTxnCategory() == Transaction.TxnCategory.INTRA) {
                txn.setProcessingFees(txn.getProcessingFees() + 10);
            }

        }
        return txns;
    }
}
