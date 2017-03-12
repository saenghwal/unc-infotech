package io.smartbudget.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import io.smartbudget.form.budget.AddBudgetForm;

public class Budget implements Serializable {
    private static final long serialVersionUID = 2625666273036891436L;

    private Long id;
    private String name;
    private double projected;
    private double actual;
    private Date periodOn;
    private Date createdAt;
    private User user;
    private Category category;

    public Budget() {
    }

    public Budget(Long budgetId) {
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Budget{");
        sb.append("id=").append(id);
        sb.append(", actual=").append(actual);
        sb.append(", name='").append(name).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append(", user=").append(user);
        sb.append(", category=").append(category);
        sb.append('}');
        return sb.toString();
    }
}
