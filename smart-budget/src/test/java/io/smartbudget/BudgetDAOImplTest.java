package io.smartbudget;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import io.smartbudget.HSQLDatabaseConnectionProvider;
import io.smartbudget.domain.enums.CategoryType;
import io.smartbudget.ejb.dao.impl.BudgetDAOImpl;
import io.smartbudget.ejb.dao.impl.CategoryDAOImpl;
import io.smartbudget.ejb.dao.impl.UserDAOImpl;
import io.smartbudget.persistence.entity.Budget;
import io.smartbudget.persistence.entity.Category;
import io.smartbudget.persistence.entity.User;
import io.smartbudget.util.Util;

import static io.smartbudget.util.Util.randomAlphabets;
import static io.smartbudget.util.Util.randomEmail;

public class BudgetDAOImplTest {
    private static Reader reader;

    private static SqlSessionFactory sqlSessionFactory;

    private static User defaultUser;
    private static Category defaultCategory;
    private static Budget defaultBudget;

    private static UserDAOImpl userDAO;
    private static CategoryDAOImpl categoryDAO;
    private static BudgetDAOImpl budgetDAO;


    @BeforeClass
    public static void setup() {
        HSQLDatabaseConnectionProvider db = new HSQLDatabaseConnectionProvider();
        db.setUp("schemedata");

        try {
            reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        userDAO = new UserDAOImpl(sqlSessionFactory);
        categoryDAO = new CategoryDAOImpl(sqlSessionFactory);
        budgetDAO = new BudgetDAOImpl(sqlSessionFactory);

        defaultUser = new User();
        defaultUser.setId(Long.valueOf(1L));
        defaultUser.setUsername(randomEmail());
        defaultUser.setPassword(randomAlphabets());
        defaultUser.setCreatedAt(Util.currentYearMonth());
        defaultUser.setCurrency("USD");

        userDAO.save(defaultUser);

        defaultCategory = new Category();
        defaultCategory.setId(Long.valueOf(1L));
        defaultCategory.setName(randomAlphabets());
        defaultCategory.setType(CategoryType.EXPENDITURE);
        defaultCategory.setCreatedAt(Timestamp.from(Instant.now()));
        defaultCategory.setUser(defaultUser);

        categoryDAO.addCategory(defaultUser, defaultCategory);

        defaultBudget = new Budget();
        defaultBudget.setName(randomAlphabets());
        defaultBudget.setPeriodOn(Util.currentYearMonth());
        defaultBudget.setCreatedAt(Timestamp.from(Instant.now()));
        defaultBudget.setCategory(defaultCategory);

        budgetDAO.addBudget(defaultUser, defaultBudget);
    }

    @Test
    public void shouldAbleCreateBudget() {
        budgetDAO.addBudget(defaultUser, defaultBudget);

        Assert.assertNotNull(defaultUser);
        Assert.assertNotNull(defaultBudget);
        Assert.assertEquals(defaultBudget.getId(),
                budgetDAO.findById(defaultUser, defaultBudget.getId()).getId());
    }

    @Test
    public void shouldBeAbleUpdateBudget() {
        defaultBudget.setName("Test");
        defaultBudget.setProjected(1000);
        defaultBudget.setCategory(defaultCategory);

        budgetDAO.merge(defaultBudget);

        Assert.assertNotNull(defaultBudget);
        Assert.assertEquals("Test", defaultBudget.getName());
        Assert.assertEquals(1000, budgetDAO.findById(defaultBudget.getId()).getProjected(), 0.000);
    }
}
