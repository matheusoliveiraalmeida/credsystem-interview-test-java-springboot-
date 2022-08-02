package br.com.credsystem.builder;

import br.com.credsystem.enums.CardStatus;
import br.com.credsystem.rest.response.CardResponse;
import br.com.credsystem.rest.response.ClientResponse;

import java.util.Date;
import java.util.function.Consumer;

public class CardResponseBuilder {

    public Integer id;
    public Double availableLimit;
    public Double totalLimit;
    public String number;
    public String password;
    public CardStatus cardStatus;
    public Date createdAt;
    public ClientResponse client;

    public CardResponseBuilder with(Consumer<CardResponseBuilder> builder) {
        builder.accept(this);
        return this;
    }

    public CardResponse build() {
        return new CardResponse(id, availableLimit, totalLimit, number, password, cardStatus, createdAt, client);
    }

}
