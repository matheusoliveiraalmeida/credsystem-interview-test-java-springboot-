package br.com.credsystem.exception;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(String exception) {
        super(exception);
    }

}
