package org.com.singlefile.service.person;

import org.com.singlefile.domain.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import com.querydsl.core.types.Predicate;

public interface PersonService {

    Mono<Page<Person>> getPersons(Pageable pageable);
    Mono<Page<Person>> getPersons(Predicate predicate, Pageable pageable);

    Mono<Person> save(Person person);
}
