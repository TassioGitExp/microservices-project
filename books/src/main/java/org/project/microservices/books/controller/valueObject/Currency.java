package org.project.microservices.books.controller.valueObject;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Currency implements Serializable {

    private String id;
    private String from;
    private String to;
    private Double conversionRate;

    private Double convertedValue;
    private String environment;
}
