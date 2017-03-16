package io.smartbudget.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import io.smartbudget.persistence.entity.Budget;
import io.smartbudget.domain.enums.RecurringType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionForm implements Serializable {

    private static final long serialVersionUID = 1432079737348530213L;

    private double amount;
    private String remark;
    private Timestamp transactionOn;
    private Boolean recurring;
    private RecurringType recurringType;
    private Budget budget;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getTransactionOn() {
        if(transactionOn == null) {
            return Timestamp.from(Instant.now());
        } else {
            return transactionOn;
        }
    }

    public void setTransactionOn(Timestamp transactionOn) {
        this.transactionOn = transactionOn;
    }

    public Boolean getRecurring() {
        return recurring;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }

    public RecurringType getRecurringType() {
        return recurringType;
    }

    public void setRecurringType(RecurringType recurringType) {
        this.recurringType = recurringType;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "TransactionForm{" +
                "amount=" + amount +
                ", remark='" + remark + '\'' +
                ", transactionOn=" + transactionOn +
                ", recurring=" + recurring +
                ", recurringType=" + recurringType +
                ", budget=" + budget +
                '}';
    }
}
