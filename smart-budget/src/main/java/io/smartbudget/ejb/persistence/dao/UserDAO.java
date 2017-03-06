package io.smartbudget.ejb.persistence.dao;

import java.util.Optional;

import io.smartbudget.domain.entity.User;

public interface UserDAO  extends GenericDAO<User, Long> {

    Optional<User> findByUsername(String username);
}
