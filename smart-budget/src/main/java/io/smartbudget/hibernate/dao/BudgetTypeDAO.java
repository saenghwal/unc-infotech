package io.smartbudget.hibernate.dao;

import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;

public class BudgetTypeDAO extends AbstractDAO<BudgetType> {

    public BudgetTypeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public BudgetType addBudgetType() {
        BudgetType budgetType = new BudgetType();
        return persist(budgetType);
    }
}
