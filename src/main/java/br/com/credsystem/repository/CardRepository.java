package br.com.credsystem.repository;

import br.com.credsystem.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {

    Card findCardByNumber(String number);

}
