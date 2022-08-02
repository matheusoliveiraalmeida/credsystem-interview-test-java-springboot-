package br.com.credsystem.repository;

import br.com.credsystem.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findClientByDocumentNumber(String documentNumber);

}
