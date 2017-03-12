package io.smartbudget.ejb.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.ejb.Local;
import javax.ejb.Stateful;

import io.smartbudget.ejb.dao.BudgetDAO;
import io.smartbudget.exception.AccessDeniedException;
import io.smartbudget.exception.NotFoundException;
import io.smartbudget.persistence.entity.Budget;
import io.smartbudget.persistence.entity.User;
import io.smartbudget.persistence.mappers.BudgetsMapper;
import io.smartbudget.util.Util;

@Stateful
@Local
public class BudgetDAOImpl extends GenericDAOImpl<Budget, Long> implements BudgetDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(BudgetDAOImpl.class);

    public BudgetDAOImpl(SqlSessionFactory sessionFactory) {
        super(sessionFactory.openSession().getMapper(BudgetsMapper.class));
    }

    public BudgetDAOImpl() {
    }

    /**
     * find budgets for a given user for current month-year
     * @param user
     * @return
     */
    public List<Budget> findBudgets(User user) {
        LocalDate now = LocalDate.now();
        return findBudgets(user, now.getMonthValue(), now.getYear());
    }

    /**
     * find budgets for a given user for given month-year
     * @param user
     * @param month
     * @param year
     * @return
     */
    @Override
    public List<Budget> findBudgets(User user, int month, int year) {
        LOGGER.debug("Find budgets by user {} by date {}-{}", user, month, year);
        Date yearMonth = Util.yearMonthDate(month, year);
        return ((BudgetsMapper) mapper).findBudgets(user, yearMonth);
    }

    /**
     * add budget for a given user
     * @param user owner
     * @param budget new budget
     * @return new budget
     */
    public void addBudget(User user, Budget budget) {
        LOGGER.debug("User {} add budget {}", user, budget);
        if(budget.getPeriodOn() == null) {
            budget.setPeriodOn(Util.currentYearMonth());
        }
        budget.setUser(user);
        save(budget);
    }

    @Override
    public void save(Budget budget) {
        mapper.save(budget);
    }

    /**
     * find budget by given id
     * @param budgetId
     * @return
     */
    @Override
    public Budget findById(Long budgetId) {
        Budget budget = (Budget) mapper.findById(budgetId);

        if(budget == null) {
            throw new NotFoundException();
        }
        return budget;
    }

    /**
     * find budget for given user and id
     * @param user
     * @param budgetId
     * @return
     */
    public Budget findById(User user, Long budgetId) {
        Budget budget = findById(budgetId);
        if(!Objects.equals(user.getId(), budget.getUser().getId())) {
            throw new AccessDeniedException();
        }
        return budget;
    }
}
