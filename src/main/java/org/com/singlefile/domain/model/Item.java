package org.com.singlefile.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Optional;

public record Item(String name, List<String> tags, double price, int quantity) {
}
