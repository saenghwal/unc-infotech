package io.smartbudget.ejb.persistence.dao;

import java.util.List;

import io.smartbudget.ejb.persistence.entity.Budget;
import io.smartbudget.ejb.persistence.entity.User;

public interface BudgetDAO extends GenericDAO<Budget, Long> {
    List<Budget> findBudgets(User user);
}
