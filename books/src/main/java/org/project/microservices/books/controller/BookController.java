package org.project.microservices.books.controller;

import org.bson.types.ObjectId;
import org.project.microservices.books.controller.valueObject.Currency;
import org.project.microservices.books.model.Book;
import org.project.microservices.books.model.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(value = "/{id}/{currency}")
    public Book findBookById(@PathVariable("id") String id,
                             @PathVariable("currency") String currency){

        var book = bookRepository.findById(id).get();

        var port = environment.getProperty("local.server.port");
        book.setEnvironment(port);
        book.setCurrency(currency);

        HashMap<String, String> params = new HashMap<>();
        params.put("amount", book.getPrice().toString());
        params.put("from", "USD");
        params.put("to", currency);

        var response = new RestTemplate()
                .getForEntity("http://localhost:8000/currency-service/{amount}/{from}/{to}",
                        Currency.class,
                        params);

        var currencyConverter = response.getBody();

        book.setPrice(currencyConverter.getConvertedValue());

        return book;
    }
}

