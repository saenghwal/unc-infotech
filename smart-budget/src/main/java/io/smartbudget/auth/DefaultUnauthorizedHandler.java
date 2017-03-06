package io.smartbudget.auth;

import java.util.Collections;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.auth.UnauthorizedHandler;
import io.smartbudget.util.ValidationMessage;


public class DefaultUnauthorizedHandler implements UnauthorizedHandler {

    private static final String CHALLENGE_FORMAT = "%s realm=\"%s\"";
    private static final ValidationMessage VALIDATION_MESSAGE = new ValidationMessage("error", Collections.singleton("Credentials are required to access this resource"));

    @Override
    public Response buildResponse(String prefix, String realm) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .header(HttpHeaders.AUTHORIZATION, String.format(CHALLENGE_FORMAT, prefix, realm))
                .entity(VALIDATION_MESSAGE)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
