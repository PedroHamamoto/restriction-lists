package br.com.hamamoto.infrastructure.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DomainExceptionHandler implements ExceptionMapper<DomainException> {


    @Override
    public Response toResponse(DomainException e) {
        var errorResource = new ErrorResponse(e.getCode(), e.getMessage());

        return Response.status(e.getHttpResponseStatus().code())
                .entity(errorResource)
                .build();
    }
}
