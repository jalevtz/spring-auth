package org.com.singlefile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class SingleFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(SingleFileApplication.class, args);
    }

}
