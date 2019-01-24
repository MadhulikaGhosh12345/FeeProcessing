package com.sapient.fee.calculator.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Transaction {

    private String externalTxnId;
    private String clientId;
    private String securityId;
    private LocalDate txnDate;
    private TxnType txnType;
    private double marketValue;
    private Priority priority;
    private double processingFees;
    private TxnCategory txnCategory;

    public enum TxnType {
        BUY, SELL, WITHDRAW, DEPOSIT
    };
    public enum TxnCategory {
        INTRA, NORMAL
    };

    public String getExternalTxnId() {
        return externalTxnId;
    }

    public void setExternalTxnId(String externalTxnId) {
        this.externalTxnId = externalTxnId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }

    public LocalDate getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(LocalDate txnDate) {
        this.txnDate = txnDate;
    }

    public TxnType getTxnType() {
        return txnType;
    }

    public void setTxnType(TxnType txnType) {
        this.txnType = txnType;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public double getProcessingFees() {
        return processingFees;
    }

    public void setProcessingFees(double processingFees) {
        this.processingFees = processingFees;
    }

    public TxnCategory getTxnCategory() {
        return txnCategory;
    }

    public void setTxnCategory(TxnCategory txnCategory) {
        this.txnCategory = txnCategory;
    }

    public enum Priority {
        Y, N
    }

    public Transaction() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String toString() {
        return ("{clientId: " +clientId +
                ", Transaction Type: " + txnType.toString()  +
                ", Transaction Date: " + txnDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                ", Priority : " + priority.toString() +
                ", Processing Fee: " +   processingFees +"}");
    }
}


