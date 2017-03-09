package smartbudget;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

import io.smartbudget.ejb.dao.impl.UserDAOImpl;
import io.smartbudget.persistence.entity.User;
import io.smartbudget.persistence.mappers.UsersMapper;

public class UserDaoImplTest {
    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = null;
        UsersMapper userMapper;
        Reader reader = null;

        SqlSession session = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sqlSessionFactory.openSession();
            userMapper = session.getMapper(UsersMapper.class);
            Optional<User> users = Optional.ofNullable(userMapper.findById(Long.valueOf(1L)));
            System.out.println(users.get());

            User user = new User();
            UserDAOImpl userDAO = new UserDAOImpl(userMapper);
            user.setUsername("frienheint89@yandex.ru");
            user.setPassword("6a4e7bca36d4f95feb5ba517abbe42898b14781fc3ea30af4276287ab87348fcd1948b83760b507b");
            //userDAO.addUser(user);

            userMapper.addUser(user);
            session.commit();
            Optional<User> newUser = Optional.ofNullable(userMapper.findById(Long.valueOf(10L)));
            System.out.println(newUser.get());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
