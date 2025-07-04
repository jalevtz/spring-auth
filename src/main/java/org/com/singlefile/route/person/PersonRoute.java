package org.com.singlefile.route.person;

import org.com.singlefile.route.sale.SaleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;

@Configuration
public class PersonRoute {


    @Bean
    public RouterFunction<ServerResponse> routePerson(PersonHandler personHandler) {
        return RouterFunctions
                .route(GET("/person")
                        , personHandler::getAllPersons)
                .andRoute(POST("/person")
                        , personHandler::addPerson);
    }

}
