import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

import io.smartbudget.HSQLDatabaseConnectionProvider;
import io.smartbudget.ejb.dao.impl.UserDAOImpl;
import io.smartbudget.persistence.entity.User;
import io.smartbudget.util.Util;

public class UserDAOImplTest {

    public static Reader reader;

    public static SqlSessionFactory sqlSessionFactory;

    @Before
    public void setup() {
        HSQLDatabaseConnectionProvider db = new HSQLDatabaseConnectionProvider();
        db.setUp("schemedata");

        try {
            reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    @Test
    public void shouldAbleCreateUser() {
        UserDAOImpl userDAO = new UserDAOImpl(sqlSessionFactory);
        User newUser = new User();

        newUser.setUsername("frienheint89@yahoo.com");
        //newUser.setName("Vitaliia");
        newUser.setPassword("6a4e7bca36d4f95feb5ba517abbe42898b14781fc3ea30af4276287ab87348fcd1948b83760b507b");
        newUser.setCreatedAt(Util.currentYearMonth());
        //newUser.setCurrency("USD");
        userDAO.save(newUser);

        Assert.assertNotNull(newUser);
        Assert.assertEquals(userDAO.findById(newUser.getId()).getId(), newUser.getId());
    }

}
