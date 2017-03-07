package io.smartbudget.ejb.persistence.dao;

import java.util.Optional;

import io.smartbudget.domain.dto.User;
import io.smartbudget.form.SignUpForm;

public interface UserDAO  extends GenericDAO<User, Long> {

    Optional<User> findByUsername(String username);

    User add(SignUpForm signUp);
}
