package br.com.credsystem.rest.resource;

import br.com.credsystem.rest.request.CardRequest;
import br.com.credsystem.rest.response.CardResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface CardResource {

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de cartãos"),
            @ApiResponse(code = 404, message = "Cartão não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @ApiOperation(value = "Retorna uma lista de cartões gerados")
    ResponseEntity<List<CardResponse>> listAll();

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cartão gerado com sucesso"),
            @ApiResponse(code = 500, message = "Problemas na hora de gerar")
    })
    @ApiOperation(value = "Gera um cartão")
    ResponseEntity<?> generate(CardRequest request) throws NoSuchAlgorithmException;

    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Não retorna nada"),
            @ApiResponse(code = 404, message = "Cartão não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @ApiOperation(value = "Cancela um cartão")
    ResponseEntity<Void> cancel(Integer id);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o cartão"),
            @ApiResponse(code = 500, message = "Cartão não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @ApiOperation(value = "Busca um cartão através do seu ID")
    ResponseEntity<CardResponse> searchById(Integer id);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o cartão"),
            @ApiResponse(code = 500, message = "Cartão não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @ApiOperation(value = "Busca um cartão através do seu número")
    ResponseEntity<CardResponse> searchByCardNumber(String cardNumber);
    
}
