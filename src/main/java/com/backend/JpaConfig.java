package com.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableJpaRepositories(basePackages = "com.backend.repository.jpa")
@EnableMongoRepositories(basePackages = "com.backend.repository.mongo")
@Configuration
public class JpaConfig {
}
