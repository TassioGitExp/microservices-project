package org.project.microservices.books.proxy;

import org.project.microservices.books.controller.valueObject.Currency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-service")
public interface CurrencyProxy {
    @GetMapping(value = "/currency-service/{amount}/{from}/{to}")
    Currency getCurrency(@PathVariable("amount") Double amount,
                         @PathVariable("from") String from,
                         @PathVariable("to") String to);
}
