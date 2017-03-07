package io.smartbudget.ejb.persistence.dao;

import java.util.List;

import io.smartbudget.domain.dto.Budget;
import io.smartbudget.domain.dto.User;

public interface BudgetDAO extends GenericDAO<Budget, Long> {
    List<Budget> findBudgets(User user);
}
