package io.smartbudget.persistence.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

import io.smartbudget.persistence.entity.Budget;
import io.smartbudget.persistence.entity.User;

public interface BudgetsMapper extends GenericMapper<Budget, Long> {

    List<Budget> findBudgets(@Param("user") User user, @Param("period") Date period);

    Date findLatestBudget(User user);

    List<Budget> findByUserAndCategory(@Param("user") User user,
                                       @Param("categoryId") Long categoryId,
                                       @Param("period") Date period);

    List<Budget> findByRange(@Param("user") User user, @Param("start") Date start,
                             @Param("end") Date end);

    List<String> findSuggestions(@Param("user") User user, @Param("query") String q);
}
