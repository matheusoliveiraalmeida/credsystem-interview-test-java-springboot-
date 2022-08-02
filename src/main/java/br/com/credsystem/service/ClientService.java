package br.com.credsystem.service;

import br.com.credsystem.rest.request.ClientRequest;
import br.com.credsystem.rest.response.ClientResponse;

import java.util.List;

public interface ClientService {

    List<ClientResponse> listAll();

    void save(ClientRequest request);

    ClientResponse update(ClientRequest request);

    void delete(Integer id);

    ClientResponse searchById(Integer id);

}
