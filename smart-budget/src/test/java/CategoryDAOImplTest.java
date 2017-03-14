import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.time.Instant;

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

public class CategoryDAOImplTest {
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

        categoryDAO.addCategory(defaultUser, defaultCategory);
    }

    @Test
    public void shouldAbleCreateCategory() {
        categoryDAO.addCategory(defaultUser, defaultCategory);

        Assert.assertNotNull(defaultUser);
        Assert.assertNotNull(defaultCategory);

        Assert.assertEquals(defaultCategory.getId(),
                categoryDAO.findById(defaultCategory.getId()).getId());
    }

    @Test
    public void shouldAbleFindValidCategory() {

    }

    @Test
    public void shouldNotAbleDeleteCategoryWithChild() {
        
    }
}
