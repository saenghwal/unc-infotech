package smartbudget.resource;


import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.Arrays;

import javax.ws.rs.core.Response;

import io.dropwizard.testing.junit.DropwizardAppRule;
import io.smartbudget.application.BudgetApplication;
import io.smartbudget.configuration.AppConfiguration;
import io.smartbudget.persistence.entity.User;
import io.smartbudget.form.LoginForm;
import io.smartbudget.form.SignUpForm;
import io.smartbudget.form.user.Password;
import io.smartbudget.resource.ResourceURL;
import io.smartbudget.resource.Resources;
import smartbudget.modal.ErrorResponse;

import static org.junit.Assert.assertTrue;

public class UserResourceIT extends ResourceIT {

    @ClassRule
    public static final DropwizardAppRule<AppConfiguration> RULE =
            new DropwizardAppRule<>(BudgetApplication.class, resourceFilePath("config-test.yml"));

    @Override
    protected int getPort() {
        return RULE.getLocalPort();
    }

    @Test
    public void shouldAbleCreateUser() {

        // given
        SignUpForm signUp = new SignUpForm();

        // when
        signUp.setUsername(randomEmail());
        signUp.setPassword(randomAlphabets());
        Response response = post(ResourceURL.USER, signUp);

        // then
        assertCreated(response);
        Assert.assertNotNull(response.getLocation());
    }

    @Test
    public void shouldBeAbleValidateSignUpForm() {
        // given
        SignUpForm signUp = new SignUpForm();

        // when
        Response response = post(ResourceURL.USER, signUp);

        // then
        assertBadRequest(response);
        ErrorResponse errorResponse = response.readEntity(ErrorResponse.class);
        assertTrue(errorResponse.getErrors().keySet().containsAll(Arrays.asList("username", "password")));
    }


    @Test
    public void shouldAbleChangePassword() {
        // given
        Password password = new Password();
        password.setOriginal(defaultUser.getPassword());
        password.setPassword(randomAlphabets());
        password.setConfirm(password.getPassword());

        // when
        put(Resources.CHANGE_PASSWORD, password);
        LoginForm login = new LoginForm();
        login.setUsername(defaultUser.getUsername());
        login.setPassword(password.getPassword());
        Response authResponse = post(Resources.USER_AUTH, login);

        // then
        assertOk(authResponse);
        Assert.assertNotNull(authResponse.readEntity(User.class).getToken());
    }
}
