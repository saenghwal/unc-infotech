package io.smartbudget.ejb.dao;

import java.util.Date;
import java.util.List;

import io.smartbudget.persistence.entity.Budget;
import io.smartbudget.persistence.entity.User;

public interface BudgetDAO extends GenericDAO<Budget, Long> {

    List<Budget> findBudgets(User user, int month, int year);

    Date findLatestBudget(User user);

    List<Budget> findByUserAndCategory(User user, Long categoryId);

    List<Budget> findByRange(User user, int startMonth, int startYear, int endMonth, int endYear);

    List<String> findSuggestions(User user, String q);
}
