package br.com.credsystem.builder;

import br.com.credsystem.model.Card;
import br.com.credsystem.model.Client;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class ClientBuilder {

    public Integer id;
    public String documentNumber;
    public Double salary;
    public Date createdAt;
    public List<Card> cards;

    public ClientBuilder with(Consumer<ClientBuilder> builder) {
        builder.accept(this);
        return this;
    }

    public Client build() {
        return new Client(id, documentNumber, salary, createdAt, cards);
    }

}
