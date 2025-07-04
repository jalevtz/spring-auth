package org.com.singlefile.service.auth;

import org.com.singlefile.domain.model.auth.MyUser;
import org.com.singlefile.domain.model.auth.SignInDTO;
import org.com.singlefile.domain.model.auth.SignUpDTO;
import org.com.singlefile.domain.model.auth.TokenDTO;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<MyUser> signUp(SignUpDTO dto);
    Mono<TokenDTO> login(SignInDTO dto);
}
