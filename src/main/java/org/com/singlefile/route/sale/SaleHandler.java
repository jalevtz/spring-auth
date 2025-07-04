package org.com.singlefile.route.sale;

import org.com.singlefile.domain.model.Person;
import org.com.singlefile.domain.model.Sale;
import org.com.singlefile.repository.PersonRepository;
import org.com.singlefile.repository.SaleRepository;
import org.com.singlefile.service.sale.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
public class SaleHandler {

    private SaleRepository saleRepository;
    private PersonRepository personRepository;

    private final SaleService saleService;

    private Mono<ServerResponse> response404
            = ServerResponse.notFound().build();

    private Mono<ServerResponse> response406
            = ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build();

    @Autowired //optional
    public SaleHandler(SaleRepository saleRepository, PersonRepository personRepository, SaleService saleService) {
        this.saleRepository = saleRepository;
        this.personRepository = personRepository;
        this.saleService = saleService;
    }

    //GET - find a contact by id
    public Mono<ServerResponse> getById(ServerRequest request) {
        String id = request.pathVariable("id");
        return saleRepository.findById(id)
                .flatMap(sale ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(fromValue(sale))
                ).switchIfEmpty(response404);
    }

    //List all contacts
    public Mono<ServerResponse> getAllSales(ServerRequest request) {
        PageRequest pageable = PageRequest.of(0, 10);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(saleService.getAllSales(pageable), Sale.class);
    }

    public Mono<ServerResponse> getAllPersons(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(personRepository.findAll(), Person.class);
    }

    //Save a new Contact
    public Mono<ServerResponse> insertSale(ServerRequest request) {
        Mono<Sale> unsavedContact = request.bodyToMono(Sale.class);

        return unsavedContact
                .flatMap(contact -> saleRepository.save(contact)
                        .flatMap(savedContact -> ServerResponse.accepted()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(fromValue(savedContact)))
                ).switchIfEmpty(response406);
    }

    //Update an existing contact
    public Mono<ServerResponse> updateSale(ServerRequest request) {
        Mono<Sale> _sale = request.bodyToMono(Sale.class);
        String id = request.pathVariable("id");

        //TODO - additional id match
        Mono<Sale> updatedContact = _sale.flatMap(newSale ->
                saleRepository.findById(id)
                        .flatMap(oldSale -> saleRepository.save(newSale)));

        return updatedContact.flatMap(contact ->
                ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(contact))
        ).switchIfEmpty(response404);
    }

    //Delete a Contact
    public Mono<ServerResponse> deleteSale(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Void> deleted = saleRepository.deleteById(id);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(deleted, Void.class);
    }
}
