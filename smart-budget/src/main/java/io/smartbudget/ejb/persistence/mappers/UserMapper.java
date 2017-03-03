package io.smartbudget.ejb.persistence.mappers;


import java.util.Optional;

import io.smartbudget.ejb.persistence.entity.User;

public interface UserMapper extends GenericMapper<User, Long> {

    void addUser(User user);

    User findById(Long userId);

    Optional<User> findByUserName(String username);

    void update(User user);
}
