package io.smartbudget.auth;

import java.util.Optional;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.hibernate.UnitOfWork;
import io.smartbudget.domain.entity.User;
import io.smartbudget.service.FinanceService;

public class TokenAuthenticator implements Authenticator<String, User> {

    private final FinanceService financeService;

    public TokenAuthenticator(FinanceService financeService) {
        this.financeService = financeService;
    }

    @UnitOfWork
    @Override
    public Optional<User> authenticate(String token) throws AuthenticationException {
        Optional<User> option = financeService.findUserByToken(token);
        if(option.isPresent()) {
            return Optional.of(option.get());
        } else {
            return Optional.empty();
        }
    }
}
