package br.com.fiap.garrovision.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        if (exception instanceof APIException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(exception.getMessage())
                    .build();
        } else if (exception instanceof ValidationException) {
            return Response.status(Response.Status.NOT_IMPLEMENTED)
                    .entity(exception.getMessage())
                    .build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Ocorreu um erro inesperado: " + exception.getMessage())
                .build();
    }
}
