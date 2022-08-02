package br.com.credsystem.rest.resource;

import br.com.credsystem.rest.request.ClientRequest;
import br.com.credsystem.rest.request.TransactionRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface TransactionResource {

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Transação salva com sucesso"),
            @ApiResponse(code = 500, message = "Problemas na hora de salvar")
    })
    @ApiOperation(value = "Gera uma transação")
    ResponseEntity<?> generate(TransactionRequest request);

}
