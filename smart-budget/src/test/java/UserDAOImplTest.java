import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

import io.smartbudget.HSQLDatabaseConnectionProvider;
import io.smartbudget.ejb.dao.impl.UserDAOImpl;
import io.smartbudget.persistence.entity.User;
import io.smartbudget.util.Util;

import static io.smartbudget.util.Util.randomAlphabets;
import static io.smartbudget.util.Util.randomEmail;

public class UserDAOImplTest{

    private static Reader reader;

    private static SqlSessionFactory sqlSessionFactory;

    private static User defaultUser;

    private static UserDAOImpl userDAO;

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

        defaultUser = new User();

        defaultUser.setUsername(randomEmail());
        defaultUser.setPassword(randomAlphabets());
        defaultUser.setCreatedAt(Util.currentYearMonth());

        userDAO.save(defaultUser);
    }

    @Test
    public void shouldAbleCreateUser() {
        defaultUser = new User();

        defaultUser.setUsername(randomEmail());
        defaultUser.setPassword(randomAlphabets());
        defaultUser.setCreatedAt(Util.currentYearMonth());

        userDAO.save(defaultUser);

        Assert.assertNotNull(defaultUser);

        Assert.assertEquals(defaultUser.getUsername(),
                userDAO.findByUsername(defaultUser.getUsername()).getUsername());
    }

    @Test
    public void shouldAbleChangePassword() {
        String newPassword = Util.randomAlphabets();

        defaultUser.setPassword(newPassword);

        userDAO.merge(defaultUser);

        Assert.assertNotNull(defaultUser);
        Assert.assertEquals(newPassword, userDAO.findByUsername(defaultUser.getUsername()).getPassword());
    }

    @Test
    public void shouldAbleChangeName() {
        String newName = Util.randomAlphabets();

        defaultUser.setName(newName);

        userDAO.merge(defaultUser);

        Assert.assertNotNull(defaultUser);
        Assert.assertEquals(newName, userDAO.findByUsername(defaultUser.getUsername()).getName());
    }

    @Test
    public void shouldAbleChangeCurrency() {
        String newCurrency = Util.randomAlphabets();

        defaultUser.setCurrency(newCurrency);

        userDAO.merge(defaultUser);

        Assert.assertNotNull(defaultUser);
        System.out.println(defaultUser.getId());
        Assert.assertEquals(newCurrency, userDAO.findById(defaultUser.getId()).getCurrency());
    }
}
