package io.smartbudget.persistence.mappers;

import java.util.List;

import io.smartbudget.persistence.entity.Budget;
import io.smartbudget.persistence.entity.User;

public interface BudgetMapper extends GenericMapper<Budget, Long> {
    List<Budget> findBudgets(User user);

}
