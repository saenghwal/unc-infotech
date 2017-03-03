package io.smartbudget.ejb.persistence.mappers;

import java.util.List;

import io.smartbudget.ejb.persistence.entity.Budget;
import io.smartbudget.ejb.persistence.entity.User;

public interface BudgetMapper extends GenericMapper<Budget, Long> {
    List<Budget> findBudgets(User user);

}
