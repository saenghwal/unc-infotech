package io.smartbudget;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import io.smartbudget.domain.enums.CategoryType;
import io.smartbudget.ejb.dao.impl.BudgetDAOImpl;
import io.smartbudget.ejb.dao.impl.CategoryDAOImpl;
import io.smartbudget.ejb.dao.impl.UserDAOImpl;
import io.smartbudget.persistence.entity.Budget;
import io.smartbudget.persistence.entity.Category;
import io.smartbudget.persistence.entity.User;
import io.smartbudget.util.Util;

public class UserDaoImplTest {
    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = null;
        Reader reader = null;

        SqlSession session = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

            UserDAOImpl userDAO = new UserDAOImpl(sqlSessionFactory);
            User user = userDAO.findByUsername("frienheint89@i.ua");
            System.out.println(user);
            user = userDAO.findById(17L);
            System.out.println(user);

            BudgetDAOImpl budgetDAO = new BudgetDAOImpl(sqlSessionFactory);
            List<Budget> foundedBudgets = budgetDAO.findBudgets(user);
            System.out.println(foundedBudgets.get(0));
            user.setName("Vitaliia");
            user.setCurrency("RUB");
            userDAO.merge(user);

            System.out.println(budgetDAO.findById(82L));
//            System.out.println(Util.toDate("2014-08-19"));

            Budget newBudget = new Budget();
            newBudget.setName("Pills");
            newBudget.setProjected(1000);
            newBudget.setActual(500);
            LocalDate now = LocalDate.now();
            int month = now.getMonthValue();
            int year = now.getYear();
            Date period = Util.yearMonthDate(month, year);
            newBudget.setPeriodOn(period);
            newBudget.setCreatedAt(Util.toDate(now));
            newBudget.setUser(user);
            Category newCategory = new Category(3L);
            newCategory.setName("Transportation");
            newCategory.setType(CategoryType.EXPENDITURE);
            newCategory.setCreatedAt(Timestamp.from(Instant.now()));
            newBudget.setCategory(newCategory);
            budgetDAO.addBudget(user, newBudget);

            System.out.println(budgetDAO.findLatestBudget(user));
            System.out.println(budgetDAO.findByUserAndCategory(user, 3L).size());
            System.out.println(budgetDAO.findByRange(user, 2, 2017, 2, 2017).size());

            newBudget.setActual(100);
            budgetDAO.merge(newBudget);

            budgetDAO.delete(newBudget);

            System.out.println(budgetDAO.findSuggestions(user, "Inter"));

            CategoryDAOImpl categoryDAO = new CategoryDAOImpl(sqlSessionFactory);
            System.out.println(categoryDAO.findSuggestions(user, "me"));

            newCategory = new Category();
            newCategory.setName("XYZ");
            newCategory.setType(CategoryType.EXPENDITURE);
            newCategory.setCreatedAt(Timestamp.from(Instant.now()));
            categoryDAO.addCategory(user, newCategory);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
