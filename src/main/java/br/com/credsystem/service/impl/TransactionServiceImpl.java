package br.com.credsystem.service.impl;

import br.com.credsystem.builder.TransactionBuilder;
import br.com.credsystem.exception.InsufficientFundsException;
import br.com.credsystem.exception.PasswordNotMatchException;
import br.com.credsystem.model.Transaction;
import br.com.credsystem.repository.TransactionRepository;
import br.com.credsystem.rest.request.TransactionRequest;
import br.com.credsystem.rest.response.CardResponse;
import br.com.credsystem.service.CardService;
import br.com.credsystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static java.util.Objects.nonNull;

@Component
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CardService cardService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, CardService cardService) {
        this.transactionRepository = transactionRepository;
        this.cardService = cardService;
    }

    @Override
    public void generate(TransactionRequest request) {
        CardResponse card = cardService.searchByCardNumber(request.getNumber());
        if (nonNull(card) && hasBalance(request, card) && samePassword(request, card)) {
            card.setAvailableLimit(card.getAvailableLimit() - request.getTransactionValue());
            request.setAuthorized(true);
            cardService.updateLimit(card.getId(), card.getAvailableLimit());
            transactionRepository.save(create(request, card.getId()));
        }
    }

    private Transaction create(TransactionRequest request, Integer cardId) {
        return new TransactionBuilder()
                .with($transaction -> {
                    $transaction.transactionValue = request.getTransactionValue();
                    $transaction.authorized = request.isAuthorized();
                    $transaction.createdAt = new Date();
                    $transaction.cardId = cardId;
                }).build();
    }

    private boolean hasBalance(TransactionRequest request, CardResponse card) {
        boolean hasBalance = request.getTransactionValue() <= card.getAvailableLimit();
        if (!hasBalance) {
            throw new InsufficientFundsException("Saldo insuficiente.");
        }
        return true;
    }

    private boolean samePassword(TransactionRequest request, CardResponse card) {
        boolean samePassword = request.getPassword().equals(card.getPassword());
        if (!samePassword) {
            throw new PasswordNotMatchException("Senha inválida.");
        }
        return true;
    }

}
