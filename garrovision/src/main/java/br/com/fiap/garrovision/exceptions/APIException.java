package br.com.fiap.garrovision.exceptions;

public class APIException extends RuntimeException {

    // Construtor que aceita apenas uma mensagem de erro
    public APIException(String message) {
        super(message);
    }

    // Construtor que aceita uma mensagem de erro e a causa (exceção original)
    public APIException(String message, Throwable cause) {
        super(message, cause);
    }
}
