package br.com.credsystem.builder;

import br.com.credsystem.enums.CardStatus;
import br.com.credsystem.model.Card;
import br.com.credsystem.model.Client;

import java.util.Date;
import java.util.function.Consumer;

public class CardBuilder {

    public Integer id;
    public Double availableLimit;
    public Double totalLimit;
    public String number;
    public String password;
    public CardStatus cardStatus;
    public Date createdAt;
    public Client client;

    public CardBuilder with(Consumer<CardBuilder> builder) {
        builder.accept(this);
        return this;
    }

    public Card build() {
        return new Card(id, availableLimit, totalLimit, number, password, cardStatus, createdAt, client);
    }

}
