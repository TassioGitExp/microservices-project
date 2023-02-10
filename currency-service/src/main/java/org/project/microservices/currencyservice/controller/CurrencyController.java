package org.project.microservices.currencyservice.controller;

import org.project.microservices.currencyservice.model.Currency;
import org.project.microservices.currencyservice.model.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@RestController
@RequestMapping("currency-service")
public class CurrencyController {

    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private Environment environment;

    @GetMapping(value = "/find-all")
    public List<Currency> getAllCurrencies(){
        return currencyRepository.findAll();
    }

    @GetMapping(value = "/{amount}/{from}/{to}")
    public Currency getCurrency(@PathVariable BigDecimal amount,
                                @PathVariable String from,
                                @PathVariable String to) {
        var port = environment.getProperty("local.server.port");

        var currency = currencyRepository.findByFromAndTo(from, to);

        if (currency == null)
            throw new RuntimeException("Currency not found");

        var conversionRate = currency.getConversionRate();

        currency.setConvertedValue(conversionRate.multiply(amount).setScale(2, RoundingMode.CEILING));

        currency.setEnvironment(port);

        return currency;
    }
}
