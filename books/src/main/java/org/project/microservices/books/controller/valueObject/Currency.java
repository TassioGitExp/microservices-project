package org.project.microservices.books.controller.valueObject;

import lombok.*;

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
