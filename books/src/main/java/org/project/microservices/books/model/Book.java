package org.project.microservices.books.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document("books")
public class Book implements Serializable {
    @Id
    private String id;
    private String author;
    private String title;
    private Date releaseDate;
    private Double price;
    @Transient
    private String currency;
    @Transient
    private String environment;
}
