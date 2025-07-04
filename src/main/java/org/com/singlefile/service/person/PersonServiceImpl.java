package org.com.singlefile.service.person;

import org.com.singlefile.domain.model.Person;
import org.com.singlefile.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import com.querydsl.core.types.Predicate;
@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Mono<Page<Person>> getPersons(Pageable pageable) {
        return personRepository.findAllBy(pageable)
                .collectList()
                .zipWith(this.personRepository.count())
                .map(p -> new PageImpl<>(p.getT1(), pageable, p.getT2()));
    }

    @Override
    public Mono<Page<Person>> getPersons(Predicate predicate, Pageable pageable) {
        return personRepository.findAllBy(predicate, pageable)
                .collectList()
                .zipWith(this.personRepository.count())
                .map(p -> new PageImpl<>(p.getT1(), pageable, p.getT2()));
    }

    @Override
    public Mono<Person> save(Person person) {
        return personRepository.save(person);
    }
}
