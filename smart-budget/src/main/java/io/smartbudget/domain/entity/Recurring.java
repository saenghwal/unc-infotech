package io.smartbudget.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.smartbudget.domain.enums.entity.RecurringType;

public class Recurring implements Serializable {

    private static final long serialVersionUID = -2889004877850258404L;

    private long id;
    private double amount;
    private RecurringType recurringType;
    private Date lastRunAt;
    private Date createdAt;
    private BudgetType budgetType;
    private String remark;
    private List<Transaction> transactions;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        this.recurringType = recurringType;
    }


    //@Transient
    public String getRecurringTypeDisplay() {
        return recurringType.getDisplay();
    }
    
    public Date getLastRunAt() {
        return lastRunAt;
    }

    public void setLastRunAt(Date lastRunAt) {
        this.lastRunAt = lastRunAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
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
        if (!budgetType.equals(recurring.budgetType)) return false;
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
        result = 31 * result + budgetType.hashCode();
        result = 31 * result + remark.hashCode();
        result = 31 * result + transactions.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    public BudgetType getBudgetType() {
        return budgetType;
    }

    public void setBudgetType(BudgetType budgetType) {
        this.budgetType = budgetType;
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
