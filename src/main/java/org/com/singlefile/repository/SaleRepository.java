package org.com.singlefile.repository;

import org.com.singlefile.domain.model.Sale;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface SaleRepository extends ReactiveMongoRepository<Sale, String> {

    Flux<Sale> findAllBy(Pageable pageable);
}
