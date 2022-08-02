package br.com.credsystem.rest.response;

import br.com.credsystem.builder.ClientBuilder;
import br.com.credsystem.builder.ClientResponseBuilder;
import br.com.credsystem.model.Client;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ClientResponse {

    @ApiModelProperty(value = "Id do Cliente")
    private Integer id;

    @ApiModelProperty(value = "Documento do cliente")
    private String documentNumber;

    @ApiModelProperty(value = "Salário do cliente")
    private Double salary;

    @ApiModelProperty(value = "Data de criação do cliente")
    private Date createdAt;

    @ApiModelProperty(value = "Cartões do cliente")
    private List<CardResponse> cards;

    public ClientResponse(Integer id, String documentNumber, Double salary, Date createdAt, List<CardResponse> cards) {
        this.id = id;
        this.documentNumber = documentNumber;
        this.salary = salary;
        this.createdAt = createdAt;
        this.cards = cards;
    }

    public static ClientResponse parse(Client client, boolean load) {
        return new ClientResponseBuilder()
                .with($client -> {
                    $client.id = client.getId();
                    $client.documentNumber = client.getDocumentNumber();
                    $client.salary = client.getSalary();
                    $client.createdAt = client.getCreatedAt();
                    if (load && client.getCards() != null && !client.getCards().isEmpty()) {
                        $client.cards = client.getCards()
                                .stream()
                                .map(c -> CardResponse.parse(c, false))
                                .collect(Collectors.toList());
                    }
                }).build();
    }

    public static Client parse(ClientResponse client, boolean load) {
        return new ClientBuilder()
                .with($client -> {
                    $client.id = client.getId();
                    $client.documentNumber = client.getDocumentNumber();
                    $client.salary = client.getSalary();
                    $client.createdAt = client.getCreatedAt();
                    if (load && client.getCards() != null && !client.getCards().isEmpty()) {
                        $client.cards = client.getCards()
                                .stream()
                                .map(c -> CardResponse.parse(c, false))
                                .collect(Collectors.toList());
                    }
                }).build();
    }

}
