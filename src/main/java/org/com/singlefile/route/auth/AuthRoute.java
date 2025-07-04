package org.com.singlefile.route.auth;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class AuthRoute {

    @Bean
    public RouterFunction<ServerResponse> routeAuth(AuthHandler authHandler) {
        return RouterFunctions
                .route(POST("/auth/sign-in")
                        , authHandler::logIn)
                .andRoute(POST("/auth/sign-up")
                        , authHandler::signUp);
    }
}
