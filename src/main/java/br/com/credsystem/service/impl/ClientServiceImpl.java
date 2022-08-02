package br.com.credsystem.service.impl;

import br.com.credsystem.builder.ClientBuilder;
import br.com.credsystem.exception.ClientAlreadyExistsException;
import br.com.credsystem.exception.ClientNotFoundException;
import br.com.credsystem.model.Client;
import br.com.credsystem.repository.ClientRepository;
import br.com.credsystem.rest.request.ClientRequest;
import br.com.credsystem.rest.response.ClientResponse;
import br.com.credsystem.service.ClientService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Component
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientResponse> listAll() {
        List<Client> clients = clientRepository.findAll();
        List<ClientResponse> clientsResponses = new ArrayList<>(clients.size());

        clients.forEach(client -> clientsResponses.add(ClientResponse.parse(client, true)));

        return clientsResponses;
    }

    @Override
    public void save(ClientRequest request) {
        Client client = clientRepository.findClientByDocumentNumber(request.getDocumentNumber());
        if (nonNull(client)) {
            throw new ClientAlreadyExistsException("Cliente já existe, tente novamente.");
        }
        clientRepository.save(create(request, null));
    }

    @Override
    public ClientResponse update(ClientRequest request) {
        Optional<Client> client = clientRepository.findById(request.getId());
        if (!client.isPresent()) {
            throw new ClientNotFoundException("Cliente não encontrado.");
        }

        return ClientResponse.parse(clientRepository.save(create(request, client.get())), false);
    }

    @Override
    public void delete(Integer id) {
        Optional<Client> client = clientRepository.findById(id);

        if (!client.isPresent()) {
            throw new ClientNotFoundException("Cliente não encontrado.");
        }

        clientRepository.deleteById(id);
    }

    @Override
    public ClientResponse searchById(Integer id) {
        Optional<Client> client = clientRepository.findById(id);

        if (!client.isPresent()) {
            throw new ClientNotFoundException("Cliente não encontrado.");
        }

        return ClientResponse.parse(client.get(), true);
    }

    private Client create(ClientRequest request, Client client) {
        return new ClientBuilder()
                .with($client -> {
                    $client.id = request.getId();
                    $client.documentNumber = request.getDocumentNumber();
                    $client.salary = request.getSalary();
                    $client.createdAt = client != null && client.getCreatedAt() != null ? client.getCreatedAt() : new Date();
                }).build();
    }

}
