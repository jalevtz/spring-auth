package org.com.singlefile.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;
import org.com.singlefile.domain.model.Person;
import org.com.singlefile.entities.QPerson;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonRepository extends ReactiveMongoRepository<Person, String>{

    Flux<Person> findAllBy(Pageable pageable);
    Flux<Person> findAllBy(Predicate predicate, Pageable pageable);
}
