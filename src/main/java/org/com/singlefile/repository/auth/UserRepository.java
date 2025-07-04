package org.com.singlefile.repository.auth;

import org.com.singlefile.domain.model.auth.MyUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<MyUser, String> {


    Mono<MyUser> findByEmail(String email);

}
