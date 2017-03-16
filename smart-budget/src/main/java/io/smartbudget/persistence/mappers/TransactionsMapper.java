package io.smartbudget.persistence.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

import io.smartbudget.form.report.SearchFilter;
import io.smartbudget.persistence.entity.Transaction;
import io.smartbudget.persistence.entity.User;

public interface TransactionsMapper extends GenericMapper<Transaction, Long> {
    List<Transaction> find(@Param("user") User user, @Param("limit") Integer limit);

    List<Transaction> findById(@Param("user") User user, @Param("id") Long id);

    List<Transaction> findByBudget(@Param("user") User user, @Param("budgetId") Long budgetId);

    List<Transaction> findByRecurring(@Param("user") User user, @Param("recurringId") Long recurringId);

    List<Transaction> findByRange(@Param("user") User user, @Param("start") Date start, @Param("end") Date end);

    List<Transaction> findTransactions(@Param("user") User user, @Param("filter") SearchFilter filter);
}
