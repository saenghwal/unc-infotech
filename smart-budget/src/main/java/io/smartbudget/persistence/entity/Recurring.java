package io.smartbudget.persistence.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import io.smartbudget.domain.enums.RecurringType;

public class Recurring implements Serializable {

    private static final long serialVersionUID = -2889004877850258404L;

    private Long id;
    private double amount;
    private RecurringType recurringType;
    private Timestamp lastRunAt;
    private Timestamp createdAt;
    private Budget budget;
    private String remark;
    private List<Transaction> transactions;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public RecurringType getRecurringType() {
        return recurringType;
    }

    public void setRecurringType(RecurringType type) {
        this.recurringType = type;
    }


    //@Transient
    public String getRecurringTypeDisplay() {
        return recurringType.getDisplay();
    }
    
    public Timestamp getLastRunAt() {
        return lastRunAt;
    }

    public void setLastRunAt(Timestamp lastRunAt) {
        this.lastRunAt = lastRunAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recurring recurring = (Recurring) o;

        if (id != recurring.id) return false;
        if (Double.compare(recurring.amount, amount) != 0) return false;
        if (recurringType != recurring.recurringType) return false;
        if (!lastRunAt.equals(recurring.lastRunAt)) return false;
        if (!createdAt.equals(recurring.createdAt)) return false;
        if (!budget.equals(recurring.budget)) return false;
        if (!remark.equals(recurring.remark)) return false;
        if (!transactions.equals(recurring.transactions)) return false;
        return name.equals(recurring.name);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + recurringType.hashCode();
        result = 31 * result + lastRunAt.hashCode();
        result = 31 * result + createdAt.hashCode();
        result = 31 * result + budget.hashCode();
        result = 31 * result + remark.hashCode();
        result = 31 * result + transactions.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    public Budget getBudgetType() {
        return budget;
    }

    public void setBudgetType(Budget budgetType) {
        this.budget = budgetType;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setName(String name) {
        this.name = name;
    }
}
