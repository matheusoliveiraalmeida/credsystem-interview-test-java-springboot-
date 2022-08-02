package br.com.credsystem.service;

import br.com.credsystem.rest.request.CardRequest;
import br.com.credsystem.rest.response.CardResponse;

import java.util.List;

public interface CardService {

    List<CardResponse> listAll();

    void generate(CardRequest request);

    void cancel(Integer id);

    CardResponse searchById(Integer id);

    CardResponse searchByCardNumber(String cardNumber);

    void updateLimit(Integer id, Double availableLimit);

}
