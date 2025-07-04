package org.com.singlefile.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("customer")
public record Customer(char gender, int age, String email, String satisfaction) {
}
