package io.smartbudget.ejb.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;

import io.smartbudget.ejb.dao.UserDAO;
import io.smartbudget.persistence.entity.User;
import io.smartbudget.persistence.mappers.UsersMapper;
import io.smartbudget.exception.NotFoundException;
import io.smartbudget.form.SignUpForm;

@Stateful
@Local
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    private SqlSessionFactory sessionFactory;

    public UserDAOImpl(UsersMapper mapper, SqlSessionFactory sessionFactory) {
        super(mapper);
        this.sessionFactory = sessionFactory;
        this.mapper = sessionFactory.openSession().getMapper(UsersMapper.class);
    }

    public UserDAOImpl(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserDAOImpl() {
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
    public User addUser(User user) {
        LOGGER.debug("Add new user {}", user);
        try (SqlSession session = sessionFactory.openSession()) {
            session.insert("User.addUser", user);
            //or
            //UsersMapper users = session.getMapper(UsersMapper.class);
            //users.addUser(signUp);
        }
        return user;
    }

    public void update(User user) {
        LOGGER.debug("Update user {}", user);
        SqlSession session = sessionFactory.openSession();
        session.update("User.update",user);
    }


    @Override
    public Optional<User> findByUsername(String username) {
        SqlSession session = sessionFactory.openSession();
        List<User> users = session.selectList("User.findByUsername", username);
        if(users.size() == 1) {
            return Optional.of(users.get(0));
        } else {
            return Optional.empty();
        }
        //return ((UsersMapper) mapper).findByUserName(username);
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

}
