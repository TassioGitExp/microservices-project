package org.project.microservices.books.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.project.microservices.books.model.Book;
import org.project.microservices.books.model.repository.BookRepository;
import org.project.microservices.books.proxy.CurrencyProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Books Service Documentation")
@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CurrencyProxy proxy;

    @GetMapping(value = "/find-all")
    public List<Book> findAllBooks(){
        String currency = "USD";
        var port = environment.getProperty("local.server.port");

        var bookList = bookRepository.findAll();

        for (Book book: bookList) {
            book.setCurrency(currency);
            book.setEnvironment(port);
        }

        return bookList;
    }

    @Operation(description = "Converts the price of a book to another currency.")
    @GetMapping(value = "/{id}/{currency}")
    public Book findBookById(@PathVariable("id") String id,
                             @PathVariable("currency") String currency) {

        var book = bookRepository.findById(id).get();

        var port = environment.getProperty("local.server.port");
        book.setEnvironment(port);
        book.setCurrency(currency);

        var currencyConverted = proxy.getCurrency(book.getPrice(), "USD", currency);

        book.setPrice(currencyConverted.getConvertedValue());

        return book;
    }
}

