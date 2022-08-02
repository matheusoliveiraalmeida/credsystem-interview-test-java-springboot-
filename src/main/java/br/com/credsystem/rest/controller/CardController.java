package br.com.credsystem.rest.controller;

import br.com.credsystem.rest.request.CardRequest;
import br.com.credsystem.rest.resource.CardResource;
import br.com.credsystem.rest.response.CardResponse;
import br.com.credsystem.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController implements CardResource {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<CardResponse>> listAll() {
        return ResponseEntity.ok(cardService.listAll());
    }

    @PostMapping
    @Override
    public ResponseEntity<?> generate(CardRequest request) {
        cardService.generate(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/cancel/{id}")
    @Override
    public ResponseEntity<Void> cancel(Integer id) {
        cardService.cancel(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    @Override
    public ResponseEntity<CardResponse> searchById(Integer id) {
        return ResponseEntity.ok(cardService.searchById(id));
    }

    @GetMapping("{cardNumber}")
    @Override
    public ResponseEntity<CardResponse> searchByCardNumber(String cardNumber) {
        return ResponseEntity.ok(cardService.searchByCardNumber(cardNumber));
    }

}
