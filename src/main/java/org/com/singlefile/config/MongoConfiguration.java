package org.com.singlefile.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
public class MongoConfiguration  extends AbstractReactiveMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "sample_supplies";
    }

    @Override
    @Bean
    public MongoClient reactiveMongoClient() {
        return MongoClients.create("mongodb+srv://singleFile:singleFile31#@cluster0.pjddw.mongodb.net/");
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }
}
