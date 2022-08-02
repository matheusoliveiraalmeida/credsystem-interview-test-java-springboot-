package br.com.credsystem.exception;

public class CardNotFoundException extends RuntimeException {

    public CardNotFoundException(String exception) {
        super(exception);
    }

}
