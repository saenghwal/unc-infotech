package io.smartbudget.hibernate.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import io.dropwizard.hibernate.AbstractDAO;
import io.smartbudget.domain.entity.Transaction;
import io.smartbudget.domain.entity.User;
import io.smartbudget.exception.NotFoundException;
import io.smartbudget.form.report.SearchFilter;

public class TransactionDAO extends AbstractDAO<Transaction> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionDAO.class);

    public TransactionDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Transaction addTransaction(Transaction transaction) {
        LOGGER.debug("Add transaction {}", transaction);
        return persist(transaction);
    }

    public List<Transaction> find(User user, Integer limit) {
        Query query = currentSession().createQuery("FROM Transaction t WHERE t.budget.user = :user ORDER BY t.transactionOn DESC, t.id ASC");
        query.setParameter("user", user);
        query.setMaxResults(limit);
        return list(query);
    }

    public Transaction findById(long id) {
        Transaction transaction = get(id);
        if(transaction == null) {
            throw new NotFoundException();
        }

        return transaction;
    }

    public Optional<Transaction> findById(User user, long id) {
        Query query = currentSession().createQuery("FROM Transaction t WHERE t.id = :id AND t.budget.user = :user");
        query.setParameter("user", user);
        query.setParameter("id", id);
        Transaction result = (Transaction) query.uniqueResult();
        return Optional.ofNullable(result);
    }

    public List<Transaction> findByBudget(User user, long budgetId) {
        Criteria criteria = defaultCriteria();
        criteria.createAlias("t.budget", "io/smartbudget");

        criteria.add(Restrictions.eq("budget.id", budgetId));
        criteria.add(Restrictions.eq("budget.user", user));
        return list(criteria);
    }

    public List<Transaction> findByRecurring(User user, long recurringId) {
        Query query = currentSession().createQuery("FROM Transaction t WHERE t.budget.user = :user AND t.recurring.id = :recurringId ORDER BY t.transactionOn DESC, t.id ASC");
        query.setParameter("user", user);
        query.setParameter("recurringId", recurringId);
        return list(query);
    }

    public List<Transaction> findByRange(User user, Date start, Date end) {



        Query query = currentSession().createQuery("FROM Transaction t WHERE t.budget.user = :user AND t.transactionOn BETWEEN :start AND :end ORDER BY t.transactionOn DESC, t.id ASC");
        query
                .setParameter("user", user)
                .setParameter("start", start)
                .setParameter("end", end);

        return list(query);
    }

    public List<Transaction> findTransactions(User user, SearchFilter filter) {
        Criteria criteria = defaultCriteria();
        criteria.createAlias("t.budget", "io/smartbudget");

        criteria.add(Restrictions.eq("budget.user", user));

        if(filter.isAmountRange()) {
            criteria.add(Restrictions.between("amount", filter.getMinAmount(), filter.getMaxAmount()));
        } else if(filter.getMinAmount() != null) {
            criteria.add(Restrictions.ge("amount", filter.getMinAmount()));
        } else if(filter.getMaxAmount() != null) {
            criteria.add(Restrictions.le("amount", filter.getMaxAmount()));
        }

        if(filter.isDateRange()) {
            criteria.add(Restrictions.between("transactionOn", filter.getStartOn(), filter.getEndOn()));
        } else if(filter.getStartOn() != null) {
            criteria.add(Restrictions.ge("transactionOn", filter.getStartOn()));
        } else if(filter.getEndOn() != null) {
            criteria.add(Restrictions.le("transactionOn", filter.getEndOn()));
        }

        if(Boolean.TRUE.equals(filter.getAuto())) {
            criteria.add(Restrictions.eq("auto", Boolean.TRUE));
        }

        return list(criteria);
    }


    private Criteria defaultCriteria() {
        Criteria criteria = currentSession().createCriteria(Transaction.class, "t");
        criteria.addOrder(Order.desc("transactionOn"));
        criteria.addOrder(Order.desc("id"));
        return criteria;
    }

    public void delete(Transaction transaction) {
        currentSession().delete(transaction);
    }
}
