package br.com.credsystem.rest.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {

    @ApiModelProperty(value = "Número do cartão")
    private String number;

    @ApiModelProperty(value = "Senha do cartão")
    private String password;

    @ApiModelProperty(value = "Valor da Transação")
    private Double transactionValue;

    @ApiModelProperty(value = "Autorização da Transação", hidden = true)
    private boolean authorized;

}
