package com.zezu.labseq.api.error;


import com.zezu.labseq.exception.InvalidNumberException;
import com.zezu.labseq.exception.NumberTooLargeException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NumberTooLargeExceptionMapper implements ExceptionMapper<NumberTooLargeException> {

    @Override
    public Response toResponse(NumberTooLargeException ex) {
        ApiError body = new ApiError("NUMBER_TOO_LARGE", ex.getMessage(), ex.getN(), ex.getMaxN());
        return Response.status(Response.Status.REQUEST_ENTITY_TOO_LARGE)
                .type(MediaType.APPLICATION_JSON)
                .entity(body)
                .build();
    }
}
