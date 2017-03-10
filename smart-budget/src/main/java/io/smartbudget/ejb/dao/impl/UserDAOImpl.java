package io.smartbudget.ejb.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Stateful;

import io.smartbudget.ejb.dao.UserDAO;
import io.smartbudget.persistence.entity.User;
import io.smartbudget.persistence.mappers.UsersMapper;
import io.smartbudget.exception.NotFoundException;

@Stateful
@Local
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    public UserDAOImpl(SqlSessionFactory sessionFactory) {
        super(sessionFactory.openSession().getMapper(UsersMapper.class));
    }

    public UserDAOImpl() {
    }

    @PostConstruct
    public void initialize() {
        // Initialize here objects which will be used
        // by the session bean
        LOGGER.debug("UserDAOImpl initialized.");
    }

    @PreDestroy
    public void destroyBean() {
        // Free here session bean resources
        LOGGER.debug("UserDAOImpl destroyed.");
    }

    @Override
    public User findById(Long userId) {
        User user = ((UsersMapper) mapper).findById(userId);
        if(user == null) {
            throw new NotFoundException();
        }
        return user;
    }

    @Override
    public void save(User user) {
        LOGGER.debug("Add new user {}", user);
        mapper.save(user);

        //or
        //try (SqlSessionFactory session = sessionFactory.openSession()) {
            //session.insert("User.save", user);
        //}
    }

    @Override
    public void merge(User user) {
        LOGGER.debug("Update user {}", user);
        mapper.merge(user);

        //or
        //SqlSession session = sessionFactory.openSession();
        //session.update("User.merge",user);
    }


    @Override
    public User findByUsername(String username) {
        return ((UsersMapper) mapper).findByUserName(username);
    }

}
