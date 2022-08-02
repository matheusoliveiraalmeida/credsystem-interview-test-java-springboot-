package br.com.credsystem.exception;

public class PasswordNotMatchException extends RuntimeException {

    public PasswordNotMatchException(String exception) {
        super(exception);
    }

}
