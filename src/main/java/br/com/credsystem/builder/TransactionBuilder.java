package br.com.credsystem.builder;

import br.com.credsystem.model.Transaction;

import java.util.Date;
import java.util.function.Consumer;

public class TransactionBuilder {

    public Integer id;
    public Double transactionValue;
    public boolean authorized;
    public Date createdAt;
    public Integer cardId;

    public TransactionBuilder with(Consumer<TransactionBuilder> builder) {
        builder.accept(this);
        return this;
    }

    public Transaction build() {
        return new Transaction(id, transactionValue, authorized, cardId, createdAt);
    }

}
