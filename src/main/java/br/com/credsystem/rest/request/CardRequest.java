package br.com.credsystem.rest.request;

import br.com.credsystem.enums.CardStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardRequest {

    @ApiModelProperty(value = "Número do cartão", hidden = true)
    private String number;

    @ApiModelProperty(value = "Senha do cartão", hidden = true)
    private String password;

    @ApiModelProperty(value = "Limite disponível do cartão", hidden = true)
    private Double availableLimit;

    @ApiModelProperty(value = "Limite total do cartão", hidden = true)
    private Double totalLimit;

    @ApiModelProperty(value = "Status do cartão", hidden = true)
    private CardStatus cardStatus;

    @ApiModelProperty(value = "Id do Cliente")
    private Integer clientId;

}
