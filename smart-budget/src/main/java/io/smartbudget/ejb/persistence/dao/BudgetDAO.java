package io.smartbudget.ejb.persistence.dao;

import java.util.List;

import io.smartbudget.domain.entity.Budget;
import io.smartbudget.domain.entity.User;

public interface BudgetDAO extends GenericDAO<Budget, Long> {
    List<Budget> findBudgets(User user);
}
