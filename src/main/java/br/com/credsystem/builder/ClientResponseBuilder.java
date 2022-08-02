package br.com.credsystem.builder;

import br.com.credsystem.rest.response.CardResponse;
import br.com.credsystem.rest.response.ClientResponse;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class ClientResponseBuilder {

    public Integer id;
    public String documentNumber;
    public Double salary;
    public Date createdAt;
    public List<CardResponse> cards;

    public ClientResponseBuilder with(Consumer<ClientResponseBuilder> builder) {
        builder.accept(this);
        return this;
    }

    public ClientResponse build() {
        return new ClientResponse(id, documentNumber, salary, createdAt, cards);
    }

}
