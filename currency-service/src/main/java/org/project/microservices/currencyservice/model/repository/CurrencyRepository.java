package org.project.microservices.currencyservice.model.repository;

import org.project.microservices.currencyservice.model.Currency;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends MongoRepository<Currency, String> {

    Currency findByFromAndTo(String from, String to);
}
