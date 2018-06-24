package org.bh.housing.mazaya.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories(basePackages = "org.bh.housing.mazaya.repository")
@EnableTransactionManagement
@Configuration
public class DatabaseConfig {

}
