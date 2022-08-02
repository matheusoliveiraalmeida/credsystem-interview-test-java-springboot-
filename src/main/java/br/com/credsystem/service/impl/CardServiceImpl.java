package br.com.credsystem.service.impl;

import br.com.credsystem.builder.CardBuilder;
import br.com.credsystem.enums.CardStatus;
import br.com.credsystem.exception.CardNotFoundException;
import br.com.credsystem.model.Card;
import br.com.credsystem.repository.CardRepository;
import br.com.credsystem.rest.request.CardRequest;
import br.com.credsystem.rest.response.CardResponse;
import br.com.credsystem.rest.response.ClientResponse;
import br.com.credsystem.service.CardService;
import br.com.credsystem.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static br.com.credsystem.utils.CardUtils.generateCreditCardPassword;
import static br.com.credsystem.utils.CardUtils.maskCreditCardNumber;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static net.andreinc.mockneat.unit.financial.CreditCards.creditCards;

@Component
public class CardServiceImpl implements CardService {

    private final Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);

    public final CardRepository cardRepository;
    public final ClientService clientService;

    private static final Double limitMinimum = 300.00;
    private static final Double limitMaximum = 2000.00;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, ClientService clientService) {
        this.cardRepository = cardRepository;
        this.clientService = clientService;
    }

    @Override
    public List<CardResponse> listAll() {
        List<Card> cards = cardRepository.findAll();
        List<CardResponse> cardsResponse = new ArrayList<>(cards.size());

        cards.forEach(card -> cardsResponse.add(CardResponse.parse(card, true)));

        return cardsResponse;
    }

    @Override
    public void generate(CardRequest request) {
        ClientResponse clientResponse = clientService.searchById(request.getClientId());
        if (nonNull(clientResponse)) {
            Double limit = getLimit(clientResponse.getSalary());
            request.setNumber(maskCreditCardNumber(creditCards().masterCard().get()));
            request.setPassword(generateCreditCardPassword());
            request.setAvailableLimit(limit);
            request.setTotalLimit(limit);
            request.setCardStatus(CardStatus.ACTIVE);
            cardRepository.save(create(request, clientResponse));
        }

    }

    @Override
    public void cancel(Integer id) {
        Optional<Card> card = cardRepository.findById(id);

        if (!card.isPresent()) {
            throw new CardNotFoundException("Cartão não encontrado.");
        }

        card.get().setCardStatus(CardStatus.CANCELLED);

        cardRepository.save(card.get());

        logger.info("Cartão cancelado.");
    }

    @Override
    public CardResponse searchById(Integer id) {
        Optional<Card> card = cardRepository.findById(id);

        if (!card.isPresent()) {
            throw new CardNotFoundException("Cartão não encontrado.");
        }

        return CardResponse.parse(card.get(), true);
    }

    @Override
    public CardResponse searchByCardNumber(String cardNumber) {
        Card card = cardRepository.findCardByNumber(cardNumber);

        if (isNull(card)) {
            throw new CardNotFoundException("Cartão não encontrado.");
        }

        return CardResponse.parse(card, true);
    }

    @Override
    public void updateLimit(Integer id, Double availableLimit) {
        Optional<Card> card = cardRepository.findById(id);

        if (card.isPresent()) {
            card.get().setAvailableLimit(availableLimit);
            cardRepository.save(card.get());
        }
    }

    private Card create(CardRequest request, ClientResponse clientResponse) {
        return new CardBuilder()
                .with($card -> {
                    $card.cardStatus = request.getCardStatus();
                    $card.totalLimit = request.getTotalLimit();
                    $card.availableLimit = request.getAvailableLimit();
                    $card.number = request.getNumber();
                    $card.password = request.getPassword();
                    $card.client = ClientResponse.parse(clientResponse, false);
                    $card.createdAt = new Date();
                }).build();
    }

    private Double getLimit(Double salary) {
        Double limit = (salary / 100) * 30;
        if (limit < limitMinimum) {
            limit = limitMinimum;
        } else if (limit > limitMaximum) {
            limit = limitMaximum;
        }
        return limit;
    }

}
