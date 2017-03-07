package io.smartbudget.ejb.persistence.mappers;

import java.util.List;

import io.smartbudget.domain.dto.Budget;
import io.smartbudget.domain.dto.User;

public interface BudgetMapper extends GenericMapper<Budget, Long> {
    List<Budget> findBudgets(User user);

}
