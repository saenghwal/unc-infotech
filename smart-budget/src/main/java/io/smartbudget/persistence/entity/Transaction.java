package io.smartbudget.persistence.entity;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {

    private static final long serialVersionUID = 2625666273036891436L;

    private long id;
    private String name;
    private double amount;
    private String remark;
    private boolean auto;
    private Date transactionOn;
    private Date createdAt;
    private Budget budget;
    private Recurring recurring;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public boolean isAuto() {
        return auto;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    public Date getTransactionOn() {
        return transactionOn;
    }

    public void setTransactionOn(Date transactionOn) {
        this.transactionOn = transactionOn;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (id != that.id) return false;
        if (Double.compare(that.amount, amount) != 0) return false;
        if (auto != that.auto) return false;
        if (!name.equals(that.name)) return false;
        if (!remark.equals(that.remark)) return false;
        if (!transactionOn.equals(that.transactionOn)) return false;
        if (!createdAt.equals(that.createdAt)) return false;
        if (!budget.equals(that.budget)) return false;
        return recurring.equals(that.recurring);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + remark.hashCode();
        result = 31 * result + (auto ? 1 : 0);
        result = 31 * result + transactionOn.hashCode();
        result = 31 * result + createdAt.hashCode();
        result = 31 * result + budget.hashCode();
        result = 31 * result + recurring.hashCode();
        return result;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budgetsByBudgetId) {
        this.budget = budgetsByBudgetId;
    }

    public Recurring getRecurring() {
        return recurring;
    }

    public void setRecurring(Recurring recurringsByRecurringId) {
        this.recurring = recurringsByRecurringId;
    }
}
