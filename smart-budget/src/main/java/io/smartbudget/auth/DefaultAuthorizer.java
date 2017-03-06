package io.smartbudget.auth;

import io.dropwizard.auth.Authorizer;
import io.smartbudget.domain.entity.User;

public class DefaultAuthorizer implements Authorizer<User> {
    @Override
    public boolean authorize(User user, String role) {
        return true;
    }
}