package smartbudget;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

import io.smartbudget.persistence.entity.User;
import io.smartbudget.persistence.mappers.UsersMapper;

public class UserDaoImplTest {
    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory;
        UsersMapper subscriberMapper;
        Reader reader = null;

        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            subscriberMapper = sqlSessionFactory.openSession().getMapper(UsersMapper.class);
            Optional<User> users = Optional.ofNullable(subscriberMapper.findById(Long.valueOf(1L)));
            System.out.println(users.get());

            User user = new User();
            user.setUsername("frienheint89@yandex.ru");
            user.setPassword("6a4e7bca36d4f95feb5ba517abbe42898b14781fc3ea30af4276287ab87348fcd1948b83760b507b");
            subscriberMapper.addUser(user);
            Optional<User> newUser = Optional.ofNullable(subscriberMapper.findById(Long.valueOf(2L)));
            System.out.println(newUser.get());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
