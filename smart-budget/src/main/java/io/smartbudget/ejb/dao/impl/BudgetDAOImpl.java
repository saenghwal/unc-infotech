package io.smartbudget.ejb.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateful;

import io.smartbudget.ejb.dao.BudgetDAO;
import io.smartbudget.persistence.entity.Budget;
import io.smartbudget.persistence.entity.User;
import io.smartbudget.persistence.mappers.BudgetsMapper;
import io.smartbudget.util.Util;

@Stateful
@Local
public class BudgetDAOImpl extends GenericDAOImpl<Budget, Long> implements BudgetDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(io.smartbudget.hibernate.dao.BudgetDAO.class);

    public BudgetDAOImpl(SqlSessionFactory sessionFactory) {
        super(sessionFactory.openSession().getMapper(BudgetsMapper.class));
    }

    public BudgetDAOImpl() {
    }

    @Override
    public List<Budget> findBudgets(User user) {
        LocalDate now = LocalDate.now();
        return findBudgets(user, now.getMonthValue(), now.getYear(), false);
    }

    /**
     * find budgets for a given user for given month-year
     * @param user
     * @param month
     * @param year
     * @param lazy
     * @return
     */
    public List<Budget> findBudgets(User user, int month, int year, boolean lazy) {
        LOGGER.debug("Find budgets by user {} by date {}-{}", user, month, year);
        Date yearMonth = Util.yearMonthDate(month, year);
        return ((BudgetsMapper) mapper).findBudgets(user);
    }


    
}
