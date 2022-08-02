package br.com.credsystem.rest.controller;

import br.com.credsystem.rest.request.ClientRequest;
import br.com.credsystem.rest.resource.ClientResource;
import br.com.credsystem.rest.response.ClientResponse;
import br.com.credsystem.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController implements ClientResource {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<ClientResponse>> listAll(){
        return ResponseEntity.ok(clientService.listAll());
    }

    @PostMapping
    @Override
    public ResponseEntity<?> save(@RequestBody @Valid ClientRequest request) {
        clientService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @Override
    public ResponseEntity<ClientResponse> update(@RequestBody @Valid ClientRequest request){
        return ResponseEntity.ok(clientService.update(request));
    }

    @DeleteMapping("{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    @Override
    public ResponseEntity<ClientResponse> searchById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(clientService.searchById(id));
    }

}
