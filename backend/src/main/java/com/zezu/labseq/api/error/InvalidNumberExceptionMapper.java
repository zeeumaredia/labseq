package com.zezu.labseq.api.error;


import com.zezu.labseq.exception.InvalidNumberException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
@Provider
public class InvalidNumberExceptionMapper implements ExceptionMapper<InvalidNumberException> {

    @Override
    public Response toResponse(InvalidNumberException ex) {
        ApiError body = new ApiError("INVALID_NUMBER", ex.getMessage(), ex.getN(), null);
        return Response.status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON)
                .entity(body)
                .build();
    }
}
