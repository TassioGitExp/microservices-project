package org.project.microservices.currencyservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Document("currency")
public class Currency implements Serializable {

    @Id
    private String id;
    private String from;
    private String to;
    private BigDecimal conversionRate;

    @Transient
    private BigDecimal convertedValue;
    @Transient
    private String environment;
}
