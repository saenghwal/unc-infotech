package io.smartbudget.ejb.dao;

import java.util.Date;
import java.util.List;

import io.smartbudget.form.report.SearchFilter;
import io.smartbudget.persistence.entity.Transaction;
import io.smartbudget.persistence.entity.User;

public interface TransactionDAO extends GenericDAO<Transaction, Long> {
    List<Transaction> find(User user, Integer limit);

    List<Transaction> findById(User user, Long id);

    List<Transaction> findByBudget(User user, Long budgetId);

    List<Transaction> findByRecurring(User user, Long recurringId);

    List<Transaction> findByRange(User user, Date start, Date end);

    List<Transaction> findTransactions(User user, SearchFilter filter);
}
