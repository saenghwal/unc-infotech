package io.smartbudget.persistence.mappers;

import io.smartbudget.persistence.entity.User;

public interface UsersMapper extends GenericMapper<User, Long> {

    User findByUserName(String username);
}
