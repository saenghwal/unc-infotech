package io.smartbudget.ejb.dao;

import java.util.Optional;

import io.smartbudget.persistence.entity.User;
import io.smartbudget.form.SignUpForm;

public interface UserDAO  extends GenericDAO<User, Long> {

    Optional<User> findByUsername(String username);

    User addUser(User user);
}
