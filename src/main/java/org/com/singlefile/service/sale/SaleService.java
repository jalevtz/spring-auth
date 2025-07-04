package org.com.singlefile.service.sale;

import org.com.singlefile.domain.model.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;


public interface SaleService {

    Mono<Page<Sale>>  getAllSales(Pageable pageable);
}
