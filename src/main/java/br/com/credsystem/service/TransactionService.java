package br.com.credsystem.service;

import br.com.credsystem.rest.request.TransactionRequest;

public interface TransactionService {

    void generate(TransactionRequest request);

}
