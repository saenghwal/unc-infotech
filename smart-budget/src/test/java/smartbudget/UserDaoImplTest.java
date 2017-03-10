package smartbudget;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

import io.smartbudget.ejb.dao.impl.BudgetDAOImpl;
import io.smartbudget.ejb.dao.impl.UserDAOImpl;
import io.smartbudget.persistence.entity.Budget;
import io.smartbudget.persistence.entity.User;
import io.smartbudget.persistence.mappers.UsersMapper;

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
            System.out.println(foundedBudgets.size());
            System.out.println(foundedBudgets.get(0).getName());
            user.setName("Vitaliia");
            user.setCurrency("RUB");
            userDAO.merge(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
