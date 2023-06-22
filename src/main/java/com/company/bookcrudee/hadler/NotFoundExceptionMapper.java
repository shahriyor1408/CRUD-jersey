package com.company.bookcrudee.hadler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 11/06/23 16:32
 * book-crud-ee
 */

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    public static class Error {
        public String key;
        public String message;
    }

    @Override
    public Response toResponse(NotFoundException exception) {
        Error error = new Error();
        error.key = "bad-json";
        error.message = exception.getMessage();
        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }
}
