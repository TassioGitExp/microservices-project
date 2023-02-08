package org.project.microservices.books.model.repository;

import org.bson.types.ObjectId;
import org.project.microservices.books.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findBookById(ObjectId id);
}
