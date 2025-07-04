package org.com.singlefile.route.sale;


import org.com.singlefile.route.sale.SaleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class SaleRestRouter {

    final String SALE_URI = "sale";

    @Bean
    public RouterFunction<ServerResponse> routeSale(SaleHandler saleHandler) {
        return RouterFunctions
                .route(GET("/sale")
                        , saleHandler::getAllSales)
                .andRoute(GET("/sale/{id}")
                        , saleHandler::getById)
                .andRoute(POST("/sale")
                        , saleHandler::insertSale)
                .andRoute(PUT("/sale/{id}")
                        , saleHandler::updateSale)
                .andRoute(DELETE("/sale/{id}")
                        , saleHandler::deleteSale);
    }
}
