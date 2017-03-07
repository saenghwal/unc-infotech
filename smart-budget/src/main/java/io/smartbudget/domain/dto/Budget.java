package io.smartbudget.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.smartbudget.form.budget.AddBudgetForm;

public class Budget implements Serializable {
    private static final long serialVersionUID = 2625666273036891436L;

    private long id;
    private String name;
    private double projected;
    private double actual;
    private Date periodOn;
    private Date createdAt;
    private User user;
    private Category category;
    private BudgetType budgetType;

    public Budget() {
    }

    public Budget(long budgetId) {
        this.id = budgetId;
    }

    public Budget(AddBudgetForm budgetForm) {
        setName(budgetForm.getName());
        setProjected(budgetForm.getProjected());
        setCategory(new Category(budgetForm.getCategoryId()));
    }

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

    public double getProjected() {
        return projected;
    }

    public void setProjected(double projected) {
        this.projected = projected;
    }

    public double getActual() {
        return actual;
    }

    public void setActual(double actual) {
        this.actual = actual;
    }

    public Date getPeriodOn() {
        return periodOn;
    }

    public void setPeriodOn(Date periodOn) {
        this.periodOn = periodOn;
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

        Budget budget = (Budget) o;

        if (id != budget.id) return false;
        if (Double.compare(budget.projected, projected) != 0) return false;
        if (Double.compare(budget.actual, actual) != 0) return false;
        if (!name.equals(budget.name)) return false;
        if (!periodOn.equals(budget.periodOn)) return false;
        if (!createdAt.equals(budget.createdAt)) return false;
        if (!user.equals(budget.user)) return false;
        if (!category.equals(budget.category)) return false;
        return budgetType.equals(budget.budgetType);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(projected);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(actual);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + periodOn.hashCode();
        result = 31 * result + createdAt.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + budgetType.hashCode();
        return result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User usersByUserId) {
        this.user = usersByUserId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BudgetType getBudgetType() {
        return budgetType;
    }

    public void setBudgetType(BudgetType budgetType) {
        this.budgetType = budgetType;
    }
}
