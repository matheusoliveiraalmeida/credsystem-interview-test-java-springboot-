package br.com.credsystem.rest.resource;

import br.com.credsystem.rest.request.ClientRequest;
import br.com.credsystem.rest.response.ClientResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientResource {

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de clientes"),
            @ApiResponse(code = 404, message = "Cliente não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @ApiOperation(value = "Retorna uma lista de clientes")
    ResponseEntity<List<ClientResponse>> listAll();

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente salvo com sucesso"),
            @ApiResponse(code = 500, message = "Problemas na hora de salvar")
    })
    @ApiOperation(value = "Salva um cliente")
    ResponseEntity<?> save(ClientRequest request);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Mostra o cliente atualizado"),
            @ApiResponse(code = 404, message = "Cliente não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @ApiOperation(value = "Atualiza as informações do cliente")
    ResponseEntity<ClientResponse> update(ClientRequest request);

    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Não retorna nada"),
            @ApiResponse(code = 404, message = "Cliente não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @ApiOperation(value = "Deleta um cliente")
    ResponseEntity<Void> delete(Integer id);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o cliente"),
            @ApiResponse(code = 500, message = "Cliente não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")
    })
    @ApiOperation(value = "Busca um cliente através do seu ID")
    ResponseEntity<ClientResponse> searchById(Integer id);

}
