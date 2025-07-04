package org.com.singlefile.service.sale;

import org.com.singlefile.domain.model.Sale;
import org.com.singlefile.repository.SaleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SaleServiceImpl implements  SaleService {

    private final SaleRepository saleRepository;

    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public Mono<Page<Sale>> getAllSales(Pageable pageable) {
        return this.saleRepository.findAllBy(pageable)
                .collectList()
                .zipWith(this.saleRepository.count())
                .map(p -> new PageImpl<>(p.getT1(), pageable, p.getT2()));
    }
}
