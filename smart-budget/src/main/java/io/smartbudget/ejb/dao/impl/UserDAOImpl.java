package io.smartbudget.ejb.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import io.smartbudget.ejb.dao.UserDAO;
import io.smartbudget.persistence.entity.User;
import io.smartbudget.persistence.mappers.UsersMapper;
import io.smartbudget.exception.NotFoundException;
import io.smartbudget.form.SignUpForm;

@Stateless
@Local
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    private SqlSessionFactory sessionFactory;

    @Inject
    public UserDAOImpl(UsersMapper mapper, SqlSessionFactory sessionFactory) {
        super(mapper);
        this.sessionFactory = sessionFactory;
    }

    public UserDAOImpl(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserDAOImpl() {
    }

    public User add(SignUpForm signUp) {
        LOGGER.debug("Add new user {}", signUp);
        User user = new User();
        user.setUsername(signUp.getUsername());
        user.setPassword(signUp.getPassword());
        try (SqlSession session = sessionFactory.openSession()) {
            UsersMapper users = session.getMapper(UsersMapper.class);
            users.addUser(signUp);
        }
        return user;
    }
    @Override
    public Optional<User> findByUsername(String username) {
        return ((UsersMapper) mapper).findByUserName(username);
    }

    @Override
    public User findById(Long userId) {
        User user = ((UsersMapper) mapper).findById(userId);
        if(user == null) {
            throw new NotFoundException();
        }
        return user;
    }
}
