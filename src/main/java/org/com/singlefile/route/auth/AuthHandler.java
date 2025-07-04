package org.com.singlefile.route.auth;

import org.com.singlefile.domain.model.auth.MyUser;
import org.com.singlefile.domain.model.auth.SignInDTO;
import org.com.singlefile.domain.model.auth.SignUpDTO;
import org.com.singlefile.domain.model.auth.TokenDTO;
import org.com.singlefile.service.auth.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
@Component
public class AuthHandler {


    private final UserService userService;

    public AuthHandler(UserService userService) {
        this.userService = userService;
    }

    private Mono<ServerResponse> response401
            = ServerResponse.status(HttpStatus.UNAUTHORIZED).build();



    public Mono<ServerResponse> signUp(ServerRequest request) {

        return request.bodyToMono(SignUpDTO.class)
                .flatMap(dto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(userService.signUp(dto), MyUser.class));
    }

    public Mono<ServerResponse> logIn(ServerRequest request) {

        return request.bodyToMono(SignInDTO.class)
                .flatMap(dto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(userService.login(dto), TokenDTO.class));
    }

}
