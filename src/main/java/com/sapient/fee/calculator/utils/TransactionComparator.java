package com.sapient.fee.calculator.utils;

import com.sapient.fee.calculator.models.Transaction;

import java.util.Comparator;

public class TransactionComparator implements Comparator<Transaction>
{
    public int compare(Transaction t1, Transaction t2)
    {
        if (t1.getClientId().compareTo(t2.getClientId()) == 0) {
            if (t2.getTxnType().toString().compareTo(t2.getTxnType().toString()) == 0) {
                if (t1.getTxnDate().compareTo(t2.getTxnDate()) == 0) {
                    return t1.getPriority().compareTo(t2.getPriority());
                }
                return t1.getTxnDate().compareTo(t2.getTxnDate());
            }
            else return t2.getTxnType().toString().compareTo(t2.getTxnType().toString());

        }
        else return t1.getClientId().compareTo(t2.getClientId());
    }
}
