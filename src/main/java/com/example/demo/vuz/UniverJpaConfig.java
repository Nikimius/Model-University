package com.example.demo.vuz;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@Profile("test")
@EnableJpaRepositories(basePackages = "com.example.demo.vuz.repositories")
@PropertySource("application.properties")
@EnableTransactionManagement
public class UniverJpaConfig {

    @Autowired
    private final Environment env;

    public UniverJpaConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username")); // другие значаения в порперти мейна
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return  dataSource;
    }
}
