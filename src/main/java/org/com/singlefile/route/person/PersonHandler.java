package org.com.singlefile.route.person;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.com.singlefile.domain.model.Person;
import org.com.singlefile.route.ParseQueryPams;
import org.com.singlefile.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.util.Set;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
public class PersonHandler {

    private PersonService personService;
    @Autowired
    private Validator validator;

    @Autowired
    public PersonHandler(PersonService personService) {
        this.personService = personService;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    public Mono<ServerResponse> getAllPersons(ServerRequest request) {
        ParseQueryPams parseQueryPams = new ParseQueryPams(request.queryParams());
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(personService.getPersons(
                        parseQueryPams.getPageAndSort()), Person.class);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public Mono<ServerResponse> addPerson(ServerRequest request) {

        Mono<Person> unSavePerson = request.bodyToMono(Person.class)
                .doOnNext(this::validate);


        return unSavePerson
                .flatMap(contact -> personService.save(contact)
                        .flatMap(savedContact -> ServerResponse.accepted()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(fromValue(savedContact)))
                );
    }

    private void validate(Person person) {

        Set<ConstraintViolation<Person>> errs = validator.validate(person);
        //errs.stream().map(personConstraintViolation -> personConstraintViolation.getPropertyPath());
        if (!errs.isEmpty()) {
            throw new ServerWebInputException(errs.toString());
        }
    }
}
