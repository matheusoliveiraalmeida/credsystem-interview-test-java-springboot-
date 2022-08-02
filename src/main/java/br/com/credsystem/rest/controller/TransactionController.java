package br.com.credsystem.rest.controller;

import br.com.credsystem.rest.request.TransactionRequest;
import br.com.credsystem.rest.resource.TransactionResource;
import br.com.credsystem.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController implements TransactionResource {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @Override
    public ResponseEntity<?> generate(TransactionRequest request) {
        transactionService.generate(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
