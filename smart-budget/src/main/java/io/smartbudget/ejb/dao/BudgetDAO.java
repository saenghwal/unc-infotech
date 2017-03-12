package io.smartbudget.ejb.dao;

import java.util.Date;
import java.util.List;

import io.smartbudget.persistence.entity.Budget;
import io.smartbudget.persistence.entity.User;

public interface BudgetDAO extends GenericDAO<Budget, Long> {
    List<Budget> findBudgets(User user, int month, int year);
}
