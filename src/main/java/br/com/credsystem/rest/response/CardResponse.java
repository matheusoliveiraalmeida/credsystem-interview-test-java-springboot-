package br.com.credsystem.rest.response;

import br.com.credsystem.builder.CardBuilder;
import br.com.credsystem.builder.CardResponseBuilder;
import br.com.credsystem.enums.CardStatus;
import br.com.credsystem.model.Card;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CardResponse {

    @ApiModelProperty(value = "Id do Cliente")
    private Integer id;

    @ApiModelProperty(value = "Limite disponível do Cartão")
    private Double availableLimit;

    @ApiModelProperty(value = "Limite total do Cartão")
    private Double totalLimit;

    @ApiModelProperty(value = "Número do cartão")
    private String number;

    @ApiModelProperty(value = "Senha do cartão", hidden = true)
    private String password;

    @ApiModelProperty(value = "Status do Cartão")
    private CardStatus cardStatus;

    @ApiModelProperty(value = "Data de criação do Cartão")
    private Date createdAt;

    @ApiModelProperty(value = "Cliente do Cartão")
    private ClientResponse client;

    public CardResponse(Integer id, Double availableLimit, Double totalLimit, String number, String password, CardStatus cardStatus, Date createdAt, ClientResponse client) {
        this.id = id;
        this.availableLimit = availableLimit;
        this.totalLimit = totalLimit;
        this.number = number;
        this.password = password;
        this.cardStatus = cardStatus;
        this.createdAt = createdAt;
        this.client = client;
    }

    public static CardResponse parse(Card card, boolean load) {
        return new CardResponseBuilder()
                .with($client -> {
                    $client.id = card.getId();
                    $client.availableLimit = card.getAvailableLimit();
                    $client.totalLimit = card.getTotalLimit();
                    $client.number = card.getNumber();
                    $client.password = card.getPassword();
                    $client.cardStatus = card.getCardStatus();
                    $client.createdAt = card.getCreatedAt();
                    if (load) {
                        $client.client = ClientResponse.parse(card.getClient(), false);
                    }
                }).build();
    }

    public static Card parse(CardResponse card, boolean load) {
        return new CardBuilder()
                .with($client -> {
                    $client.id = card.getId();
                    $client.availableLimit = card.getAvailableLimit();
                    $client.totalLimit = card.getTotalLimit();
                    $client.number = card.getNumber();
                    $client.password = card.getPassword();
                    $client.cardStatus = card.getCardStatus();
                    $client.createdAt = card.getCreatedAt();
                    if (load) {
                        $client.client = ClientResponse.parse(card.getClient(), false);
                    }
                }).build();
    }

}
