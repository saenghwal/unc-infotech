package io.smartbudget.ejb.persistence.mappers;

import java.util.List;

import io.smartbudget.domain.entity.Budget;
import io.smartbudget.domain.entity.User;

public interface BudgetMapper extends GenericMapper<Budget, Long> {
    List<Budget> findBudgets(User user);

}
