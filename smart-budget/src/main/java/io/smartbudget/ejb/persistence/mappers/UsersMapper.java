package io.smartbudget.ejb.persistence.mappers;


import java.util.Optional;

import io.smartbudget.domain.dto.User;
import io.smartbudget.form.SignUpForm;

public interface UsersMapper extends GenericMapper<User, Long> {

    void addUser(SignUpForm signUp);

    User findById(Long userId);

    Optional<User> findByUserName(String username);

    void update(User user);
}
