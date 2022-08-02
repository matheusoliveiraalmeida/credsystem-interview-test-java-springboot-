package br.com.credsystem.rest.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClientRequest {

    @ApiModelProperty(value = "Id do Cliente")
    private Integer id;

    @CPF(message = "Documento inválido")
    @ApiModelProperty(value = "Documento do Cliente")
    private String documentNumber;

    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "Salário do Cliente")
    private Double salary;

}
