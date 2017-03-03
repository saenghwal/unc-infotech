package io.smartbudget.ejb.persistence.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;

import io.smartbudget.ejb.persistence.dao.UserDAO;
import io.smartbudget.ejb.persistence.entity.User;
import io.smartbudget.ejb.persistence.mappers.UserMapper;
import io.smartbudget.exception.NotFoundException;
import io.smartbudget.form.SignUpForm;

@Stateless
@Local
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    @Inject
    public UserDAOImpl(UserMapper mapper) {
        super(mapper);
    }

    public UserDAOImpl() {
    }

    public User add(SignUpForm signUp) {
        LOGGER.debug("Add new user {}", signUp);
        User user = new User();
        user.setUsername(signUp.getUsername());
        user.setPassword(signUp.getPassword());
        //user = persist(user);
        return user;
    }
    @Override
    public Optional<User> findByUsername(String username) {
        return ((UserMapper) mapper).findByUserName(username);
    }

    @Override
    public User findById(Long userId) {
        User user = ((UserMapper) mapper).findById(userId);
        if(user == null) {
            throw new NotFoundException();
        }
        return user;
    }
}
