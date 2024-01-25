package com.amichdev.spring.dataaccess.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Data
@ConfigurationProperties(prefix = "postgresql.spring.datasource")
@Profile("test")
public class PostgreSQLDataSourceProperties {

    private String url;
    private String driverClassName;
    private String username;
    private String password;

    @Value("${spring.datasource.hikari.connection-timeout}")
    private String connectionTimeout;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private String maximumPoolSize;

    @Value("${spring.datasource.hikari.idle}")
    private String minimumIdle;


}
