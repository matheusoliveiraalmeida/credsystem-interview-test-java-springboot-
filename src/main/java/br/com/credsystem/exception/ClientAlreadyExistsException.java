package br.com.credsystem.exception;

public class ClientAlreadyExistsException extends RuntimeException {

    public ClientAlreadyExistsException(String exception) {
        super(exception);
    }

}
