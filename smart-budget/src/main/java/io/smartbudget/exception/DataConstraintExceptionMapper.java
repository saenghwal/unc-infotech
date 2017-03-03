package io.smartbudget.exception;

import java.util.Collections;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataConstraintExceptionMapper implements ExceptionMapper<DataConstraintException> {

    @Override
    public Response toResponse(DataConstraintException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(Collections.singletonMap("errors", Collections.singletonMap(e.getPath(), Collections.singletonList(e.getMessage())))).build();
    }
}
